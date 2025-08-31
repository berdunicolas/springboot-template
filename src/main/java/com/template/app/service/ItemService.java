package com.template.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.template.app.exception.ItemNotFoundException;
import com.template.app.model.Item;
import com.template.app.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item dataItem) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(dataItem.getName());
                    item.setPrice(dataItem.getPrice());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public void deleteItem(Long id) {
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException(id);
        }
    }
}