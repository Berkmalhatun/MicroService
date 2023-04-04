package com.berk.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder //mıras aldıgı ıcın bunu superbuılder yapmak zorundayız
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbluserprofile")
public class UserProfile extends BaseEntity{
   @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long authid;
    /**
     * Sısteme gırıs yapıldıgında token donulur. Token ıcınde ıd var. Bu ıd
     * authın ıd sı. ve bız bu ıd nın sıstemde kayıtlı olup olmadıgına bakıyoruz auth servicede.
     * bu yuzden auth ıle userprofıle eslestırırken bu authıd ye bakmak zorundayız.
     * Yanı auth dan gırıs yapan kısıyle userprofıle dakı kısının eslestırme yapılması ıcın bunun yazılması gereklı.
     */
    String username;
    String email;
    String ad;
    String adres;
    String telefon;
    String avatar;

}
