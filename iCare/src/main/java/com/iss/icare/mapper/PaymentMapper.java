package com.iss.icare.mapper;
import com.iss.icare.dto.*;
import com.iss.icare.model.*;

public class PaymentMapper {
    public PaymentDto convertToDto(Payment pay) {
        PaymentDto payDto = new PaymentDto();

        payDto.setPayId(pay.getPayId());
        payDto.setCardNumber(pay.getCardNumber());
        payDto.setCardName(pay.getCardName());
        payDto.setExpDate(pay.getExpDate());
        payDto.setKeyCode(pay.getKeyCode());
        payDto.setPayedSum(pay.getPayedSum());

        return payDto;
    }

    public Payment convertToEntity(PaymentDto payDto) {
        Payment pay = new Payment();

        pay.setPayId(payDto.getPayId());
        pay.setCardNumber(payDto.getCardNumber());
        pay.setCardName(payDto.getCardName());
        pay.setExpDate(payDto.getExpDate());
        pay.setKeyCode(payDto.getKeyCode());
        pay.setPayedSum(payDto.getPayedSum());

        return pay;
    }
}
