package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Entities.User.NivelAcess;
import VladBudiu.proiectPj.Entities.User.User;
import VladBudiu.proiectPj.Repositories.UserInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ModificareUserController {
    @Autowired
    private UserInterface userInterface;

    @GetMapping("/modificareUser")
    public String modificareUserForm(Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("userLogat");
        if(user == null || !user.isOperator())
        {
            return "redirect:/index";
        }
        List<User> useri = userInterface.findAll();
        model.addAttribute("users", useri);
        return "/modificareUser";
    }
    @PostMapping("/modificareUser")
    public String modificareUser(@RequestParam("userSelectat") String username,
                                 @RequestParam(value = "isOperator", defaultValue = "false") boolean isOperator)
    {
        Optional<User> optionalUser= userInterface.findByUsername(username);
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if (isOperator) {
                user.setnivelAccess(NivelAcess.OPERATOR);
            } else {
                user.setnivelAccess(NivelAcess.VIZITATOR);
            }
            userInterface.save(user);

        }
        return "redirect:/index";
    }

}
