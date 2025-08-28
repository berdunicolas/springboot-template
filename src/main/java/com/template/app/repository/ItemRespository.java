package com.template.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.template.app.model.Item;

public interface ItemRespository extends JpaRepository<Item, Long> {}