package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;

import java.util.List;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {
    int countByName(String name);

    List<Star> findByStarTypeAndObserversIsEmptyOrderByLightYearsAsc(String redGiant);
}