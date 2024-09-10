package com.gahasa.ecommerce.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Produk implements Serializable {

    @Id
    private String id;
    private String nama;
    private String deskripsi;
    private String gambar;
    @JoinColumn
    @ManyToOne
    private Kategori kategori;
    private BigDecimal harga;
    private Double stok;

}
