package uz.pdp.online.m6l4apprestjwt.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l4apprestjwt.payload.LoginDto;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @GetMapping
    public ResponseEntity<?>getReports(){

        return ResponseEntity.ok("report sends");
    }

    @PostMapping("/test")
    public HttpEntity<?>addTest(@RequestBody LoginDto loginDto){

        System.out.println(loginDto);
        return ResponseEntity.ok(loginDto);

    }

}
