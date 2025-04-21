package com.astronaut.mission.exam.cs489_final_exam.service;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.AstronautDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.AstronautResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AstronautService {
    AstronautResponseDTO createAstronaut(@Valid AstronautDTO dto);
    List<AstronautResponseDTO> getAllAstronauts(String sortBy, String order);

}

