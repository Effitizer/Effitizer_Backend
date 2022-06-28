package com.effitizer.start.repository;

import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findBySubscribe(Subscribe subscribe);
}
