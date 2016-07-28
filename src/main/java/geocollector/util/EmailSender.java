package geocollector.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender
{
	private static final transient Logger log = LoggerFactory.getLogger(EmailSender.class);
	
	private final String username = "geocollector2015@gmail.com";
	private final String password = "gamificationlab2015";
	
	private Properties props;
	private Session session;
	
	public EmailSender()
	{
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");

		
		session = Session.getInstance
				(props, new Authenticator()
						{
							protected PasswordAuthentication getPasswordAuthentication()
							{
								return new PasswordAuthentication(username, password);
							}
						}
				);
		session.setDebug(true);
	}
	
	public void sendActiveEmail(String recipent, String nome,  String sha256)	
	{
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("geocollector2015@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipent));
			message.setSubject("Ative sua conta agora!");
			
			message.setContent("<h3>Olá " + nome + ", bem vindo!</h3> <br>" +
					"<p> Obrigado por se registrar no GeoCollector! <br> Você acaba de dar um grande passo em sua coleta de campo. Aproveite agora! <br><br></p>" +
					"<p> Para começar a usar o GeoCollector, é necessário ativar sua conta. Para ativá-la, clique no link abaixo:" +
					"<div style='background-color:#fffadb;border:#eddd79 solid 1px;margin:1.2em 0;padding:.5em'> <a href='www.geocollector.com.br/activeUser?email=" + recipent + "&key=" + sha256 + "'> www.geocollector.com.br/activeUser?email=" + recipent + "&key=" + sha256 +" </a> </div>" +
					"<p> Se você não esperava este email, ou não tem idéia de porque recebeu este, por favor ignore-o. Pode ter sido causado por um erro de alguma pessoa. </p> <br>" +
					"<p> Atenciosamente, </p> <br> <br>" +
					"<p> Geocollector - Soluções em Geoinformática </p>" +
					"<p> © GeoCollector, Todos os direitos reservados.  </p>", "text/html");
			Transport.send(message); 
		}
		catch (MessagingException e)
		{
			log.error("Erro ao enviar email de recuperação de senha para " + recipent + ". Erro = " + e.getMessage());
		}
	}
	
	public void sendContactEmail(String name, String email, String tel, String site, String userMessage)	
	{
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("geocollector2015@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("geocollector2015@gmail.com"));
			message.setSubject("CONTATO PELO WEBSITE");
			
			message.setContent("<h3>" + name + "</h3> <br>" +
					"<p> Email:" + email + ", Tel.:" + tel + ", Site:" + site + 
					"<br><p> Mensagem:" + userMessage + "</p>", "text/html");
			Transport.send(message); 
		}
		catch (MessagingException e)
		{
			log.error("Erro ao enviar email de CONTATO. Erro = " + e.getMessage());
		}
	}
	
	public void sendNewPassword(String recipent, String nome,  String senha)	
	{
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("geocollector2015@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipent));
			message.setSubject("Ative sua conta agora!");
			
			message.setContent("<h3>Olá " + nome + "!</h3> <br>" +
					"<p> Você efetuou uma solicitação para recuperação de senha em nossos sistema.</p>" +
					"<p> Sua nova senha é esta: " + senha + "</p> <br><br>" +
					"<p> Caso deseje trocar a senha, acesse <a href='http://www.geocollector.com.br/trocarSenha.jsp'> aqui  </a> para realizá-la.  </p> <br> <br>" +
					"<p> Atenciosamente, </p> <br> <br>" +
					"<p> Geocollector - Soluções em Geoinformática </p>" +
					"<p> © GeoCollector, Todos os direitos reservados.  </p>", "text/html");
			Transport.send(message); 
		}
		catch (MessagingException e)
		{
			log.error("Erro ao enviar email de recuperação de senha para " + recipent + ". Erro = " + e.getMessage());
		}
	}

}
