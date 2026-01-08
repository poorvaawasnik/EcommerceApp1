package com.example.E_commerce.App.service;

import com.example.E_commerce.App.entity.User;
import com.example.E_commerce.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
        .orElseThrow(()->new UsernameNotFoundException("User NOt Found") );
    }
    public void saveUser(User user){
        repository.save(user);
    }
    public User getUserById(int id){
        return repository.findById(id);
    }
}
