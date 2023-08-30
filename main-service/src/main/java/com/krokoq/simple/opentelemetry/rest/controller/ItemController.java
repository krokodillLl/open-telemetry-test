package com.krokoq.simple.opentelemetry.rest.controller;

import com.krokoq.starter.dto.CreateItemRequest;
import com.krokoq.starter.dto.ItemResponse;
import com.krokoq.starter.dto.UpdateItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final RestTemplate restTemplate;

    @GetMapping
    public ItemResponse[] getItems() {
        return restTemplate.getForEntity(
                "http://item-service:8081/items",
                ItemResponse[].class
        ).getBody();
    }

    @GetMapping("{id}")
    public ItemResponse getItems(@PathVariable Long id) {
        return restTemplate.getForEntity(
                "http://item-service:8081/items/" + id,
                ItemResponse.class
        ).getBody();
    }

    @PostMapping("/create")
    public ItemResponse createItem(@RequestBody CreateItemRequest createItemRequest) {
        return restTemplate.postForEntity(
                "http://item-service:8081/items/create",
                createItemRequest,
                ItemResponse.class
        ).getBody();
    }

    @PutMapping("/update/{id}")
    public ItemResponse updateItem(@RequestBody UpdateItemRequest updateItemRequest, @PathVariable Long id) {
        return restTemplate.exchange(
                "http://item-service:8081/items/update/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(updateItemRequest),
                ItemResponse.class
        ).getBody();
    }


    @GetMapping("/solr")
    public ItemResponse[] getSolrItems() {
        return restTemplate.getForEntity(
                "http://item-service:8081/items/solr",
                ItemResponse[].class
        ).getBody();
    }

    @GetMapping("/solr/{title}")
    public ItemResponse getSolrItemByTitle(@PathVariable String title) {
        return restTemplate.getForEntity(
                "http://item-service:8081/items/solr/" + title,
                ItemResponse.class
        ).getBody();
    }

    @PostMapping("/solr/create")
    public ItemResponse createSolrItem(@RequestBody CreateItemRequest createItemRequest) {
        return restTemplate.postForEntity(
                "http://item-service:8081/items/solr/create",
                createItemRequest,
                ItemResponse.class
        ).getBody();
    }

    @PutMapping("/solr/update/{title}")
    public ItemResponse updateSolrItem(@RequestBody UpdateItemRequest updateItemRequest, @PathVariable String title) {
        return restTemplate.exchange(
                "http://item-service:8081/items/solr/update/" + title,
                HttpMethod.PUT,
                new HttpEntity<>(updateItemRequest),
                ItemResponse.class
        ).getBody();
    }
}
