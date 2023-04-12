package javastreams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class MapStreamCode {
    public void creatingHash1(int[] a){
//      third parameter of toMap() is to handle the collision of key
//      most important thing to note is boxed(). It's required because Arrays.stream(a) gives IntStream
//      but in order to collect the integers in Map we need Stream<Integer>.
        Map<Integer, Integer> countMap = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toMap(e -> e, v -> 1, Integer::sum));

        countMap.entrySet().forEach(System.out::println);
    }

    public void creatingHash2(int[] a){
//      same thing can be  achieved using groupingBy, but value field is Long
        Map<Integer, Long> countMap = Arrays.stream(a)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        countMap.entrySet().forEach(System.out::println);
    }

    public void creatingHash3(int[] a){
//      third parameter of toMap() is to handle the collision of key
        Map<Integer, Integer> countMap = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toMap(e -> e, v -> 1, (oldItem, newItem) -> oldItem+1));
        countMap.entrySet().forEach(System.out::println);
    }

    public void creatingHash4(List<Integer> a){
//      third parameter of toMap() is to handle the collision of key
//      boxed() is NOT applicable here because List.stream is already giving Stream<Integer>.
        Map<Integer, Integer> countMap = a.stream()
                .collect(Collectors.toMap(e -> e, v -> 1, Integer::sum));

        countMap.entrySet().forEach(System.out::println);
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,5,2,7,4,4,2,7,7,7};
        MapStreamCode test = new MapStreamCode();
//        test.creatingHash3(a);
        test.creatingHash4(List.of(4,5,2,7,4,4,2,7,7,7));
    }
}
