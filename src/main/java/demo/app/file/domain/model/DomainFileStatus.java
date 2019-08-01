package demo.app.file.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by itaesu on 01/08/2019.
 */
@AllArgsConstructor @Getter
public enum DomainFileStatus {
    COMPLETE_DISK_UPLOADED("COMPLETE_DISK_UPLOADED"),
    PROCESSING("PROCESSING"),
    COMPLETE_CLOUD_UPLOAD("COMPLETE_CLOUD_UPLOAD");

    private String code;

}
