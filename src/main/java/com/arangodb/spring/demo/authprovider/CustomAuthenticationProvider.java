//package com.arangodb.spring.demo.authprovider;
//
//import com.arangodb.spring.demo.entity.Role;
//import com.arangodb.spring.demo.entity.User;
//import com.arangodb.spring.demo.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String login = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userService.findByLogin(login);
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
////        Iterable<Role> roles = user.getRoles();
////        for(Role role : roles)
////        {
////            authorities.add(new SimpleGrantedAuthority(role.getName()));
////        }
//        Collection<Role> role = user.getRoles();
//        authorities.add(new SimpleGrantedAuthority(role.iterator().next().getName()));
//        return new UsernamePasswordAuthenticationToken(login, password, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}