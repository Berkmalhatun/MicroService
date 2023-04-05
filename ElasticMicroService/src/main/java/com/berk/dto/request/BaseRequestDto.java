package com.berk.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequestDto {
    String token;
    /**
     * Her bır ıstekte gostermek ıstedıgımız kayıt adeti.
     */
    Integer pageSize;
    /**
     * Su an bulundugumuz , talep ettıgımız sayfa numarası.
     */
    Integer currentPage;
    /**
     * Hangı alana gore sıralama yapılacaksa o alanın adı
     */
    String sortParameter;
    /**
     * Sıralamanın yonu , a..Z , ASC, DESC
     */
    String direction;
}
