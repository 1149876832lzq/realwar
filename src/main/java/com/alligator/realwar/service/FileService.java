package com.alligator.realwar.service;

import java.io.File;
import java.io.InputStream;

/**
 * 文件上传服务接口  可以拓展为本地或者以后加入阿里云之类的
 */
public interface FileService {

    /**
     * 文件上传
     * @param inputStream
     * @param filename
     */
    void upload(InputStream inputStream,String filename);

    /**
     * 文件上传
     * @param file
     */
    void upload(File file);
}
