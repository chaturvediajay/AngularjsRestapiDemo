package com.example.demo.scope;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
 
public class SHASecure {
//	 static  byte[] salt = getSalt();
     
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String passwordToHash = "password";
         
        String securePassword = get_SHA_1_SecurePassword(passwordToHash );
        System.out.println(securePassword);
         
        securePassword = get_SHA_256_SecurePassword(passwordToHash);
        System.out.println(securePassword);
         
        securePassword = get_SHA_384_SecurePassword(passwordToHash);
        System.out.println(securePassword);
         
        securePassword = get_SHA_512_SecurePassword(passwordToHash);
        System.out.println(securePassword);
    }
 
    public static String get_SHA_1_SecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(getSalt());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
    private static String get_SHA_256_SecurePassword(String passwordToHash)
    { return null;
        //Use MessageDigest md = MessageDigest.getInstance("SHA-256");
    }
     
    private static String get_SHA_384_SecurePassword(String passwordToHash)
    {
    	return null;
        //Use MessageDigest md = MessageDigest.getInstance("SHA-384");
    }
     
    private static String get_SHA_512_SecurePassword(String passwordToHash)
    {
    	return null;
        //Use MessageDigest md = MessageDigest.getInstance("SHA-512");
    }
     
    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
    	
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}