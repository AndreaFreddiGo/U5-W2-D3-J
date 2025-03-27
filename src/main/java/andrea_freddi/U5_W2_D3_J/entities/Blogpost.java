package andrea_freddi.U5_W2_D3_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    @Column(name = "blogpost_id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    private String cover;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int readingTime;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Blogpost(String category, String content, String cover, int readingTime, String title) {
        this.category = category;
        this.content = content;
        this.cover = cover;
        this.readingTime = readingTime;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Blogpost{" +
                "author=" + author +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", id=" + id +
                ", readingTime=" + readingTime +
                ", title='" + title + '\'' +
                '}';
    }
}
