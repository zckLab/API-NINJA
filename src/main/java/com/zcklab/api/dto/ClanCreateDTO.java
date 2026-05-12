package com.zcklab.api.dto;

import java.util.List;

public record ClanCreateDTO(
        String name,
        Long population,
        List<Long> ninjaIds
) {
}
