package com.example.util;

import java.io.File;

public final class FileNameGenerateUtil {

    public static String getFileName(String suffix) {
        String name = getDirName() + File.separator + random();
        File file;
        for (;;) {
            file = new File(name);
            if (!file.exists()) {
                return name + suffix;
            } else {
                name = getDirName() + File.separator + random();
            }
        }
    }

    private static String random() {
        String prefix = String.valueOf(System.currentTimeMillis());
        String suffix = String.valueOf((int) (1000 * Math.random()));
        return prefix + suffix;
    }

    @SuppressWarnings("all")
    private static String getDirName() {
        String dirPath = "D:" + File.separator + "demo_data";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
}
