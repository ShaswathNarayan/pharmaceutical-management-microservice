package com.pharmaceutical.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entity representing the medicine information.
 */
@Entity
@Table(name = "medicine_info")
public class MedicineInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "strength_volume", nullable = false)
    private String strengthVolume;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "min_quantity", nullable = false)
    private int minQuantity;

    @Column(name = "price", nullable = false)
    private double price;

    /**
     * Default constructor.
     */
    public MedicineInfo() {}

    /**
     * All-arguments constructor.
     *
     * @param id the ID of the medicine.
     * @param name the name of the medicine.
     * @param strengthVolume the strength or volume of the medicine.
     * @param type the type of the medicine.
     * @param availableQuantity the available quantity of the medicine.
     * @param minQuantity the minimum quantity of the medicine.
     * @param price the price of the medicine.
     */
    public MedicineInfo(Long id, String name, String strengthVolume, String type, int availableQuantity, int minQuantity, double price) {
        this.id = id;
        this.name = name;
        this.strengthVolume = strengthVolume;
        this.type = type;
        this.availableQuantity = availableQuantity;
        this.minQuantity = minQuantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrengthVolume() {
        return strengthVolume;
    }

    public void setStrengthVolume(String strengthVolume) {
        this.strengthVolume = strengthVolume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MedicineInfo [id=" + id + ", name=" + name + ", strengthVolume=" + strengthVolume + ", type=" + type 
               + ", availableQuantity=" + availableQuantity + ", minQuantity=" + minQuantity
               + ", price=" + price + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineInfo that = (MedicineInfo) o;
        return availableQuantity == that.availableQuantity && minQuantity == that.minQuantity
                && Double.compare(that.price, price) == 0 && Objects.equals(id, that.id)
                && Objects.equals(name, that.name) && Objects.equals(strengthVolume, that.strengthVolume)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, strengthVolume, type, availableQuantity, minQuantity, price);
    }
}
