package com.example.demo.service;

import com.example.demo.entities.Habitation;
import com.example.demo.repositories.HabitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HabitationService {
    private final HabitationRepository habitationRepository;

    @Autowired
    public HabitationService(HabitationRepository habitationRepository) {
        this.habitationRepository = habitationRepository;
    }

    public List<Habitation> getHabitations() {return habitationRepository.findAll();}
}
