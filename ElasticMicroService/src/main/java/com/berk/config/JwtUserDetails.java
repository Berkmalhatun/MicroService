package com.berk.config;

import com.berk.repository.entity.UserProfile;
import com.berk.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    /**
     * Bu classda amacımız token ıcınden cıkardıgımız authıd ıle bır UserDetail nesnesı yaratmak
     */
    @Autowired
    UserProfileService userProfileService;
    public UserDetails getUserByAuthId(Long authId){
        Optional<UserProfile> userProfile = userProfileService.findByAuthId(authId);
        if (userProfile.isEmpty()) return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Admin"));
        authorities.add(new SimpleGrantedAuthority("Ozel Musteri"));
        authorities.add(new SimpleGrantedAuthority("VIP"));
        return User.builder()
                .username(userProfile.get().getUsername())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorities).build(); // authorities yetkılerı
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
