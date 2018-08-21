package examples.timeline.timeline.web;

import examples.timeline.timeline.aws.S3Uploader;
import examples.timeline.timeline.dto.ReviewDto;
import examples.timeline.timeline.service.ReviewService;
import examples.timeline.timeline.utils.FileUtils;
import examples.timeline.timeline.utils.HttpSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileUploadController {
    private final S3Uploader s3Uploader;

    @Autowired
    private ReviewService reviewService;

    @ResponseBody
    @PostMapping("/upload")
    public JSONObject upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        log.info("multipartFile.getSize() : {}", multipartFile.getSize());

        if (multipartFile != null) {
            if (multipartFile.getSize() > FileUtils.MAXIMUM_SIZE_MB) {
                throw new ValidationException("업로드 가능한 이미지 최대 크기는 1MB 이상입니다.");
            }
        }

        // JSONObject 사용
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", s3Uploader.upload(multipartFile, String.format("static/%s", LocalDate.now().toString().replace("-", ""))));
        return jsonObject;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(ReviewDto reviewDto, HttpSession session) throws IOException {
        log.info("reviewDto : {}", reviewDto);
        String url = s3Uploader.upload(reviewDto.getImage(), "static");
        reviewService.create(reviewDto, url, HttpSessionUtils.getUserFromSession(session));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
