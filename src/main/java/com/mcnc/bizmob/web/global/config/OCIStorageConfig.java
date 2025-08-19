package com.mcnc.bizmob.web.global.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.SimpleAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;

@Configuration
@ConditionalOnProperty(prefix="storage.oci", name="enabled", havingValue="true")
public class OCIStorageConfig {
	
	@Value("${oci.object.storage.config.tenancy}")
	private String tenancyId;
	
	@Value("${oci.object.storage.config.user}")
	private String userId;
	
	@Value("${oci.object.storage.config.fingerprint}")
	private String fingerprint;
	
	@Value("${oci.object.storage.config.region}") 
	private String ociConfigRegion;
	
	@Value("${oci.object.storage.config.key-file}")
	private String keyFile;
	
	@Bean
    public ObjectStorage objectStorageClient(){
		try {
			Region region = Region.fromRegionId(ociConfigRegion);
			
			 Supplier<InputStream> privateKeySupplier = () -> {
	             try {
	          	   File file = ResourceUtils.getFile(keyFile);
	          	   return new FileInputStream(file);
	             } catch (Exception e) {
	                 throw new RuntimeException("Failed to load private key", e);
	             }
	         };
			
	        // 인증 정보 설정
	        SimpleAuthenticationDetailsProvider provider = SimpleAuthenticationDetailsProvider.builder()
	                .tenantId(tenancyId)
	                .userId(userId)
	                .fingerprint(fingerprint)
	                .privateKeySupplier(privateKeySupplier)
	                .region(region)
	                .build();
	        
	        // Object Storage 클라이언트 생성
	        ObjectStorageClient client = ObjectStorageClient.builder()
	                .build(provider); 
	        return client;
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
    }

	@Bean
	public UploadManager uploadManager(@Qualifier("objectStorageClient") ObjectStorage objectStorageClient) {
		UploadConfiguration uploadConfiguration = UploadConfiguration.builder()
				.allowMultipartUploads(false)
				.build();
		
		UploadManager uploadManager = new UploadManager(objectStorageClient, uploadConfiguration);
		
		return uploadManager;
	}
}
