package com.astronaut.mission.exam.cs489_final_exam.controller;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.AstronautDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.request.SatelliteDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.AstronautResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.SatelliteResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.service.AstronautService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/astronauts")
@RequiredArgsConstructor
public class AstronautController {
    private final AstronautService astronautService;

    @PostMapping
    public ResponseEntity<AstronautResponseDTO> create(@Valid @RequestBody AstronautDTO dto) {
        AstronautResponseDTO response = astronautService.createAstronaut(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AstronautResponseDTO>> getAll(
            @RequestParam(defaultValue = "experienceYears") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        List<AstronautResponseDTO> responses = astronautService.getAllAstronauts(sort, order);
        return ResponseEntity.ok(responses);
    }

}


