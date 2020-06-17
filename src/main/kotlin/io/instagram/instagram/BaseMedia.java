package io.instagram.instagram;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Transient;

public abstract class BaseMedia {

    private String url;

    @Transient
    private MultipartFile file;

    @Transient
    private final String upload_dir = System.getProperty("user.dir") + "/files/";

    public BaseMedia() {
    }

    public BaseMedia(MultipartFile file) {
        this.file = file;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
