package com.example.demoLog4j.api;

import com.example.demoLog4j.dto.ProductDto;
import com.example.demoLog4j.service.ProductService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApi {
    private Logger logger = LogManager.getLogger(ProductApi.class);
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDto> results = productService.getAllProducts();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        ProductDto productDto = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> addOrUpdateProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errors.add(error.getField() + ":" + error.getDefaultMessage());
                logger.error("Bad request: " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ProductDto product = productService.saveProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam String keyword){
        List<ProductDto> result = productService.searchProduct(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
