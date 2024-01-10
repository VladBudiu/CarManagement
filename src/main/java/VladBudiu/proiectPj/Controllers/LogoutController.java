package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Repositories.UserInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String loginForm(Model model, HttpSession session)
    {
        session.invalidate();
        return "redirect:/login";
    }

}
