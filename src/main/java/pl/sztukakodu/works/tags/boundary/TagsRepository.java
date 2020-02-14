package pl.sztukakodu.works.tags.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sztukakodu.works.tags.entity.Tag;

import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByNameContainingIgnoreCase(String name);
}