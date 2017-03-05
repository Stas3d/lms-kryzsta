package com.lux.lms.readingFile;

import java.io.InputStream;

/**
 * Created by Stasico on 27.05.2015.
 */
public interface ReadingFile {
    InputStream getInputStream(String filepath) throws Exception;
}
