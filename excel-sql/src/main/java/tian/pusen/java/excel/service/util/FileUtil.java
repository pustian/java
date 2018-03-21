package tian.pusen.java.excel.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by tianpusen on 2017/4/3.
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void writeFile(String content, String filename,  String filepath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filepath + "/" + filename);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.writeBytes(content);
        dataOutputStream.close();
        logger.info("UFT-8 格式写入 {}", filename);
    }

    /**
     * 如果文件目录filename不存在创建新的目录
     * @param filename
     */
    public static void createDir(String filename){
        File file = new File(filename);
        if(!file.exists() || ! file.isDirectory() ) {
            boolean dirCreated = file.mkdirs();
            if(dirCreated == false ) {
                logger.error("It is failed to create the directory:" + filename);
                throw new RuntimeException("It is failed to create the directory:" + filename);
            }
        }
    }

//    public static void main(String[] args) throws IOException {
//        String context ="context";
//        String filename = "filename";
//        String filepath = "./";
//        writeFile(context, filename, filepath);
//    }
}
