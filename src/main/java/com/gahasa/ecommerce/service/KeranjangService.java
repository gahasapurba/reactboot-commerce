package com.gahasa.ecommerce.service;

import com.gahasa.ecommerce.entity.Keranjang;
import com.gahasa.ecommerce.entity.Pengguna;
import com.gahasa.ecommerce.entity.Produk;
import com.gahasa.ecommerce.exception.BadRequestException;
import com.gahasa.ecommerce.repository.KeranjangRepository;
import com.gahasa.ecommerce.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KeranjangService {

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Transactional
    public Keranjang addKeranjang(String username, String produkId, Double kuantitas) {
        // apakah produk ada di dalam database
        // apakah sudah ada keranjang milik user
        // jika sudah ada maka update kuantitasnya dan hitung
        // jika belum ada maka buat baru

        Produk produk = produkRepository.findById(produkId).orElseThrow(() -> new BadRequestException("Produk ID " + produkId + " tidak ditemukan"));

        Optional<Keranjang> optional = keranjangRepository.findByPenggunaIdAndProdukId(username, produkId);
        Keranjang keranjang;
        if (optional.isPresent()) {
            keranjang = optional.get();
            keranjang.setKuantitas(keranjang.getKuantitas() + kuantitas);
            keranjang.setJumlah(new BigDecimal(keranjang.getHarga().doubleValue() * keranjang.getKuantitas()));
            keranjangRepository.save(keranjang);
        } else {
            keranjang = new Keranjang();
            keranjang.setId(UUID.randomUUID().toString());
            keranjang.setProduk(produk);
            keranjang.setKuantitas(kuantitas);
            keranjang.setHarga(produk.getHarga());
            keranjang.setJumlah(new BigDecimal(keranjang.getHarga().doubleValue() + keranjang.getKuantitas()));
            keranjang.setPengguna(new Pengguna(username));
            keranjangRepository.save(keranjang);
        }

        return keranjang;

    }

    @Transactional
    public Keranjang updateKuantitas(String username, String produkId, Double kuantitas) {

        Keranjang keranjang = keranjangRepository.findByPenggunaIdAndProdukId(username, produkId).orElseThrow(() -> new BadRequestException("Produk ID " + produkId + " tidak ditemukan dalam keranjang anda"));
        keranjang.setKuantitas(kuantitas);
        keranjang.setJumlah(new BigDecimal(keranjang.getHarga().doubleValue() * keranjang.getKuantitas()));
        keranjangRepository.save(keranjang);
        return keranjang;

    }

    @Transactional
    public void delete(String username, String produkId) {

        Keranjang keranjang = keranjangRepository.findByPenggunaIdAndProdukId(username, produkId).orElseThrow(() -> new BadRequestException("Produk ID " + produkId + " tidak ditemukan dalam keranjang anda"));
        keranjangRepository.delete(keranjang);

    }

    public List<Keranjang> findByPenggunaId(String username) {
        return keranjangRepository.findByPenggunaId(username);
    }

}
