package com.zcklab.api.model;


import com.zcklab.api.enums.*;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;


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

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "usrPasswd", nullable = false, length = 100)
    private String password;

    @Column(name = "usrEmail", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "usrCategory", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @OneToMany(mappedBy = "ninjaMissions") //one ninja can have a lot of missions
    private List<Missions>  missions;


    @Column(nullable = false)
    @ManyToMany(mappedBy = "ninjaClans")
    private List<Clan> clans;



}