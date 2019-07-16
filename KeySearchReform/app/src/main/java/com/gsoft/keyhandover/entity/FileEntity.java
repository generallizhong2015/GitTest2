package com.gsoft.keyhandover.entity;

/**
 * Created by Administrator on 2018/1/22.
 */

public class FileEntity {

    /**
     * fileName : 48203b2f022857d6d59db9158b14b479.jpg
     * id : 2b29e3580722460b893f8ad021d15f1b
     * fileSize : 109575
     * uploadDate : 1517484519000
     */

    private String fileName;
    private String id;
    private int fileSize;
    private long uploadDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }
}
