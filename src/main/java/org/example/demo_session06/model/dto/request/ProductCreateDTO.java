package org.example.demo_session06.model.dto.request;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.demo_session06.model.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateDTO {

    private String productName ;
    private Double price ;
    private Long categoryId ;
}
