package com.berk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    MUSTERI_BULUNAMADI(1003,"Aradınız müşteri sistemde kayıt değildir.", INTERNAL_SERVER_ERROR),
    REGISTER_ERROR_PASSWORD_UNMATCH(1004,"Girilen paralolar uyusmamaktadır.", BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1005,"Bu kullanıcı adı daha once alınmıstır.", BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Kullanıcı adı ya da sıfre hatalıdır.", BAD_REQUEST),
    INVALID_TOKEN(1007,"Geçersiz token", BAD_REQUEST),
    URUN_EKLEME(2001,"Ürün ekleme başarısız oldu", INTERNAL_SERVER_ERROR),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre giriş yaptınız", BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}

