import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class ClosableFileReaderTest {

    ClosableFileReader underTest;
    private final String TEST_FILE_PATH = "src\\test\\resources\\test.txt";
    private final String LINE1 = "line1";
    private final String LINE2 = "line2";
    private final String LINE3 = "line3.1 line3.2";

    @Before
    public void setUp() throws Exception {
        underTest = ClosableFileReader.forFile(TEST_FILE_PATH);
    }

    @Test
    public void testConstructor_fileLoaded_lineIsNotEmpty() {
        Optional < String > line1 = underTest.line();
        Assert.assertTrue(StringUtils.isNotBlank(line1.get()));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructor_nonExisitngFile_exceptionIsThrown() {
        ClosableFileReader.forFile("src\\test\\resources\\non-existing-file.txt");
    }

    @Test
    public void testLine_readAllLines_afterLastLineReturnsOptionalEmpty() {
        Optional<String> line1 = underTest.line();
        Optional<String> line2 = underTest.line();
        Optional<String> line3 = underTest.line();
        Optional<String> line4 = underTest.line();

        Assert.assertEquals(line1.get(), LINE1);
        Assert.assertEquals(line2.get(), LINE2);
        Assert.assertEquals(line3.get(), LINE3);
        Assert.assertEquals(line4.isPresent(), false);
    }

    @After
    public void tearDown() throws Exception {
        if (underTest != null) {
            underTest.close();
        }
        underTest = null;
    }
}