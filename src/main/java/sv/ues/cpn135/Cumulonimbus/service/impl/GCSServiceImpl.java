
package sv.ues.cpn135.Cumulonimbus.service.impl;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import sv.ues.cpn135.Cumulonimbus.service.StorageCloudService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Primary
public class GCSServiceImpl implements StorageCloudService {

  private final Storage storage;

  @Value("${GCP_BUCKET_NAME}")
  private String bucketName;

  public GCSServiceImpl(Storage storage) {
    this.storage = storage;
  }

  @Override
  public void uploadFile(String objectName, String filePath) {
    try {
      Path path = Path.of(filePath);
      byte[] bytes = Files.readAllBytes(path);

      BlobId blobId = BlobId.of(bucketName, objectName);
      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

      storage.create(blobInfo, bytes);

      System.out.println("Archivo subido a GCS: " + objectName);
    } catch (Exception e) {
      System.err.println("Error subiendo archivo a GCS: " + e.getMessage());
      e.printStackTrace();

    }
  }

}
