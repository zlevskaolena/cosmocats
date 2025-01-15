package com.cosmocats.intergalacticmarketplace.repository;

import com.cosmocats.intergalacticmarketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
