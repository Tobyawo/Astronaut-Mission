package com.astronaut.mission.exam.cs489_final_exam.model;


import com.astronaut.mission.exam.cs489_final_exam.enums.OrbitType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"astronauts"})
public class Satellite {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate launchDate;

    @Enumerated(EnumType.STRING)
    private OrbitType orbitType;

    private boolean decommissioned;

    @ManyToMany(mappedBy = "satellites")
    private Set<Astronaut> astronauts = new HashSet<>();



    public Satellite(String name, LocalDate launchDate, OrbitType orbitType, boolean decommissioned) {
        this.name = name;
        this.launchDate = launchDate;
        this.orbitType = orbitType;
        this.decommissioned = decommissioned;
    }

}
