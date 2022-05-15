package com.example.demo.controllers;

import com.example.demo.entities.Habitation;
import com.example.demo.repositories.HabitationRepository;
import com.example.demo.service.HabitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HabitationController {

    private final HabitationService habitationService;
    @Autowired
    private HabitationRepository habitationRepository;

    @Autowired
    public HabitationController(HabitationService habitationService) {
        this.habitationService = habitationService;
    }

    @GetMapping("/getUsersHabitations/{user}")
    public List<Habitation> getHabitationsByUserId(@PathVariable("user") Integer userId){
        return habitationService.getHabitations().stream()
                .filter(habitation -> userId.equals(habitation.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getUsersHabitations")
    public List<Habitation> getHabitations() {return habitationRepository.findAll();}
}
