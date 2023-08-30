package com.krokoq.simple.opentelemetry.rest.service.impl;

import com.krokoq.simple.opentelemetry.rest.domain.ConvertibleItem;
import com.krokoq.simple.opentelemetry.rest.domain.db.entity.ItemEntity;
import com.krokoq.simple.opentelemetry.rest.domain.db.ItemDBRepository;
import com.krokoq.simple.opentelemetry.rest.domain.solr.ItemSolrRepository;
import com.krokoq.simple.opentelemetry.rest.domain.solr.document.ItemDocument;
import com.krokoq.simple.opentelemetry.rest.service.ItemService;
import com.krokoq.simple.opentelemetry.rest.service.in.ItemIn;
import com.krokoq.simple.opentelemetry.rest.service.out.ItemOut;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDBRepository itemDBRepository;
    private final ItemSolrRepository itemSolrRepository;

    @Override
    public List<ItemOut> getItems() {
        return itemDBRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public ItemOut getItemById(Long id) {
        return convert(
                findEntityByIdOrElseThrow(id)
        );
    }

    @Override
    public ItemOut createItem(ItemIn itemIn) {
        return convert(
                itemDBRepository.save(
                        convert(itemIn, new ItemEntity())
                )
        );
    }

    @Override
    public ItemOut updateItem(ItemIn itemIn) {
        var dbItem = findEntityByIdOrElseThrow(itemIn.getId());
        var inItem = convert(itemIn, new ItemEntity());

        dbItem.setContent(inItem.getContent());
        dbItem.setUpdateDate(inItem.getUpdateDate());

        return convert(
                itemDBRepository.save(dbItem)
        );
    }

    @Override
    public List<ItemOut> getItemsFromSolr() {
        List<ItemOut> result = new ArrayList<>();
        itemSolrRepository.findAll(Sort.unsorted())
                .forEach(item ->
                        result.add(
                                convert(item)
                        )
                );

        return result;
    }

    @Override
    public ItemOut getItemFromSolrByTitle(String title) {
        return convert(
                findDocumentByTitleOrElseThrow(title)
        );
    }

    @Override
    public ItemOut createItemInSolr(ItemIn itemIn) {
        ItemDocument newItem = convert(itemIn, new ItemDocument());
        newItem.setId(1L);
        return convert(
                itemSolrRepository.save(
                        newItem, Duration.ofHours(72)
                )
        );
    }

    @Override
    public ItemOut updateItemInSolr(ItemIn itemIn) {
        var solrItem = findDocumentByTitleOrElseThrow(itemIn.getTitle());
        var inItem = convert(itemIn, new ItemDocument());

        solrItem.setContent(inItem.getContent());
        solrItem.setUpdateDate(inItem.getUpdateDate());

        return convert(
                itemSolrRepository.save(solrItem, Duration.ofHours(72))
        );
    }

    private ItemDocument findDocumentByTitleOrElseThrow(String title) {
        return itemSolrRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Can't find the item with title " + title));
    }

    private ItemEntity findEntityByIdOrElseThrow(Long id) {
        return itemDBRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find the item with id " + id));
    }

    private ItemOut convert(ConvertibleItem storeItem) {
        return new ItemOut(
                storeItem.getId(),
                storeItem.getTitle(),
                storeItem.getContent(),
                storeItem.getCreateDate(),
                storeItem.getUpdateDate()
        );
    }

    private <T extends ConvertibleItem> T convert(ItemIn itemIn, T result) {
        result.setId(itemIn.getId());
        result.setTitle(itemIn.getTitle());
        result.setContent(itemIn.getContent());
        result.setCreateDate(new Date());
        result.setUpdateDate(new Date());

        return result;
    }
}
