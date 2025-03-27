package andrea_freddi.U5_W2_D3_J.controllers;

import andrea_freddi.U5_W2_D3_J.entities.Blogpost;
import andrea_freddi.U5_W2_D3_J.payloads.BlogpostsPayload;
import andrea_freddi.U5_W2_D3_J.services.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogpostsController {
    @Autowired
    BlogpostService blogpostService;

    // 1. - POST http://localhost:3001/blogposts (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost save(@RequestBody BlogpostsPayload body) {
        return blogpostService.save(body);
    }

    // 2. - GET http://localhost:3001/blogposts
    @GetMapping
    public Page<Blogpost> getBlogposts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return blogpostService.getBlogposts(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/blogposts/{id}
    @GetMapping("/{id}")
    public Blogpost findById(@PathVariable UUID id) {
        return blogpostService.findById(id);
    }

    // 4. - PUT http://localhost:3001/blogposts/{id} (+ req.body)
    @PutMapping("/{id}")
    public Blogpost findByIdAndUpdate(@PathVariable UUID id, @RequestBody BlogpostsPayload body) {
        return blogpostService.findByIdAndUpdate(id, body);
    }

    // 5. - DELETE http://localhost:3001/blogposts/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204
    public void findByIdAndDelete(@PathVariable UUID id) {
        blogpostService.findByIdAndDelete(id);
    }
}
