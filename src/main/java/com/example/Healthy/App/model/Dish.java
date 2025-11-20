package com.example.Healthy.App.model;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "carb")
    private Double carb;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "fiber")
    private Double fiber;

    @Column(name = "foodPicture")
    private String imageUrl;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "des")
    private String des;

    @Column(name = "unit")
    private Unit unit;

    @Column(unique = true)
    private String barcode;

    @Column(name = "servingSize")
    private String servingSize;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<MealDetail> mealDetails;
}
