package com.astronaut.mission.exam.cs489_final_exam.mapper;

import com.astronaut.mission.exam.cs489_final_exam.dto.request.SatelliteDTO;
import com.astronaut.mission.exam.cs489_final_exam.dto.response.SatelliteResponseDTO;
import com.astronaut.mission.exam.cs489_final_exam.model.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface SatelliteMapper {
    Satellite toEntity(SatelliteDTO dto);

    @Mapping(target = "launchDate", expression = "java(convertLocalDateToInstant(satellite.getLaunchDate()))")
    SatelliteResponseDTO toResponse(Satellite satellite);

    default Instant convertLocalDateToInstant(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
    }
}

