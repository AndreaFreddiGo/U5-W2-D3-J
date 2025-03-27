package andrea_freddi.U5_W2_D3_J.controllers;

import andrea_freddi.U5_W2_D3_J.entities.Author;
import andrea_freddi.U5_W2_D3_J.payloads.AuthorsPayload;
import andrea_freddi.U5_W2_D3_J.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    private AuthorService authorService;


    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Author save(@RequestBody AuthorsPayload body) {
        return authorService.save(body);
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorService.getAuthors(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{id}")
    public Author findById(@PathVariable UUID id) {
        return authorService.findById(id);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{id}")
    public Author findByIdAndUpdate(@PathVariable UUID id, @RequestBody AuthorsPayload body) {
        return authorService.findByIdAndUpdate(id, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204
    public void findByIdAndDelete(@PathVariable UUID id) {
        authorService.findByIdAndDelete(id);
    }
}
