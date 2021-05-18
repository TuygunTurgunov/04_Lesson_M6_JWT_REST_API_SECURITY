package uz.pdp.online.m6l4apprestjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.online.m6l4apprestjwt.security.JwtFilter;
import uz.pdp.online.m6l4apprestjwt.service.MyAuthService;

@EnableWebSecurity//classni security qiladi
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthService myAuthService;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//  1) .userDetailsService() ==> Userlar bilan ishlovchi interface MyAuthService classiga implement bo'lgan.

        authenticationManagerBuilder
                .userDetailsService(myAuthService);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1) .permitAll() ro'yxatidigi linklaga parol va loginsiz ham kirsa bo'ladi digani.

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/auth/login").permitAll()
                .anyRequest().authenticated();


        //Spring security ga UsernamePasswordAuthenticationFilter.class dan oldin jwtFiltr ni ishlatish ni buyuradi
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Spring Security ga sessiyaga ushlab qolmasligini ushlab qolmasligini buyurmoqda
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //yoki .NEVER ga




    }

    //Passwordlarno code lab qoyadi
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //==> Controllerda @Autowired qilib chaqirishga
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
