package com.practicething.numberfiddler.repo;

import com.practicething.numberfiddler.model.FileEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntryRepo extends JpaRepository<FileEntry, Long> {
    void deleteFileEntryById(Long id);

}
