package com.lucascorrea.forohub.security;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class TokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService; private final UserDetailsService userDetailsService;
    public TokenFilter(TokenService tokenService, UserDetailsService userDetailsService){this.tokenService=tokenService; this.userDetailsService=userDetailsService;}
    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            try{
                String email=tokenService.getSubject(token);
                UserDetails user=userDetailsService.loadUserByUsername(email);
                var auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch(Exception e){}
        }
        filterChain.doFilter(request,response);
    }
}