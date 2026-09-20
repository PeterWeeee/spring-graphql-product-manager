package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "productName", columnDefinition = "nvarchar(500) not null")
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unitPrice", nullable = false)
    private Double unitPrice;

    @Column(name = "images", columnDefinition = "nvarchar(500)")
    private String images;

    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Column(name = "status", nullable = false)
    private Short status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;
}
