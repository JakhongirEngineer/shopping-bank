package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.request.OrderRequestDto;
import com.mastery.testspringproductmicroservice.dtos.response.*;
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


@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class OrderService {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
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

        // Order can only be made if and only if both customer and product exist in the database
        if (optionalCustomer.isPresent() && optionalProduct.isPresent()){
            // new order is created and persisted into the database
            try {
                Order order = new Order();
                order.setCustomer(optionalCustomer.get());
                order.setDate(LocalDate.now());
                orderRepository.save(order);

                // new detail for the above order is created and persisted into the database
                Detail detail = new Detail();
                detail.setOrder(order);
                detail.setProduct(optionalProduct.get());
                detail.setQuantity(orderRequestDto.getQuantity());
                detailRepository.save(detail);

                // new invoice is created for the above order, amount is calculated, and persisted into the database
                Invoice invoice = new Invoice();
                invoice.setOrder(order);
                invoice.setIssued(LocalDate.now());
                invoice.setDue(LocalDate.now().plusDays(10)); // assuming that the product is delivered within 10 days.
                BigDecimal amount = optionalProduct.get().getPrice().multiply(new BigDecimal(detail.getQuantity()));
                invoice.setAmount(amount);
                invoiceRepository.save(invoice);

                // if there is not any error, response with SUCCESS status is returned
                OrderResponseDto orderResponseDto = new OrderResponseDto();
                orderResponseDto.setStatus("SUCCESS");
                orderResponseDto.setInvoiceId(invoice.getInvoiceId());
                return ResponseEntity.ok().body(orderResponseDto);
            } catch (RuntimeException e){
                // if there is an error while persisting, FAILED status is returned
                OrderResponseDto failedToPersist = new OrderResponseDto();
                failedToPersist.setStatus("FAILED");
                failedToPersist.setInvoiceId(-1);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToPersist);
            }

        } else {
            // if either customer or product is not found, FAILED is returned
            OrderResponseDto failedToFindCustomerOrProduct = new OrderResponseDto();
            failedToFindCustomerOrProduct.setStatus("FAILED");
            failedToFindCustomerOrProduct.setInvoiceId(-1);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedToFindCustomerOrProduct);
        }
    }

    public ResponseEntity<OrderDetailsDto> findOrderDetailsByOrderId(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        // order details can be obtained if and only if the order exists
        if (optionalOrder.isPresent()){
            Optional<Detail> optionalDetail = detailRepository.findByOrder(optionalOrder.get());
            if (optionalDetail.isPresent()){
                Product product = optionalDetail.get().getProduct();
                OrderDetailsDto orderDetailsDto = new OrderDetailsDto(optionalDetail.get(),product.getName());
                return ResponseEntity.ok().body(orderDetailsDto);
            }
        }
        // if order does not exist, status NOT_FOUND is returned
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
