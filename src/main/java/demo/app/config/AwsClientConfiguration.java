package demo.app.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by itaesu on 01/08/2019.
 */
@Configuration
public class AwsClientConfiguration {

    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard()
                                    .withCredentials(new ProfileCredentialsProvider())
                                    .withRegion(Regions.AP_NORTHEAST_2).build();
    }

    @Bean
    public TransferManager transferManager() {
        return TransferManagerBuilder.standard()
                                     .withS3Client(amazonS3Client())
                                     .build();
    }
}
