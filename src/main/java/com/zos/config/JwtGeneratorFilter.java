package com.zos.config;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtGeneratorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication!=null) {
			SecretKey key=Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());
			
			String jwt = Jwts.builder()
					.setIssuer("zos academy")
					.setSubject("JWT Token")
					.claim("username", authentication.getName())
					.claim("authorities", populateAuthorities(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()+400000000))
					.signWith(key).compact();
			
			response.setHeader(SecurityConstant.JWT_HEADER, jwt);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	  private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
	        
	    	Set<String> authoritiesSet = new HashSet<>();
	        
	        for (GrantedAuthority authority : collection) {
	            authoritiesSet.add(authority.getAuthority());
	        }
	        return String.join(",", authoritiesSet);
	   
	    
	    }
	  
	  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
			
	        return !request.getServletPath().equals("/signin");	
		}

}
