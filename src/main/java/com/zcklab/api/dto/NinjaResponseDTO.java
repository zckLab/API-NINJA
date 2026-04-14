package com.zcklab.api.dto;

import com.zcklab.api.enums.Ability;
import com.zcklab.api.enums.Category;
import com.zcklab.api.enums.Elementals;
import com.zcklab.api.enums.Rank;

import java.time.LocalDate;

public record NinjaResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        LocalDate birthDate,
        Category category,
        Ability ability,
        Elementals elementals,
        Rank rank,
        String description
){}
