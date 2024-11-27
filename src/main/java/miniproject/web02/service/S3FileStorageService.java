package miniproject.web02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3FileStorageService {
    private final S3Client s3Client;
    public S3FileStorageService(S3Client s3Client) {
        this.s3Client = s3Client;
        System.out.println("S3Client successfully injected into S3FileStorageService"); // 디버깅 로그 추가
    }


    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region; // Region 정보를 추가로 가져옴

    public String uploadFile(MultipartFile file, String fileName) {
        try {


//            System.out.println("Bucket Name: "+bucketName); 디버그 로그 추가해놓은 부분
//            System.out.println("File Name: "+ fileName);
            // S3에 파일 업로드
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            // 업로드된 파일의 URL 반환
            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("S3 파일 업로드 중 오류 발생: " + e.getMessage(), e);
        }
    }

    public String generateFileName(MultipartFile file) {
        // 고유한 UUID를 기반으로 파일 이름을 생성
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }
}
