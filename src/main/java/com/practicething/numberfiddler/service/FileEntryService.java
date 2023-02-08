package com.practicething.numberfiddler.service;

import com.practicething.numberfiddler.model.FileEntry;
import com.practicething.numberfiddler.repo.FileEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FileEntryService {
    private final FileEntryRepo fileEntryRepo;

    @Autowired
    public FileEntryService(FileEntryRepo fileEntryRepo) {
        this.fileEntryRepo = fileEntryRepo;
    }

    public FileEntry addFileEntry(FileEntry fileEntry) {
        return fileEntryRepo.save(fileEntry);
    }
    public List<FileEntry> findAllFileEntry() {
        return fileEntryRepo.findAll();
    }
    /* public void deleteFileEntry(Long id){
        fileEntryRepo.deleteFileEntryById(id);
       }
    */
}