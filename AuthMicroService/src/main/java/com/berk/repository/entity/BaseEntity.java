package com.berk.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder // baseentity mıras alındıgı ıcın superbuılder kullanılır.
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass // tablo olusturmayan sınıf demek.Soyutlanır.Extends alınan sınıfa yazılabılır.
public class BaseEntity {
    Long createat;
    Long updateat;
    boolean state;
}
