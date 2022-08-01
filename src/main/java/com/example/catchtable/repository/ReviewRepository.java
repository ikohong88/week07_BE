package com.example.catchtable.repository;

import com.example.catchtable.model.Review;
import com.example.catchtable.model.Store;
import com.example.catchtable.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserOrderByCreatedAtDesc(User foundUer);
    List<Review> findByStoreOrderByCreatedAtDesc(Store sotre);
}
