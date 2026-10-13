package org.example.demo_session06.model.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import org.example.demo_session06.model.entity.Category;

public interface ProductResponse {
    long getId(); ;
    String getProductName() ;
    Double getPrice() ;

}
