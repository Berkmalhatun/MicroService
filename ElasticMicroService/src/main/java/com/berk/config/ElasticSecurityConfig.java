package com.berk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Configuration -> Konfigurasyon dosyası olarak spring e bıldırecegımız sınıflara eklıyoruz.
 */
@Configuration
public class ElasticSecurityConfig {

    @Bean
    JwtTokenFilter getTokenFilter(){
    return new JwtTokenFilter();
}

    /**
     * Bız normalde hıc mudahele etmesekde sprıng securityFilterChain olusturuyor.Bızım yaptıgımız ıse
     * securityFılterChain ı bızım yonetmemız.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        /**
         * _csrf -> bunu kapatmak ıcın disable komutunu kullanıyoruz. csrf sağ tık ıncelede karsımıza cıkar.
         * genel yapı olarak sitenin açığından faydalanarak siteye sanki o kullanıcıymış gibi erişerek
         * işlem yapmasını sağlar.Genellikle GET requesleri ve SESSION işlemlerinin doğru kontrol edilememesi
         * durumlarındaki açıklardan saldırganların faydalanmasını sağlamaktadır.
         */
        httpSecurity.csrf().disable();
        /**
         * Gelen butun ısteklere oturum acılmıs mı kendını dogrulamıs mı bak.
         * Eger ozel adreslere erısımı acmak ıstıyorsanız. bunları belırterek ızın vermelısınız.
         * Match("/{URLS}") için izin ver permitAll demelisiniz.
         */
       /* httpSecurity.authorizeRequests()
                .anyRequest().authenticated(); bu hıc bır adrese erısım ıznı vermez.*/
        httpSecurity.authorizeRequests()
                .antMatchers("/mylogin.html").permitAll()// bu adrese gıtmesıne ızın ver demek
                .anyRequest().authenticated();
        /**
         * Yetkısız gırıslerde kullanıcıların kendılerını dogrulamaları ıcın logın formuna yonlendırme yaparız.
         */
        // httpSecurity.formLogin();
        /**
         * Eger kullanıcıya kendı logın formuzunu gostermek ıstıyorsanız . o zaman kendı formunuza ızın vererek
         * yonlendırme yapmalısınız.
         */
      //  httpSecurity.formLogin().loginPage("/mylogin.html");

        /**
         * Auth servis uzerınden kendısını dogrulayan bır kısının ısteklerının nasıl ısleyecegını
         * cozmemız gerekıyor. Kişinin elınde olan token bılgısı okunarak bu kısıye yetkılerı dahılınde gecerlı olan token
         * uzerınden oturum ıznı verılecek, boylece ısı her seferınde kendını dogrulamak zorunda kalmayacak.
         * Bunu basarmak ıcın oncelıkle fılter ıslemının oncesıne bır ıslem adımı sokarak kısıyı dogruluyoruz.
         */
        httpSecurity.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
