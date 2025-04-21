package com.astronaut.mission.exam.cs489_final_exam.exception;

public class SatelliteNotFoundException extends RuntimeException {
    public SatelliteNotFoundException(Long id) {
        super("Satellite with ID " + id + " not found");
    }
}

