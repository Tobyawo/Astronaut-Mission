package com.astronaut.mission.exam.cs489_final_exam.controller;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.AstronautDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.request.SatelliteDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.AstronautResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.SatelliteResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.service.AstronautService;
import com.astronaut.mission.exam.cs489_final_exam.service.SatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/satellite")
@RequiredArgsConstructor
public class SatelliteController {
    private final SatelliteService satelliteService;


    @PutMapping("/{id}")
    public ResponseEntity<SatelliteResponseDTO> updateSatellite(
            @PathVariable Long id,
            @Valid @RequestBody SatelliteDTO satelliteDTO) {

        SatelliteResponseDTO updatedSatellite = satelliteService.updateSatellite(id, satelliteDTO);

        return ResponseEntity.ok(updatedSatellite);
    }

    @GetMapping
    public ResponseEntity<List<SatelliteResponseDTO>> getAll(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        List<SatelliteResponseDTO> responses = satelliteService.getAllSatellites(sort, order);
        return ResponseEntity.ok(responses);
    }
}


