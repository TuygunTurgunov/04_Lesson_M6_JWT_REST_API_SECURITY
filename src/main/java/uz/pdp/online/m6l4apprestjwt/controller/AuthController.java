package uz.pdp.online.m6l4apprestjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.m6l4apprestjwt.payload.LoginDto;
import uz.pdp.online.m6l4apprestjwt.security.JwtProvider;
import uz.pdp.online.m6l4apprestjwt.service.MyAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    MyAuthService myAuthService;
    @Autowired
    JwtProvider jwtProvider;

    //    @Autowired
    //    PasswordEncoder passwordEncoder;  //2 usulda ishlatamiz


    @Autowired
    AuthenticationManager authenticationManager; //==>Buni bean qilish uchun SecurityConfig class da ctrl+0 ni bosib chaqirib bean qilib qo'y
    // token ni berib yuborvotganda o'zimiz yozib tekshirmimiz o'tirmi (match)
    // match qilib o'tirmimiz


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {

        //parol va loginni ruchnoy tekshirish 3-usul
        // UsernamePasswordAuthenticationToken ==>Avtomat o'zi MyAuthService classini  .loadUserByUsername() metoddan userlani listini ovoladi
                                                // va SOLISHTIRADI 2 tali konstruktori 3 taligi esa sistemaga kirdi shu user didi filtrda ishlatdim
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);

        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(401).body("Login yoki parol hato");
        }





        /*parol va loginni ruchnoy tekshirish 1-usul
        UserDetails userDetails = myAuthService.loadUserByUsername(loginDto.getUsername());
        boolean existUser = userDetails.getPassword().equals(loginDto.getPassword());
        if (existUser){
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);

        }
        return ResponseEntity.status(401).body("Login yoki parol hato");
        */


        /* parol va loginni ruchnoy tekshirish 2-usul
        UserDetails userDetails = myAuthService.loadUserByUsername(loginDto.getUsername());
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
        if (matches){
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);
        }    return ResponseEntity.status(401).body("Login yoki parol hato");
        */




    }


}
