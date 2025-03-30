package com.RMRendimiento.JWT.security;

import com.RMRendimiento.JWT.dto.RoleTokenDTO;
import com.RMRendimiento.JWT.dto.UserTokenDTO;
import com.RMRendimiento.JWT.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final JwtService jwtService;

    public DomainUserDetailsService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(final String name ) {
        log.debug("Authenticating {}", name);

        return jwtService
                .findOneUserByName( name )
                .map( this::createSpringSecurityUser )
                .orElseThrow( () -> new UsernameNotFoundException( "El usuario " + name + " no existe" ) );
    }

    private UserDetails createSpringSecurityUser( UserTokenDTO user ) {
        List<GrantedAuthority> grantedAuthorities = user
                .getRoles()
                .stream()
                .map(RoleTokenDTO::getName )
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toList() );
        return new org.springframework.security.core.userdetails.User( user.getName(), user.getPassword(), grantedAuthorities );
    }

}
