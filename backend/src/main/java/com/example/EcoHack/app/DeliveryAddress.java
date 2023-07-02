package com.example.EcoHack.app;

import jakarta.persistence.*;

@Entity
@Table(name = "DeliveryAddress")
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String addressLine_1;
    String city;
    String state;
    String postalCode;
    String phoneNumber;
}
