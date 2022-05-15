package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.service.HabitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HabitationController {

    private final HabitationService habitationService;
    @Autowired
    private HabitationRepository habitationRepository;

    @Autowired
    public HabitationController(HabitationService habitationService) {
        this.habitationService = habitationService;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getUsersHabitations/{user}")
    public List<Habitation> getHabitationsByUserId(@PathVariable ("user") Integer userId){
        return habitationService.getHabitations().stream()
                .filter(habitation -> userId.equals(habitation.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getUsersHabitations")
    public List<Habitation> getHabitations() {return habitationRepository.findAll();}
    ////

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch) {
        List<Habitation> habitationsList = habitationRepository.getHabitationByCity("%" + userSearch + "%");
        model.addAttribute("result", habitationsList);
        return "searchResults";
    }

    @GetMapping(value = {"", "homepage"})
    public String homePage(Model model) {
        List<Habitation> habitationList = habitationRepository.findAll();
        model.addAttribute("habitations", habitationList);
        return "homepage";
    }
}
