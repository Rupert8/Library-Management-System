package learn.store.entities;


import jakarta.persistence.*;
import learn.store.extendedInfo.Status;
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
@Table(name = "leans", schema = "library")
public class Lean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User user;

    @Column(name = "loan_date", nullable = false)
    @Builder.Default
    private LocalDate loanDate = LocalDate.now();

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
