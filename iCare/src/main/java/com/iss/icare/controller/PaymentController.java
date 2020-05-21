package com.iss.icare.controller;

import com.iss.icare.dto.*;
import com.iss.icare.mapper.*;
import com.iss.icare.model.*;
import com.iss.icare.service.*;
import io.swagger.annotations.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@Api(value = "payment controller")
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger LOGGER = LogManager.getLogger(PaymentController.class);
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
        this.paymentMapper = new PaymentMapper();
    }

    @ApiOperation(value = "Find All Payments")
    @GetMapping
    public ResponseEntity<List<PaymentDto>> findAllPayments() {
        LOGGER.info("Getting all payments");
        List<Payment> list = paymentService.findAllPayments();

        List<PaymentDto> result = list.stream().map(paymentMapper::convertToDto).collect(Collectors.toList());

        LOGGER.info("Returning {} payments", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Find payment by id")
    @GetMapping("/{pay_id}")
    public ResponseEntity<PaymentDto> findPaymentById(@PathVariable("pay_id") int paymentId) {
        LOGGER.info("Finding payment with id:{}", paymentId);

        if (!paymentService.exists(paymentId)) {
            LOGGER.info("Payment with id: {} doesn't exists", paymentId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Payment payment = paymentService.findPaymentById(paymentId);

        LOGGER.info("Payment {} found successfully", payment);
        return new ResponseEntity<>(paymentMapper.convertToDto(payment), HttpStatus.OK);
    }

    @ApiOperation(value = "Insert a payment")
    @PostMapping
    public ResponseEntity<Void> insertPayment(@RequestBody PaymentDto paymentDto) {

        if(!paymentService.existsByNumber(paymentDto.getCardNumber())) {
            LOGGER.info("Adding a new payment");
            paymentService.insertPayment(paymentMapper.convertToEntity(paymentDto));
        }

        LOGGER.info("Payment {} added successfully!", paymentDto.getPayId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a payment")
    @PutMapping(value = "/{pay_id}")
    public ResponseEntity<Void> update(@PathVariable("pay_id") int pay_id, @RequestBody PaymentDto payment) {
        LOGGER.info("Updating  payment with id:{}", pay_id);

        if (!paymentService.exists(pay_id)) {
            LOGGER.info("Payment with id:{} doesn't exists", pay_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        payment.setPayId(pay_id);
        paymentService.update(paymentMapper.convertToEntity(payment));
        LOGGER.info("Payment with id:{} updated successfully", pay_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a payment")
    @DeleteMapping(value = "/{pay_id}")
    public ResponseEntity<Void> delete(@PathVariable("pay_id") int pay_id) {
        LOGGER.info("Deleting payment with id={}", pay_id);

        if (!paymentService.exists(pay_id)) {
            LOGGER.info("Payment with id:{} doesn't exists", pay_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        paymentService.delete(pay_id);

        LOGGER.info("Payment {} deleted successfully!", pay_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
