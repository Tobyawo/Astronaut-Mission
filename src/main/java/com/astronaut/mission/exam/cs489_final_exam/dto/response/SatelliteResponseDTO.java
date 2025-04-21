package com.astronaut.mission.exam.cs489_final_exam.dto.response;

import java.time.Instant;

public record SatelliteResponseDTO(
        Long id,
        String name,
        Instant launchDate,
        String orbitType,
        boolean decommissioned
) {}
