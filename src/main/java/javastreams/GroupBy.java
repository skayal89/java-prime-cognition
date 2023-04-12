package javastreams;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;


public class GroupBy {

    public void groupByCity(List<Photo> photos){
        // key would be city and value would be list objects grouped by city
        Map<String, List<Photo>> res = photos.stream() // resultant lists would be ArrayList by default
                .collect(Collectors.groupingBy(photo -> photo.city)); // by default groupingBy will call toList() to collect the result
        System.out.println(res);
    }

    public void groupByCityAndCollectInLinkedList(List<Photo> photos){
        // key would be city and value would be list objects grouped by city
        Map<String, List<Photo>> res = photos.stream() // resultant lists would be LinkedList
                .collect(Collectors.groupingBy(photo -> photo.city, Collectors.toCollection(LinkedList::new)));
        System.out.println(res);
    }

    public void groupByCityAndSortValueListOfEachGroupByTime(List<Photo> photos){
        // key would be city and value would be sorted list objects grouped by city
        Map<String, List<Photo>> photosGroupedByCityAndSortByTime = photos.stream()
                .collect(Collectors.groupingBy(photo -> photo.city, // group by city
                        Collectors.mapping(Function.identity(), // sort collected objects by time and store them in a list as the value of corresponding city
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream()
                                                .sorted(Comparator.comparing(photo -> photo.dateTime))
                                                .collect(Collectors.toList())))));

        for(Map.Entry<String, List<Photo>> entry : photosGroupedByCityAndSortByTime.entrySet()){
            System.out.println(entry);
        }
    }

    public void groupByCityAndSortByTimeAndModifyListObjectsAfterSorting(List<Photo> photos){
        // key would be city and value would be sorted list objects grouped by city
        Map<String, List<Photo>> photosGroupedByCityAndSortByTime = photos.stream()
                .collect(Collectors.groupingBy(photo -> photo.city, // group by city
                        Collectors.mapping(Function.identity(), // sort collected objects by time and store them in a list as the value of corresponding city
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream()
                                                .sorted(Comparator.comparing(photo -> photo.dateTime))
                                                .collect(Collectors.toList())))));

        photosGroupedByCityAndSortByTime.entrySet()
                .stream()
                .forEachOrdered(entry -> populateNewNameAndLikes(entry.getValue()));

        for(Map.Entry<String, List<Photo>> entry : photosGroupedByCityAndSortByTime.entrySet()){
            System.out.println(entry);
        }
    }

    private void populateNewNameAndLikes(List<Photo> photos){
        int i = 1;
        for(Photo p : photos){
            p.newName = p.name + i++;
            p.likes = getRandomNumberUsingNextInt(50, 200);
        }
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void groupByCityAndSortByTimeAndPopulateLikesAndFlattenAllPhotosSortedByLikesDesc(List<Photo> photos){
        // key would be city and value would be sorted list objects grouped by city
        Map<String, List<Photo>> photosGroupedByCityAndSortByTime = photos.stream()
                .collect(Collectors.groupingBy(photo -> photo.city, // group by city
                        Collectors.mapping(Function.identity(), // sort collected objects by time and store them in a list as the value of corresponding city
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream()
                                                .sorted(Comparator.comparing(photo -> photo.dateTime))
                                                .collect(Collectors.toList())))));

        photosGroupedByCityAndSortByTime.entrySet()
                .stream()
                .forEachOrdered(entry -> populateNewNameAndLikes(entry.getValue()));

        List<Photo> sortedByLikeDesc = photosGroupedByCityAndSortByTime.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(p -> p.likes, Comparator.reverseOrder()))
                .toList();

        for(Map.Entry<String, List<Photo>> entry : photosGroupedByCityAndSortByTime.entrySet()){
            System.out.println(entry);
        }
    }

    public void groupByCityAndSortByCity(List<Photo> photos){
        // key would be city and value would be list of Photos grouped by city
        // by default result of groupingBy is collected in HashMap which does not preserve the insertion order which is required if sorting applied to the grouping key
        Map<String, List<Photo>> photosGroupedByCity = photos.stream()
                .sorted(Comparator.comparing(photo -> photo.city))
                .collect(Collectors.groupingBy(photo -> photo.city),
                        LinkedHashMap::new, // in order to preserve the sorted order after groupingBy, collect the result in LinkedHashMap to preserve the insertion order
                        Collectors.toList()); // value List<Photo> would be collected in ArrayList

        for(Map.Entry<String, List<Photo>> entry : photosGroupsSorted.entrySet()){
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Photo p1 = new Photo("abc", "kol", LocalDateTime.parse("2022-01-03 13:09:08", formatter));
        Photo p2 = new Photo("def", "kol", LocalDateTime.parse("2018-01-03 13:09:08", formatter));
        Photo p3 = new Photo("pqr", "hyd", LocalDateTime.parse("2019-01-03 13:09:08", formatter));
        Photo p4 = new Photo("xyz", "hyd", LocalDateTime.parse("2017-01-03 13:09:08", formatter));
        Photo p5 = new Photo("wtey", "kol", LocalDateTime.parse("2021-01-03 13:09:08", formatter));
        GroupBy g = new GroupBy();
//        g.groupByCity(List.of(p1,p2,p3,p4,p5));
//        g.groupByCityAndSortByTime(List.of(p1,p2,p3,p4,p5));
        g.groupByCityAndSortByTimeAndModifyListObjectsAfterSorting(List.of(p1,p2,p3,p4,p5));
    }
}

class Photo {
    String name;
    String city;
    String newName;
    LocalDateTime dateTime;

    int likes;

    public Photo(String name, String city, LocalDateTime dateTime){
        this.name = name;
        this.city = city;
        this.dateTime = dateTime;
    }

    public String getCity(String city){
        return city;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", newName='" + newName + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}