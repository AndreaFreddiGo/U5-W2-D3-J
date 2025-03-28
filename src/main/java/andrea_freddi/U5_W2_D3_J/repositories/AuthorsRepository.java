package andrea_freddi.U5_W2_D3_J.repositories;

import andrea_freddi.U5_W2_D3_J.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);
}
