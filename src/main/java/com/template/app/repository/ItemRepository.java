package com.template.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.template.app.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT COUNT(i) > 0 FROM Item i WHERE i.name = :name AND i.id <> :id")
    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") Long id);

}