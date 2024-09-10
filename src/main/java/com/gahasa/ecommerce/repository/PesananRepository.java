package com.gahasa.ecommerce.repository;

import com.gahasa.ecommerce.entity.Pesanan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesananRepository extends JpaRepository<Pesanan, String> {
}
