package esig.tg.gestionbanque;

import esig.tg.gestionbanque.Component.CronPlanner;
import org.aspectj.weaver.CrosscuttingMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class GestionbanqueApplication {
    @Autowired
    private CronPlanner mailSender;
    private CronPlanner daily;

    public static void main(String[] args) {
        SpringApplication.run(GestionbanqueApplication.class, args);
    }
/*
    @EventListener(ApplicationReadyEvent.class)
    public CronPlanner getDaily() {
        return daily;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void  sendMail(){
        mailSender.sendEmail("bmouke17@gmail.com",
                "Test",
                "This is a test");
    }
*/
}