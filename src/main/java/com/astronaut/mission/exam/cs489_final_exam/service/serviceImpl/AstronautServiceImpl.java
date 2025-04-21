package com.astronaut.mission.exam.cs489_final_exam.service.serviceImpl;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.AstronautDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.AstronautResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.exception.SatelliteNotFoundException;
import com.astronaut.mission.exam.cs489_final_exam.mapper.AstronautMapper;
import com.astronaut.mission.exam.cs489_final_exam.model.Astronaut;
import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;
import com.astronaut.mission.exam.cs489_final_exam.repository.AstronautRepository;
import com.astronaut.mission.exam.cs489_final_exam.repository.SatelliteRepository;
import com.astronaut.mission.exam.cs489_final_exam.service.AstronautService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AstronautServiceImpl implements AstronautService {
    private final AstronautRepository astronautRepo;
    private final SatelliteRepository satelliteRepo;
    private final AstronautMapper astronautMapper;

    @Override
    public AstronautResponseDTO createAstronaut(@Valid AstronautDTO dto) {
        Set<Satellite> satellites = dto.satelliteIds().stream()
                .map(id -> satelliteRepo.findById(id)
                        .orElseThrow(() -> new SatelliteNotFoundException(id)))
                .collect(Collectors.toSet());

        Astronaut astronaut = new Astronaut();
        astronaut.setFirstName(dto.firstName());
        astronaut.setLastName(dto.lastName());
        astronaut.setExperienceYears(dto.experienceYears());
        astronaut.setSatellites(satellites);

        return astronautMapper.toResponse(astronautRepo.save(astronaut));
    }

    @Override
    public List<AstronautResponseDTO> getAllAstronauts(String sortBy, String order) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        return astronautRepo.findAll(sort)
                .stream()
                .map(astronautMapper::toResponse)
                .toList();
    }
}
