package com.berk.controller;

import com.berk.dto.request.DoLoginRequestDto;
import com.berk.dto.request.RegisterRequestDto;
import com.berk.exception.AuthServiceException;
import com.berk.exception.EErrorType;
import com.berk.repository.entity.Auth;
import com.berk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.berk.constants.EndPoints.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody @Valid RegisterRequestDto dto){
       if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(DOLOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto){
       return ResponseEntity.ok(authService.doLogin(dto));
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<Auth>> findAll(String token){
    return ResponseEntity.ok(authService.findAll(token));
    }
    @GetMapping("/message")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Genel bir mesajjj");
    }
}
