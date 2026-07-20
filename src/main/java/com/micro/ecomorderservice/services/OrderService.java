package com.micro.ecomorderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Locale;

@Service
public class OrderService {
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String placeOrder(String productId){
        //TODO Call inventory service to check stock
       String response = restTemplate.getForObject(
                "http://localhost:8081/inventory/" +productId,
                String.class
        );

       return "IN STOCK".equals(response) ?
               "Order Placed Successfully":
               "product Out of Stock";


            }


}
