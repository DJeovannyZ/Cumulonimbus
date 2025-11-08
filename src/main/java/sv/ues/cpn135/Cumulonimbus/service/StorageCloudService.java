package sv.ues.cpn135.Cumulonimbus.service;

/**
 * S3Service
 */
public interface StorageCloudService {

  public void uploadFile(String keyName, String filePath);

}
