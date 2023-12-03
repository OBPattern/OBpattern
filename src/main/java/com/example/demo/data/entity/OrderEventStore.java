package com.example.demo.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="order_event_store")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventStore implements Serializable {

    @Id
    @Column(name="order_id"/*, columnDefinition ="NUMBER(19) NOT NULL"*/)
    private Long id;

    @Column(name="order_details" /*, columnDefinition = "VARCHAR2(40)"*/)
    private String orderDetails;

    @Column(name="event_action" /*, columnDefinition = "VARCHAR2(40)"*/)
    private String eventAction;

    @Column(name="created_date"/*, columnDefinition = "DATE NOT NULL"*/)
    private Timestamp createdDate;

    @Column(name="modified_date"/*, columnDefinition = "DATE NOT NULL"*/)
    private Timestamp modifiedDate;

}
