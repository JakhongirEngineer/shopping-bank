package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.models.dtos.request.OrderRequestDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.*;
import com.mastery.testspringproductmicroservice.exceptions.ResourceNotFoundException;
import com.mastery.testspringproductmicroservice.exceptions.ServerErrorException;
import com.mastery.testspringproductmicroservice.models.entities.*;
import com.mastery.testspringproductmicroservice.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                .orElseThrow(()->new ServerErrorException("unable to find orders without details before september 2016", LocalDateTime.now()));
    }

    public List<NumberOfProductsInYearDto> findNumberOfProductsInYear(int year){
        return orderRepository
                .findNumberOfProductsInYear(year)
                .orElseThrow(()->new ServerErrorException("unable to find number of products in " + year+" year",LocalDateTime.now()));
    }

    public List<OrderWithoutInvoiceDto> findOrdersWithoutInvoice(){
        return orderRepository
                .findOrdersWithoutInvoice()
                .orElseThrow(()->new ServerErrorException("unable to find orders without invoice", LocalDateTime.now()));
    }

    public OrderResponseDto makeOrder(OrderRequestDto orderRequestDto) {

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
                return orderResponseDto;
            } catch (RuntimeException e){
                // if there is an error while persisting, FAILED status is returned
                OrderResponseDto failedToPersist = new OrderResponseDto();
                failedToPersist.setStatus("FAILED");
                failedToPersist.setInvoiceId(-1);
                return failedToPersist;
            }

        } else {
            // if either customer or product is not found, FAILED is returned
            OrderResponseDto failedToFindCustomerOrProduct = new OrderResponseDto();
            failedToFindCustomerOrProduct.setStatus("FAILED");
            failedToFindCustomerOrProduct.setInvoiceId(-1);

            return failedToFindCustomerOrProduct;
        }
    }

    public OrderDetailsDto findOrderDetailsByOrderId(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        // order details can be obtained if and only if the order exists
        if (optionalOrder.isPresent()){
            Optional<Detail> optionalDetail = detailRepository.findByOrder(optionalOrder.get());
            if (optionalDetail.isPresent()){
                Product product = optionalDetail.get().getProduct();
                OrderDetailsDto orderDetailsDto = new OrderDetailsDto(optionalDetail.get(),product.getName());
                return orderDetailsDto;
            }
        }
        // if order does not exist, status NOT_FOUND is returned
        throw new ResourceNotFoundException("unable to find order details by orderId: " + orderId);
    }
}
