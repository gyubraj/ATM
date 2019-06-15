import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail{
	void send()  throws Exception{
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		String query="select email,account_no from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		while(rs.next())
		{
			String email=rs.getString("email");
			int anumber=rs.getInt("account_no");
		final String username = "gyubraj104@gmail.com"; // enter your mail id
		final String password = "AdRaY104@/*+-;";// enter ur password
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new		  javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("gyubraj104@gmail.com")); // same email id
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(""+email+""));// whome u have to send mails that person id
				//message
				message.setSubject("Bank Statement");
				message.setText("Your Last four Transation from Hamro Afnai Bank is in this pdf");
				//body attach
				MimeMultipart emailContent=new MimeMultipart();
				MimeBodyPart pdf=new MimeBodyPart();
				MimeBodyPart t=new MimeBodyPart();
				t.setText(" Your BankStatement from Hamro Afnai Bank ");
				
				try {
					pdf.attachFile("C:/Users/Public/BankPDF/"+anumber+".pdf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				emailContent.addBodyPart(t);
				emailContent.addBodyPart(pdf);
			
				message.setContent(emailContent);
				Transport.send(message);


			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			}
		
		
	}

}
