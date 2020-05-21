package com.iss.icare.service;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAllPayments();
    }

    public Payment findPaymentById(int pay_id) {
        return paymentRepository.findById(pay_id);
    }

    public int insertPayment(Payment payment) {
        return paymentRepository.insert(payment);
    }

    public boolean exists(int id) {
        return paymentRepository.exists(id);
    }

    public void delete(int pay_id) {
        paymentRepository.delete(pay_id);
    }

    public void update(Payment payment) {
        paymentRepository.update(payment);
    }

    public boolean isValidCard(String expDate) {
        String[] splitted = expDate.split("/");
        String month;
        if((Integer.parseInt(splitted[1])>=LocalDate.now().getYear())) {
            if(LocalDate.now().getMonth().getValue() <10) { month= "0" + LocalDate.now().getMonth().getValue();}
            else {month = String.valueOf(LocalDate.now().getMonth().getValue());}
            if((Integer.parseInt(splitted[0])>=1) && ((Integer.parseInt(splitted[0])<=12) )) {
                if(splitted[0].compareTo(String.valueOf(LocalDate.now().getMonth().getValue()))>=0) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean existsByNumber(String cardNumber) {
        List<Payment> p = this.findAllPayments();
        for(Payment pay: p) {
            if(pay.getCardNumber().equals(cardNumber)) {
                return true;
            }
        }
        return false;

    }
}
