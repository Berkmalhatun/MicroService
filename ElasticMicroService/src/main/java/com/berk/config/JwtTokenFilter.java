package com.berk.config;

import com.berk.exception.EErrorType;
import com.berk.exception.ElasticServiceException;
import com.berk.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    /**
     * Gelen ıstegın ıcınde token var mı yok mu bakıcaz. Bu tokenın suresı v.s gecerlı mı dıye bakıcaz.
     * Her sey yolunda ıse Autontıcatıonı bır oturuma atıcaz.
     */
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filter için buradayım.");
        final String BearerToken =  request.getHeader("Authorization"); //postmande http://localhost:9100/elastic/user/getall bu kısma gırıcek
        //System.out.println("Bearer token...: "+ BearerToken.substring(7)); // token yerıne yazdıgımız kısımı alıcak
        /**
         * Bu kısım fıltreye takılan tum ısteklerın ıncelenecegı yerdır. Bu nedenle buraya gelen tum ısteklerde Başlık(Header)
         * kısmında Bearer token ı arıyoruz.
         * Eger Bearer token yok ıse(null) hata fırlatırız.
         * Eger gelen degerın ıcınde token yok ıse hata fırlatırız.
         */
        if (SecurityContextHolder.getContext().getAuthentication()== null){
            if (BearerToken==null || !BearerToken.startsWith("Bearer "))
                throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
            String token = BearerToken.substring(7);
            Optional<Long> authID = jwtTokenManager.getByIdFromToken(token);
                if (authID.isEmpty()) throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
            /**
             * Eger tum kosullar dogru ise , buradan ıtıbaren bılgılerı kontrol edılen kısıye aıt
             * Spring Security tarafından kullanılacak olan bır kımlık kartı hazırlamamız gerekmektedır.
             */
            UserDetails userDetails = jwtUserDetails.getUserByAuthId(authID.get());
            UsernamePasswordAuthenticationToken authenticationToken =                               //getAuthorites yetkılerı bulur
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken); //ben bu kısıyı dogruladım al kullan
            // bu kısım standart bı kısım. usernamepasswordan buraya kadar.
        }
        filterChain.doFilter(request, response);
    }

}
