package com.krokoq.simple.opentelemetry.rest.service;

import com.krokoq.simple.opentelemetry.rest.service.in.ItemIn;
import com.krokoq.simple.opentelemetry.rest.service.out.ItemOut;

import java.util.List;

public interface ItemService {

    List<ItemOut> getItems();
    ItemOut getItemById(Long id);
    ItemOut createItem(ItemIn itemIn);
    ItemOut updateItem(ItemIn itemIn);

    List<ItemOut> getItemsFromSolr();
    ItemOut getItemFromSolrByTitle(String title);
    ItemOut createItemInSolr(ItemIn itemIn);
    ItemOut updateItemInSolr(ItemIn itemIn);

}
