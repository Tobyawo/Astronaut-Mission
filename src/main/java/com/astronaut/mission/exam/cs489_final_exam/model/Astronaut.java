package com.astronaut.mission.exam.cs489_final_exam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"satellites"})
@AllArgsConstructor
@NoArgsConstructor
public class Astronaut {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank @Size(min = 2, max = 20)
    private String lastName;

    @Min(0) @Max(50)
    private int experienceYears;

    @ManyToMany
    @JoinTable(
            name = "astronaut_satellite",
            joinColumns = @JoinColumn(name = "astronaut_id"),
            inverseJoinColumns = @JoinColumn(name = "satellite_id")
    )
    private Set<Satellite> satellites = new HashSet<>();



    public Astronaut(String firstName, String lastName, int experienceYears, Set<Satellite> satellites) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experienceYears = experienceYears;
        this.satellites = satellites;
    }
}
