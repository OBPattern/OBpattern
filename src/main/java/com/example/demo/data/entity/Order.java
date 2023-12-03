package com.example.demo.data.entity;

//import jakarta.persistence.Entity;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="order_table")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(name="order_id"/*, columnDefinition ="NUMBER(19) NOT NULL"*/)
    private Long id;

    @Column(name="product_name" /*, columnDefinition = "VARCHAR2(40)"*/)
    private String productName;

    @Column(name="order_status"/*, columnDefinition = "VARCHAR2(40)"*/)
    private String orderStatus;

    @Column(name="created_date"/*, columnDefinition = "DATE NOT NULL"*/)
    private Timestamp createdDate;

    @Column(name="modified_date"/*, columnDefinition = "DATE NOT NULL"*/)
    private Timestamp modifiedDate;
}
