import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalUtils {

    /**
     * @param strings: the input collection of <code>String</code>s.
     * @return a collection of <code>String</code>s that start with a
     * capital letter (i.e., ‘A’ through ‘Z’).
     */
    public static Collection<String> capitalized(Collection<String> strings){

        return strings.stream().filter(y -> y.charAt(0)>='A' && y.charAt(0)<='Z').collect(Collectors.toList());
    }



    /**
     * Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
     *
     * @param strings: the given collection of <code>String</code>s.
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
    If <code>true</code>, then the element encountered earlier in
     * the iteration is returned, otherwise the element encountered
     * later is returned.
     * @return the longest <code>String</code> in the given collection,
     * where ties are broken based on <code>from_start</code>.
     */
    public static String longest(Collection<String> strings, boolean from_start){

        return from_start ? findLargest(strings).get(0) : findLargest(strings).get(findLargest(strings).size()-1);


        // return from_start ? strings.stream().max(Comparator.comparingInt(String::length)).get() : strings.stream().max(Comparator.comparingInt(String::length).reversed()).get()
    }

    public static List<String> findLargest (Collection<String> strings){
        return strings.stream().filter(e -> e.length() == (strings.stream().max(Comparator.comparingInt(String::length)).get().length())).collect(Collectors.toList());
    }

    /**
     * Find and return the least element from a collection of given elements that are comparable.
     *

     1

     CSE 216 Homework III Submission Deadline: Apr 21, 2019
     * @param items: the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     * If <code>true</code>, then the element encountered earlier in
     * the iteration is returned, otherwise the element encountered
     * later is returned.
     * @param <T>: the type parameter of the collection (i.e., the items are all of type T).
     * @return the least element in <code>items</code>, where ties are
     * broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start){
        //return items.stream().min(Comparator.naturalOrder()).orElse();
        return items.stream().reduce((x,y) -> x.compareTo(y) < 0 ? y : x.compareTo(y) > 0 ? x : x.compareTo(y) == 0 ? from_start ? x : y : y).get();
                //.stream().reduce((x,y) -> x.compareTo(y) == 1 ? x : x.compareTo(y) == -1 ? y : null).get();
    }

    /**
     * Flattens a map to a stream of <code>String</code>s, where each element in the list
     * is formatted as "key -> value".
     *
     * @param aMap the specified input map.
     * @param <K> the type parameter of keys in <code>aMap</code>.
     * @param <V> the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap.keySet().stream().flatMap(val -> Stream.of(val + " -> " + aMap.get(val))).collect(Collectors.toList());
    }


}
