package com.application.rest.controller.dto;

import com.application.rest.entity.Fabricante;
import com.application.rest.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private Long id;
    private String name;
    private BigDecimal precio;
    private Fabricante fabricante;
}
