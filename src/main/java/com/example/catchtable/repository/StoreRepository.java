package com.example.catchtable.repository;

import com.example.catchtable.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // 추천순, 별점순, 리뷰 많은 순, 가격 높은 순, 가격 낮은 순
    
    // 필터없을시 전체 조회 - 내림차순
    List<Store> findAllByOrderByIdDesc();
    // 오름차순
    List<Store> findAllByOrderByIdAsc();
    List<Store> findByStorename(String storename);
    // minPrice, maxPrice 사이값 조회
    List<Store> findAllByIdBetween(Integer minPrice, Integer maxPrice);
    Optional<Store> findById(Long id);
}
