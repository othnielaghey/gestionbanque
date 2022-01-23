package esig.tg.gestionbanque.controller;

import esig.tg.gestionbanque.dao.CompteRepository;
import esig.tg.gestionbanque.dao.OpererRepository;
import esig.tg.gestionbanque.domain.Compte;
import esig.tg.gestionbanque.domain.Operer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class OperationController {

    private CompteRepository compteRepository;
    private OpererRepository opererRepository;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


    @Autowired
    public OperationController(CompteRepository compteRepository, OpererRepository opererRepository) {
        this.compteRepository = compteRepository;
        this.opererRepository = opererRepository;
    }


//    @GetMapping("/checkcompte/{numeroCompte}")
//    @ResponseBody
//    public String getCheck(@PathVariable("numeroCompte") String accountNumber){
//        System.out.println("Enter here");
//        Compte compte = compteRepository.findByIdCpt(Long.valueOf(accountNumber));
//        String value = "";
//        if (compte == null){
//            value = "";
//        }else{
//             value = compte.getClient().getNom();
//
//        }
//       // String value = compte.getClient().getNom();
//        System.out.println("afficher value "+value);
//
//        return value;
//    }

    @GetMapping("/operation")
    public String afficheOperation(Model model){
        model.addAttribute("listOperation", opererRepository.findAll());

        return "Operation";
    }

    @PostMapping("/operer")
    public String operer(@RequestParam("numCompte") String numCompte,
                         @RequestParam("typeOperation") String typeOperation,
                         @RequestParam("soldeOperation") String soldeOperation,
                         @RequestParam("commentaire") String commentaire,
                         RedirectAttributes redirectAttributes
    ){
        Compte compte = compteRepository.findCompteByNumCompte(numCompte);
        if (typeOperation.equals("depot")){
            compte.setSoldeCpt(String.valueOf(Long.valueOf(compte.getSoldeCpt()) + Long.valueOf(soldeOperation)) );
            compteRepository.save(compte);
            Operer operer = new Operer();
            operer.setTypeOperation(typeOperation);
            operer.setSoldeOperation(soldeOperation);
            operer.setCommentaire(commentaire);
            operer.setCompteOp(compte);
            operer.setDateOperation(LocalDateTime.now().format(dateTimeFormatter));
            operer.setClientOp(compte.getClient());

            opererRepository.save(operer);
            redirectAttributes.addFlashAttribute("transaction_success", "transaction_réussie");

        }else if (typeOperation.equals("retrait")){
            if (Long.valueOf(compte.getSoldeCpt()) > Long.valueOf(soldeOperation)){
                compte.setSoldeCpt(String.valueOf(Long.valueOf(compte.getSoldeCpt()) - Long.valueOf(soldeOperation)) );
                compteRepository.save(compte);
                Operer operer = new Operer();
                operer.setTypeOperation(typeOperation);
                operer.setSoldeOperation(soldeOperation);
                operer.setCommentaire(commentaire);
                operer.setCompteOp(compte);
                operer.setDateOperation(LocalDateTime.now().format(dateTimeFormatter));
                operer.setClientOp(compte.getClient());

                opererRepository.save(operer);
                redirectAttributes.addFlashAttribute("save_success", "transaction_réussie");

            }else {
                redirectAttributes.addFlashAttribute("save_error", "transaction_échoué");

            }
        }

        return "redirect:/operation";

    }
}
