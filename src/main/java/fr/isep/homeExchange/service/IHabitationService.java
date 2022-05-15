package fr.isep.homeExchange.service;

import fr.isep.homeExchange.model.Habitation;

import java.util.List;

public interface IHabitationService {
    public List<Habitation> findAll();
    public List<Habitation> findByCity(String city);
}
