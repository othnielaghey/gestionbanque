package esig.tg.gestionbanque.Component;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class CronPlanner {

    private JavaMailSender javaMailSender;
    public void sendMail(){
        String adresse = "othnielignacio@gmail.com";
        String adressecc = "legitimepharaon@outlook.com";
        String object = "Hello world";
        String contenu = "Hello <br>\n" +
                "<br>\n"+ 
                "Thanks for your visit";
    }
}