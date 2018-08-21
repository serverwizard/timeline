package examples.timeline.timeline.service;

import examples.timeline.timeline.domain.User;
import examples.timeline.timeline.dto.ReviewDto;
import examples.timeline.timeline.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public void create(ReviewDto reviewDto, String url, User loginUser) {
        reviewRepository.save(reviewDto.toEntity(url, loginUser));
    }

}