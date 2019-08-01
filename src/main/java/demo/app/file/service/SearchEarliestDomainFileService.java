package demo.app.file.service;

import demo.app.file.domain.model.DomainFile;
import demo.app.file.domain.model.DomainFileStatus;
import demo.app.file.domain.repository.DomainFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by itaesu on 01/08/2019.
 */
@Component @RequiredArgsConstructor
public class SearchEarliestDomainFileService {
    private final DomainFileRepository domainFileRepository;

    public List<DomainFile> search() {
        final Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "entityBase.createDateTime"));

        return this.domainFileRepository.findAllByStatus(DomainFileStatus.COMPLETE_DISK_UPLOADED, pageable).getContent();
    }
}
