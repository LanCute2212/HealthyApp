package com.example.Healthy.App.security;

import com.example.Healthy.App.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Healthy.App.model.User;
import com.example.Healthy.App.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User person =  userRepository.findByEmail(username).get();
        System.out.println(person);
        System.out.println(username);
        if(person == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return CustomUserDetail.build(person);
    }

}