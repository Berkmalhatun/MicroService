package com.berk.repository;

import com.berk.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
    /**
     * Bu kullanıcı adı daha once kullanılmıs mı ? yoksa kaydetcek varsa kaydetmıcek
     */
    @Query(value = "select COUNT(a)>0 from Auth a where a.username=?1")
    boolean isUsername(String username);

    /**
     * Kullanıcı adı ve sıfresı verılen kaydın olup olmadıgı kontrol edılıyor.
     */
    Optional<Auth> findOptionalByUsernameAndPassword(String username,String password);
}
