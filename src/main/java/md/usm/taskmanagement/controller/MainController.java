package md.usm.taskmanagement.controller;

import md.usm.taskmanagement.model.Beverage;
import md.usm.taskmanagement.service.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class MainController {

    private BeverageService beverageService;

    public MainController(BeverageService beverageService) {
        this.beverageService = beverageService;
        beverageService.init();
    }

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/beverages")
    public String favoriteBeverages(Model model) {

        if (beverageService.isWorkingTime())
            model.addAttribute("drinks", beverageService.getAllowedDrinks());
        else
            model.addAttribute("drinks", beverageService.getDrinks());

        return "beverages";

    }

}
