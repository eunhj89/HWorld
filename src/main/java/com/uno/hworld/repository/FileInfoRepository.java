package com.uno.hworld.repository;

import com.uno.hworld.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, String> {

}
