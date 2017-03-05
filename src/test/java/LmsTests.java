import com.lux.lms.dataModel.AggregatedData;
import com.lux.lms.parser.Parser;
import com.lux.lms.parser.ParserImpl;
import com.lux.lms.processor.Processing;
import com.lux.lms.processor.ProcessingImpl;
import com.lux.lms.readingFile.ReadingFile;
import com.lux.lms.readingFile.ReadingFileImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Stasico on 20.04.2015.
 */
public class LmsTests {

    private Parser parser;

    private InputStream inputStream;

    @Before
    public void setUp() {
        parser = new ParserImpl();
    }

    @After
    public void shutdown() throws Exception {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Test
    public void testEmptySheet() {
        inputStream = getInputStream("/emptySheet.lms");
        assertNotNull(inputStream);
        AggregatedData aggregatedData = parser.simpleParser(inputStream);
        assertNotNull(aggregatedData);
        Processing processor = new ProcessingImpl();
        processor.findExceeding(aggregatedData);
    }

    @Test
    public void testInputNotNull() {
        inputStream = getInputStream("/sheet01.lms");
        assertNotNull(inputStream);
    }

    @Test
    public void testNotExistingSheet() {
        inputStream = getInputStream("/noSheet.lms");
        assertNull(inputStream);
    }

    @Test
    public void testGettingData() {
        inputStream = getInputStream("/sheet01.lms");
        AggregatedData aggregatedData = parser.simpleParser(inputStream);
        assertNotNull(aggregatedData);
        Processing processor = new ProcessingImpl();
        processor.findExceeding(aggregatedData);
    }

    @Test(expected = IOException.class)
    public void catchIOExceptionWhenNotExistingFile() throws Exception {
        ReadingFile readingFile = new ReadingFileImpl();
        readingFile.getInputStream("notActualFile");
    }

    private InputStream getInputStream(String fileName) {
        return getClass().getResourceAsStream(fileName);
    }

}
