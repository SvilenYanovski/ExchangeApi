package com.yanovski.exchangeapi.repositories;

import com.yanovski.exchangeapi.entities.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeysRepository extends JpaRepository<ApiKey, Long> {
    List<ApiKey> findAllByActiveTrue();
}
