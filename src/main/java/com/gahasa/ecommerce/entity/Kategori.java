package com.gahasa.ecommerce.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Kategori implements Serializable {

    @Id
    private String id;
    private String nama;

}
