package com.daffodilsw.services;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultipartFileBodyCreator {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private final List<String> mFilePathList;
    private final String     mPartName;

    public MultipartFileBodyCreator(String partName) {
        mFilePathList = new ArrayList<>();
        mPartName = partName;
    }

    private MultipartFileBodyCreator(Builder builder) {
        mFilePathList = builder.mFilePathList;
        mPartName = builder.mPartName;
    }

    public List<MultipartBody.Part> createFilePart() {

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();

        for (String filePath : mFilePathList) {
            File file = new File(filePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
            multipartBuilder.addFormDataPart(mPartName, file.getName(), requestBody);
        }

        return multipartBuilder.build().parts();
    }


    public static final class Builder {
        private final List<String> mFilePathList;
        private final String mPartName;

        public Builder(List<String> mFilePathList, String mPartName) {
            this.mFilePathList = mFilePathList;
            this.mPartName = mPartName;
        }

        public Builder(String partName) {
            mFilePathList = new ArrayList<>();
            this.mPartName = partName;
        }

        public Builder(MultipartFileBodyCreator copy) {
            this.mFilePathList = copy.mFilePathList;
            this.mPartName = copy.mPartName;
        }

        public Builder addFilePath(String filePath) {
            mFilePathList.add(filePath);
            return this;
        }

        public MultipartFileBodyCreator build() {
            return new MultipartFileBodyCreator(this);
        }
    }
}
