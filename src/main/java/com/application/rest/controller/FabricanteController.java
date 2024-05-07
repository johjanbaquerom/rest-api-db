package com.application.rest.controller;

import com.application.rest.controller.dto.FabricanteDTO;
import com.application.rest.entity.Fabricante;
import com.application.rest.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maker")
public class FabricanteController {

    @Autowired
    private IFabricanteService fabricanteService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Fabricante> fabricanteOptional = fabricanteService.findById(id);

        if(fabricanteOptional.isPresent()){
            Fabricante fabricante = fabricanteOptional.get();
            //se combiente la enrtidad fabricante a fabricanteDto por buenas practicas y se envia el dto

            FabricanteDTO fabricanteDTO = FabricanteDTO.builder()
                    .id(fabricante.getId())
                    .name(fabricante.getName())
                    .poductoList(fabricante.getPoductoList())
                    .build();

            return ResponseEntity.ok(fabricanteDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<FabricanteDTO> fabricanteList = fabricanteService.findAll()
                .stream()
                .map(fabricante -> FabricanteDTO.builder()
                        .id(fabricante.getId())
                        .name(fabricante.getName())
                        .poductoList(fabricante.getPoductoList())
                        .build())
                .toList();
        return ResponseEntity.ok(fabricanteList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody FabricanteDTO fabricanteDTO) throws URISyntaxException {

        if(fabricanteDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        fabricanteService.save(Fabricante.builder()
                .name(fabricanteDTO.getName())
                .build());

        return ResponseEntity.created(new URI("/api/maker/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFabricante(@PathVariable Long id, @RequestBody  FabricanteDTO fabricanteDTO){

        Optional<Fabricante> fabricanteOptional = fabricanteService.findById(id);

        if(fabricanteOptional.isPresent()){
            Fabricante fabricante = fabricanteOptional.get();
            fabricante.setName(fabricanteDTO.getName());
            fabricanteService.save(fabricante);
            return ResponseEntity.ok("Registro Actualizado");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        if(id != null){
            fabricanteService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
