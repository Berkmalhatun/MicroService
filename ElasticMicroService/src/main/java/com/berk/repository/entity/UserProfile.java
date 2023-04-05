package com.berk.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.Documented;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "userprofile")
public class UserProfile extends BaseEntity{
    @Id
    String id; // uuid seklınde tutulur.
    Long userprofileid; //userprofile içindeki id
    Long authid;

    String username;
    String email;
    String ad;
    String adres;
    String telefon;
    String avatar;
}
