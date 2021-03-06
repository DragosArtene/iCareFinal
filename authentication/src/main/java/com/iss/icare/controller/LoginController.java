package com.iss.icare.controller;

import com.iss.icare.config.JwtTokenUtil;
import com.iss.icare.dto.LoginUserDTO;
import com.iss.icare.dto.UserDTO;
import com.iss.icare.model.*;
import com.iss.icare.repository.UserRepository;
import com.iss.icare.service.MailService;
import com.iss.icare.service.UserService;
import com.iss.icare.utils.SecurityUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Api
@Controller
@RequestMapping("/api")
@Transactional
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private MailService mailService;
    private UserRepository userRepository;





    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService, MailService mailService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @CrossOrigin
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUserDTO loginUserDTO) throws AuthenticationException {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        if(!authentication.isAuthenticated()){
//            return ResponseEntity.status(401).build();
//        }
//        String jwt = jwtTokenUtil.generateToken(authentication);
//        CurrentUser userPrincipal = (CurrentUser) authentication.getPrincipal();
//
//
//        return ResponseEntity.ok().header("Authorization",new AuthToken(jwt, userPrincipal.getUsername(), userPrincipal.getId()).toString()).build();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken(authentication);
        CurrentUser userPrincipal = (CurrentUser) authentication.getPrincipal();

        return ResponseEntity.ok(new AuthToken(jwt, userPrincipal.getUsername(), userPrincipal.getId()));
    }

    @CrossOrigin
    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public User resetPassword(@RequestParam String email) throws UnsupportedEncodingException, MessagingException {
        String plainPassword = RandomStringUtils.randomAlphanumeric(7);
        String encryptedPassword = passwordEncoder.encode(plainPassword);
        User updatedAccount = userService.updateUserPassword(email, encryptedPassword);
        mailService.sendEmail(updatedAccount.getEmail(), plainPassword);

        return userRepository.findByEmail(email);

    }

    @CrossOrigin
//    @PreAuthorize("hasAuthority('GIVER') or @PreAuthorize("hasAuthority('ONG') or hasAuthority('ADMINISTRATOR') ")
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<String> check(@RequestHeader("Authorization") String token){
//        String token = tokenEntity.getHeaders().get("Authorization").get(0);
        boolean isOk = jwtTokenUtil.validateToken(token);
        if(!isOk) {
            return ResponseEntity.status(401).build();
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('GIVER') or hasAuthority('ONG') or hasAuthority('ADMINISTRATOR') ")
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    @ModelAttribute("currentUser")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser userPrincipal = (CurrentUser) authentication.getPrincipal();
        return (userPrincipal == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(new ApiResponse(userPrincipal.getUsername(), userPrincipal.getId()).toString());
    }

    //@CrossOrigin
    //@PreAuthorize("hasAuthority('GIVER') or hasAuthority('ONG') or hasAuthority('ADMINISTRATOR') ")
    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUsername(@RequestParam String username,@RequestHeader("Authorization") String token) {

        if (userService.findOne(username)==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user1 = userRepository.getUserByUsername(username);

        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ONG') or hasAuthority('GIVER')")
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public void changePassword(@RequestParam String newPassword,@RequestParam String username) throws Exception {
        User user1 = userRepository.getUserByUsername(username);
        User user = userRepository.findById(SecurityUtils.getCurrentUserID());
        String encodedP = passwordEncoder.encode(newPassword);
        userRepository.updateUserPassword(user1.getId(),encodedP);
    }


    @CrossOrigin
    @PreAuthorize("hasAuthority('ONG') or hasAuthority('GIVER') or hasAuthority('ADMINISTRATOR') ")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout() throws AuthenticationException {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
        authentication.setAuthenticated(false);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody User register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);
        if(!userDTO.getPhoneNumber().equals("")) {
            user.setRole(Role.ONG);
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        else {
            user.setRole(Role.GIVER);
            user.setPhoneNumber("");
        }


        return userService.save(user);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
