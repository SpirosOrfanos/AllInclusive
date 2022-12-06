package com.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.products.annotations.AllAuth;
import com.products.annotations.OnlyBoAuth;
import com.products.dto.ProductDetailsDto;
import com.products.dto.ProductResponseDto;
import com.products.enums.ProductType;
import com.products.service.ProductService;
import com.products.vo.RetrieveProductsRequest;

@RestController
@RequestMapping(value = "/api")
@Validated
public class ProductsApi {

  @Autowired
  private ProductService productService;

  @AllAuth
  @GetMapping(value = {"/product/{productType}"}, path = {"/product/{productType}"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductResponseDto> getProducts(
      @PathVariable(name = "productType", value = "productType",
          required = true) ProductType productType,
      @RequestParam(name = "page", required = false) Integer page,
      @RequestParam(name = "size", required = false) Integer size) {
    ProductResponseDto res = productService.getProducts(new RetrieveProductsRequest.Builder()
        .setPage(page).setSize(size).setType(productType.getId()).build());
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
  }

  @AllAuth
  @GetMapping(value = {"/product/{id}/details"}, path = {"/product/{id}/details"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getProductDetails(
      @PathVariable(name = "id", value = "id", required = true) Long id) {
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
        .body(productService.getProductDetails(id));
  }

  @OnlyBoAuth
  @DeleteMapping(value = {"/product/{id}/details/{did}"}, path = {"/product/{id}/details/{did}"})
  public ResponseEntity deleteProductDetails(
      @PathVariable(name = "id", value = "id", required = true) Long id,
      @PathVariable(name = "did", value = "did", required = true) Long did) {
    productService.deleteDetails(did);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @OnlyBoAuth
  @PatchMapping(value = {"/product/{id}/details/{did}"}, path = {"/product/{id}/details/{did}"})
  public ResponseEntity updateProductDetails(
      @PathVariable(name = "id", value = "id", required = true) Long id,
      @PathVariable(name = "did", value = "did", required = true) Long did,
      @RequestBody ProductDetailsDto productDetailsDto) {
    productService.updateProductDetails(productDetailsDto, did);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }


}
