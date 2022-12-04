package com.uno.hworld.controller;

import com.google.common.net.HttpHeaders;
import com.uno.hworld.domain.FileInfo;
import com.uno.hworld.repository.FileInfoRepository;
import com.uno.hworld.service.FileService;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;
    private final FileInfoRepository fileInfoRepository;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String upload() {
        return "/upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {
        fileService.saveFile(file);

        for (MultipartFile multipartFile : files) {
            fileService.saveFile(multipartFile);
        }

        return "redirect:/upload";
    }

    @GetMapping("/uploadView")
    public String view(Model model) {
        List<FileInfo> files = fileInfoRepository.findAll();
        model.addAttribute("all", files);
        return "uploadView";
    }

    @GetMapping("/uploadView/images/{fileId}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("fileId") String id, Model model) throws IOException {
        FileInfo file = fileInfoRepository.findById(id).orElse(null);
        return new UrlResource("file:" + fileDir + file.getFileSavedNm());
    }

    //downlaod controller, not used
    @GetMapping("/uploadView/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable String id) throws MalformedURLException {
        FileInfo file = fileInfoRepository.findById(id).orElse(null);
        UrlResource resource = new UrlResource("file:" + fileDir + file.getFileSavedNm());
        String encodedFileName = UriUtils.encode(file.getFileOrgNm(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" +encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }
}
