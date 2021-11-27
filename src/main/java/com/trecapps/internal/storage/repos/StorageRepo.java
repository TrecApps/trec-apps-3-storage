package com.trecapps.internal.storage.repos;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Repository
public class StorageRepo {

    BlobServiceClient client;

    @Autowired
    StorageRepo(BlobServiceClient client1)
    {
        client = client1;
    }

    public String getContent(String containerName, String blobName, boolean useBase64)  {
        BlobContainerClient containerClient = client.getBlobContainerClient(containerName);

        BlobClient client = containerClient.getBlobClient(blobName);

        BinaryData bData = client.downloadContent();

        byte[] data = bData.toBytes();

        return useBase64 ? Base64.getEncoder().encodeToString(data) : new String(data, StandardCharsets.UTF_8);
    }

    public void uploadContent(String containerName, String blobName, String content, boolean usesBase64) {
        BlobContainerClient containerClient = client.getBlobContainerClient(containerName);

        BlobClient client = containerClient.getBlobClient(blobName);

        byte[] bytes = usesBase64 ? Base64.getDecoder().decode(content) : content.getBytes(StandardCharsets.UTF_8);

        client.upload(BinaryData.fromBytes(bytes), true);
    }

    public void deleteContent(String containerName, String blobName) {
        BlobContainerClient containerClient = client.getBlobContainerClient(containerName);

        BlobClient client = containerClient.getBlobClient(blobName);

        client.delete();
    }
}
