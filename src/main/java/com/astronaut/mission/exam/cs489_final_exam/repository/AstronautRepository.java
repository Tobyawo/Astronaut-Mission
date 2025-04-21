package com.astronaut.mission.exam.cs489_final_exam.repository;

import com.astronaut.mission.exam.cs489_final_exam.model.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstronautRepository extends JpaRepository<Astronaut, Long> {
}

