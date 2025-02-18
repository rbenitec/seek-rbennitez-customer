package com.seek.rbenitez.customer.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Value("${spring.config.properties.user.auth.username}")
    private String userAuth;

    @Value("${spring.config.properties.user.auth.password}")
    private String passwordAuth;

    @Value("${spring.config.properties.user.auth.role}")
    private String roleAuth;

    /**
     * De donde va obtener los usuarios.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(userAuth)) {
            throw new UsernameNotFoundException("El Usuario " + username + " no encontrado");
        }
        Collection<? extends GrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority("ROLE_".concat(roleAuth)));
        return new User(userAuth, passwordAuth, authorities);
    }
}
