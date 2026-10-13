package org.example.demo_session06.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseDTO {
    private long id; ;
    private String productName ;
    private Double price ;
}
