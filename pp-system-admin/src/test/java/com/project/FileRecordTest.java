package com.project;

import com.project.service.fileRecord.FileRecordService;
import com.project.service.tableFile.TableFileService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileRecordTest {
    @Autowired
    private FileRecordService fileRecordService;
    @Autowired
    private TableFileService tableFileService;
}
