package pl.sztukakodu.works.tags.boundary;

import org.springframework.data.repository.CrudRepository;
import pl.sztukakodu.works.tags.entity.Tag;

public interface TagsCrudRepository extends CrudRepository<Tag, Long> {
}
