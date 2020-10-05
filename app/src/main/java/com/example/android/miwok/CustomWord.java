package com.example.android.miwok;

public class CustomWord {

    private String englishTranslation, miwokTranslation;
    private int imageId ,audioId;

    public CustomWord(String englishTranslation, String miwokTranslation, int audioId) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioId = audioId;
        this.imageId = 0;
    }

    public CustomWord(String englishTranslation, String miwokTranslation, int imageId, int audioId) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageId = imageId;
        this.audioId = audioId;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public boolean isImage() {
        if(this.getImageId()!=0)
            return true;

        return false;
    }
}
