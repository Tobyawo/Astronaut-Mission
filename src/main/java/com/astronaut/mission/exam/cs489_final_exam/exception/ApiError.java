package com.astronaut.mission.exam.cs489_final_exam.exception;

import java.time.Instant;

public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {}

