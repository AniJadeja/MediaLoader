package com.example.videoloader.Model;

public class VideoModel {
    String path,thumb;
    boolean selected;

    public String getPath() {
        return path;
    }

    public String getThumb() {
        return thumb;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }
}
