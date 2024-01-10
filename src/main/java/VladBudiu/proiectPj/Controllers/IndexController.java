package VladBudiu.proiectPj.Controllers;

import VladBudiu.proiectPj.Entities.Masini.Masina;
import VladBudiu.proiectPj.Entities.User.User;
import VladBudiu.proiectPj.Repositories.MasiniInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String indexPage(Model model, HttpSession session,
                            @RequestParam(name="brand", required = false) String brand,
                            @RequestParam(name="model", required = false) String modelParametru,
                            @RequestParam(name = "capacitateCilindrica", required = false) Integer capacitateCilindrica)
    {
        User userLogat = (User)session.getAttribute("userLogat");
        if(userLogat==null)
        {
            return "redirect:/login";
        }
        populateSortingCriteria(model);
        boolean isOperator = userLogat.isOperator();
        model.addAttribute("isOperator" , isOperator);
        model.addAttribute("username", userLogat.getUsername());
        populateModelWithCars(model, brand, modelParametru, capacitateCilindrica);
            return "/index";
    }

    private void populateModelWithCars(Model model, String brand, String modelParametru, Integer capacitateCilindrica) {

        List<Masina> masini;

        if((brand== null || (brand.isEmpty()))
                && (modelParametru==null || (modelParametru.isEmpty()))
                && capacitateCilindrica==null)
        {
            masini=masiniInterface.findAll();
        }
        else
        {
            if(brand.isEmpty()) brand = null;
            if(modelParametru.isEmpty()) modelParametru = null;
            //if(capacitateCilindrica == null) capacitateCilindrica=0;
            masini=masiniInterface.cautaDupaToateCriteriile(brand,modelParametru,capacitateCilindrica);
        }
        model.addAttribute("masini",masini);
    }

    private void populateSortingCriteria(Model model) {
        List<String> distinctBrands = masiniInterface.cautaBrandDistincte();
        List<String> distinctModels = masiniInterface.cautaModelDistinct();
        List<Integer> distinctCapacitateCilindrica = masiniInterface.cautaCapacitateCilindricaDistincta();

        model.addAttribute("distinctBrands", distinctBrands);
        model.addAttribute("distinctModels", distinctModels);
        model.addAttribute("distinctCapacitateCilindrica", distinctCapacitateCilindrica);
    }

    @Autowired
    private MasiniInterface masiniInterface;
}
