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

        Word.Builder wb = getWord(raw);

        Elements lists = doc.select(qList);

        for (Element list : lists) {

            Elements exp	= list.select(qExp);
            Elements attr = list.select(qAttr);

            Property.Builder pb = new Property.Builder()
                    .setWordClass(list.select(qProperty).text())
                    .addAllAtrribute(Arrays.stream(attr.text().split(" ")).collect(Collectors.toCollection(ArrayList::new)));

            for (Element e : exp){
                Elements lis	= e.select(qLi);

                for (Element li : lis){
                    Elements definition = li.select(qDefinition);

                    pb.addMeaning(definition.text().trim());
                }
                pb.next();
            }
            wb.addProperty(pb.build());
        }

        return wb.build();
    }

    private static Word.Builder getWord(String raw){
        String word = raw.replaceAll("[-^ㆍ]","");

        int number = 1;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher m = pattern.matcher(word);
        if(m.find()){
            String strNum = m.group();
            number = Integer.parseInt(strNum);
            word = word.substring(0, word.indexOf(strNum));
        }

        return new Word.Builder().setWord(word).setHomonym(number);
    }
}
