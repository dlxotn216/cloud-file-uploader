package demo.app.file.domain.repository;

import demo.app.file.domain.model.DomainFile;
import demo.app.file.domain.model.DomainFileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by itaesu on 01/08/2019.
 */
public interface DomainFileRepository extends JpaRepository<DomainFile, Long> {
    Page<DomainFile> findAllByStatus(DomainFileStatus status, Pageable pageable);
}
