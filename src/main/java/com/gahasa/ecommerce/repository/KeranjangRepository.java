package com.gahasa.ecommerce.repository;

import com.gahasa.ecommerce.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeranjangRepository extends JpaRepository<Keranjang, String> {
}
