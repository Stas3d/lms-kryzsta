import com.lux.lms.dataModel.AggregatedData;
import com.lux.lms.dataModel.LimitsData;
import com.lux.lms.dataModel.OperationsData;
import com.lux.lms.parser.Parser;
import com.lux.lms.parser.ParserImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ParserTests {
    private enum TestData {
        ;
        public static final String LIMITS_RAW_EXAMPLE = "Ukraine UAH 2000";
        public static final String OPERATIONS_RAW_EXAMPLE = "117 USA UAH 100000";

        public static final String LIMITS_RAW_ARRAY_EXAMPLE[] = {"Ukraine", "UAH", "2000"};
        public static final String OPERATIONS_RAW_ARRAY_EXAMPLE[] = {"117", "USA", "UAH", "100000"};

        public static final String NOT_VALID_LIMITS_RAW = "1#7 U!A U00";
        public static final String NOT_VALID_OPERATIONS_RAW = "1#7 U!A U@H !D";
    }

    ;

    private static Parser parser = new ParserImpl();

    @Test
    public void testLimitsParsingMethod() {
        LimitsData overLimit = parser.limitsParsing(TestData.LIMITS_RAW_ARRAY_EXAMPLE);
        assertEquals("Ukraine", overLimit.getCountry());
        assertEquals("UAH", overLimit.getCurrency());
        assertEquals(new BigDecimal("2000"), overLimit.getValue());
    }

    @Test
    public void testOperationsParsingMethod() {
        OperationsData overLimit = parser.operationsParsing(TestData.OPERATIONS_RAW_ARRAY_EXAMPLE);
        assertEquals(117, overLimit.getIdOperation());
        assertEquals("USA", overLimit.getCountry());
        assertEquals("UAH", overLimit.getCurrency());
        assertEquals(new BigDecimal("100000"), overLimit.getAmount());
    }

    @Test
    public void testLimitsMatcher() {
        boolean result = parser.checkLimit(TestData.LIMITS_RAW_EXAMPLE);
        assertEquals(true, result);
    }

    @Test
    public void testOperationsMatcherUsingReflection() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = ParserImpl.class.getDeclaredMethod("checkOperation", String.class);
        method.setAccessible(true);
        Object result = method.invoke(parser, TestData.OPERATIONS_RAW_EXAMPLE);
        assertEquals(true, result);
    }

    @Test(expected = com.lux.lms.exception.ParserException.class)
    public void testNotValidLimitsRaw() {
        parser.checkLimit(TestData.NOT_VALID_LIMITS_RAW);
    }

    @Test(expected = com.lux.lms.exception.ParserException.class)
    public void testNotValidOperationRaw() {
        parser.checkOperation(TestData.NOT_VALID_OPERATIONS_RAW);
    }

    @Test
    public void testSimpleParser() {
        AggregatedData aggregatedData = parser.simpleParser(null);
        assertNull(aggregatedData);
    }
}
