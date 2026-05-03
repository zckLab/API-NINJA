package com.zcklab.api.Controller;



import com.zcklab.api.handler.ParamsNotFoundException;
import com.zcklab.api.service.ServiceNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class NinjaController {

    private final ServiceNinja serviceNinja;


    // ResponseEntity is used to display HTTP protocols correctly.
    // Here are the 4 methods for a REST API.


    @GetMapping("/health") // GET /api/v1/users/health
    public ResponseEntity<String> Health(){

        return ResponseEntity.ok("API is running...");
    }


    // Here, we use the findAllNinjas method from the Service.
    // It gets all entities, transforms each one using the toResponseDTO method (to safely convert the model into a response),
    // and then returns everything as a list.
    @GetMapping // GET /api/v1/users
    public ResponseEntity<Page<NinjaResponseDTO>> getAllNinjas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int items)
    {
        return ResponseEntity.ok(serviceNinja.findAllNinjas(page, items));
    }






    @GetMapping // GET /api/v1/users
    public ResponseEntity<List<NinjaResponseDTO>> getNinjasByNameAndEmail(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        List<NinjaResponseDTO> result = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            result.add(serviceNinja.findByName(name));
        }

        if (email != null && !email.isEmpty()) {
            result.add(serviceNinja.findByEmail(email));
        }

        if (result.isEmpty()) {
            throw new ParamsNotFoundException("At least one parameter (name or email) must be provided");
        }

        return ResponseEntity.ok(result); //return 200


    }




    @GetMapping
    public ResponseEntity<List<String>> getAllEmails(){
        return ResponseEntity.ok(serviceNinja.findAllMails());
    }


    @GetMapping
    public ResponseEntity<Page<NinjaResponseDTO>> getActiveAdults(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int items
    ) {
        return ResponseEntity.ok(serviceNinja.activeAdults(page, items));
    }



    // Without the @Valid attribute, the request would be accepted without meeting the RequestDTO requirements.
    //We created a ResponseDTO that calls the createNinja method.
    // It receives the Request, uses toEntity to convert it to Entity, and then toResponse to transform it into ResponseDTO,
    // returning the result (This may seem confusing at first glance)
    @PostMapping // GET /api/v1/users
    public ResponseEntity<NinjaResponseDTO> createNinja(@RequestBody @Valid NinjaRequestDTO ninjaDTO){
        NinjaResponseDTO newNinja = serviceNinja.createNinja(ninjaDTO);
        return new ResponseEntity<>(newNinja, HttpStatus.CREATED); // Return 201
    }






    //Actually, this is the easiest one.
    //We just use a Void method (return nothing) and call deleteNinja
    @DeleteMapping("/{id}") // GET /api/v1/users/id
    public ResponseEntity<Void> deleteNinja(@PathVariable Long id){
        serviceNinja.deleteNinja(id);
        return ResponseEntity.noContent().build(); //Return 204 No Content (without this line,
                                                  // that would return 200 (not the correct one))
    }






    //We create the method updateNinja and use the both (@PathVariable and @RequestBody).
    //Retrun the status ok (200) and inside it the variable calls the updateNinja method with its respective values.
    @PutMapping("/{id}") // GET /api/v1/id
    public ResponseEntity<NinjaResponseDTO> updateNinja(@PathVariable Long id, @RequestBody @Valid NinjaRequestDTO ninjaRequestDTO){
        return ResponseEntity.ok(serviceNinja.updateNinja(id, ninjaRequestDTO)); // Return 200
    }


}