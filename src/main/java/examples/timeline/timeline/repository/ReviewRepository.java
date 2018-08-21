package examples.timeline.timeline.repository;

import examples.timeline.timeline.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}