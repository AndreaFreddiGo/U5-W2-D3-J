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
        Author newAuthor = new Author("https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname(), author.getDateOfBirth(), author.getEmail(), author.getName(), author.getSurname());
        authors.add(newAuthor);
        return newAuthor;
    }

    public Page<Author> getAuthors(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorsRepository.findAll(pageable);
    }

    public Author findById(UUID id) {
        Author found = null;
        for (Author author : authors) {
            if (author.getId().equals(id))
                found = author;
        }
        if (found == null)
            throw new NotFoundException(String.valueOf(id));
        return found;
    }

    public void findByIdAndDelete(UUID id) {
        Author found = null;
        for (Author author : authors) {
            if (author.getId().equals(id))
                found = author;
        }
        if (found == null)
            throw new NotFoundException(String.valueOf(id));
        authors.remove(found);
    }

    public Author findByIdAndUpdate(UUID id, AuthorsPayload author) {
        Author found = null;
        for (Author currentAuthor : authors) {
            if (currentAuthor.getId().equals(id)) {
                found = currentAuthor;
                if (!found.getEmail().equals(author.getEmail())) {
                    for (Author current : authors)
                        if (current.getEmail().equals(author.getEmail()))
                            throw new BadRequestException("Email + " + author.getEmail() + " già in uso");
                }
                found.setName(author.getName());
                found.setSurname(author.getSurname());
                found.setEmail(author.getEmail());
                found.setDateOfBirth(author.getDateOfBirth());
                found.setAvatar("https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname());
            }
        }
        if (found == null)
            throw new NotFoundException(String.valueOf(id));
        return found;
    }
}
