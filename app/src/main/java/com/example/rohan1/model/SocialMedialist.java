package com.example.rohan1.model;

public class SocialMedialist {
    private final Integer socialMediaIcon;
    private final String socialMediaName;

    public SocialMedialist(Integer socialMediaIcon, String socialMediaName) {
        this.socialMediaIcon = socialMediaIcon;
        this.socialMediaName = socialMediaName;
    }

    public Integer getSocialMediaIcon() {
        return socialMediaIcon;
    }

    public String getSocialMediaName() {
        return socialMediaName;
    }
}
