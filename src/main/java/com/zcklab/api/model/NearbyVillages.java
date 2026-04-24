package com.zcklab.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class NearbyVillages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String VillageName;
    private Long population;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ninja ninja;
}
