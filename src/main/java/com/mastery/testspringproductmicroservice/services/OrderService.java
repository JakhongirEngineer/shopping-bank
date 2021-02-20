package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.request.OrderRequestDto;
import com.mastery.testspringproductmicroservice.dtos.response.NumberOfProductsInYearDto;
import com.mastery.testspringproductmicroservice.dtos.response.OrderDetailsDto;
import com.mastery.testspringproductmicroservice.dtos.response.OrderResponseDto;
import com.mastery.testspringproductmicroservice.dtos.response.OrderWithoutInvoiceDto;
import com.mastery.testspringproductmicroservice.entities.*;
import com.mastery.testspringproductmicroservice.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DetailRepository detailRepository;

    public List<Order> findOrdersWithoutDetailsBeforeSeptember2016(){
        return orderRepository
                .findOrdersWithoutDetailsBeforeSeptember2016()
                .orElseThrow(()->new RuntimeException("error while querying findOrdersWithoutDetailsBeforeSeptember2016"));
    }

    public List<NumberOfProductsInYearDto> findNumberOfProductsInYear(int year){
        return orderRepository
                .findNumberOfProductsInYear(year)
                .orElseThrow(()->new RuntimeException("error while querying findNumberOfProductsInYear(int year)"));
    }

    public List<OrderWithoutInvoiceDto> findOrdersWithoutInvoice(){
        return orderRepository
                .findOrdersWithoutInvoice()
                .orElseThrow(()->new RuntimeException("error while querying with findOrdersWithoutInvoice()"));
    }

    public ResponseEntity<OrderResponseDto> makeOrder(OrderRequestDto orderRequestDto) {

        Optional<Customer> optionalCustomer = customerRepository.findById(orderRequestDto.getCustomerId());
        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());

        if (optionalCustomer.isPresent() && optionalProduct.isPresent()){

            Order order = new Order();
            order.setCustomer(optionalCustomer.get());
            order.setDate(LocalDate.now());
            orderRepository.save(order);

            Detail detail = new Detail();
            detail.setOrder(order);
            detail.setProduct(optionalProduct.get());
            detail.setQuantity(orderRequestDto.getQuantity());
            detailRepository.save(detail);

            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            invoice.setIssued(LocalDate.now());
            invoice.setDue(LocalDate.now().plusDays(10)); // assuming that the product is delivered within 10 days.
            BigDecimal amount = optionalProduct.get().getPrice().multiply(new BigDecimal(detail.getQuantity()));
            invoice.setAmount(amount);
            invoiceRepository.save(invoice);

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setStatus("SUCCESS");
            orderResponseDto.setInvoiceId(invoice.getInvoiceId());
            return ResponseEntity.ok().body(orderResponseDto);

        } else {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setStatus("FAILED");
            orderResponseDto.setInvoiceId(-1);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderResponseDto);
        }
    }

    public ResponseEntity<OrderDetailsDto> findOrderDetailsByOrderId(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()){
            Optional<Detail> optionalDetail = detailRepository.findByOrder(optionalOrder.get());
            if (optionalDetail.isPresent()){
                Product product = optionalDetail.get().getProduct();
                OrderDetailsDto orderDetailsDto = new OrderDetailsDto(optionalDetail.get(),product.getName());

                return ResponseEntity.ok().body(orderDetailsDto);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
