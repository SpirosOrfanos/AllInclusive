package com.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.products.service.ProductDetailsService;

@RestController
@RequestMapping(value = "/api")
@Validated
public class ProductDetailsApi {
  
  @Autowired
  private ProductDetailsService productDetailsService;
  

  @GetMapping (
      value = {"/product/{id}/details"},
      path =  {"/product/{id}/details"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getProductDetails(
      @PathVariable(name = "id", value = "id", required = true) Long id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(productDetailsService.getDetailsForProduct(id));
  }
  @DeleteMapping (
      value = {"/product/{id}/details/{did}"},
      path =  {"/product/{id}/details/{did}"})
  public ResponseEntity deleteProductDetails(
      @PathVariable(name = "id", value = "id", required = true) Long id,
      @PathVariable(name = "did", value = "did", required = true) Long did) {
    productDetailsService.delete(did);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
  
  @PatchMapping (
      value = {"/product/{id}/details/{did}"},
      path =  {"/product/{id}/details/{did}"})
  public ResponseEntity updateProductDetails(
      @PathVariable(name = "id", value = "id", required = true) Long id,
      @PathVariable(name = "did", value = "did", required = true) Long did,
      @RequestBody ProductDetailsDto productDetailsDto) {
    productDetailsService.update(productDetailsDto, did);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
  

}
