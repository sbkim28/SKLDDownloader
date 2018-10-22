package com.ignited.skld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Section {
    private int sector;
    private List<Polysemy> polysemies;

    Section(int sector) {
        this.sector = sector;
        this.polysemies = new ArrayList<>();
    }

    void addPolysemy(Polysemy polysemy){
        polysemies.add(polysemy);
    }

    public int getSector() {
        return sector;
    }

    public List<Polysemy> getPolysemies() {
        return Collections.unmodifiableList(polysemies);
    }

    @Override
    public String toString() {
        return "Section{" +
                "sector=" + sector +
                ", polysemies=" + polysemies +
                '}';
    }

    public static class Polysemy {
        private int index;
        private String meaning;

        Polysemy(int index, String meaning) {
            this.index = index;
            this.meaning = meaning;
        }

        public int getIndex() {
            return index;
        }

        public String getMeaning() {
            return meaning;
        }

        @Override
        public String toString() {
            return "Polysemy{" +
                    "index=" + index +
                    ", meaning='" + meaning + '\'' +
                    '}';
        }
    }
}
