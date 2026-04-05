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




   //1. Buscar todos agora retorna uma lista de ResponseDTO
    public List<NinjaResponseDTO> findAllNinjas(){
        return repositoryNinja.findAll()
                .stream()
                .map(NinjaResponseDTO::new) // Para cada Entity, chama o construtor do ResponseDTO
                .collect(Collectors.toList()); // Fecha a esteira e entrega a lista de ResponseDTOs
    }





    //2. Criar recebe o RequestDTO e retorna o ResponseDTO
    public NinjaResponseDTO createNinja(NinjaRequestDTO ninjaDTO){
        Ninja ninjaConvertido = new Ninja(ninjaDTO); // Converte o RequestDTO de entrada em Entity


        //Devolve o Ninja (agora com ID) para o Service, que o prepara para a saida
        return new NinjaResponseDTO(repositoryNinja.save(ninjaConvertido));
    }





    //delete com condicional
    public void deleteNinja(Long id) {
        if (!repositoryNinja.existsById(id)) {
            throw new RuntimeException("Ninja not found");
        }
        repositoryNinja.deleteById(id);
    }




    //3. Update recebe o RequestDTO
    public NinjaResponseDTO updateNinja(Long id, NinjaRequestDTO ninjaDTO) {
        Ninja ninjaExistente = repositoryNinja.findById(id)
                .orElseThrow(() -> new RuntimeException("Ninja not found"));

        // atualiza os dados da Entity com o que veio no DTO
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
