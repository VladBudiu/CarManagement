package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Entities.User.User;
import VladBudiu.proiectPj.Repositories.UserInterface;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntregistrareConstroller {
    @Autowired
    private UserInterface userInterface;
    public IntregistrareConstroller() {
    }

    @GetMapping("/inregistrare")
    public String registerForm(Model model, HttpSession session)
    {
        session.invalidate();
        model.addAttribute("user", new User());
        return "/inregistrare";
    }
    @PostMapping("/inregistrare")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam("confirmPassword") String confirmPassword, Model model)
    {
        if(!user.hasPassword(confirmPassword))
        {
            model.addAttribute("password_error", true);
            return "/inregistrare";
        }
        if(userInterface.findByUsername(user.getUsername()).isPresent())
        {
            model.addAttribute("usernameTaken", true);
            return "/inregistrare";
        }
        //user.setPassword();
        userInterface.save(user);
        return "/login";
    }

}
