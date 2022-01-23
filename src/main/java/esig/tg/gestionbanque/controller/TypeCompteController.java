package esig.tg.gestionbanque.controller;

import esig.tg.gestionbanque.dao.TypeCompteRepository;
import esig.tg.gestionbanque.domain.TypeCompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

@Controller
public class TypeCompteController {

    private TypeCompteRepository typeCompteRepository;

    @Autowired
    public TypeCompteController(TypeCompteRepository typeCompteRepository){
        this.typeCompteRepository = typeCompteRepository;
    }

    @GetMapping("/typeCompte")
    public String getAllTypeCompte(Model model){
        model.addAttribute("ListType", typeCompteRepository.findAll());
        return "TypeCompte";
    }

    @PostMapping("/saveTypeCompte/{libelleCompte}")
    public String saveTypeC(@PathVariable("libelleCompte")
                                 String libelleCompte){
                                         TypeCompte typeCompte = new TypeCompte();

                                         typeCompte.setLibelleCompte(libelleCompte);

                                         typeCompteRepository.save(typeCompte);

                                        //return typeCompte.toString();
        return "redirect:/typeCompte";
    }

    @PostMapping("/affiche")
    public String getSome(@RequestParam("libelle") String value ){
        System.out.println("Enter here " +value);
        TypeCompte typeCompte = new TypeCompte();

        typeCompte.setLibelleCompte(value);

        typeCompteRepository.save(typeCompte);

        //return "TypeCompte";
        return "redirect:/typeCompte";
    }

       @GetMapping("/deletetypecompte")
    public String deleteTypeCompte(String id, RedirectAttributes redirectAttributes){
        try{
            typeCompteRepository.deleteById(Long.valueOf(id));
            redirectAttributes.addFlashAttribute("delete_success", "suppression effectué");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/typeCompte";
  }

    @GetMapping("/updatetypecompte")
    public  String updateTypeCompte(String id,
                                    String libelleCompte,
                                    RedirectAttributes redirectAttributes){
        TypeCompte typeCompte = new TypeCompte();

        typeCompte = typeCompteRepository.findByIdType(Long.valueOf(id));

        typeCompte.setLibelleCompte(libelleCompte);
        typeCompteRepository.save(typeCompte);
        redirectAttributes.addFlashAttribute("save_success", "modification éffectuée");

        return "redirect:/typeCompte";

    }

}