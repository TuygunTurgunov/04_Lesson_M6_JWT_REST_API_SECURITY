package uz.pdp.online.m6l4apprestjwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
//jwt ni ishlatuvchi class
@Component
public class JwtProvider {

   static long expireTime=108_000_000;  //1_000  milesukund = 1 sekundga
   static  String kalitSoz="BuTokenniMaxfiySoziHechKimBilmasin123";

    //1) Tokenni generatsiya qilish
    public  String generateToken(String username){
    Date expireDate=new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, kalitSoz)
                .compact();
        return token;
    }

    //2) Tokenni parse qilish
    public boolean validateToken(String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(kalitSoz)
                .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return false;

    }

    //3)Tokenni user name ni olish
    public  String getUserNameFromToken(String token){

        String username = Jwts
                .parser()
                .setSigningKey(kalitSoz)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
return username;
    }




//    public static void main(String[] args) {
//        System.out.println(generateToken("ppp"));
//
//
//    }

}
