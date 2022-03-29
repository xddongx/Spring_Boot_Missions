package site.xddongx.community.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.community.entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
