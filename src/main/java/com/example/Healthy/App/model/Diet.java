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
@Table(name = "Diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "carbPercent")
    private int carbPercent;

    @Column(name = "fatPercent")
    private int fatPercent;

    @Column(name = "proteinPercent")
    private int proteinPercent;

}
