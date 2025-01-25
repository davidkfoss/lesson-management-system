package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Item;
import com.example.backend.reopsitory.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Item create(Item item) {
        return repository.save(item);
    }

    public Optional<Item> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
