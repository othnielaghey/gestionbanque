package esig.tg.gestionbanque.controller;

import esig.tg.gestionbanque.dao.CompteRepository;
import esig.tg.gestionbanque.dao.TransfererRepository;
import esig.tg.gestionbanque.domain.Compte;
import esig.tg.gestionbanque.domain.Transferer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TransfertController {
    private TransfererRepository transfererRepository;
    private CompteRepository compteRepository;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    public TransfertController(CompteRepository compteRepository, TransfererRepository transfererRepository){
        this.compteRepository = compteRepository;
        this.transfererRepository = transfererRepository;
    }


    @GetMapping("/transfert")
    public String afficheTransfert(Model model){
        model.addAttribute("listTransfert", transfererRepository.findAll());

        return  "Transfert";
    }

    @PostMapping("/transferer")
    public String transferer(@RequestParam("numCompteExpediteur") String numCompteExpediteur,
                             @RequestParam("numCompteRecepteur") String numCompteRecepteur,
                             @RequestParam("montantTransfert") String montantTransfert,
                             @RequestParam("commentaire") String commentaire,
                             RedirectAttributes redirectAttributes
    ){
        Compte compte = compteRepository.findCompteByNumCompte(numCompteExpediteur);
        Compte compte1 = compteRepository.findCompteByNumCompte(numCompteRecepteur);
        if (Long.valueOf(compte.getSoldeCpt()) < Long.valueOf(montantTransfert)){
            redirectAttributes.addFlashAttribute("save_error", "transaction_échoué");
        } else {
            compte.setSoldeCpt(String.valueOf(Long.valueOf(compte.getSoldeCpt()) - Long.valueOf(montantTransfert)) );
            compte1.setSoldeCpt(String.valueOf(Long.valueOf(compte1.getSoldeCpt()) + Long.valueOf(montantTransfert)) );
            compteRepository.save(compte);
            compteRepository.save(compte1);
            Transferer transferer = new Transferer();
            transferer.setNumCompteExpediteur(numCompteExpediteur);
            transferer.setNumCompteRecepteur(numCompteRecepteur);
            transferer.setMontantTransfert(montantTransfert);
            transferer.setCommentaire(commentaire);
            transferer.setDateTransfert(LocalDateTime.now().format(dateTimeFormatter));
            transferer.setCompteTransft(compte);
            transferer.setCompteTransft(compte1);
            transferer.setClientTransft(compte.getClient());
            transferer.setClientTransft(compte1.getClient());

            transfererRepository.save(transferer);
            redirectAttributes.addFlashAttribute("save_success", "transaction_réussie");

        }

        return "redirect:/transfert";
    }
}
