package com.astronaut.mission.exam.cs489_final_exam.exception;

public class AstronautNotFoundException extends RuntimeException {
    public AstronautNotFoundException(Long id) {
        super("Astronaut with ID " + id + " not found");
    }
}

