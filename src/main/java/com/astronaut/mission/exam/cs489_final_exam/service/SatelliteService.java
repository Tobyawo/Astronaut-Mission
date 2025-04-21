package com.astronaut.mission.exam.cs489_final_exam.service;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.SatelliteDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.SatelliteResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;

import java.util.List;

public interface SatelliteService {

    SatelliteResponseDTO updateSatellite(Long id, SatelliteDTO dto);
    List<SatelliteResponseDTO> getAllSatellites(String sort, String order);

}
