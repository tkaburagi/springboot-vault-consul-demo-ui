package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class UiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    LoadBalancerClient loadBalancerClient;


    public UiService(RestTemplateBuilder builder, ObjectMapper objectMapper, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
        this.loadBalancerClient = loadBalancerClient;
    }

    public String getUrl() {
        return this.loadBalancerClient.choose("hashiboot-api").getUri().toString();
    }


    public Book[] getAllBooks() throws Exception {

        String url = getUrl();
        System.out.println("You accessed the API, " + url);
        String result = this.restTemplate.getForObject(url + "/allbooks", String.class);
        Book[] bList = this.objectMapper.readValue(result, Book[].class);
        return  bList;
    }

//    public Book getBookById(@RequestParam("id") String id) throws Exception {
//        String targetUrl = UriComponentsBuilder.fromUriString(apiUrl + "/book").queryParam("id", id).build().toString();
//        String result = this.restTemplate.getForObject(targetUrl, String.class);
//        Book b = this.objectMapper.readValue(result, Book.class);
//        return b;
//    }
}