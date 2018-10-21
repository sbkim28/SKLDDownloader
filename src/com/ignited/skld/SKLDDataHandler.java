package com.ignited.skld;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SKLDDataHandler {

    public static void save(String path, List<Word> wordList) throws IOException {
        File file = new File(path);
        Writer writer = new FileWriter(file);
        Gson gson = new GsonBuilder().create();
        gson.toJson(wordList, writer);
        writer.close();
    }

    public static List<Word> load(String path) throws FileNotFoundException {
        File file = new File(path);
        Reader reader = new FileReader(file);
        Gson gson = new GsonBuilder().create();
        Word[] words = gson.fromJson(reader, Word[].class);
        return new ArrayList<>(Arrays.asList(words));
    }

}
