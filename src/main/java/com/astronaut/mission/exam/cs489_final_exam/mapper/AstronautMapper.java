package com.astronaut.mission.exam.cs489_final_exam.mapper;

import com.astronaut.mission.exam.cs489_final_exam.dto.response.AstronautResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.model.Astronaut;
import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AstronautMapper {
    @Mapping(target = "satelliteNames", expression = "java(getSatelliteNames(astronaut))")
    AstronautResponseDTO toResponse(Astronaut astronaut);

    default Set<String> getSatelliteNames(Astronaut astronaut) {
        return astronaut.getSatellites().stream()
                .map(Satellite::getName)
                .collect(Collectors.toSet());
    }
}

