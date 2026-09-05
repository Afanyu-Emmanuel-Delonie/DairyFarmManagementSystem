package com.dairyfarm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cattle")
public class Cattle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cattle_id")
    private Long id;

    @NotNull(message = "Tag number is required")
    @Size(min = 3, max = 20, message = "Tag number must be between 3 and 20 characters")
    @Column(name = "tag_number", nullable = false, unique = true)
    private String tagNumber;

    @NotBlank(message = "Breed is required")
    @Column(name = "breed", nullable = false)
    private String breed;

    @NotNull(message = "Date of birth is required")
    @PastOrPresent(message = "Date of birth cannot be in the future")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Health status is required")
    @Column(name = "health_status", nullable = false)
    private String healthStatus;

    @OneToMany(mappedBy = "cattle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MilkProduction> milkRecords;

    // Constructors
    public Cattle() {}

    public Cattle(String tagNumber, String breed, LocalDate dateOfBirth, String healthStatus) {
        this.tagNumber = tagNumber;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.healthStatus = healthStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTagNumber() { return tagNumber; }
    public void setTagNumber(String tagNumber) { this.tagNumber = tagNumber; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }

    public List<MilkProduction> getMilkRecords() { return milkRecords; }
    public void setMilkRecords(List<MilkProduction> milkRecords) { this.milkRecords = milkRecords; }
}