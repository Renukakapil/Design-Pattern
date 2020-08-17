package com.code.designpatternapp.data.contracts.models;


import com.code.common.contracts.ISynchronizedModel;
import com.code.common.contracts.ModelState;

import java.util.ArrayList;
import java.util.Date;

public class ImageModel implements ISynchronizedModel {
    Long id;
    String imageName;
    String imageUri;
    String url;
    String imageStatus;
    Long orderNumber;
    Long aboveImageId;
    boolean imageEdited;
    String bucket;

    public int getBucketCount() {
        return bucketCount;
    }

    public void setBucketCount(int bucketCount) {
        this.bucketCount = bucketCount;
    }

    int bucketCount;
    int imageCount;
    ArrayList<String> appliedFilters;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getTimeStamp() {
        return null;
    }

    @Override
    public void setTimeStamp(Date timeStamp) {

    }

    @Override
    public ModelState getModelStatus() {
        return null;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setStatus(Integer status) {

    }



    @Override
    public Long getServerId() {
        return null;
    }

    @Override
    public void setServerId(Long id) {

    }

    public Long getOrderNumber() {
        return orderNumber;
    }



    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getAboveImageId() {
        return aboveImageId;
    }

    public void setAboveImageId(Long aboveImageId) {
        this.aboveImageId = aboveImageId;
    }

    public boolean isImageEdited() {
        return imageEdited;
    }

    public void setImageEdited(boolean imageEdited) {
        this.imageEdited = imageEdited;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public ArrayList<String> getAppliedFilters() {
        if(appliedFilters==null){
            appliedFilters=new ArrayList<>();
        }
        return appliedFilters;
    }

    public void setAppliedFilters(ArrayList<String> appliedFilters) {
        if(appliedFilters==null){
            appliedFilters=new ArrayList<>();
        }
        this.appliedFilters = appliedFilters;
    }

    public void addAppliedFilter(String filterName){
        if(appliedFilters==null){
            appliedFilters=new ArrayList<>();
        }
        appliedFilters.add(filterName);
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }
}
