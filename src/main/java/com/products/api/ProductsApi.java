package com.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.products.dto.ProductDetailsDto;
import com.products.dto.ProductDto;
import com.products.service.ProductDetailsService;
import com.productsenums.ProductType;

@RestController
@RequestMapping(value = "/api")
@Validated
public class ProductsApi {
  
 
  
  
    @GetMapping(value = {"/product/{productType}"}, path = {"/product/{productType}"})
    public ResponseEntity<ProductDto> getProducts(
        @PathVariable(name = "productType", value = "productType", required = true) ProductType productType) {
    
      return ResponseEntity.status(HttpStatus.OK).build();
    }
   

}
