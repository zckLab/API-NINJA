package com.zcklab.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Missions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String MissionName;
    private String MissionDescription;

    @ManyToOne // Many missions can be assigned to a single ninja
    private Ninja ninja;
}
