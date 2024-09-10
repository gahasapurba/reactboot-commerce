package com.gahasa.ecommerce.repository;

import com.gahasa.ecommerce.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository extends JpaRepository<Kategori, String> {
}
