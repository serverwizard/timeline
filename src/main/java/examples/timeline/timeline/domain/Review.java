package examples.timeline.timeline.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted != true")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"))
    private User user;

    @Column
    private String title;

    @Column
    @Lob
    private String contents;

    @Column
    private String imageUrl;

    @Column
    @ColumnDefault("false")
    private boolean isDeleted;

    @Column
    private LocalDateTime writtenTime;


    public Review(User user, String contents) {
        this.user = user;
        this.contents = contents;
        this.writtenTime = LocalDateTime.now();
    }

    public Review(User user, String contents, String imageUrl) {
        this.user = user;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.writtenTime = LocalDateTime.now();
    }
}