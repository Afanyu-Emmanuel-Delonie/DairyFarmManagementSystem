package com.dairyfarm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "milk_production")
public class MilkProduction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "production_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cattle_id", nullable = false)
    @NotNull(message = "Cattle selection is required")
    private Cattle cattle;

    @NotNull(message = "Record date is required")
    @PastOrPresent(message = "Record date cannot be in the future")
    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @NotBlank(message = "Shift selection is required")
    @Pattern(regexp = "^(Morning|Evening)$", message = "Shift must be 'Morning' or 'Evening'")
    @Column(name = "shift", nullable = false)
    private String shift;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Milk quantity must be greater than or equal to 0")
    @Max(value = 100, message = "Milk quantity exceeds maximum realistic limit per shift (100L)")
    @Column(name = "quantity_liters", nullable = false)
    private Double quantityLiters;

    // Constructors
    public MilkProduction() {}

    public MilkProduction(Cattle cattle, LocalDate recordDate, String shift, Double quantityLiters) {
        this.cattle = cattle;
        this.recordDate = recordDate;
        this.shift = shift;
        this.quantityLiters = quantityLiters;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cattle getCattle() { return cattle; }
    public void setCattle(Cattle cattle) { this.cattle = cattle; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public Double getQuantityLiters() { return quantityLiters; }
    public void setQuantityLiters(Double quantityLiters) { this.quantityLiters = quantityLiters; }
}