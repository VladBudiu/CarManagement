package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Entities.User.User;
import VladBudiu.proiectPj.Repositories.MasiniInterface;
import VladBudiu.proiectPj.Repositories.UserInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StergereMasinaController {
    @Autowired
    private MasiniInterface masiniInterface;
    @PostMapping("/stergereMasina")
    public String deleteCar(@RequestParam("numarInmatriculare") String numarInmatriculare, HttpSession session)
    {
        User user =(User)session.getAttribute("userLogat");
        if(user == null || !user.isOperator())
        {
            return "redirect:/index";
        }
        masiniInterface.deleteById(numarInmatriculare);
        return "redirect:/index";

    }
}
