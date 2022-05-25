package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Equipment;
import fr.isep.homeExchange.model.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    List<Equipment> getEquipmentByHabitation(Habitation habitation);
}
