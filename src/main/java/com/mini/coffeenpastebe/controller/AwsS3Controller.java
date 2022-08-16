package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.AwsS3;
import com.mini.coffeenpastebe.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/api/post/upload-image")
    public ResponseEntity<?> upload(@RequestPart("image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(awsS3Service.upload(multipartFile));
    }

    @DeleteMapping("/api/delete/upload-image")
    public void remove (AwsS3 awsS3) {
        awsS3Service.remove(awsS3);
    }

}
