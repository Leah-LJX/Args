package edu.utexas.sypet;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

class MapValueComparator implements Comparator<Map.Entry<String, Double>> {

    @Override
    public int compare(Entry<String, Double> me1, Entry<String, Double> me2) {

        return me2.getValue().compareTo(me1.getValue());
    }
}
