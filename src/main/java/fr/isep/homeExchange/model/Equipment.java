package fr.isep.homeExchange.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEquipment;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "equipments")
    private List<Habitation> habitation = new ArrayList<>();

    public Equipment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Equipment() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public List<Habitation> getHabitation() {
        return habitation;
    }

    public void setHabitation(List<Habitation> habitation) {
        this.habitation = habitation;
    }
}
