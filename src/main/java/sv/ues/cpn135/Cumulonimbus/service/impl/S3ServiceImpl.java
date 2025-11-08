package sv.ues.cpn135.Cumulonimbus.service.impl;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import sv.ues.cpn135.Cumulonimbus.service.StorageCloudService;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class S3ServiceImpl implements StorageCloudService {

  private final S3Client s3Client;

  @Value("${AWS_BUCKET_NAME}")
  private String bucketName;

  public S3ServiceImpl(S3Client s3Client) {
    this.s3Client = s3Client;
  }

  public void uploadFile(String keyName, String filePath) {
    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(keyName)
        .build();

    PutObjectResponse response = s3Client.putObject(putObjectRequest, Paths.get(filePath));
    System.out.println("Archivo subido a s3: " + response.eTag());
  }
}
