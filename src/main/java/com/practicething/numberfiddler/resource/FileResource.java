package com.practicething.numberfiddler.resource;

import com.practicething.numberfiddler.model.FileEntry;
import com.practicething.numberfiddler.service.FileEntryService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;


@RestController
@RequestMapping("/file")
public class FileResource {

    private final FileEntryService fileEntryService;

    public FileResource(FileEntryService fileEntryService) {
        this.fileEntryService = fileEntryService;
    }
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";


    @GetMapping("/all")
    public ResponseEntity<List<FileEntry>> getAllFileEntry () {
        List<FileEntry> entry = fileEntryService.findAllFileEntry();
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
            addNewEntries(file);
        }
        return ResponseEntity.ok().body(filenames);
    }
    private void addNewEntries(MultipartFile file) throws IOException {
        Scanner s = new Scanner(file.getInputStream()).useDelimiter(";");
        ArrayList<String> result=new ArrayList<>();
        while (s.hasNext()){
            result.add(s.next());
        }

        String date= new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime());
        FileEntry entry =new FileEntry(date,createSum(result),file.getName());
        FileEntry newEntry=fileEntryService.addFileEntry(entry);
    }
    private int createSum(ArrayList<String> content){
        int sum=0;
        for ( String e :content){
            sum=sum+Integer.parseInt(e);
        }
        return sum;
    }
}