package com.trecapps.internal.storage.config;


import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Value("${azure.storage.blob-endpoint}")
    String endpoint;

    @Value("${azure.storage.account-name}")
    String accountName;

    @Value("${azure.storage.account-key}")
    String accountKey;

    @Bean
    public BlobServiceClient getBlobServiceClient() {

        BlobServiceClientBuilder builder = new BlobServiceClientBuilder();
        return builder.credential(new StorageSharedKeyCredential(accountName, accountKey)).
                endpoint(endpoint).buildClient();
    }
}
