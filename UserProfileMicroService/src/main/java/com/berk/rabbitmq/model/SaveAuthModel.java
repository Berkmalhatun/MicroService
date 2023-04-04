package com.berk.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ONEMLI!!!!
 * Mutlaka modeller serileştirilmelidir. yani implements serializable yapılmalıdır.
 * Rabbitmq serileştirip base64 e cevırır.
 * Ayrıca paket ısmı dahil olmak uzere bu sınıfın aynısı ıletılen servıstede olmalıdır.
 * Yanı her parametre ve ımport edılen com.berk vs aynı olmak zorunda dıger sınıfta
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveAuthModel implements Serializable {
    Long authid;
    String username;
    String email;
}
