package com.zcklab.api.dto;

public record NearbyVillageDTO(
        Long id,
        String VillageName,
        Long population
) {}