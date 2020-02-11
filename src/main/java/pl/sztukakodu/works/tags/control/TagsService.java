package pl.sztukakodu.works.tags.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sztukakodu.works.exceptions.NotFoundException;
import pl.sztukakodu.works.tags.boundary.TagsRepository;
import pl.sztukakodu.works.tags.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TagsService {
    private final TagsRepository tagsRepository;

    public Tag findById(Long id) {
        return tagsRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag not found!"));
    }

    public Set<Tag> findAllById(List<Long> tagIds) {
        return StreamSupport.stream(tagsRepository.findAllById(tagIds).spliterator(), false).collect(Collectors.toSet());

    }
}