package com.example.android.miwok;

public class CustomWord {

    private String englishTranslation, miwokTranslation;

    public CustomWord(String englishTranslation, String miwokTranslation) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
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
}
