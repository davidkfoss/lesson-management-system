package com.example.backend.reopsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
