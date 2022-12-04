package com.uno.hworld.service;

import com.uno.hworld.domain.FileInfo;
import com.uno.hworld.domain.User;
import com.uno.hworld.repository.FileInfoRepository;
import com.uno.hworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    private final FileInfoRepository fileInfoRepository;
    private final UserRepository userRepository;

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String originFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String savedFileName = uuid + extension;
        String savedFilePath = fileDir + savedFileName;
        String fileType = "IMAGE"; // 임시

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User is not found."));
        String fileId = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + userId;

        FileInfo fileInfo = FileInfo.builder()
                .fileId(fileId)
                .user(user)
                .fileType(fileType)
                .fileOrgNm(originFileName)
                .fileSavedNm(savedFileName)
                .build(); // 처음에는 isLock = true로 설정해서 막는 것이 필요

        //해당 경로에 저장
        file.transferTo(new File(savedFilePath));

        //DB에 저장
        FileInfo savedFileInfo = fileInfoRepository.save(fileInfo);

        return savedFileInfo.getFileId();
    }
}
