package com.berk.dto.request;

import lombok.*;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileSaveRequestDto {
    Long authid;
    String username;
    String email;
}
