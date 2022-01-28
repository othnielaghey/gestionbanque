package esig.tg.gestionbanque.Component;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.sql.*;

@Component
@Configuration
@EnableScheduling
public class CronPlanner {

        //Mise à jour des fichiers CSV tous les jours à minuit
        @Scheduled(cron = "0 0 00 * * *")
        public void updateCsv() throws SQLException, IOException {
            compteCsvFileSave();
        System.out.println("fichier csv mis jour");
        }

        @Autowired
        private JavaMailSender mailSender;

        //Envoie du rapport journalier à 6h
        @Scheduled(cron = "0 0 6 * * *")
        public void sendMessageWithAttachment() throws MessagingException {
            String to = "legitimepharaon@outlook.com";
            String subject = "Rapport journalier";
            String text = "Bonjour,"+
                    "çi-joint le rapport journalier des comptes, des opérations et des transferts." +

                    "Cordialement.";
            String pathToAttachment1 = "listCompte.csv";
            String pathToAttachment2 = "listOperation.csv";
            String pathToAttachment3 = "listTransfert.csv";


            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("othnielignacio@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file1
                    = new FileSystemResource(new File(pathToAttachment1));
            FileSystemResource file2
                    = new FileSystemResource(new File(pathToAttachment2));
            FileSystemResource file3
                    = new FileSystemResource(new File(pathToAttachment3));



            helper.addAttachment("listCompte.csv", file1);
            helper.addAttachment("listOperation.csv", file2);
            helper.addAttachment("listTransfert.csv", file3);

            mailSender.send(message);

            System.out.println("Rapport journalier envoyé...");
        }

        public void compteCsvFileSave() throws IOException, SQLException {
        String[] args;

        //creation de listCompte.csv
        CSVWriter csvWriter;
        csvWriter = new CSVWriter(new FileWriter("listcompte.csv"));
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionbanque", "root", "");
        Statement stmt1 = connection1.createStatement();
        ResultSet resultSet1 = stmt1.executeQuery("select * from compte");
        csvWriter.writeAll(resultSet1, true);
        csvWriter.close();
        connection1.close();

        //creation de listOperation.csv
        csvWriter = new CSVWriter(new FileWriter("listOperation.csv"));
        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionbanque", "root", "");
        Statement stmt2 = connection2.createStatement();
        ResultSet resultSet2 = stmt2.executeQuery("select * from operer");
        csvWriter.writeAll(resultSet2, true);
        csvWriter.close();
        connection2.close();

        //creation de listTransfert.csv
        csvWriter = new CSVWriter(new FileWriter("listTransfert.csv"));
        Connection connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionbanque", "root", "");
        Statement stmt3 = connection3.createStatement();
        ResultSet resultSet3 = stmt3.executeQuery("select * from transferer");
        csvWriter.writeAll(resultSet3, true);
        csvWriter.close();
        connection3.close();


        System.out.println("listCompte.csv créé");
        System.out.println("listOperation.csv créé");
        System.out.println("listTransfert.csv créé");
    }

}