package com.xiaoxin.projectinterface.utils;

import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * 调用百度人脸识别脚本并获取识别结果
     *
     * @param path1 人脸图片地址
     * @param path2 考勤图片地址
     * @return 0：成功 1：失败
     */
    public static Integer doIdentify(String path1, String path2) {
        //   src/main/resources/static/image/face/12.png
        //   src/main/resources/static/image/check/12_60.png

        String ak = "LeAddpgtN70F4GRuoZGDHhtc";
        String sk = "a1mPh47hZvKv5dcrGpkwzsnbYe92yFrg";
        String result = FaceMatch.match(ak, sk, path1, path2);
        System.out.println("对比结果为：" + result);

        double score = new JSONObject(result).getJSONObject("result").getDouble("score");
        System.out.println("相似得分为：" + score);
        int res = 1;
        // 阈值为90，高于90分判断为同一人
        if(score >= 90){
            res = 0;
        }

        return res;
    }

    /**
     * 上传图像至服务器
     *
     * @param file 上传的图片文件
     * @param type 1:老师头像，2:学生头像，3:课程头像，4:人脸信息，5:考勤时的人脸信息
     * @param id   编号
     * @return 存储的映射路径
     */
    public static String saveImage(MultipartFile file, String type, String id) {
        if (file.isEmpty()) {
            return null;
        }
        String prefix = "";
        String parentPath = "";
        switch (type) {
            case "1":
                prefix = "t";
                parentPath = "avatars";
                break;
            case "2":
                prefix = "s";
                parentPath = "avatars";
                break;
            case "3":
                prefix = "c";
                parentPath = "avatars";
                break;
            case "4":
                prefix = "";
                parentPath = "face";
                break;
            case "5":
                prefix = "";
                parentPath = "check";
                break;
            default:
                break;
        }

        String path = "src/main/resources/static/image/" + parentPath + "/";
        File folder = new File(path);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String ext = ".png";
        String photoName = prefix + id + ext;
        try {
            File newFile = new File(folder.getAbsolutePath() + File.separator + photoName);
            file.transferTo(newFile);

            System.out.println(folder.getAbsolutePath() + File.separator + photoName);
            return "image/" + parentPath + "/" + photoName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String renameImage(String path, String name) {
        String realPath = "src/main/resources/static/image/avatars/";
        String virtualPath = path.substring(0, path.lastIndexOf("/") + 1);
        String oldName = path.substring(path.lastIndexOf("/") + 1);
        String ext = oldName.substring(oldName.lastIndexOf("."));
        File file1 = new File(realPath + oldName);
        File file2 = new File(realPath + name + ext);
        try {
            FileCopyUtils.copy(file1,file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return virtualPath + name + ext;
    }

    /**
     * 删除图片
     *
     * @param path 图片的路径
     */
    public static void deleteImage(String path, String parentPath) {
        String p = "src/main/resources/static/" + parentPath + path.substring(path.lastIndexOf("/"));
        File file = new File(p);
        if (!path.isEmpty()) {
            file.delete();
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o 需要判断的对象
     * @return 为空返回true, 不为空返回false
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if ("".equals(o.toString().trim())) {
                return true;
            }
            return "undefined".equals(o.toString().trim());
        } else if (o instanceof List) {
            return ((List) o).size() == 0;
        } else if (o instanceof Map) {
            return ((Map) o).size() == 0;
        } else if (o instanceof Set) {
            return ((Set) o).size() == 0;
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        } else if (o instanceof int[]) {
            return ((int[]) o).length == 0;
        } else if (o instanceof long[]) {
            return ((long[]) o).length == 0;
        }
        return false;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        boolean flag = false;
        try {
            String pattern = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean IsEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 生成随机课程码
     *
     * @return
     */
    public static String courseCode(){
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
}
