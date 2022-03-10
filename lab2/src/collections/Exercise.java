package collections;

import java.util.*;
import java.util.stream.Collectors;
import collections.entity.*;

public class Exercise {

    // T0) Write a method to insert an element into the array list at the first position
    public static <T> void pushFront(List<T> list, T item) {
        list.add(0, item);
    }

    // T1) Write a method to retrieve an element (at a specified index) from a given list.
    public static <T> T getAt(List<T> list, int index) {
        return index >= 0 && index < list.size() ? list.get(index) : null;
    }

    // T2) Write a method to remove the third element from an array list.
    public static <T> void removeThird(ArrayList<T> list) {
        if (list.size() > 0) list.remove(2);
    }

    // T3) Write a method to search an element in a list.
    public static <T> int find(List<T> list, T item) {
        return list.indexOf(item); 
    }

    // T4) Write a method to sort a given array list. (list of String/Integer/Dog).
    // Implement Dog class with attribute breed and weight, sort the array by weight property.
    // Tip: implement the task with Comparator and Comparable
    public static <T extends Comparable<T>> void sort(ArrayList<T> list) {
        Collections.sort(list);
    }

    // T5) Write a method to replace the second element of a ArrayList with the specified element.
    public static <T> void replaceSecond(ArrayList<T> list, T item) {
        if (list.size() >= 2) list.set(1, item);
    }

    // T6)?? Write a Java program to count the number of key-value (size) mappings in a map.
    public static <K, V> int countPairs(Map<K, V> map) {
        return map.size();
    }

    // T7) Write the following structure against aircraft tail number associate
    // list of leg information (fromAirport, toAirport, date).
    // Fill test information
    // Extract legs inside list/set that have from/to airport for a specific airport
    // (Example: All legs for airport LBSF)
    public static List<Leg> getLegs(List<Leg> legs, String airport) {
        return legs.stream()
            .filter(leg -> leg.getToAirport().equals(airport) || leg.getFromAirport().equals(airport))
            .collect(Collectors.toList());
    }

}
