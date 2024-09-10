package com.gahasa.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class PesananItem implements Serializable {

    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    private Pesanan pesanan;
    @JoinColumn
    @ManyToOne
    private Produk produk;
    private String deskripsi;
    private Double kuantitas;
    private BigDecimal harga;
    private BigDecimal jumlah;

}
