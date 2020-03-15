package com.manhpd.search;

import com.manhpd.ReadFileUtils;
import org.junit.Test;

public class SearchHelperTest {

    @Test
    public void compareWordFreqMethods() {
        String path = "D:\\Backup_OneTab.txt";
        String words = ReadFileUtils.readContentFile(path);

        SearchHelper.wordFreqTraditional(words);
        SearchHelper.wordFreqStreamJava8(words);
    }

}
