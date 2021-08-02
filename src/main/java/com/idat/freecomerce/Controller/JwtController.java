package com.idat.freecomerce.Controller;

import com.idat.freecomerce.Dto.JwtRequest;
import com.idat.freecomerce.Service.CustomUserDetailsService;
import com.idat.freecomerce.helper.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class JwtController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<Object>generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        Map<String, Object> mapRespuesta = new HashMap<>();
        String msg="Bad Credenctials";
      //  System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            mapRespuesta.put("error", "Usuario No Encontrado");
            //throw  new Exception("Bad Credenctials");
            return new ResponseEntity<>(mapRespuesta, HttpStatus.BAD_REQUEST);



        }
        catch (BadCredentialsException be){
            be.printStackTrace();
            mapRespuesta.put("error", msg);

            //throw  new Exception("Bad Credentials");
            return new ResponseEntity<>(mapRespuesta, HttpStatus.BAD_REQUEST);


        }

        // fine area ..

        UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT"+token);
        mapRespuesta.put("token", token);
        mapRespuesta.put("username", jwtRequest.getUsername());
        mapRespuesta.put("msg", "Usuario Logeado Correctamente");

        //{"token":"value"}
        //return  ResponseEntity.ok(new JwtResponse(token));
        return new ResponseEntity<>(mapRespuesta, HttpStatus.CREATED);
    }
}
