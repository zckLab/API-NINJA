package com.zcklab.api.Model;


import com.zcklab.api.Enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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


    @Column(name = "full_name", nullable = false, length = 20)
    @NotBlank(message = "Name is Required")
    private String name;


    @Column(name = "usr_email", nullable = false)
    @Email(message = "Invalid Email")
    private String email;


    @Column(name = "usr_birthDate", nullable = false)
    @Past // makes the date not today or in the future (correct use for past or today @PastOrPresent)
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