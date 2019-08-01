package demo.app.file.infra.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.transfer.model.UploadResult;
import demo.app.file.domain.model.DomainFile;
import demo.app.file.domain.repository.DomainFileRepository;
import demo.app.file.infra.BackgroundFileUploadTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by itaesu on 01/08/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class S3BackgroundFileUploadTask implements BackgroundFileUploadTask {
    private final TransferManager transferManager;
    private final DomainFileRepository domainFileRepository;

    @Override
    public void transfer(DomainFile domainFile) {
        domainFile.toProcessing();

        final File file = new File(domainFile.getDiskLocation());
        try {
            final Upload upload = this.transferManager.upload("demo-mail-attach", domainFile.getId(), file);
            final UploadResult uploadResult = upload.waitForUploadResult();
            log.info("upload success {}", uploadResult.getKey());

            domainFile.onSuccessToUpload(uploadResult.getBucketName()+"/"+uploadResult.getKey());
            this.domainFileRepository.save(domainFile);

        } catch (AmazonServiceException e) {
            log.error("파일 전송 요청 중 에러 {}", e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error("파일 전송 대기 중 에러 {}", e.getMessage(), e);
        }
    }
}
