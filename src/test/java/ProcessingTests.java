import com.lux.lms.dataModel.ExceedingData;
import com.lux.lms.processor.Processing;
import com.lux.lms.processor.ProcessingImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stasico on 25.04.2015.
 */
public class ProcessingTests {

    private final static Processing processing = new ProcessingImpl();

    @Test
    public void excessCheckerTest() {
        BigDecimal overLimit = processing.checkOverLimitAndPutExcessToDataList(0, new BigDecimal("2"), new BigDecimal("1"));
        assertEquals(new BigDecimal("2"), overLimit);
    }

    @Test(expected = NullPointerException.class)
    public void processingImplMethodReceiveNullTest() {
        List<ExceedingData> overLimit = processing.findExceeding(null);
    }
}
