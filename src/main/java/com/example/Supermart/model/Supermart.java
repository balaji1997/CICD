package com.example.Supermart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Supermarts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Supermart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String productName;
    private double price;

}
