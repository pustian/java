///**
// * 
// */
//package javaABC.demo.email;
//
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
//import java.security.NoSuchProviderException;
//import java.util.Properties;
//
///**
// * <p> Title:EMailSample </p>
// * <p> Description:       </p>
// * <p> Company: Masapay   </p>
// * @author: tianpusen
// * @Date  : 2016年10月19日 下午5:01:47
// */
//public final class EMailSample {
//	public static void main(String[] args) {
//		// Configure mail provider
//	    Properties props = new Properties();
//	    props.put("mail.smtp.host", "smtp.mymailprovider.com");
//	    props.put("mail.pop3.host", "pop3.mymailprovider.com");
//	    // Enable SSL
//	    props.put("mail.pop3.ssl.enable", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//
//	    // Enable SMTP Authentication
//	    props.put("mail.smtp.auth","true");
//
//	    Authenticator auth = new PasswordAuthentication("user", "password");
//	    Session session = Session.getDefaultInstance(props, auth);
//
//	    // Get the store for authentication
//	    final Store store;
//	    try {
//	        store = session.getStore("pop3");
//	    } catch (NoSuchProviderException e) {
//	        throw new IllegalStateException(e);
//	    }
//
//	    try {
//	        store.connect();
//	    } catch (AuthenticationFailedException | MessagingException e) {
//	        throw new IllegalStateException(e);
//	    }
//	    
//	    try {
//	      // Setting up the mail
//	      InternetAddress from = new InternetAddress("sender@example.com");
//	      InternetAddress to = new InternetAddress("receiver@example.com");
//
//	      MimeMessage message = new MimeMessage(session);
//	      message.setFrom(from);
//	      message.addRecipient(Message.RecipientType.TO, to);
//
//	      message.setSubject("Test Subject");
//	      message.setText("Hi, I'm a Mail sent with Java Mail API.");
//
//	      // Send the mail
//	      Transport.send(message);
//	    } catch (AddressException | MessagingException e)
//	        throw new IllegalStateException(e);
//	    } 
//	}
//}
