package demo.app.file.infra;

import demo.app.file.domain.model.DomainFile;

/**
 * Created by itaesu on 01/08/2019.
 */
public interface BackgroundFileUploadTask {
    void transfer(DomainFile domainFile);
}
