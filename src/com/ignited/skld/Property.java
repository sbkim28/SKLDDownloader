package com.ignited.skld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * The Word Property
 *
 * @author Ignited
 */
public class Property{
    private final String wordClass;
    private final List<String> attributes;
    private final List<String> meanings;

    private Property(Builder builder) {
        this.wordClass = builder.wordClass;
        this.attributes = Collections.unmodifiableList(builder.attributes);
        this.meanings = Collections.unmodifiableList(builder.meanings);
    }

    /**
     * Gets word class.
     *
     * @return the word class
     */
    public String getWordClass() {
        return wordClass;
    }

    /**
     * Gets the list of attributes.
     *
     * @return the unmodifiable list of attributes
     */
    public List<String> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Property{" +
                "wordClass='" + wordClass + '\'' +
                ", attributes=" + attributes +
                ", meanings=" + meanings +
                '}';
    }

    /**
     * The Property Builder.
     * Instantiates a new Property
     */
    public static class Builder{
        private String wordClass;
        private final List<String> attributes;
        private final List<String> meanings;

        private final List<String> m;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            attributes = new ArrayList<>();
            meanings = new ArrayList<>();
            m = new ArrayList<>();
        }

        /**
         * Set word class
         *
         * @param wordClass the word class
         * @return this
         */
        public Builder setWordClass(String wordClass){
            this.wordClass = wordClass;
            return this;
        }

        /**
         * Add an attribute of the word
         *
         * @param attribute an attribute of the word
         * @return this
         */
        public Builder addAttribute(String attribute){
            this.attributes.add(attribute);
            return this;
        }

        /**
         * Add a collection of attributes of the word
         *
         * @param attributes the attributes
         * @return this
         */
        public Builder addAllAtrribute(Collection<String> attributes){
            this.attributes.addAll(attributes);
            return this;
        }

        @Deprecated
        public Builder next(){ return this;}

        /**
         * Add a meaning of the word.
         *
         * @param meaning a meaning of the word
         * @return this
         */
        public Builder addMeaning(String meaning){
            m.add(meaning);
            return this;
        }

        /**
         * Build the property of the word.
         *
         * @return the property of the word
         */
        public Property build(){
            return new Property(this);
        }
    }
}