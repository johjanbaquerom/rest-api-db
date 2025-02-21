package com.application.rest.controller.dto;

import com.application.rest.entity.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FabricanteDTO {

    private Long id;
    private String name;
    private List<Producto> poductoList = new ArrayList<>();
}
