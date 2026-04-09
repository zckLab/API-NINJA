package com.zcklab.api.dto;

import com.zcklab.api.Enums.Ability;
import com.zcklab.api.Enums.Category;
import com.zcklab.api.Enums.Elementals;
import com.zcklab.api.Enums.Rank;

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
