package com.example.aapplication.Chroma.colormode;


public final class Channel {

    private final int nameResourceId;
    private final int min;
    private final int max;
    private final ColorExtractor extractor;

    private int progress = 0;

    public Channel(int nameResourceId, int min, int max, ColorExtractor extractor) {
        this.nameResourceId = nameResourceId;
        this.min = min;
        this.max = max;
        this.extractor = extractor;
    }

    public Channel(int nameResourceId, int min, int max, int progress, ColorExtractor extractor) {
        this.nameResourceId = nameResourceId;
        this.min = min;
        this.max = max;
        this.extractor = extractor;
        this.progress = progress;
    }

    public interface ColorExtractor {
        int extract(int color);
    }

    public int getNameResourceId() {
        return nameResourceId;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public ColorExtractor getExtractor() {
        return extractor;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
