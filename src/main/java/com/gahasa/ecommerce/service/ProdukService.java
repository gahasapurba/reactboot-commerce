package com.gahasa.ecommerce.service;

import com.gahasa.ecommerce.entity.Produk;
import com.gahasa.ecommerce.exception.BadRequestException;
import com.gahasa.ecommerce.exception.ResourceNotFoundException;
import com.gahasa.ecommerce.repository.KategoriRepository;
import com.gahasa.ecommerce.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepository produkRepository;
    @Autowired
    private KategoriRepository kategoriRepository;

    public Produk findById(String id) {
        return produkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produk dengan id " + id + " tidak ditemukan"));
    }

    public List<Produk> findAll() {
        return produkRepository.findAll();
    }

    public Produk create(Produk produk) {
        if(!StringUtils.hasText(produk.getNama())){
            throw new BadRequestException("Nama produk tidak boleh kosong");
        }

        if(produk.getKategori() == null){
            throw new BadRequestException("Kategori tidak boleh kosong");
        }

        if(!StringUtils.hasText(produk.getKategori().getId())){
            throw new BadRequestException("Id kategori tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId()).orElseThrow(() -> new BadRequestException("Id kategori " + produk.getKategori().getId() + " tidak ditemukan dalam database"));

        produk.setId(UUID.randomUUID().toString());
        return produkRepository.save(produk);
    }

    public Produk edit(Produk produk) {
        if(!StringUtils.hasText(produk.getId())){
            throw new BadRequestException("Id produk tidak boleh kosong");
        }

        if(!StringUtils.hasText(produk.getNama())){
            throw new BadRequestException("Nama produk tidak boleh kosong");
        }

        if(produk.getKategori() == null){
            throw new BadRequestException("Kategori tidak boleh kosong");
        }

        if(!StringUtils.hasText(produk.getKategori().getId())){
            throw new BadRequestException("Id kategori tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId()).orElseThrow(() -> new BadRequestException("Id kategori " + produk.getKategori().getId() + " tidak ditemukan dalam database"));

        return produkRepository.save(produk);
    }

    public Produk ubahGambar(String id, String gambar) {
        Produk produk = findById(id);
        produk.setGambar(gambar);
        return produkRepository.save(produk);
    }

    public void deleteById(String id) {
        produkRepository.deleteById(id);
    }

}
