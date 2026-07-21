package com.micro.ecomorderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.net.URI;
import java.util.Locale;

@Service
public class OrderService {
    private final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(String productId){
      /*  //TODO Call inventory service to check stock
       String response = restTemplate.getForObject(
                "http://localhost:8081/inventory/" +productId,
                String.class
        );*/
       ResponseEntity<String> entity = restClient.get()
               .uri("http://localhost:8081/inventory/{productId}",productId)
               .retrieve()
               .toEntity(String.class);
       System.out.println(entity.getStatusCode());
       return "IN STOCK".equals(entity.getBody()) ?
               "Order Placed Successfully":
               "product Out of Stock";


            }


}
