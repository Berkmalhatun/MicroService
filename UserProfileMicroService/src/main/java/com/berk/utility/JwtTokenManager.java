package com.berk.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {

    @Value("${authservice.bunu-ben-yazdım.secret-key}")
    String secretKey;
    @Value("${authservice.bunu-ben-yazdım.issuer}")
    String issuer;


    public Optional<String> createToken(Long id){
     String token = null;
     Long exDate= 1000L*60;
     try {
         /**
          * DIKKAT!!!
          * Claim objelerının ıcıne onemlı ve herkes ıle paylasmayacagınız bılgılerı koyamazsınız.
          * emaıl, username, password v.s. gıbı onemlı bılgıler payload ıcınde olamaz.
          * Onemlı olan hıc bır bılgı koyulmaz anlayacagın.
          */
         token = JWT.create().withAudience()
                 .withClaim("id",id)
                 .withClaim("howtopage", "AuthMicroService")
                 .withClaim("lastjoin",System.currentTimeMillis())
                 .withClaim("ders","Java JWT")
                 .withClaim("Hoca","Muhammet Hoca")
                 .withIssuer(issuer) // jwt nin sahıbı
                 .withIssuedAt(new Date()) //token olusturma tarıhı
                 .withExpiresAt(new Date(System.currentTimeMillis()+exDate)) // gecerlılık tarıhı
                 .sign(Algorithm.HMAC512(secretKey)); // burada sign yaparız.bızden secretkey anahtarı ıster
                                            // olabıldıgınce karmasık bı anahtar koyulmalı.
         return Optional.of(token);
     }catch (Exception e) {
        return Optional.empty();
     }

    }

    /**
     * Token dogrulamak ıcın yapılır.
     */
    public Boolean verifyToken(String token){
     try {
         Algorithm algorithm = Algorithm.HMAC512(secretKey);
         JWTVerifier verifier = JWT.require(algorithm)
                 .withIssuer(issuer).build();
       DecodedJWT decodedJWT= verifier.verify(token);
       if (decodedJWT == null)
           return false;
     }catch (Exception e) {
        return false;
     }
        return true;
    }
    public Optional<Long> getByIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            Long id = decodedJWT.getClaim("id").asLong();
            String howToPage= decodedJWT.getClaim("howtopage").asString();
            System.out.println("howtopage...: "+ howToPage);
            return Optional.of(id);
        }catch (Exception e) {
            return Optional.empty();
        }

    }
}
