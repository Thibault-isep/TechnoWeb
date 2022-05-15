package fr.isep.homeExchange.service;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.repository.HabitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class HabitationService implements IHabitationService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Habitation> findAll() {
        Query query = entityManager.createQuery("SELECT h FROM Habitation h");
        return query.getResultList();
    }

    @Override
    public List<Habitation> findByCity(String city) {
        Query query = entityManager.createQuery("SELECT h FROM Habitation h WHERE h.city LIKE :x");
        return query.getResultList();
    }

    private final HabitationRepository habitationRepository;

    @Autowired
    public HabitationService(HabitationRepository habitationRepository) {
        this.habitationRepository = habitationRepository;
    }

    public List<Habitation> getHabitations() {return habitationRepository.findAll();}
}
