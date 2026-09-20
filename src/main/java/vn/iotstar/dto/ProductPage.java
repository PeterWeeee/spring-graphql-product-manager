package vn.iotstar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iotstar.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPage {
    private List<Product> content;
    private Integer totalPages;
    private Integer totalElements;
    private Integer currentPage;
    private Integer pageSize;
}
