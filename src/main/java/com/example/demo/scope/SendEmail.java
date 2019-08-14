package com.example.demo.scope;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
public class SendEmail {
   public static boolean send(String msg, String sub, String email) {
        String str = "";
        final String pass = "ajay@1987";
        final String user = "info@blagot.com";
        String to = email;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "blagot.com");
        props.put("mail.smtp.port", "25");
        props.setProperty("mail.smtp.**ssl.enable", "false");
        props.setProperty("mail.smtp.**ssl.required", "false");
        Session session = Session.getInstance(props,new javax.mail.Authenticator()
		{
		@Override
		protected PasswordAuthentication getPasswordAuthentication()
		{
		       return new PasswordAuthentication(user,pass);
		}
		});

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom((Address)new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(to));
            message.setSubject(sub);
            message.setContent((Object)msg, "text/html");
            Transport.send((Message)message);
            return true;
        }
        catch (Exception e) {
            str = String.valueOf(str) + e.toString() + user + pass;
            return false;
        }
    }
	
	
	
		public static void main(String args[])
		{
			   String str = "<h3>Welcome&nbsp;ajay chaturvedi</h3><p>&nbsp; &nbsp; &nbsp; &nbsp; please activate your account following link.... &nbsp;</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<a href='http://localhost:8080/TestSpring?activation=O0xlGtF8Os5M+RxbGaKIjw=='>Activation Account</a>(expire after 48 hours)</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p style=\"text-align: center;\">@Blagot2016-Team</p>";
		        System.out.println(SendEmail.send(str, "subject testing", "jychtrvd@gmail.com"));
		}
	}  

