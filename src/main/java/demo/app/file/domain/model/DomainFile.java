package demo.app.file.domain.model;

import demo.app.base.domain.EntityBase;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by itaesu on 01/08/2019.
 */
@Audited @EntityListeners(value = {AuditingEntityListener.class})
@Entity @SequenceGenerator(name = "FILE_SEQ", sequenceName = "FILE_SEQ")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class DomainFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "FILE_SEQ")
    private Long key;
    private String id;
    private String name;
    private String diskLocation;
    private String cloudLocation;

    @Builder.Default
    private DomainFileStatus status = DomainFileStatus.COMPLETE_DISK_UPLOADED;

    @Builder.Default
    @Embedded
    private EntityBase entityBase = new EntityBase();

    public LocalDateTime getUpdatedDateTime() {
        return this.entityBase.getUpdateDateTime();
    }

    public void onSuccessToUpload(String cloudLocation){
        this.status = DomainFileStatus.COMPLETE_CLOUD_UPLOAD;
        this.cloudLocation = cloudLocation;
    }

    public void toProcessing() {
        if(this.status != DomainFileStatus.COMPLETE_DISK_UPLOADED){
            throw new IllegalStateException("");
        }

        this.status = DomainFileStatus.PROCESSING;
    }
}

