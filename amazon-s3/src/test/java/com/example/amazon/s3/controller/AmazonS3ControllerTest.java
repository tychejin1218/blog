package com.example.amazon.s3.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("local")
class AmazonS3ControllerTest {

  @Autowired
  MockMvc mockMvc;

  @DisplayName("uploadFiles_S3로 파일 업로드")
  @Test
  public void testUploadFiles() throws Exception {

    // Given
    String name = "files";
    String contentType = "text/plain";
    String path = "src/main/resources/static/temp";
    String fileType = "temp";
    String originalFileName01 = "temp01.txt";
    String originalFileName02 = "temp02.txt";
    String originalFileName03 = "temp03.txt";

    MockMultipartFile multipartFile01 = new MockMultipartFile(
        name,
        originalFileName01,
        contentType,
        new FileInputStream(path + "/" + originalFileName01));

    MockMultipartFile multipartFile02 = new MockMultipartFile(
        name,
        originalFileName02,
        contentType,
        new FileInputStream(path + "/" + originalFileName02));

    MockMultipartFile multipartFile03 = new MockMultipartFile(
        name,
        originalFileName03,
        contentType,
        new FileInputStream(path + "/" + originalFileName03));

    // When
    ResultActions resultActions = mockMvc.perform(
        multipart("/uploads")
            .file(multipartFile01)
            .file(multipartFile02)
            .file(multipartFile03)
            .param("fileType", fileType)
    );

    // Then
    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].originalFileName").value(originalFileName01))
        .andExpect(jsonPath("$[0].uploadFileName").isNotEmpty())
        .andExpect(jsonPath("$[0].uploadFilePath").isNotEmpty())
        .andExpect(jsonPath("$[0].uploadFileUrl").isNotEmpty())
        .andExpect(jsonPath("$[1].originalFileName").value(originalFileName02))
        .andExpect(jsonPath("$[1].uploadFileName").isNotEmpty())
        .andExpect(jsonPath("$[1].uploadFilePath").isNotEmpty())
        .andExpect(jsonPath("$[1].uploadFileUrl").isNotEmpty())
        .andExpect(jsonPath("$[2].originalFileName").value(originalFileName03))
        .andExpect(jsonPath("$[2].uploadFileName").isNotEmpty())
        .andExpect(jsonPath("$[2].uploadFilePath").isNotEmpty())
        .andExpect(jsonPath("$[2].uploadFileUrl").isNotEmpty())
        .andDo(print());
  }
}