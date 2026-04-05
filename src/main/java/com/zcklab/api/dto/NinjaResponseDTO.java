package com.zcklab.api.dto;


import com.zcklab.api.Enums.Ability;
import com.zcklab.api.Enums.Category;
import com.zcklab.api.Enums.Elementals;
import com.zcklab.api.Enums.Rank;
import com.zcklab.api.Model.Ninja;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class NinjaResponseDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;



    private Category category;
    private Ability ability;


    private Elementals elementals;


    private Rank rank;

    private String description;

    public NinjaResponseDTO(Ninja ninja) {
        BeanUtils.copyProperties(ninja,this);
    }
}
