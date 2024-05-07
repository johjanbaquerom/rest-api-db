package com.application.rest.controller;

import com.application.rest.controller.dto.FabricanteDTO;
import com.application.rest.controller.dto.ProductoDTO;
import com.application.rest.entity.Fabricante;
import com.application.rest.entity.Producto;
import com.application.rest.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/find/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Producto> productoOptional = productoService.findById(id);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            //se combiente la enrtidad fabricante a fabricanteDto por buenas practicas y se envia el dto

            ProductoDTO productoDTO = ProductoDTO.builder()
                    .id(producto.getId())
                    .name(producto.getName())
                    .precio(producto.getPrecio())
                    .fabricante(producto.getFabricante())
                    .build();

            return ResponseEntity.ok(productoDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ProductoDTO> productoList = productoService.findAll()
                .stream()
                .map(producto -> ProductoDTO.builder()
                        .id(producto.getId())
                        .name(producto.getName())
                        .precio(producto.getPrecio())
                        .fabricante(producto.getFabricante())
                        .build())
                .toList();
        return ResponseEntity.ok(productoList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) throws URISyntaxException {

        if(productoDTO.getName().isBlank() || productoDTO.getPrecio() == null || productoDTO.getFabricante() == null){
            return ResponseEntity.badRequest().build();
        }

        Producto producto = (Producto.builder()
                .name(productoDTO.getName())
                .precio(productoDTO.getPrecio())
                .fabricante(productoDTO.getFabricante())
                .build());

        productoService.save(producto);

        return ResponseEntity.created(new URI("/api/product/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody  ProductoDTO productoDTO){

        Optional<Producto> productoOptional = productoService.findById(id);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            producto.setName(productoDTO.getName());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setFabricante(productoDTO.getFabricante());
            productoService.save(producto);

            return ResponseEntity.ok("Registro Actualizado");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        if(id != null){
            productoService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
