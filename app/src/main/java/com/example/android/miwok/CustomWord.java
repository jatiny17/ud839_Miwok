package com.example.android.miwok;

public class CustomWord {

    private String englishTranslation, miwokTranslation;
    private int imageId;

    public CustomWord(String englishTranslation, String miwokTranslation) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        imageId = 0;
    }

    public CustomWord(String englishTranslation, String miwokTranslation, int imageId) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageId = imageId;
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

    public boolean isImage() {
        if(this.getImageId()!=0)
            return true;

        return false;
    }
}
