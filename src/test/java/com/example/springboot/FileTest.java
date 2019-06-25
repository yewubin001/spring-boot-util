package com.example.springboot;

import com.example.springboot.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileTest {
    @Test
    public void fileTest() throws IOException {
        String path ="C:/sign/磁金融App需求说明书_v1.5.docx";
        //byte[] bytes = FileUtils.getContent(path);
        FileUtils.toByteArray3(path);
    }
}
