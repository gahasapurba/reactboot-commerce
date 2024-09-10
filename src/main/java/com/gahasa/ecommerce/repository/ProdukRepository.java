package com.gahasa.ecommerce.repository;

import com.gahasa.ecommerce.entity.Produk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepository extends JpaRepository<Produk, String> {
}
