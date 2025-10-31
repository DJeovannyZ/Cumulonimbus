package sv.ues.cpn135.Cumulonimbus.service;

/**
 * S3Service
 */
public interface S3Service {

  public void uploadFile(String keyName, String filePath);

}
