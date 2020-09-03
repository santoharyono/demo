package com.santo.demo.controller;

import com.santo.demo.model.ProductType;
import com.santo.demo.response.ProductTypeResponse;
import com.santo.demo.service.ProductTypeService;
import com.santo.demo.viewModel.ProductViewModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productType")
public class ProductTypeController {

    @Autowired
    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @ApiOperation(value = "get all product type", response = ResponseEntity.class)
    @GetMapping
    public ResponseEntity<ProductTypeResponse<List<ProductType>>> getAllProductType() {
        List<ProductType> result = productTypeService.getAllProductType();
        ProductTypeResponse<List<ProductType>> response = new ProductTypeResponse<List<ProductType>>();
        response.setMessage("get product type list");
        response.setBody(result);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "get product type by id", response = ResponseEntity.class)
    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeResponse<ProductType>> getProductType(@PathVariable Long id) {
        Optional<ProductType> result = productTypeService.getProductTypeById(id);
        ProductTypeResponse<ProductType> response = new ProductTypeResponse<ProductType>();
        if (result.isPresent()) {
            response.setMessage("get product type by id");
            response.setBody(result.get());
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @ApiOperation(value = "get product type by type name", response = ResponseEntity.class)
    @GetMapping("/{type}")
    public ResponseEntity<ProductTypeResponse<ProductType>> getProductTypeByTypename(@PathVariable String type) {
        Optional<ProductType> result = productTypeService.getProductTypeByTypename(type);
        ProductTypeResponse<ProductType> response = new ProductTypeResponse<ProductType>();
        if (result.isPresent()) {
            response.setMessage("get product type by typename");
            response.setBody(result.get());
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @ApiOperation(value = "create new product type", response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<ProductTypeResponse<ProductType>> createProductType(@RequestBody ProductViewModel productViewModel) {
        ProductType result = productTypeService.createProductType(productViewModel);
        ProductTypeResponse<ProductType> response = new ProductTypeResponse<ProductType>();
        if (result != null) {
            response.setMessage("create new product type");
            response.setBody(result);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("failed to create new product type");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @ApiOperation(value = "update product type", response = ResponseEntity.class)
    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable Long id, @RequestBody ProductViewModel productType) {
        return productTypeService.getProductTypeById(id)
                .map(productTypeObject -> {
                    productTypeObject.setId(id);
                    productTypeObject.setType(productType.getType());
                    productTypeObject.setUpdatedDate(new Date());
                    ProductType result = productTypeService.updateProductType(productTypeObject);
                    ProductTypeResponse<ProductType> response = new ProductTypeResponse<ProductType>();
                    response.setMessage("updated product type");
                    response.setBody(result);
                    return ResponseEntity.ok(response);
                })
                .orElse(failedToUpdate());
    }

    private ResponseEntity failedToUpdate() {
        ProductTypeResponse<ProductType> response = new ProductTypeResponse<ProductType>();
        response.setMessage("updated product type");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
