package com.example.controllers;

import com.example.constant.ErrorCode;
import com.example.pojos.BaseResponse;
import com.example.util.ZCTextUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.example.util.FileNameGenerateUtil.getFileName;

@Controller
public class UploadController {

    @PostMapping(value = "/upload")
    public @ResponseBody BaseResponse upload(@RequestParam(name = "file") MultipartFile file) {
        System.out.println("上传请求 : " + ((file == null) || file.isEmpty()));
        BaseResponse response = new BaseResponse();
        if (file == null || file.isEmpty()) {
            response.setCode(ErrorCode.UPLOAD_FILE_NOT_NULL);
            response.setMessage("上传的文件不能为空");
            return response;
        }
        String suffix = getFileSuffix(file.getOriginalFilename());
        if (ZCTextUtils.isEmpty(suffix)) {
            response.setCode(ErrorCode.UPLOAD_FILE_NAME_SYNTAX_WRONG);
            response.setMessage("上传的文件名称错误");
            return response;
        }
        String fileName = getFileName(suffix);
        File newFile = new File(fileName);
        try {
            FileUtils.writeByteArrayToFile(newFile, file.getBytes());
            response.setCode(ErrorCode.SUCCESS);
            response.setMessage("文件保存成功");
            response.setData(getUserName(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            response.setCode(ErrorCode.UPLOAD_FILE_FAILED);
            response.setMessage("文件保存失败");
        }
        System.out.println("处理上传请求 : " + response.toString());
        return response;
    }

    @GetMapping(value = "/download")
    public void download(String url, HttpServletResponse res) {
        String fileName;
        if (ZCTextUtils.isEmpty(url)) {
            fileName = "a.png";
        } else {
            fileName = url;
        }
        File file = new File("D:" + File.separator + "demo_data" + File.separator
                + fileName);
        OutputStream os;
        if (!file.exists()) {
            return;
        }

//        res.setHeader("content-type", "application/octet-stream");
//        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        res.setContentType("image/jpeg");
        res.setHeader("Content-Disposition", "inline;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;

        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File("D:" + File.separator + "demo_data" + File.separator
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getFileSuffix(String name) {
        if (ZCTextUtils.isEmpty(name)) {
            return null;
        }
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return null;
        }
        return name.substring(index);
    }

    private String getUserName(String originName) {
        System.out.println(originName);
        int index = originName.lastIndexOf(File.separator);
        return originName.substring(index + 1);
    }
}
