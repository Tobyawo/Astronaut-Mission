package com.astronaut.mission.exam.cs489_final_exam.repository;

import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
}

