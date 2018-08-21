package examples.timeline.timeline.dto;

import examples.timeline.timeline.domain.Review;
import examples.timeline.timeline.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class ReviewDto {
    private String contents;
    private MultipartFile image;
    private double starPoint;

    public ReviewDto(String contents, MultipartFile image, double starPoint) {
        this.contents = contents;
        this.image = image;
        this.starPoint = starPoint;
    }

    public Review toEntity(String url, User user) {
        return new Review(user, contents, url);
    }
}