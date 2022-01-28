package esig.tg.gestionbanque.controller;

import esig.tg.gestionbanque.dao.ClientRepository;
import esig.tg.gestionbanque.dao.CompteRepository;
import esig.tg.gestionbanque.dao.TypeCompteRepository;
import esig.tg.gestionbanque.domain.Client;
import esig.tg.gestionbanque.domain.Compte;
import esig.tg.gestionbanque.domain.TypeCompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CompteController {

    private ClientRepository clientRepository;
    private CompteRepository compteRepository;
    private TypeCompteRepository typeCompteRepository;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private Object csvExportation;

    @Autowired
    public CompteController(ClientRepository clientRepository, CompteRepository compteRepository, TypeCompteRepository typeCompteRepository) {

        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.typeCompteRepository = typeCompteRepository;
    }

    @GetMapping("/getcompte")
    public String getAllCompte(Model model){
        model.addAttribute("listCompte", typeCompteRepository.findAll());
        model.addAttribute("listdatacompte", compteRepository.findAll());

        return "Compte";

    }

  @PostMapping("/insertcompte")
  public  String saveAllcompte(@RequestParam("solde") String solde,
                               @RequestParam("typeCompte") Long typeCompte,
                               @RequestParam("nom") String nom,
                               @RequestParam("prenom") String prenom,
                               @RequestParam("date_naiss") String date_naiss,
                               @RequestParam("adresse") String adresse,
                               @RequestParam("numCompte") String numCompte
  ) {
        Client client = new Client();

        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(date_naiss);
        client.setAdresse(adresse);
        Client clientSave = clientRepository.save(client);
        TypeCompte typeCompte1 = typeCompteRepository.findByIdType(typeCompte);


        Compte compte = new Compte(solde, LocalDateTime.now().format(dateTimeFormatter), typeCompte1 ,numCompte, clientSave);

/*        Compte compte = new Compte();

        compte.setSoldeCpt(solde);
        compte.setDateCreation(LocalDateTime.now().format(dateTimeFormatter));
        compte.setTypeCompte(typeCompte1);
        compte.setClient(clientSave);*/

      compteRepository.save(compte);

      return "redirect:/getcompte";

  }

  @GetMapping("/deletecompte")
    public String deleteCompte(String id, RedirectAttributes redirectAttributes){
        try{
            compteRepository.deleteById(Long.valueOf(id));
            redirectAttributes.addFlashAttribute("delete_success", "suppression effectué");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/getcompte";
  }

  @GetMapping("/updatecompte")
    public String updateCompte(String id,
                               String solde,
                               TypeCompte typeCompte,
                               String nom,
                               String prenom,
                               String date_naiss,
                               String adresse,
                               String numCompte, RedirectAttributes redirectAttributes){

        Compte compte = compteRepository.findByIdCpt(Long.valueOf(id));

        compte.setSoldeCpt(solde);
        compte.setTypeCompte(typeCompte);
        compte.setNumCompte(numCompte);
        compte.getClient().setNom(nom);
        compte.getClient().setPrenom(prenom);
        compte.getClient().setDateNaissance(date_naiss);
        compte.getClient().setAdresse(adresse);

        compteRepository.save(compte);

        redirectAttributes.addFlashAttribute("uodate_success", "Modifié avec succès");

        return "redirect:/getcompte";

  }

}
