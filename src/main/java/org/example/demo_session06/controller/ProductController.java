package org.example.demo_session06.controller;

import org.example.demo_session06.model.dto.request.ProductCreateDTO;
import org.example.demo_session06.model.dto.response.ProductResponse;
import org.example.demo_session06.model.dto.response.ProductResponseDTO;
import org.example.demo_session06.model.entity.Product;
import org.example.demo_session06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findAllResponse")
    public ResponseEntity<List<ProductResponse>> findAllResponse() {
        return new ResponseEntity<>(productService.findAllResponse(), HttpStatus.OK);
    }

    @GetMapping("/findAllResponseDTO")
    public  ResponseEntity<List<ProductResponseDTO>> findAllResponseDTO() {
        return new ResponseEntity<>(productService.findAllResponseDTO(),HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>> getAllPagination(@PageableDefault(page = 0 ,size = 10,
            direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(productService.findAllAndPagination(pageable),HttpStatus.OK);
    }

    @GetMapping("/paginationAndSort")
    public ResponseEntity<Page<Product>> findAllPaginationAndSort(@RequestParam("page") int page,
                                                                  @RequestParam("size") int size,
                                                                  @RequestParam("sortField") String sortField,
                                                                  @RequestParam("direction")  String direction) {
        return new ResponseEntity<>(productService.findAllPaginationAndSort(page,size,direction,sortField),HttpStatus.OK);
    }



    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam("keyword") String keyword){
        return new ResponseEntity<>(productService.findAllAndSearch(keyword), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.addProduct(productCreateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody  ProductCreateDTO productCreateDTO){
        return new ResponseEntity<>( productService.updateProduct(id , productCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
