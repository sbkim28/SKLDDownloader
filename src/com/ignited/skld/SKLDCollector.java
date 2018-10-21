package com.ignited.skld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SKLDCollector {

    // it changes continuously
    public static final int END_INDEX = 519208;

    public static List<Word> collect() throws IOException {
        return collect(1, END_INDEX);
    }

    public static List<Word> collect(int start, int end) throws IOException{
        List<Word> wordList = new ArrayList<>();
        for(int index = start;index<= end;++index){
            Word word = SKLDReader.read(index);
            if(word != null) {
                wordList.add(word);
            }
        }
        return wordList;
    }

}
