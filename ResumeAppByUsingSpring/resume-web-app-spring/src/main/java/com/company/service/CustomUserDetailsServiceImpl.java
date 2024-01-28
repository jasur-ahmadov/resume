package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceInter userService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user != null) {
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);

            builder.disabled(false);
            builder.password(user.getPassword());

            String[] authoritiesArr = userService.getAuthorities(user.getId());
            builder.authorities(authoritiesArr);

            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}