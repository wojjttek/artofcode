package pl.sztukakodu.works.tags.boundary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sztukakodu.works.tags.entity.Tag;

public interface TagsRepository extends JpaRepository<Tag, Long> {
}