package javastreams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class MapStreamCode {
    public void creatingHash1(int[] a){
//      third parameter of toMap() is to handle the collision of key, otherwise IllegalStateException would be thrown
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
//      third parameter of toMap() is to handle the collision of key, otherwise IllegalStateException would be thrown
        Map<Integer, Integer> countMap = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toMap(e -> e, v -> 1, (oldItem, newItem) -> oldItem+1));
        countMap.entrySet().forEach(System.out::println);
    }

    public void creatingHash4(List<Integer> a){
//      third parameter of toMap() is to handle the collision of key, otherwise IllegalStateException would be thrown
//      boxed() is NOT applicable here because List.stream is already giving Stream<Integer>.
        Map<Integer, Integer> countMap = a.stream()
                .collect(Collectors.toMap(e -> e, v -> 1, Integer::sum));

        countMap.entrySet().forEach(System.out::println);
    }

    public Map<String, Photo> indexPhotoByName(List<Photo> photos){
        // Not allowing to store multiple photos with same name, IllegalStateException would be thrown when collision occurs
        Map<String, Photo> namePhotoMap = photos.stream()
                .collect(Collectors.toMap(Photo::getName,
                        Function.identity()));
        namePhotoMap.entrySet().forEach(System.out::println);
        return namePhotoMap;
    }

    public Photo findPhotoByName(List<Photo> photos, String target){
        Map<String, Photo> index = indexPhotoByName(photos);

        return index.getOrDefault(target, null);
    }

    public Collection<Photo> getPhotosSortedByCityAndTime(List<Photo> photos){
        // example of flatten a map by collecting all values (i.e. multiple Collection<Photo> of each Map.Entry) together in single collection
        GroupBy g = new GroupBy();
        Map<String, Collection<Photo>> map = g.groupByCityAndCollectInTreeSetToSortByTimeAndPopulateNewName(photos);

        Collection<Photo> flatResult = map.values().stream()  // stream of list of Collection from each map entry
                .flatMap(Collection::stream) // stream of Photo by flattening list of Collection
                .collect(Collectors.toCollection(LinkedList::new)); // collected in LinkedList to preserve the insertion order

        flatResult.forEach(System.out::println);
        return flatResult;
    }

    public List<Photo> flatMultipleLists(List<Photo>... photos){
        // the input here is array of Lists instead of List<List<Integer>>
        // we have to use Stream.of() to get the stream out of array of items
        // after getting the stream from array of Lists, we can use flatMap() to collect all photos from different lists in one collection
        List<Photo> flatPhotos = Stream.of(photos).flatMap(List::stream).collect(Collectors.toList());

        flatPhotos.forEach(System.out::println);
        return flatPhotos;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,5,2,7,4,4,2,7,7,7};
        MapStreamCode test = new MapStreamCode();
//        test.creatingHash3(a);
//        test.creatingHash4(List.of(4,5,2,7,4,4,2,7,7,7));
//        test.indexPhotoByName(Photo.getSamplePhotos());
//        System.out.println(test.findPhotoByName(Photo.getSamplePhotos(), "abc"));
//        System.out.println(test.findPhotoByName(Photo.getSamplePhotos(), "abcd"));
        test.getPhotosSortedByCityAndTime(Photo.getSamplePhotos());
    }
}
