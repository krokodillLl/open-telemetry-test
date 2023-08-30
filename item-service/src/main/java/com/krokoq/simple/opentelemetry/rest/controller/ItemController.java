package com.krokoq.simple.opentelemetry.rest.controller;

import com.krokoq.simple.opentelemetry.rest.service.ItemService;
import com.krokoq.simple.opentelemetry.rest.service.in.ItemIn;
import com.krokoq.simple.opentelemetry.rest.service.out.ItemOut;
import com.krokoq.starter.dto.CreateItemRequest;
import com.krokoq.starter.dto.ItemResponse;
import com.krokoq.starter.dto.UpdateItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemResponse> getItems() {
        return itemService.getItems().stream()
                .map(this::convert)
                .toList();
    }

    @GetMapping("{id}")
    public ItemResponse getItemById(@PathVariable Long id) {
        return convert(
                itemService.getItemById(id)
        );
    }

    @PostMapping("/create")
    public ItemResponse createItem(@RequestBody @Valid CreateItemRequest createItemRequest) {
        return convert(
                itemService.createItem(
                        convert(createItemRequest)
                )
        );
    }

    @PutMapping("/update/{id}")
    public ItemResponse updateItem(@RequestBody @Valid UpdateItemRequest updateItemRequest, @PathVariable Long id) {
        return convert(
                itemService.updateItem(
                        convert(updateItemRequest, id)
                )
        );
    }


    @GetMapping("/solr")
    public List<ItemResponse> getSolrItems() {
        return itemService.getItemsFromSolr().stream()
                .map(this::convert)
                .toList();
    }

    @GetMapping("/solr/{title}")
    public ItemResponse getSolrItemByTitle(@PathVariable String title) {
        return convert(
                itemService.getItemFromSolrByTitle(title)
        );
    }

    @PostMapping("/solr/create")
    public ItemResponse createSolrItem(@RequestBody @Valid CreateItemRequest createItemRequest) {
        return convert(
                itemService.createItemInSolr(
                        convert(createItemRequest)
                )
        );
    }

    @PutMapping("/solr/update/{title}")
    public ItemResponse updateSolrItem(@RequestBody @Valid UpdateItemRequest updateItemRequest, @PathVariable String title) {
        return convert(
                itemService.updateItemInSolr(
                        convert(updateItemRequest, title)
                )
        );
    }

    private ItemResponse convert(ItemOut itemOut) {
        return new ItemResponse(
                itemOut.getId(),
                itemOut.getTitle(),
                itemOut.getContent(),
                itemOut.getCreateDate(),
                itemOut.getUpdateDate()
        );
    }

    private ItemIn convert(CreateItemRequest createItemRequest) {
        return ItemIn.builder()
                .title(createItemRequest.getTitle())
                .content(createItemRequest.getContent())
                .build();
    }

    private ItemIn convert(UpdateItemRequest updateItemRequest, Long id) {
        return ItemIn.builder()
                .id(id)
                .content(updateItemRequest.getContent())
                .build();
    }

    private ItemIn convert(UpdateItemRequest updateItemRequest, String title) {
        return ItemIn.builder()
                .title(title)
                .content(updateItemRequest.getContent())
                .build();
    }
}
