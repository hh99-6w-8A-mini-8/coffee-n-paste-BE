package com.mini.coffeenpastebe.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mini.coffeenpastebe.domain.AwsS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public AwsS3 upload(MultipartFile multipartFile) throws IOException {
        File file = convertMultipartFileToFile(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));
        return upload(file);
    }

    private AwsS3 upload(File file) {
        String key = randomFileName(file); // UUID 를 사용해 고유한 key 값 생성
        String path = putS3(file, key);
        removeFile(file);

        return AwsS3.builder()
                .key(key)
                .img(path)
                .build();
    }

    private String randomFileName(File file) {
        return UUID.randomUUID() + file.getName();
    }

    private String putS3(File uploadFile, String fileName) {
        // 지정된 AmazonS3 버킷에 새 객체를 업로드한다.
        // 여기서 String fileName => private AwsS3 upload 의 key;
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)); // 미리 준비된 엑세스 제어 목록을 정의하는 상수를 지정
                                                                     // PublicRead 공개 읽기
                                                                     // 이 정책이 개체에 사용되면 인증 없이 브라우저에서 읽을 수 있습니다.

        return getS3(bucket, fileName); // S3 에 저장하고 업로드 된 이미지의 key 값과 path 를 반환한다.
                                        // key 값은 객체 이름이고, path 는 해당객체의 절대경로값이다.
    }

    private String getS3(String bucket, String fileName) { // String fileName 은 저장된 key 값.
        return amazonS3.getUrl(bucket, fileName).toString(); // amazonS3 bucket 에 저장된 이미지 URL 을 가져옴.
    }

    private void removeFile(File file) {
        file.delete();
    }

    // convertMultipartFileToFile
    public Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        // 새로운 파일을 만드는데.. 우리 시스템 현재 디렉토리 + "/" + multipartFile 로 들어온 이미지의 실제 file name
        File file = new File(System.getProperty("user.dir") + "/" + multipartFile.getOriginalFilename());

        // 빈 파일을 만들어 낼 때 사용한다.
        // .createNewFile은 Boolean 값을 반환하는데 같은 이름의 파일이 없으면 true, 있으면 false를 반환한다.
        if (file.createNewFile()) { // 이게 true 면
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(file);
        }
        return Optional.empty();
    }

    public void remove(AwsS3 awsS3) {
        if (!amazonS3.doesObjectExist(bucket, awsS3.getKey())) {
            throw new AmazonS3Exception("Object " +awsS3.getKey()+ " does not exist!");
        }
        amazonS3.deleteObject(bucket, awsS3.getKey());
    }
}
