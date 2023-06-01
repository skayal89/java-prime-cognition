package javastreams;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeMapDemo {

    public TreeMap<Integer, Integer> buildCountMap1(List<Integer> values){
        TreeMap<Integer, Integer> valueCountMap = values.stream()
                .collect(Collectors.toMap(
                        i -> i, // keyMapper, i -> i is equivalent to Function.identity()
                        v -> 1, // valueMapper
                        (oldValue, newValue) -> oldValue + 1, // mergerFunction to handle collision
                        TreeMap::new)); // mapFactory - default is HashMap::new
        return valueCountMap;
    }

    public TreeMap<Integer, Long> buildCountMap2(List<Integer> values){
        TreeMap<Integer, Long> valueCountMap = values.stream()
                .collect(Collectors.groupingBy(
                        i -> i, // group by integer value itself, can be used Function.identity() as well
                        TreeMap::new, // mapFactory - what would resultant Map implementation, using TreeMap to sort by Key
                        Collectors.counting() // downstream collectors - either collect end result or do further processing using multiple collectors
                ));
        return valueCountMap;
    }

    public TreeMap<Integer, Integer> buildCountMap3(List<Integer> values){
        TreeMap<Integer, Integer> valueCountMap = values.stream()
                .collect(Collectors.toMap(
                        i -> i, // keyMapper
                        v -> 1, // valueMapper
                        Integer::sum, // mergerFunction to handle collision
                        TreeMap::new)); // mapFactory - default is HashMap::new
        return valueCountMap;
    }

    public static void main(String[] args) {
        TreeMapDemo mapDemo = new TreeMapDemo();
        System.out.println(mapDemo.buildCountMap1(List.of(2,3,2,1,2,3,7,2,4)));
        System.out.println(mapDemo.buildCountMap2(List.of(2,3,2,1,2,3,7,2,4)));
        System.out.println(mapDemo.buildCountMap3(List.of(2,3,2,1,2,3,7,2,4)));
        System.out.println(mapDemo.buildCountMap3(List.of()));
    }
}
