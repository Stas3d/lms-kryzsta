package com.lux.lms;

import com.lux.lms.dataModel.*;
import com.lux.lms.displayResults.DisplayResults;
import com.lux.lms.parser.*;
import com.lux.lms.processor.*;
import com.lux.lms.readingFile.ReadingFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Stasico on 28.04.2015.
 */
public class App {
    public static void main(String[] args) throws Exception {
        checkInputFromKeyboard(args);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Parser parserContextBean = applicationContext.getBean("parser", Parser.class);
        Processing processingContextBean = applicationContext.getBean("processing", Processing.class);
        DisplayResults results = applicationContext.getBean("display", DisplayResults.class);

        InputStream inputStream = new ReadingFileImpl().getInputStream("input/" + args[0]);
        AggregatedData data = parserContextBean.simpleParser(inputStream);
        List<ExceedingData> excesses = processingContextBean.findExceeding(data);
        results.displayMethod(excesses);
    }
 
    private static void checkInputFromKeyboard(String[] args) throws Exception {
        if ( args == null || args.length > 1) throw new Exception("PROBLEMS WHILE INPUT FILE");
    }
}