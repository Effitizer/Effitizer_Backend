package com.effitizer.start.component;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.effitizer.start.domain.Payment;
import com.effitizer.start.service.Payment.PaymentService;
import com.effitizer.start.service.Payment.SchedulePaymentService;
import com.effitizer.start.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReqPaymentScheduler {
    //스케줄러
    private ThreadPoolTaskScheduler scheduler;
    @Autowired SchedulePaymentService setSchedulePay;
    @Autowired PaymentService paymentService;

    public void stopScheduler() {
        //구독 취소 시 scheduler shutdown을 통해 결제 요청 멈춤
        scheduler.shutdown();
    }

    public void startScheduler(String customer_uid, int price) {
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();

        // 스케쥴러가 시작되는 부분
        scheduler.schedule(getRunnable(customer_uid, price), getTrigger());
    }

    public static Date convertFromJAVADateToSQLDate(
            Date javaDate) {
        Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }

    private Runnable getRunnable(String customer_uid, int price){
        // 계속해서 구독 id를 가지고 있도록 -> 그래서 거기서 customer_uid와 price를 사용하도록하기
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, 1);
        Date s = convertFromJAVADateToSQLDate(cal.getTime());
        return () -> {
            String scheduleData = setSchedulePay.schedulePay(customer_uid, price);
            log.info("---------------------- ReqPaymentScheduler----------------------: -> 스케줄 예약 : "+scheduleData);

            // 정기 결제 데이터 업데이트
            //Payment payment = paymentService.saveRegularPayment(scheduleData);
        };
    }

    private Trigger getTrigger() {
        // 작업 주기 설정
        return new PeriodicTrigger(5, TimeUnit.MINUTES);
    }
}
