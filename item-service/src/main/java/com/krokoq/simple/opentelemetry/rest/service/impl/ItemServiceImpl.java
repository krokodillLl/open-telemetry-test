package com.krokoq.simple.opentelemetry.rest.service.impl;

import com.krokoq.simple.opentelemetry.rest.domain.ItemEntity;
import com.krokoq.simple.opentelemetry.rest.repository.ItemRepository;
import com.krokoq.simple.opentelemetry.rest.service.ItemService;
import com.krokoq.simple.opentelemetry.rest.service.in.ItemIn;
import com.krokoq.simple.opentelemetry.rest.service.out.ItemOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemOut> getItems() {
        return itemRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public ItemOut getItemById(Long id) {
        return convert(
                findByIdOrElseThrow(id)
        );
    }

    @Override
    public ItemOut createItem(ItemIn itemIn) {
        return convert(
                itemRepository.save(
                        convert(itemIn)
                )
        );
    }

    @Override
    public ItemOut updateItem(ItemIn itemIn) {
        var dbItem = findByIdOrElseThrow(itemIn.getId());
        var inItem = convert(itemIn);

        dbItem.setContent(inItem.getContent());
        dbItem.setUpdateDate(inItem.getUpdateDate());

        return convert(
                itemRepository.save(dbItem)
        );
    }

    private ItemEntity findByIdOrElseThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find the item with id " + id));
    }

    private ItemOut convert(ItemEntity itemEntity) {
        return new ItemOut(
                itemEntity.getId(),
                itemEntity.getTitle(),
                itemEntity.getContent(),
                itemEntity.getCreateDate(),
                itemEntity.getUpdateDate()
        );
    }

    private ItemEntity convert(ItemIn itemIn) {
        return new ItemEntity(
                itemIn.getId(),
                itemIn.getTitle(),
                itemIn.getContent(),
                new Date(),
                new Date()
        );
    }
}
