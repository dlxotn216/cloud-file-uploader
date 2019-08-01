package demo.app.task;

import demo.app.file.infra.BackgroundFileUploadTask;
import demo.app.file.service.SearchEarliestDomainFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by itaesu on 01/08/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class ScheduledCloudFileUploadTask {
    private final SearchEarliestDomainFileService searchEarliestDomainFileService;
    private final BackgroundFileUploadTask backgroundFileUploadTask;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void uploadDiskFile() {
        log.info("search earliest uploaded file");
        this.searchEarliestDomainFileService.search()
                                            .forEach(this.backgroundFileUploadTask::transfer);
    }
}
