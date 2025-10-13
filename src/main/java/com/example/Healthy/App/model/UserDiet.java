package com.example.Healthy.App.model;
import com.example.Healthy.App.model.composite.UserDietID;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "UserDiet")
public class UserDiet {
    @EmbeddedId
    private UserDietID userDietID;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("dietId")
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @Column(name = "carbPercent")
    private int carbPercent;

    @Column(name = "fatPercent")
    private int fatPercent;

    @Column(name = "proteinPercent")
    private int proteinPercent;

}
