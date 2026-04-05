package com.zcklab.api.Controller;


import com.zcklab.api.Model.Ninja;
import com.zcklab.api.Service.ServiceNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
@AllArgsConstructor
public class NinjaController {

    private final ServiceNinja serviceNinja;


    @GetMapping("/listninjas")
    public ResponseEntity<List<NinjaResponseDTO>> getAllNinjas(){
        return ResponseEntity.ok(serviceNinja.findAllNinjas());
    }

    @PostMapping("/registerninja")
    public ResponseEntity<NinjaResponseDTO> createNinja(@RequestBody @Valid NinjaRequestDTO ninjaDTO){
        NinjaResponseDTO novoNinja = serviceNinja.createNinja(ninjaDTO);
        return new ResponseEntity<>(novoNinja, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNinja(@PathVariable Long id){
        serviceNinja.deleteNinja(id);
        return ResponseEntity.noContent().build(); //Return 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<NinjaResponseDTO> updateNinja(@PathVariable Long id, @RequestBody @Valid NinjaRequestDTO ninjaRequestDTO){
        return ResponseEntity.ok(serviceNinja.updateNinja(id, ninjaRequestDTO));
    }

}
