package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.models.dtos.request.OrderRequestDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.*;
import com.mastery.testspringproductmicroservice.models.entities.Order;
import com.mastery.testspringproductmicroservice.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor // dependencies are automatically injected because of Lombok's All arguments constructor
@RestController
public class OrderController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final OrderService orderService;

    @GetMapping("/orders_without_details")
    public ResponseEntity<List<Order>> getOrdersWithoutDetailsBeforeSeptember2016(){
        List<Order> orders = orderService.findOrdersWithoutDetailsBeforeSeptember2016();
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    // the requirement was to retrieve data in 2016, but I generalized it, so the endpoint supports any year.
    @GetMapping("/number_of_products_in_year/{year}")
    public ResponseEntity<List<NumberOfProductsInYearDto>> getNumberOfProductsInYearByCountry(@PathVariable("year") int year){
        List<NumberOfProductsInYearDto> numberOfProductsInYearDtos = orderService.findNumberOfProductsInYear(year);
        return new ResponseEntity<>(numberOfProductsInYearDtos, HttpStatus.FOUND);
    }

    @GetMapping("/orders_without_invoices")
    public ResponseEntity<List<OrderWithoutInvoiceDto>> getOrdersWithoutInvoices(){
        List<OrderWithoutInvoiceDto> orderWithoutInvoiceDtos = orderService.findOrdersWithoutInvoice();
        return new ResponseEntity<>(orderWithoutInvoiceDtos,HttpStatus.FOUND);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponseDto> makeOrder(@RequestBody OrderRequestDto orderRequestDto){ // DTO must be created
        OrderResponseDto orderResponseDto = orderService.makeOrder(orderRequestDto);
        if (orderResponseDto.getInvoiceId()==-1){
            return new ResponseEntity<>(orderResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(orderResponseDto, HttpStatus.CREATED);
        }
    }

    @GetMapping("/order/details")
    public ResponseEntity<OrderDetailsDto> getOrderDetailsByOrderId(@RequestParam("order_id") int orderId){
        OrderDetailsDto orderDetailsDto = orderService.findOrderDetailsByOrderId(orderId);
        return new ResponseEntity<>(orderDetailsDto,HttpStatus.FOUND);
    }
}
