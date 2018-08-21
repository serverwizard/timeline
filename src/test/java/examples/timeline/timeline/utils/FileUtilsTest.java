package examples.timeline.timeline.utils;

import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

public class FileUtilsTest {
    @Test
    public void 디렉토리_생성() {
        String path = LocalDate.now().toString().replace("-", "") + File.separator;
        FileUtils.makeDirs(path + "jongwanhong");
    }
}