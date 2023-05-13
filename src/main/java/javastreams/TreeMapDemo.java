package javastreams;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeMapDemo {

    public TreeMap<Integer, Integer> buildCountMap1(List<Integer> values){
        TreeMap<Integer, Integer> valueCountMap = values.stream()
                .collect(Collectors.toMap(
                        i -> i, // keyMapper
                        v -> 1, // valueMapper
                        (oldValue, newValue) -> oldValue + 1, // mergerFunction to handle collision
                        TreeMap::new)); // mapFactory - default is HashMap::new
        return valueCountMap;
    }

    public TreeMap<Integer, Long> buildCountMap2(List<Integer> values){
        TreeMap<Integer, Long> valueCountMap = values.stream()
                .collect(Collectors.groupingBy(
                        i -> i,
                        TreeMap::new,
                        Collectors.counting()
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
}
