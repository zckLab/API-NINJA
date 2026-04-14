package com.zcklab.api.model;


import com.zcklab.api.enums.*;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;


@Entity
@Table(name="tb_ninjas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Ninja {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "full_name", nullable = false, length = 100)
    private String name;


    @Column(nullable = false, unique = true)
    private String cpf;


    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;


    @Column(name = "usr_birth_date", nullable = false)
    private LocalDate birthDate;


    @Column(name = "usr_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "usr_ability",  nullable = false)
    @Enumerated(EnumType.STRING)
    private Ability ability;


    @Column(name = "usr_elementals", nullable = false)
    @Enumerated(EnumType.STRING)
    private Elementals elementals;


    @Column(name = "usr_rank", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank rank;


    @Column(name = "usr_description", nullable = false, length = 120)
    private String description;

}