package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentOrderService {

    public ResponseEntity<?> transferFromAccountToAccount(
            Double amount,
            String fromAccountNumber,
            String toAccountNumber) {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        Order order = new Order(amount, fromAccountNumber, toAccountNumber);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);

        String url = "http://localhost:8080/api/account/transferAccountAccount";

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, entity, String.class, json);

        response.getHeaders().getLocation();
        response.getStatusCode();
        String responseBody = response.getBody();
        System.out.println(responseBody);

        httpClient.getConnectionManager().shutdown();
        return response;
    }
}
