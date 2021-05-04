import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class WordCounterTest {

    WordCounter underTest;

    @Before
    public void setUp() throws Exception {
        this.underTest = new WordCounter(ComparisionMode.IGNORE_CASE);
    }

    @Test
    public void testAnalyze_nonLettersIgnoredDuringAnalysis_differentCaseWordsConsideredSame() {
        underTest.analyze("test TEST: tEST. two   two  ");
        Map<String, Integer> wordsMap = underTest.getWordsMap();
        Assert.assertEquals(2, wordsMap.size());
        Assert.assertEquals((Integer)3, wordsMap.get("test"));
        Assert.assertEquals((Integer)2, wordsMap.get("two"));
    }

    @Test
    public void testAnalyze_blankStringIsIngnored_emptyMapReturnedBack() {
        underTest.analyze("        ");
        Assert.assertEquals(0, underTest.getWordsMap().size());
    }

    @Test
    public void testAnalyze_nullIsIgnored_emptyMapReturnedBack() {
        underTest.analyze(null);
        Assert.assertEquals(0, underTest.getWordsMap().size());
    }
}