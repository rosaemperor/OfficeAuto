package com.shiyidaoAndroidApp.uitle;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * 文件操作工具类
 *
 *
 */
public class FileUtile {
    private static FileUtile fileUtile;
    //缓存文件地址
    private String cacheDir = "cache";
    //图片保存地址
    private String photoDir = "photo";
    //一般文件保存地址
    private String tempfile = "tempfile";
    //数据文件类型
    private String dataDir = "data";

    private String ACCOUNT_DIR = Environment.getExternalStorageDirectory().getPath()
            + "/OfficeAutomation/";
    private String DATA = "data/";
//    private String user = Application.getApplication().getUserInfoEntity().getUserId()+"/";
//    private String company = Application.getApplication().getUserInfoEntity().getCompanyId()+"/";
//    private String COMPANY_CACHE = ACCOUNT_DIR + DATA + company;
//    private String USER_CACHE = ACCOUNT_DIR + DATA + company + user;
    private String PUBLIC_DATA_CACHE = ACCOUNT_DIR + DATA;

    private FileUtile() {
    }


    /**
     * 获取文件工具类实体
     *
     * @return
     */
    public static FileUtile getInstance() {
        if (fileUtile == null) {
            fileUtile = new FileUtile();
        }
        return fileUtile;
    }

    /**
     * 判断是否存在SDCard
     *
     * @return
     */
    public boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 初始化根目录，并返回地址<br>
     * 如果存在SDcard则对应文件位置为SD卡内，如果没有SD卡，则直接存储到手机的更目录下
     *
     * @return 返回文件根目录
     */
//    public String getRootFilePath() {
//        File file = null;
//        if (fileUtile.hasSDCard()) {
//            file = new File(Environment.getExternalStorageDirectory(), AppCongif.FILE_PATH_ROOT);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//        } else {
//            file = new File(Environment.getRootDirectory(), AppCongif.FILE_PATH_ROOT);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//        }
//        return file.getAbsolutePath();
//    }

    /**
     * 获取数据存储地址
     *
     * @return
     */
//    public String getDataDir() {
//        String filePath = getRootFilePath() + File.separatorChar + dataDir;
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        return filePath;
//    }

    /**
     * 创建文件夹
     *
     * @param path    文件夹所在目录
     * @param dirName 文件夹名称
     * @return 创建好的文件夹路径
     */
    public String creatDir(String path, String dirName) {
        File file = new File(path, dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 随机文件名称生成 (文件会加入当前时间进行处理)
     *
     * @param fileName 文件主要标示名称
     * @param fileType 文件主要标示类型
     * @return
     */
    public String getRoundFileName(String fileName, String fileType) {
        String currTime = System.currentTimeMillis() + "";
        currTime = currTime.substring(currTime.length() - 5);
        return fileName + currTime + fileType;
    }


    /**
     * 文件(流)存储
     *
     * @param inputStream
     * @param filePath
     * @param fileName
     * @return 返回以保存的文件的路径
     */
    public String saveFile(InputStream inputStream, String filePath, String fileName) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        File savefile = new File(file, fileName);
        FileOutputStream fos = new FileOutputStream(savefile);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        fos.flush();
        fos.close();
        inputStream.close();
        return savefile.getAbsolutePath();
    }

    /**
     * 删除指定文件
     *
     * @param file 需要删除的文件
     * @return 是否删除成功
     */
    public boolean deletFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                if (file.listFiles().length > 0) {
                    for (File f : file.listFiles()) {
                        deletFile(f);
                    }
                }
            } else {
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 删除指定文件
     *
     * @param filePath 需要删除的文件的文件地址
     * @return 是否删除成功
     */
    public boolean deletFile(String filePath) {
        return deletFile(new File(filePath));
    }

    /**
     * 保存文件到SDCard下DATA_CACHE文件夹下
     * @param fileName 文件名
     * @param content 内容
     * @return true:成功保存 false:保存失败
     */
    public boolean saveToSDCard(String path, String fileName, String content) {
        // judge weather the SDCard exits,and can be read and written
        if (!hasSDCard()) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        File f = new File(path);
        if (!f.exists() || !f.isDirectory()) {
            f.mkdirs();
        }
        File file = new File(f,fileName);
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 读取DATA_CACHE文件夹下的文件
     * @param fileName 文件名
     * @return String内容
     */
    public String readFromSD(String path, String fileName) {
        FileInputStream fileInputStream = null;
        File file = new File(path,
                fileName);
        try {
            fileInputStream = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            while ((len = fileInputStream.read(buffer)) != -1) {
                byteArrayInputStream.write(buffer, 0, len);
            }
            String string = new String(byteArrayInputStream.toByteArray());
            return string;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 存储不同缓存
     * @param type 1公司信息  2公共信息 3个人信息
     * @param fileName
     * @param content
     */
//    public void saveMyInfoToSD(int type,String fileName, String content){
//        if(type == 1){//公司信息
//            this.saveToSDCard(COMPANY_CACHE, fileName, content);
//        }else if(type == 2){//公共信息
//            this.saveToSDCard(PUBLIC_DATA_CACHE, fileName, content);
//        }else if(type == 3){//个人信息
//            this.saveToSDCard(USER_CACHE, fileName, content);
//        }
    }

    /**
     * 读取不同缓存
     * @param type 1公司信息  2公共信息 3个人信息
     * @param fileName
     * @return
     */
//    public String readMyInfoFromSD(int type,String fileName){
//        if(type == 1){//公司信息
//            return this.readFromSD(COMPANY_CACHE,fileName);
//        }else if(type == 2){//公共信息
//            return this.readFromSD(PUBLIC_DATA_CACHE,fileName);
//        }else if(type == 3){//个人信息
//            return this.readFromSD(USER_CACHE, fileName);
//        }
//        return null;
//    }
//}
