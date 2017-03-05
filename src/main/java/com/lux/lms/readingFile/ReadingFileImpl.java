package com.lux.lms.readingFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadingFileImpl implements ReadingFile{

    public ReadingFileImpl() {
    }

    public InputStream getInputStream(String filepath) throws Exception {
        File file = new File(filepath);
        if (file.exists() && !file.isDirectory()) {
            return new FileInputStream(file);
        } else
            throw new IOException("PROBLEMS WHILE READING FILE");
    }
}
