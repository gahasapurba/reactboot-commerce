package com.gahasa.ecommerce.repository;

import com.gahasa.ecommerce.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<Pengguna, String> {
    Boolean existsByEmail(String email);
}
