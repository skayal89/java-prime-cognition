package javastreams;

import model.Photo;

import java.util.*;
import java.util.stream.Collectors;

public class StringOperation {

    public String getNameOfAllPhotos(List<Photo> photos){
        String allNames = photos.stream().map(Photo::getName).collect(Collectors.joining(", "));
        System.out.println(allNames);
        return allNames;
    }

    public Collection<String> splitStringAndCollectInList(){
        String allPhotos = getNameOfAllPhotos(Photo.getSamplePhotos()); // comma separated photo names
        String[] splittedNames = allPhotos.split(",");

//        List<String> photoNames = Arrays.stream(splittedNames)
//                .collect(Collectors.toList()); // this includes extra space after each comma

        List<String> photoNames = Arrays.stream(splittedNames)
                .map(String::trim)
                .collect(Collectors.toList());

        photoNames.forEach(System.out::println);
        return photoNames;
    }

    public Collection<String> splitStringAndSortAlphabeticallyAndCollect(){
        String allPhotos = getNameOfAllPhotos(Photo.getSamplePhotos()); // comma separated photo names
        String[] splittedNames = allPhotos.split(",");

        Collection<String> photoNames = Arrays.stream(splittedNames)
                .map(String::trim) // sorting can be impacted by extra spaces
                .collect(Collectors.toCollection(TreeSet::new));

        photoNames.forEach(System.out::println);
        return photoNames;
    }

    public Collection<String> splitStringAndSortAlphabeticallyAndCollect2(){
        String allPhotos = getNameOfAllPhotos(Photo.getSamplePhotos()); // comma separated photo names
        String[] splittedNames = allPhotos.split(",");

        Collection<String> photoNames = Arrays.stream(splittedNames)
                .map(String::trim) // sorting can be impacted by extra spaces
                .sorted() // natural order of sorting, so no need to define Comparator.comparing
                .collect(Collectors.toList());

        photoNames.forEach(System.out::println);
        return photoNames;
    }

    public Collection<String> splitStringAndCollectNameStartWithA(){
        String allPhotos = getNameOfAllPhotos(Photo.getSamplePhotos()); // comma separated photo names
        String[] splittedNames = allPhotos.split(",");

        Collection<String> photoNames = Arrays.stream(splittedNames)
                .map(String::trim) // sorting can be impacted by extra spaces
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());

        photoNames.forEach(System.out::println);
        return photoNames;
    }

    public Collection<String> splitStringAndConvertToListUsing3ParamCollect(){
        /*
        https://www.digitalocean.com/community/tutorials/java-stream-collect-method-examples
        Must Read above tutorial to understand 3 parameter collect() - very useful

        The Collector is an interface that provides a wrapper for the supplier, accumulator,
        and combiner objects. The three parameters of the collect() function are:

        1. supplier: a function that creates a new mutable result container. For the parallel execution,
                this function may be called multiple times and it must return a fresh value each time.
        2. accumulator is a stateless function that must fold an element into a result container.
        3. combiner is a stateless function that accepts two partial result containers and merges them,
                which must be compatible with the accumulator function.
         */

        Collection<String> photoNames = Photo.getSamplePhotos().stream()
                .map(Photo::getName)
                .collect(ArrayList::new, // supplier function is returning a new StringBuilder object in every call, for sequential stream it would one call
                        ArrayList::add, // accumulator function is adding the list string element to the ArrayList object.
                        ArrayList::addAll); // call only if the stream is parallel to combine multiple ArrayLists

        photoNames.forEach(System.out::println);
        return photoNames;
    }

    public String sortString1(String s){
        char c[] = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public String sortString2(String s){
        return s.chars().boxed().map(Character::toString).sorted().collect(Collectors.joining());
    }

    public static void main(String[] args) {
        StringOperation so = new StringOperation();
//        so.getNameOfAllPhotos(Photo.getSamplePhotos());
//        so.splitStringAndConvertToListUsing3ParamCollect();
        System.out.println(so.sortString2("geeksforgeeks"));

        char ch = 'a';
        int[] hash = new int[256];
        hash[ch]++;
        System.out.println(((int)ch));
        System.out.println(hash[ch]);
        System.out.println(ch - 'a');
    }
}
