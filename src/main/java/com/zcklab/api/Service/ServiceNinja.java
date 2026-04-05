package com.zcklab.api.Service;


import com.zcklab.api.Model.Ninja;
import com.zcklab.api.Repository.RepositoryNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceNinja {

    private final RepositoryNinja repositoryNinja;




   //1. Fetch All now returns a list of ResponseDTO
    public List<NinjaResponseDTO> findAllNinjas(){
        return repositoryNinja.findAll()
                .stream() //conveyor
                .map(NinjaResponseDTO::new) // For each Entity, call the ResponseDTO constructor
                .collect(Collectors.toList()); // Close the conveyor belt and hand over the ResponseDTO list
    }





    //2. Create receives the RequestDTO and returns the ResponseDTO
    public NinjaResponseDTO createNinja(NinjaRequestDTO ninjaDTO){
        Ninja ninjaConvertido = new Ninja(ninjaDTO); // Converts input RequestDTO to Entity


        //Returns the Ninja (now with ID) to Service, which prepares it for exit
        return new NinjaResponseDTO(repositoryNinja.save(ninjaConvertido));
    }





    //3. delete with conditional
    public void deleteNinja(Long id) {
        if (!repositoryNinja.existsById(id)) {
            throw new RuntimeException("Ninja not found");
        }
        repositoryNinja.deleteById(id);
    }




    //4. Update receives RequestDTO
    public NinjaResponseDTO updateNinja(Long id, NinjaRequestDTO ninjaDTO) {
        Ninja ninjaExistente = repositoryNinja.findById(id)
                .orElseThrow(() -> new RuntimeException("Ninja not found"));

        // updates the Entity data with what came in the ninjaDTO
        ninjaExistente.setName(ninjaDTO.getName());
        ninjaExistente.setEmail(ninjaDTO.getEmail());
        ninjaExistente.setBirthDate(ninjaDTO.getBirthDate());
        ninjaExistente.setCategory(ninjaDTO.getCategory());
        ninjaExistente.setAbility(ninjaDTO.getAbility());
        ninjaExistente.setElementals(ninjaDTO.getElementals());
        ninjaExistente.setRank(ninjaDTO.getRank());
        ninjaExistente.setDescription(ninjaDTO.getDescription());

        return new NinjaResponseDTO(repositoryNinja.save(ninjaExistente));
    }
}
