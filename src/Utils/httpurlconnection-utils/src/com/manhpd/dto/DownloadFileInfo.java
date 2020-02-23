package com.manhpd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DownloadFileInfo {

    private String saveDir;

    private String fileName;

}
