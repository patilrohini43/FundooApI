package com.bridgelabz.fundoo.util;


import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoo.exception.UserException;


public class UserToken {
	
	

	private static String TOKEN_SECRET="gh2we43jue";
	public static String createToken(long id) throws UserException
	{
		try {
			Algorithm algorithm= Algorithm.HMAC256(TOKEN_SECRET);
			String token=JWT.create()
							.withClaim("ID", id)
							.sign(algorithm);
			return token;		
		}
		catch(Exception exception)
		{
			throw new UserException(100,"Token Not Generated");
		}
	}
	
	public static long tokenVerify(String token)throws Exception	
	{
		long userid;
		try {
			Verification verification=JWT.require(Algorithm.HMAC256(UserToken.TOKEN_SECRET));
			JWTVerifier jwtverifier=verification.build();
			DecodedJWT decodedjwt=jwtverifier.verify(token);
			Claim claim=decodedjwt.getClaim("ID");
			userid=claim.asLong();	
			System.out.println(userid);
		}
		catch(Exception exception)
		{
			throw new UserException(100,"Token Not Verified");
		}
		
			return userid;
	}

	
	
	
	
	
	
	
	
	
	
	
/**
    public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";
    
    

    public static String createToken(Long id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("Id", id)
                    //.withClaim("createdAt", new Date())
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        return null;
    }
    
    
    
 

	public static long tokenVerify(String token)throws Exception	
	{
		long userid;
		try {
			Verification verification=JWT.require(Algorithm.HMAC256(UserToken.TOKEN_SECRET));
			JWTVerifier jwtverifier=verification.build();
			DecodedJWT decodedjwt=jwtverifier.verify(token);
			Claim claim=decodedjwt.getClaim("ID");
			userid=claim.asLong();	
			System.out.println(userid);
		}
		catch(Exception exception)
		{
			throw new UserException(100,"Token Not Verified");
		}
		
			return userid;
}

**/


}
