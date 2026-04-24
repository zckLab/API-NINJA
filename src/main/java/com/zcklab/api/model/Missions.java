package com.zcklab.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Missions { // Many missions can be assigned to a single ninja

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Think about it, if we only want to view the missions,
    // it would be inconvenient to show all the ninjas attached to them. Nobody asked for that crap,
    // so Lazy Loading does it for us
    @ManyToOne(fetch = FetchType.LAZY)
    private Ninja ninja;
}
