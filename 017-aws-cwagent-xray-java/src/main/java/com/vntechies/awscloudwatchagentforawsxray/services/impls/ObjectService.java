package com.vntechies.awscloudwatchagentforawsxray.services.impls;

import com.vntechies.awscloudwatchagentforawsxray.constants.CredentialsConstant;
import com.vntechies.awscloudwatchagentforawsxray.services.IObjectService;
import java.nio.ByteBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class ObjectService implements IObjectService {

    @Autowired
    private S3Client s3Client;

    @Override
    public Object putObject(String key, byte[] bytes) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(CredentialsConstant.BUCKET_NAME)
                .key(key)
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(bytes));
        return null;
    }
}
