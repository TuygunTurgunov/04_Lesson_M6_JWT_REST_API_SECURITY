package uz.pdp.online.m6l4apprestjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    //1)    .userDetailsService() ==>user lar bilan ishlaydi

    //2)    loadUserByUsername ==> implement qilingan method();
            //userni user name bo'yicha yuklash
    //3)    UserDetailsService ==> Default securityni parolini ham bermidigan qilib qo'ydi
            // (3ae0238b-024d-4af5-9270-ae2a7fed6307)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> userList=new ArrayList<>(
                Arrays.asList(
                        new User("pdp",passwordEncoder.encode("pdpUz"),new ArrayList<>()),
                        new User("abad",passwordEncoder.encode("abadUz"),new ArrayList<>()),
                        new User("olcha",passwordEncoder.encode("olchaUz"),new ArrayList<>()),
                        new User("olcham",passwordEncoder.encode("olchamUz"),new ArrayList<>())
                )


        );



        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException("User topilmadi");





    }
}