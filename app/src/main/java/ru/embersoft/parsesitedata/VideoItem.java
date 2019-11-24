package ru.embersoft.parsesitedata;

public class VideoItem {

    private String videoUrl;
    private String previewUrl;

    public VideoItem() {
    }

    public VideoItem(String videoUrl, String previewUrl) {
        this.videoUrl = videoUrl;
        this.previewUrl = previewUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
