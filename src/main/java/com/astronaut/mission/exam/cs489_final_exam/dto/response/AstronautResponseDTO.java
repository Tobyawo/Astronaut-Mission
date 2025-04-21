package com.astronaut.mission.exam.cs489_final_exam.dto.response;


import java.util.Set;

public record AstronautResponseDTO(
        Long id,
        String firstName,
        String lastName,
        int experienceYears,
        Set<String> satelliteNames
) {}

