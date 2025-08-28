package com.template.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.template.app.model.Item;
import com.template.app.repository.ItemRespository;

@Service
public class ItemService {

    private final ItemRespository itemRespository;

    public ItemService(ItemRespository itemRespository){
        this.itemRespository = itemRespository;
    }

    public List<Item> getAllItems(){
        return itemRespository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRespository.findById(id);
    }

    public Item createItem(Item item) {
        return itemRespository.save(item);
    }

    public Item updateItem(Long id, Item dataItem) {
        return itemRespository.findById(id)
                .map(item -> {
                    item.setName(dataItem.getName());
                    item.setPrice(dataItem.getPrice());
                    return itemRespository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item no encontrado con id " + id));
    }

    public boolean deleteItem(Long id) {
        if(itemRespository.existsById(id)) {
            itemRespository.deleteById(id);
        } else {
            throw new RuntimeException("Item con id " + id + " no existe");
        }
        return true;
    }
}
