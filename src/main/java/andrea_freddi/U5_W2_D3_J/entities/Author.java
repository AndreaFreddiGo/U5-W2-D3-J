package andrea_freddi.U5_W2_D3_J.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    @Column(name = "author_id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String dateOfBirth;
    private String avatar;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Blogpost> blogpostsList;
    
    public Author(String avatar, String dateOfBirth, String email, String name, String surname) {
        this.avatar = avatar;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "avatar='" + avatar + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
