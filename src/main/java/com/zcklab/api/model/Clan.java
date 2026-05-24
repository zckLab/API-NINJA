package com.zcklab.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Long population;

    @ManyToMany
    private List<Ninja> ninja_clans;

    // `cascadeType` is interesting; if we want it to happen automatically
    // when removing, refreshing, merging, or detaching operations, we use `CascadeType.ALL`,
    // or if it's just one of the previous ones, use that. The analogy is as follows: "If the father bird dies, its chicks also die;
    // if the chicks are separated from their father, they die," and `orphanRemoval = true` ensures that this happens automatically.

    @OneToMany(mappedBy="clan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Missions> missions;
}
