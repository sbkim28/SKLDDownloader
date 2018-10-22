package com.ignited.skld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word implements IWord{

    private int homonym;
    private String word;
    private boolean containsOldHangeul;
    private final List<Property> properties;

    protected Word(int homonym, String word, boolean containsOldHangeul){
        this.homonym = homonym;
        this.word = word;
        this.containsOldHangeul = containsOldHangeul;
        properties = new ArrayList<>();
    }

    public int getHomonym() {
        return homonym;
    }

    public String getWord() {
        return word;
    }

    public boolean isContainsOldHangeul() {
        return containsOldHangeul;
    }

    void addProperty(Property property){
        properties.add(property);
    }

    public List<Property> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    @Override
    public String toString() {
        return "Word{" +
                "homonym=" + homonym +
                ", word='" + word + '\'' +
                ", containsOldHangeul=" + containsOldHangeul +
                ", properties=" +  properties +
                '}';
    }

    public static class Property{
        private String wordClass;
        private List<String> attributes;
        private List<Section> sections;

        Property(String wordClass, List<String> attributes) {
            this.wordClass = wordClass;
            this.attributes = attributes;
            sections = new ArrayList<>();
        }

        void addSection(Section section){
            sections.add(section);
        }

        public String getWordClass() {
            return wordClass;
        }

        public List<String> getAttributes() {
            return Collections.unmodifiableList(attributes);
        }

        public List<Section> getSections() {
            return Collections.unmodifiableList(sections);
        }

        @Override
        public String toString() {
            return "Property{" +
                    "wordClass='" + wordClass + '\'' +
                    ", attribute=" + attributes +
                    ", sections=" + sections +
                    '}';
        }
    }
}
