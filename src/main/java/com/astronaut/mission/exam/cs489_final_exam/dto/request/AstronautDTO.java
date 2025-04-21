package com.astronaut.mission.exam.cs489_final_exam.dto.request;

import jakarta.validation.constraints.*;

import java.util.Set;

public record AstronautDTO(
        @NotBlank @Size(min = 2, max = 20) String firstName,
        @NotBlank @Size(min = 2, max = 20) String lastName,
        @Min(0) @Max(50) int experienceYears,
        @NotNull Set<Long> satelliteIds
) {}

