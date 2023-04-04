package com.berk.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder //mıras aldıı ıcın bunu superbuılder yapmak zorundayız
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblauth")
public class Auth extends BaseEntity{
   @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
   @Column(unique = true)
    String username;
    String email;
    String password;
}
