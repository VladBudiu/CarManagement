package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Entities.Masini.Masina;
import VladBudiu.proiectPj.Entities.User.User;
import VladBudiu.proiectPj.Repositories.MasiniInterface;
import VladBudiu.proiectPj.Repositories.UserInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class AdaugareMasinaController {
    @Autowired
    private MasiniInterface masiniInterface;
    @GetMapping("/addCar")
    public String adaugareMasinaForm(Model model, HttpSession session)
    {
        User user =(User)session.getAttribute("userLogat");
        if(user==null || !user.isOperator())
        {
            return "redirect:/login";
        }
        model.addAttribute("masina", new Masina());
        return "/addCar";
    }

    @PostMapping("/addCar")
    public String adaugareMasina(@ModelAttribute("masina") Masina car, Model model,
                                 HttpSession session, RedirectAttributes redirectAttributes)
    {
        String registrationNumber = car.getNumarInmatriculare().toUpperCase();
        if (masiniInterface.findById(registrationNumber).isPresent())
        {
            redirectAttributes.addFlashAttribute("existaInmatricularea", true);
            return "redirect:/index";
        }
        User user = (User)session.getAttribute("userLogat");
        car.setUser(user);
        car.setNumarInmatriculare(registrationNumber);
        masiniInterface.save(car);
        return "redirect:/index";
    }
}
