package com.pk.auth_server.Service;

import com.pk.auth_server.Model.AppUser;
import com.pk.auth_server.Repo.AppUserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private final AppUserRepository repo;

    public DbUserDetailsService(AppUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRoles().split(","))
                .build();
    }
}