package sv.ues.cpn135.Cumulonimbus.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class StorageCloudConfig {

  public S3Client s3Client(@Value("${AWS_ACCESS_KEY_ID}") String accessKey,
      @Value("${AWS_SECRET_ACCESS_KEY}") String secretKey,
      @Value("${AWS_REGION}") String region) {

    AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

    return S3Client.builder()
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .build();
  }

  @Bean
  public Storage storage(@Value("${GOOGLE_APPLICATION_CREDENTIALS}") String credentialsPath,
      @Value("${GCP_PROJECT_ID}") String projectId) throws IOException {

    return StorageOptions.newBuilder()
        .setProjectId(projectId)
        .setCredentials(
            com.google.auth.oauth2.ServiceAccountCredentials.fromStream(
                new FileInputStream(credentialsPath)))
        .build()
        .getService();
  }
}
