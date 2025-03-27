package andrea_freddi.U5_W2_D3_J.repositories;

import andrea_freddi.U5_W2_D3_J.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogpostsRepository extends JpaRepository<Blogpost, UUID> {
}
