package demo.app.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * Created by itaesu on 01/08/2019.
 */
@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor
public class EntityBase {

    @CreatedDate
    @Column(name = "CREATE_DATETIME")
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "UPDATE_DATETIME")
    private LocalDateTime updateDateTime;

}
