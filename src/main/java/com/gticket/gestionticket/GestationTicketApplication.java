package com.gticket.gestionticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestationTicketApplication {

/*@Autowired
private EmailService senderService;*/

    public static void main(String[] args) {

        SpringApplication.run(GestationTicketApplication.class, args);
    }
/*@EventListener(ApplicationReadyEvent.class)
    public void sendEmail(){
        senderService.sendEmail("alextienou14@gmail.com",
                "Subject Title",
                "subject body ......");

}*/


}
