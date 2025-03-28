package andrea_freddi.U5_W2_D3_J.services;

import andrea_freddi.U5_W2_D3_J.entities.Author;
import andrea_freddi.U5_W2_D3_J.exceptions.BadRequestException;
import andrea_freddi.U5_W2_D3_J.exceptions.NotFoundException;
import andrea_freddi.U5_W2_D3_J.payloads.AuthorsPayload;
import andrea_freddi.U5_W2_D3_J.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorsRepository authorsRepository;

    public Author save(AuthorsPayload author) {
        authorsRepository.findByEmail(author.getEmail()).ifPresent(
                a -> {
                    throw new BadRequestException("Email " + author.getEmail() + " già in uso");
                }
        );
        Author newAuthor = new Author("https://ui-avatars.com/api/?name=" + author.getName()
                + "+" + author.getSurname(), author.getDateOfBirth(), author.getEmail(),
                author.getName(), author.getSurname());
        return authorsRepository.save(newAuthor);
    }

    public Page<Author> getAuthors(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorsRepository.findAll(pageable);
    }

    public Author findById(UUID id) {
        return this.authorsRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id)
        );
    }

    public void findByIdAndDelete(UUID id) {
        Author found = this.findById(id);
        this.authorsRepository.delete(found);
    }

    public Author findByIdAndUpdate(UUID id, AuthorsPayload author) {
        Author found = this.findById(id);
        if (!found.getEmail().equals(author.getEmail())) {
            this.authorsRepository.findByEmail(author.getEmail()).ifPresent(
                    a -> {
                        throw new BadRequestException("Email " + author.getEmail() + " già in uso");
                    }
            );
        }
        found.setName(author.getName());
        found.setSurname(author.getSurname());
        found.setEmail(author.getEmail());
        found.setDateOfBirth(author.getDateOfBirth());
        found.setAvatar("https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname());

        return this.authorsRepository.save(found);
    }
}
