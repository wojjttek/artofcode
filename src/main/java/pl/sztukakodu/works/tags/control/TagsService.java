package pl.sztukakodu.works.tags.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sztukakodu.works.exceptions.NotFoundException;
import pl.sztukakodu.works.tags.boundary.TagsCrudRepository;
import pl.sztukakodu.works.tags.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TagsService {
    private final TagsCrudRepository tagsCrudRepository;

    public Tag findById(Long id) {
        return tagsCrudRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag not found!"));
    }

    public Set<Tag> findAllById(List<Long> tagIds) {
        return StreamSupport.stream(tagsCrudRepository.findAllById(tagIds).spliterator(), false).collect(Collectors.toSet());

    }
}
