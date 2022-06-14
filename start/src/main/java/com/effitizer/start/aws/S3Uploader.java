package com.effitizer.start.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Bookcoverfile;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.repository.BookRepository;
import com.effitizer.start.repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class S3Uploader {
    @Autowired AmazonS3Client amazonS3Client;
    @Autowired ContentsRepository contentsRepository;
    @Autowired
    BookRepository bookRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Contentsfile upload(Contents contents, MultipartFile multipartFile, String dirName) throws IOException {
        log.info("파일 확인"+ multipartFile.toString());
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return upload(contents, uploadFile, dirName, multipartFile.getSize(), multipartFile.getContentType(), multipartFile.getOriginalFilename());
    }

    private Contentsfile upload(Contents contents, File uploadFile, String dirName, Long size, String extend, String origin_filename) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return new Contentsfile(contents, origin_filename, fileName, size, uploadImageUrl, extend);
    }

    public Bookcoverfile uploadBookcoverfile(Long bookId, MultipartFile multipartFile, String dirName) throws IOException {
        log.info("파일 확인"+ multipartFile.toString());
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return uploadBookcoverfile(bookId, uploadFile, dirName, multipartFile.getSize(), multipartFile.getContentType(), multipartFile.getOriginalFilename());
    }

    private Bookcoverfile uploadBookcoverfile(Long bookId, File uploadFile, String dirName, Long size, String extend, String origin_filename) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        Book book = bookRepository.getById(bookId);
        return Bookcoverfile.builder()
                            .book(book)
                            .extend(extend)
                            .name(fileName)
                            .size(size)
                            .path(uploadImageUrl)
                            .real_name(origin_filename)
                            .build();
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
                CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }


}