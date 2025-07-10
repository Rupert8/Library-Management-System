package learn.store.entities;

import jakarta.persistence.*;
import learn.store.extendedInfo.UserPersonInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "library")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Embedded
    private UserPersonInfo personInfo;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Builder.Default
    private LocalDate registrationDate = LocalDate.now();
}
