package buisness;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class Email {

    @Resource(lookup = "java:jboss/mail/gmail")
    private Session session;


    public Email() {
    }

    public void sendEmail(String to, String subject , String body){
        try{
            Message message = new MimeMessage(session);
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
