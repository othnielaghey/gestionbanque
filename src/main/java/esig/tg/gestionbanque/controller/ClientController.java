package esig.tg.gestionbanque.controller;

import esig.tg.gestionbanque.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    private ClientRepository clientRepository;

    @Autowired ClientController(ClientRepository clientRepository){

        this.clientRepository = clientRepository;
    }

    @GetMapping("/client")
    public String getAllClient(){

        return "save";
    }
}

