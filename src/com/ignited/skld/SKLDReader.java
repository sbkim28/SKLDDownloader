package com.ignited.skld;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SKLDReader{

    private static final String link = "http://stdweb2.korean.go.kr/search/View.jsp?idx=";

    private static final String qWordTitle = ".word_title";
    private static final String qList = ".list";
    private static final String qExp = ".exp";
    private static final String qAttr = ".list > font";
    private static final String qProperty = ".NumRG font";
    private static final String qNumRG2 = ".NumRG2";
    private static final String qLi = "li";
    private static final String qNumNO = ".NumNO";
    private static final String qDefinition = ".Definition";

    public static Word read(int i) throws IOException {

        Document doc = Jsoup.connect(link + i).get();

        String raw;
        try {
             raw = doc.select(qWordTitle).first().text();
        }catch (NullPointerException e){
            return null;
        }

        Word wordSheet = getWord(raw);

        Elements lists = doc.select(qList);

        for (Element list : lists) {

            Elements exp	= list.select(qExp);
            Elements attr = list.select(qAttr);

            Word.Property property = new Word.Property(list.select(qProperty).text(), Arrays.stream(attr.text().split(" ")).collect(Collectors.toCollection(ArrayList::new)));
            wordSheet.addProperty(property);

            for (Element e : exp){
                String numRG2 = e.select(qNumRG2).text();
                Elements lis	= e.select(qLi);

                int sector = 1;
                if(!numRG2.isEmpty()){
                    sector = Integer.parseInt(numRG2.substring(1,numRG2.length() - 1));
                }
                Section section = new Section(sector);

                property.addSection(section);

                for (Element li : lis){
                    String numNO = li.select(qNumNO).text();
                    Elements definition = li.select(qDefinition);

                    int index = 1;
                    if(!numNO.isEmpty()){
                        index = Integer.parseInt(numNO.substring(1,numNO.length() - 1));
                    }
                    section.addPolysemy(new Section.Polysemy(index, definition.text().trim()));
                }
            }
        }

        return wordSheet;
    }

    private static Word getWord(String raw){
        String word = raw.replaceAll("[-^ㆍ]","");

        int number = 1;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher m = pattern.matcher(word);
        if(m.find()){
            String strNum = m.group();
            number = Integer.parseInt(strNum);
            word = word.substring(0, word.indexOf(strNum));
        }

        boolean old = !containsOldHangeul(word);

        return new Word(number, word, old);
    }

    private static boolean containsOldHangeul(String word){
        return Pattern.matches("^[ㄱ-ㅎ가-힣]*$", word);
    }
}
