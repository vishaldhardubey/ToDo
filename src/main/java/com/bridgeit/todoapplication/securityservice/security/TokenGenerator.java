package com.bridgeit.todoapplication.securityservice.security;

import java.sql.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <p>
 * <b>To generate the token.</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 12-07-2018
 */
@Service
public class TokenGenerator {

	final static String KEY = "Vishal";

	/**
	 * <p>
	 * Function is to generate the unique token for every user.
	 * </p>
	 * 
	 * @param user
	 * @return token
	 */
	@SuppressWarnings("unused")
	public String generator(String id) {
		// String passkey = ((User) user).getPassword();
		long time = System.currentTimeMillis();
		long nowMillis = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(id).signWith(SignatureAlgorithm.HS256, KEY);
		return builder.compact();
	}

	/**
	 * <p>
	 * Function is to claim the token.
	 * </p>
	 * 
	 * @param jwt
	 * @return email ID
	 * @throws ToDoException
	 */
	public String parseJWT(String jwt) {

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("Subject: " + claims.getSubject());
		return claims.getSubject();
	}
}
