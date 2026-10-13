package org.example.demo_session06.service;

import org.example.demo_session06.model.dto.request.ProductCreateDTO;
import org.example.demo_session06.model.dto.response.ProductResponse;
import org.example.demo_session06.model.dto.response.ProductResponseDTO;
import org.example.demo_session06.model.entity.Category;
import org.example.demo_session06.model.entity.Product;
import org.example.demo_session06.repository.CategoryRepository;
import org.example.demo_session06.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    protected CategoryRepository categoryRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<ProductResponse> findAllResponse() {
        return productRepository.findAllResponse();
    }

    public List<ProductResponseDTO> findAllResponseDTO() {
        return productRepository.findAllResponseDTO();
    }

    public ResponseEntity<?> addProduct(ProductCreateDTO productCreateDTO){
        Category category = categoryRepository.findById(productCreateDTO.getCategoryId()).orElse(null);
        if (category == null){
            return new ResponseEntity<>("Không tìm thấy category có id : " + productCreateDTO.getCategoryId(), HttpStatus.NOT_FOUND);
        }
        Product newProduct = new Product();
        newProduct.setProductName(productCreateDTO.getProductName());
        newProduct.setPrice(productCreateDTO.getPrice());
        newProduct.setCategory(category);
       return new ResponseEntity<>(productRepository.save(newProduct), HttpStatus.CREATED);
    }

    public Product updateProduct(Long id , ProductCreateDTO productCreateDTO){
        Category category = categoryRepository.findById(productCreateDTO.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy category"));
        Product oddProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tìm thấy sản phẩm có id : " + id));
        oddProduct.setProductName(productCreateDTO.getProductName());
        oddProduct.setPrice(productCreateDTO.getPrice());
        oddProduct.setCategory(category);
        return productRepository.save(oddProduct);

    }

    public List<Product> findAllAndSearch(String keyword){
        return productRepository.findProductByNameOrCategory(keyword);
    }

    public Page<Product> findAllAndPagination(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAllPaginationAndSort(int page , int size ,String direction,String sortField){
        Sort sorted = direction.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, sortField) : Sort.by(Sort.Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(page, size, sorted);
        return productRepository.findAll(pageable);
    }

    public String deleteProduct(Long id){
        Product oddProduct = productRepository.findById(id).orElse(null);
        if(oddProduct == null){
            return "Id sản phẩm không tồn tại";
        }
        productRepository.delete(oddProduct);
        return "Xóa sản phẩm thành công";

    }
}
