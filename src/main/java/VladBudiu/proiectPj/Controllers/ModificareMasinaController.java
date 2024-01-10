package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Entities.Masini.Masina;
import VladBudiu.proiectPj.Entities.User.User;
import VladBudiu.proiectPj.Repositories.MasiniInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ModificareMasinaController {
    @Autowired
    private MasiniInterface masiniInterface;

    @GetMapping("/modificareMasina")
    public String modificareMasinaForm(Model model, @RequestParam String numarInmatriculare, HttpSession session)
    {
        User user = (User)session.getAttribute("userLogat");
        if(user == null || !user.isOperator())
        {
            return "redirect:/index";
        }
        Optional<Masina> optionalMasina = masiniInterface.findById(numarInmatriculare);
        if(optionalMasina.isPresent())
        {
            Masina masina = optionalMasina.get();
            List<Masina> masini = masiniInterface.findAll();
            model.addAttribute("masina", masina);
            model.addAttribute("masini", masini);
            return "/modificareMasina";
        }
        return "redirect:/index";
    }

    @PostMapping("/modificareMasina")
    public String modificareMasina(@ModelAttribute("masina") Masina masinaModificata, HttpSession session)
    {
        User user = (User)session.getAttribute("userLogat");
        if(user == null || !user.isOperator())
        {
            return "redirect:/index";
        }
        masinaModificata.setUser(user);
        masiniInterface.save(masinaModificata);
        return "redirect:/index";

    }


}
