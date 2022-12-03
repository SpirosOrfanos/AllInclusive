package com.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.products.dto.ProductDto;
import com.products.dto.ProductResponseDto;
import com.products.service.ProductService;
import com.products.vo.RetrieveProductsRequest;
import com.productsenums.ProductType;

@RestController
@RequestMapping(value = "/api")
@Validated
public class ProductsApi {  
  
  @Autowired
  private ProductService productService;
  
    @GetMapping(
        value = {"/product/{productType}"}, 
        path = {"/product/{productType}"},
        produces = MediaType.APPLICATION_JSON_VALUE
        )
    public ResponseEntity<ProductResponseDto> getProducts(
        @PathVariable(name = "productType", value = "productType", required = true) ProductType productType,
        @RequestParam(name = "page", required = false)  Integer page,
        @RequestParam(name = "size", required = false)  Integer size) { 
      ProductResponseDto res = productService.getProducts(new RetrieveProductsRequest.Builder()
          .setPage(page)
          .setSize(size)
          .setType(productType.getId())
          .build());
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(res);
    }
   

}
