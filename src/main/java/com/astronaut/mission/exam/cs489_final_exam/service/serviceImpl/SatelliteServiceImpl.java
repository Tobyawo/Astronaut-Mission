package com.astronaut.mission.exam.cs489_final_exam.service.serviceImpl;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.SatelliteDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.SatelliteResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.enums.OrbitType;
import com.astronaut.mission.exam.cs489_final_exam.exception.SatelliteNotFoundException;
import com.astronaut.mission.exam.cs489_final_exam.exception.SatelliteUpdateNotAllowedException;
import com.astronaut.mission.exam.cs489_final_exam.mapper.AstronautMapper;
import com.astronaut.mission.exam.cs489_final_exam.mapper.SatelliteMapper;
import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;
import com.astronaut.mission.exam.cs489_final_exam.repository.SatelliteRepository;
import com.astronaut.mission.exam.cs489_final_exam.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SatelliteServiceImpl implements SatelliteService {
    private final SatelliteMapper satelliteMapper;
    private final SatelliteRepository satelliteRepo;

    @Override
    public SatelliteResponseDTO updateSatellite(Long id, SatelliteDTO dto) {
        Satellite sat = satelliteRepo.findById(id)
                .orElseThrow(() -> new SatelliteNotFoundException(id ));

        if (sat.isDecommissioned()) {
            throw new SatelliteUpdateNotAllowedException("Satellite is decommissioned and cannot be updated.");
        }

        sat.setName(dto.name());
        sat.setLaunchDate(dto.launchDate());
        sat.setOrbitType(dto.orbitType());
        return satelliteMapper.toResponse(satelliteRepo.save(sat));

    }

    @Override
    public List<SatelliteResponseDTO> getAllSatellites(String sort, String order) {
        List<Satellite> satellites = satelliteRepo.findAll();
        if (sort != null && order != null) {
            if (sort.equals("name")) {
                if (order.equals("asc")) {
                    satellites.sort((a, b) -> a.getName().compareTo(b.getName()));
                } else if (order.equals("desc")) {
                    satellites.sort((a, b) -> b.getName().compareTo(a.getName()));
                }
            } else if (sort.equals("launchDate")) {
                if (order.equals("asc")) {
                    satellites.sort((a, b) -> a.getLaunchDate().compareTo(b.getLaunchDate()));
                } else if (order.equals("desc")) {
                    satellites.sort((a, b) -> b.getLaunchDate().compareTo(a.getLaunchDate()));
                }
            } else if (sort.equals("orbitType")) {
                if (order.equals("asc")) {
                    satellites.sort((a, b) -> a.getOrbitType().compareTo(b.getOrbitType()));
                } else if (order.equals("desc")) {
                    satellites.sort((a, b) -> b.getOrbitType().compareTo(a.getOrbitType()));
                }
            }
        }
        return satellites.stream().map(satelliteMapper::toResponse).toList();
    }

}
