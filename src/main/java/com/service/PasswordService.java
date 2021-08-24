package com.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import org.springframework.stereotype.Service;



/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

//@Service
public class PasswordService {
	
	public static String getMD5(String data)
    { 
		
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			
			messageDigest.update(data.getBytes());
	        byte[] digest=messageDigest.digest();
	        StringBuffer sb = new StringBuffer();
	        for (byte b : digest) {
	            sb.append(Integer.toHexString((int) (b & 0xff)));
	        }
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

        return "";
    }
	
}
