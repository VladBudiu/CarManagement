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

import java.util.Optional;

@Controller
public class LoginController {

    public LoginController(){
        super();
    }

    @GetMapping({"/login"})
    public String loginForm(Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("userLogat");
        if(user==null)
        {
            model.addAttribute("user", new User());
            return "/login";
        }
        return "redirect:/index";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session)
    {
        Optional<User> optionalUser = userInterface.findByUsername(user.getUsername());
        if (optionalUser.isEmpty() || (user.getPassword().compareTo(optionalUser.get().getPassword())!=0))
        {
            model.addAttribute("error", true);
            return "/login";
        }
        session.setAttribute("userLogat", optionalUser.get());
        return "redirect:/index";
    }

    @Autowired
    private UserInterface userInterface;
}
