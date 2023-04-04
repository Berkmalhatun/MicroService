package com.berk.dto.request;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Kullanıcı adı bos gecılemez.")
            @Size(min=3 , max = 32)
    String username;
    @Email(message = "Lutfen gecerlı bır emaıl adresı gırınız.")
    String email;
    @NotBlank(message = "Sıfre bos gecılemez")
            @Size(min = 8, max = 64)
            @Pattern(
                    message = "Sifre en az 8 karakter olmalı , içinde en az bir büyük bir " +
                            "küçük harf , sayı ve rakam olmalıdır.",
                    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String password;
    String repassword;
}
