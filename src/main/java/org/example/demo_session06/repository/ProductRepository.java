package org.example.demo_session06.repository;

import org.example.demo_session06.model.dto.response.ProductResponse;
import org.example.demo_session06.model.dto.response.ProductResponseDTO;
import org.example.demo_session06.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductByProductNameOrCategory_Name(String productName, String categoryName);

    @Query(" from Product p where p.productName like concat('%',:keyword,'%') or p.category.name like  concat('%',:keyword,'%')")
    List<Product> findProductByNameOrCategory(@Param("keyword") String keyword);

    @Query("from Product ")
    List<ProductResponse> findAllResponse();

    @Query("select new org.example.demo_session06.model.dto.response.ProductResponseDTO(p.id,p.productName,p.price) from Product p")
    List<ProductResponseDTO> findAllResponseDTO();
}
