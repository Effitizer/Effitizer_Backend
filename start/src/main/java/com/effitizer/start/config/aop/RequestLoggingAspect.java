package com.effitizer.start.config.aop;

import com.effitizer.start.domain.User;
import com.effitizer.start.domain.UserHistory;
import com.effitizer.start.repository.UserHistoryRepository;
import com.effitizer.start.repository.UserRepository;
import com.google.common.base.Joiner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // 1
@Aspect // 2
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)", entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    @Pointcut("within(com.effitizer.start.controller..*)") // 3
    public void onRequest() {
    }

    @Around("com.effitizer.start.config.aop.RequestLoggingAspect.onRequest()") // 4
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, String[]> paramMap = request.getParameterMap();
        String params = "";
        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }


        long start = System.currentTimeMillis();
        try {
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            System.out.println("current user: "+username);

            SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
            String lastTime = formatter.format ( request.getSession().getLastAccessedTime() );//???????????????????????????
            String initTime = formatter.format ( request.getSession().getCreationTime());//??????????????????

            UrlPathHelper urlPathHelper = new UrlPathHelper();
            String accessPath = urlPathHelper.getOriginatingRequestUri(request);
            System.out.println("originalURL-->" + accessPath);

            UserHistory userhistory = new UserHistory();

            userhistory.setAccess_path(accessPath);			//????????????
            userhistory.setIp_address(request.getRemoteAddr());//???????????????

            userhistory.setLogin_date(initTime);			//??????????????????
            userhistory.setSession_lastaccess(lastTime);	//?????????????????????
            userhistory.setOs_type(request.getHeader("User-Agent"));

//            if(!(username.equals("anonymousUser"))) {//???????????? ????????? ????????? ??? ?????? ??????
//                Optional<User> findUser = userRepository.findByNickname(username);
//                User users=new User();
//                long userid = findUser.get().getId();
//                users.setId(userid);
//                //userhistory.setUser(users);
//                userhistory.setUserNumber(findUser.get().getId());
//                userhistory.setUserId(findUser.get().getUserid());
//                userhistory.setAccessname(findUser.get().getNickname());
//            }
            //?????? ????????? accessname, userid?????? ??????

            userHistoryRepository.save(userhistory);
            return pjp.proceed(pjp.getArgs()); // 6
        } finally {
            long end = System.currentTimeMillis();
            logger.debug("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(), params,
                    request.getRemoteHost(), end - start);
        }
    }
}
