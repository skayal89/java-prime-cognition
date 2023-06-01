package javastreams;

import model.Photo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;


public class GroupBy {

    public void groupByCity(List<Photo> photos){
        // key would be city and value would be list objects grouped by city
        Map<String, List<Photo>> res = photos.stream() // resultant lists would be ArrayList by default
                .collect(Collectors.groupingBy(Photo::getCity)); // by default groupingBy will call toList() to collect the result
        System.out.println(res);
    }

    public void groupByCityAndCollectInLinkedList(List<Photo> photos){
        // key would be city and value would be list objects grouped by city
        Map<String, List<Photo>> res = photos.stream() // resultant lists would be LinkedList
                .collect(Collectors.groupingBy(Photo::getCity, Collectors.toCollection(LinkedList::new)));
        res.entrySet().forEach(System.out::println);
    }

    public void groupByCityAndCollectInTreeSetToSortByTime(List<Photo> photos){
        // key would be city and value would be list objects grouped by city
        // this is an example where we are trying to collect in a collection which needs Comparator
        // also this is a cleanest way to sort the value list after groupingBy
        Map<String, Set<Photo>> res = photos.stream() // resultant lists would be TreeSet which will be sorted
                .collect(Collectors.groupingBy(Photo::getCity,
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Photo::getDateTime)))));
        res.entrySet().forEach(System.out::println);
    }

    public void groupByCityAndCollectDateTimeToSortByCity(List<Photo> photos) {
//        compute the set of last names of people in each city, where the city names are sorted
        Map<String, Set<LocalDateTime>> res = photos.stream() // resultant lists would be TreeSet which will be sorted
                .collect(Collectors.groupingBy(
                        Photo::getCity, // grouping by city
                        TreeMap::new, // sort by city which is the grouping by
                        Collectors.mapping(Photo::getDateTime, Collectors.toSet()))); // mapping() helped to collect Set<LocalDateTime> instead of Set<Photo>
        res.entrySet().forEach(System.out::println);
    }

    public Map<String, Collection<Photo>> groupByCityAndCollectInTreeSetToSortByTimeAndPopulateNewName(List<Photo> photos){
        // key would be city and value would be list objects grouped by city
        // this is an example where we are trying to collect in a collection which needs Comparator
        // also this is a cleanest way to sort the value list after groupingBy
        Map<String, Collection<Photo>> res = photos.stream() // resultant lists would be TreeSet (BST) which will be sorted
                .collect(Collectors.groupingBy(Photo::getCity,
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Photo::getDateTime))), // sort the photos groupingBy city
                                p -> new LinkedList<>(populateNewNameAndLikes(p)) // populate new name and likes and preserve the sorted order using LinkedList
                        )
                        ));
        res.entrySet().forEach(System.out::println);
        return res;
    }

    public void groupByCityAndSortValueListOfEachGroupByTime(List<Photo> photos){
        // key would be city and value would be sorted list objects grouped by city
        Map<String, List<Photo>> photosGroupedByCityAndSortByTime = photos.stream()
                .collect(Collectors.groupingBy(Photo::getCity, // group by city
                        Collectors.mapping(Function.identity(), // sort collected objects by time and store them in a list as the value of corresponding city
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream()
                                                .sorted(Comparator.comparing(Photo::getDateTime))
                                                .collect(Collectors.toList())))));

        for(Map.Entry<String, List<Photo>> entry : photosGroupedByCityAndSortByTime.entrySet()){
            System.out.println(entry);
        }
    }

    public void groupByCityAndSortByTimeAndModifyListObjectsAfterSorting(List<Photo> photos){
        // key would be city and value would be sorted list objects grouped by city
        // groupByCityAndCollectInTreeSetToSortByTimeAndPopulateNewName() method is better than this one. This method shows another way of doing the task.
        Map<String, List<Photo>> photosGroupedByCityAndSortByTime = photos.stream()
                .collect(Collectors.groupingBy(Photo::getCity, // group by city
                        Collectors.mapping(Function.identity(), // sort collected objects by time and store them in a list as the value of corresponding city
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream()
                                                .sorted(Comparator.comparing(Photo::getDateTime))
                                                .collect(Collectors.toList())))));

        photosGroupedByCityAndSortByTime.entrySet()
                .stream()
                .forEachOrdered(entry -> populateNewNameAndLikes(entry.getValue()));

        for(Map.Entry<String, List<Photo>> entry : photosGroupedByCityAndSortByTime.entrySet()){
            System.out.println(entry);
        }
    }

    private Collection<Photo> populateNewNameAndLikes(Collection<Photo> photos){
        int i = 1;
        for(Photo p : photos){
            p.setNewName(p.getName() + i++);
//            p.setLikes(getRandomNumberUsingNextInt(50, 200));
        }
        return photos;
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void groupByCityAndSortByTimeAndPopulateLikesAndFlattenAllPhotosSortedByLikesDesc(List<Photo> photos){
        // key would be city and value would be sorted list objects grouped by city
        Map<String, List<Photo>> photosGroupedByCityAndSortByTime = photos.stream()
                .collect(Collectors.groupingBy(Photo::getCity, // group by city
                        Collectors.mapping(Function.identity(), // sort collected objects by time and store them in a list as the value of corresponding city
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream()
                                                .sorted(Comparator.comparing(Photo::getDateTime))
                                                .collect(Collectors.toList())))));

        photosGroupedByCityAndSortByTime.entrySet()
                .stream()
                .forEachOrdered(entry -> populateNewNameAndLikes(entry.getValue()));

        List<Photo> sortedByLikeDesc = photosGroupedByCityAndSortByTime.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Photo::getLikes, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        for(Map.Entry<String, List<Photo>> entry : photosGroupedByCityAndSortByTime.entrySet()){
            System.out.println(entry);
        }
    }

    public void groupByCityAndSortByCity(List<Photo> photos){
        // key would be city and value would be list of Photos grouped by city
        // by default result of groupingBy is collected in HashMap which does not preserve the insertion order which is required if sorting applied before the grouping
        Map<String, List<Photo>> photosGroupsSorted = photos.stream()
                .sorted(Comparator.comparing(Photo::getDateTime))
                .collect(Collectors.groupingBy(Photo::getCity,
                        LinkedHashMap::new, // in order to preserve the sorted order after groupingBy, collect the result in LinkedHashMap to preserve the insertion order
                        Collectors.toList())); // value List<Photo> would be collected in ArrayList

        for(Map.Entry<String, List<Photo>> entry : photosGroupsSorted.entrySet()){
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        GroupBy g = new GroupBy();
//        g.groupByCity(List.of(p1,p2,p3,p4,p5));
//        g.groupByCityAndSortByTime(List.of(p1,p2,p3,p4,p5));
//        g.groupByCityAndSortByTimeAndModifyListObjectsAfterSorting(List.of(p1,p2,p3,p4,p5));
//        g.groupByCityAndCollectInTreeSetToSortByTimeAndPopulateNewName(Photo.getSamplePhotos());
        g.groupByCityAndCollectDateTimeToSortByCity(Photo.getSamplePhotos());
    }
}
