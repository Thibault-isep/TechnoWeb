package com.example.demo.repositories;

import com.example.demo.entities.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Long> {
}
