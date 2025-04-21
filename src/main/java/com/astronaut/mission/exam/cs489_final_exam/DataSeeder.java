package com.astronaut.mission.exam.cs489_final_exam;

import com.astronaut.mission.exam.cs489_final_exam.enums.OrbitType;
import com.astronaut.mission.exam.cs489_final_exam.model.Astronaut;
import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;
import com.astronaut.mission.exam.cs489_final_exam.repository.AstronautRepository;
import com.astronaut.mission.exam.cs489_final_exam.repository.SatelliteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AstronautRepository astronautRepository;
    private final SatelliteRepository satelliteRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Avoid seeding if data already exists
        if (satelliteRepository.count() == 0) {
            Satellite hubble = new Satellite("Hubble", LocalDate.of(1990, 4, 24), OrbitType.LEO, false);
            Satellite starlink = new Satellite("Starlink-17", LocalDate.of(2023, 8, 14), OrbitType.MEO, false);
            Satellite sentinel = new Satellite("Sentinel-6", LocalDate.of(2020, 11, 21), OrbitType.LEO, true);

            satelliteRepository.saveAll(List.of(hubble, starlink, sentinel));
        }

        if (astronautRepository.count() == 0) {
            List<Satellite> allSatellites = satelliteRepository.findAll();

            Astronaut neil = new Astronaut("Neil", "Armstrong", 12, new HashSet<>(allSatellites));
            Astronaut sally = new Astronaut("Sally", "Ride", 8, Set.of(allSatellites.get(0), allSatellites.get(1)));
            Astronaut chris = new Astronaut("Chris", "Hadfield", 15, Set.of(allSatellites.get(0)));

            astronautRepository.saveAll(List.of(neil, sally, chris));
        }
    }
}

