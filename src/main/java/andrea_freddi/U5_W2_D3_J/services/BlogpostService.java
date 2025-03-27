package andrea_freddi.U5_W2_D3_J.services;

import andrea_freddi.U5_W2_D3_J.entities.Blogpost;
import andrea_freddi.U5_W2_D3_J.exceptions.NotFoundException;
import andrea_freddi.U5_W2_D3_J.payloads.BlogpostsPayload;
import andrea_freddi.U5_W2_D3_J.repositories.BlogpostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogpostService {
    @Autowired
    private BlogpostsRepository blogpostsRepository;

    public Blogpost save(BlogpostsPayload blogpost) {
        Blogpost newBlogpost = new Blogpost(blogpost.getCategory(), blogpost.getContent(), "https://picsum.photos/200/300", blogpost.getReadingTime(), blogpost.getTitle());
        blogposts.add(newBlogpost);
        return newBlogpost;
    }

    public Page<Blogpost> getBlogposts(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogpostsRepository.findAll(pageable);
    }

    public Blogpost findById(UUID id) {
        Blogpost found = null;
        for (Blogpost blogpost : blogposts) {
            if (blogpost.getId().equals(id))
                found = blogpost;
        }
        if (found == null)
            throw new NotFoundException(String.valueOf(id));
        return found;
    }

    public void findByIdAndDelete(UUID id) {
        Blogpost found = null;
        for (Blogpost blogpost : blogposts) {
            if (blogpost.getId().equals(id))
                found = blogpost;
        }
        if (found == null)
            throw new NotFoundException(String.valueOf(id));
        blogposts.remove(found);
    }

    public Blogpost findByIdAndUpdate(UUID id, BlogpostsPayload blogpost) {
        Blogpost found = null;
        for (Blogpost currentBlogpost : blogposts) {
            if (currentBlogpost.getId().equals(id)) {
                found = currentBlogpost;
                found.setCategory(blogpost.getCategory());
                found.setContent(blogpost.getContent());
                found.setReadingTime(blogpost.getReadingTime());
            }
        }
        if (found == null)
            throw new NotFoundException(String.valueOf(id));
        return found;
    }
}
