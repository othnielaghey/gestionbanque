package esig.tg.gestionbanque.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
@Configuration
@EnableScheduling
public class CronPlanner {

    @Scheduled(cron ="0 5 * * * *")
    public void updateCsv(){
        System.out.println("fichier csv mis à jour");
        csvfileSave();
    }

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 50 16 * * *")
    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("othnielignacio@gmail.com");
        message.setTo("ananijosue08@gmail.com");
        message.setText("This is a test");
        message.setSubject("Test");

        /*
        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("test.csv", file);
*/
        mailSender.send(message);

        System.out.println("Mail sent successfully...");
    }


    public void csvfileSave(){

        List<String[]> datalines = new ArrayList<>();
        datalines.add(new String[]{"nom","prenom","age"});
        datalines.add(new String[]{"yao","koffi","20"});
        datalines.add(new String[]{"komi","zika","28"});
        datalines.add(new String[]{"restga","bob","29"});


        File CsvOutPutFile = new File("esig_test.csv");

        try (PrintWriter pw = new PrintWriter(CsvOutPutFile)){
            datalines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(CsvOutPutFile.exists());
    }

    public String convertToCSV(String [] data){

        return data !=null ? Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(";")):"";
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = "";
        System.out.println(data);
        if (data != null && !(data.isEmpty())) {
            escapedData = data.replaceAll("\\R", " ");
            if (data.contains(",") || data.contains("\"") || data.contains("'")) {
                data = data.replace("\"", "\"\"");
                escapedData = "\"" + data + "\"";
            }
        }

        return escapedData;
    }

  /*

    @Autowired
    public CronPlanner(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendMail() throws MessagingException, UnsupportedEncodingException{

        String adresse = "othnielignacio@yahoo.com";
        String adressecc = "ananijosue08@gmail.com";
        String objet = "Salutation";
        String fromMail ="othnielignacio@gmail.com";
        String fromName ="AGHEY";

        String contenu = "Bonjour, <br>\n"+
                "<br>\n"+
                "merci pour ta visite";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom(fromMail, fromName);
        helper.setTo(adresse);
        helper.setCc(adressecc);
        helper.setSubject(objet);
        helper.setText(contenu, true);
        javaMailSender.send(message);
        System.out.println("Message envoyé");
    }
*/
}