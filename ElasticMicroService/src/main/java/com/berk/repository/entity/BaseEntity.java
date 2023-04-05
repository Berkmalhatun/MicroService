package com.berk.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@SuperBuilder // baseentity mıras alındıgı ıcın superbuılder kullanılır.
@NoArgsConstructor
@AllArgsConstructor
//@MappedSuperclass jpadan gelır bu. bız NOSQLiz // tablo olusturmayan sınıf demek.Soyutlanır.Extends alınan sınıfa yazılabılır.
public class BaseEntity {
    Long createat;
    Long updateat;
    boolean state;
}
