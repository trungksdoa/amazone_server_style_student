package com.student.project.amazone.controller;


import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.File.payload.Response;
import com.student.project.amazone.entity.Banner_model;
import com.student.project.amazone.service.Banner_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v2/banner")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9111")
public class Banner_controller {
    private final FileStorageService storeService;
    private final Banner_service BannerService;

    @GetMapping
    public ResponseEntity<List<Banner_model>> getListFile() {
        return ResponseEntity.ok().body(BannerService.getList());
    }

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {


        String[] getType = file.getContentType().split("/");


        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String fileName = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Banner_model banner = new Banner_model();


        banner.setImageName(fileName + "." + getType[1]);
        banner.setLink("thiendia.com");

        BannerService.SaveOrUpdate(banner);

        storeService.storeFileBanner(file, fileName + "." + getType[1]);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new Response(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//
//        int leftLimit = 97; // letter 'a'
//        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 15;
//        Random random = new Random();
//
//        String generatedString = random.ints(leftLimit, rightLimit + 1)
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file,generatedString))
//                .collect(Collectors.toList());
//    }
}
