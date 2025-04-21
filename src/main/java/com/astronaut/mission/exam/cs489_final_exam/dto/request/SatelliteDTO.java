package com.astronaut.mission.exam.cs489_final_exam.dto.request;

import com.astronaut.mission.exam.cs489_final_exam.enums.OrbitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SatelliteDTO(
        @Size(min = 2, max = 20)
        @NotBlank String name,
        @Past(message = "Launch date must be in the past")
        LocalDate launchDate,
        OrbitType orbitType,
        boolean decommissioned

) {}

