package com.alligator.realwar.service.impl;

import com.alligator.realwar.exception.BusinessException;
import com.alligator.realwar.exception.ErrorCodeEnum;
import com.alligator.realwar.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 本地文件上传服务类
 */
@Slf4j
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {

    /**
     * 参照其他云存储的方式定义存储空间
     */
    private static final String BUCKET = "uploads";

    @Override
    public void upload(InputStream inputStream, String filename) {

        //拼接文件的存储路径
        String storagePath = BUCKET + "/" + filename;


        try (
                //JDK8 中TWR方式 不能关闭外部的流，所以定义一个内部的流来接受参数中传递的流
                InputStream innerInputStream = inputStream;
                //这里最好判断一下文件夹是否存在
                FileOutputStream outputStream = new FileOutputStream(new File(storagePath));
                ) {

            //拷贝缓冲区
            byte[] buffer = new byte[1024];
            //读取文件流的长度
            int len;
            //循环读取input stream中的数据写入到outputstream
            while ((len = innerInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            //冲刷流,否则不能完全写入文件
            outputStream.flush();
        } catch (Exception e) {

            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }

    }

    @Override
    public void upload(File file) {
        try {

            upload(new FileInputStream(file),file.getName());
        } catch (Exception e) {

            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }
}
