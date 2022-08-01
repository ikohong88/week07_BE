package com.example.catchtable.repository;

import com.example.catchtable.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAllByOrderByCreatedAtDesc();
}
