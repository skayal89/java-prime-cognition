package javastreams;

import model.Photo;

import java.util.*;
import java.util.stream.Collectors;

public class Stats {

    public int getTotalLikes(List<Photo> photos){
//        return photos.stream().mapToInt(Photo::getLikes).sum();
//        return photos.stream().map(Photo::getLikes).reduce(Integer::sum).orElse(0);

//        reduce() is generic to perform any operation either manual function or any in-built method
//        return photos.stream().map(Photo::getLikes).reduce((a,b)-> a+b).orElse(0);

//        This is useful where Collectors are required as parameter, for example while groupingBy
//        also Collectors.summingInt returns Integer, unlike reduce() which returns Optional
//        also Collectors.summingInt does not require to map(Photo::getLikes)
        return photos.stream().collect(Collectors.summingInt(Photo::getLikes));
    }

    public int getHighestLike(List<Photo> photos){
        return photos.stream().map(Photo::getLikes).max(Comparator.naturalOrder()).orElse(0);
//        return photos.stream().map(Photo::getLikes).reduce(Math::max).orElse(0);
    }

    public Photo getMostLikedPhoto(List<Photo> photos){
//        return photos.stream().collect(Collectors.maxBy(Comparator.comparing(Photo::getLikes))).orElse(null);
        return photos.stream().max(Comparator.comparing(Photo::getLikes)).orElse(null);
//        return photos.stream()
//                .reduce((photo1, photo2) -> photo1.getLikes() > photo2.getLikes() ? photo1 : photo2)
//                .orElse(null);
    }

    public Map<String, Integer> getTotalLikesByCity(List<Photo> photos){
        Map<String, Integer> cityLikesMap = photos.stream()
                .collect(Collectors.groupingBy(Photo::getCity,
                        Collectors.summingInt(Photo::getLikes)));

        cityLikesMap.entrySet().forEach(System.out::println);

        return cityLikesMap;
    }

    public Map<String, Optional<Photo>> getMaxLikesByCity(List<Photo> photos){
        Map<String, Optional<Photo>> cityLikesMap = photos.stream()
                .collect(Collectors.groupingBy(Photo::getCity,
                        Collectors.maxBy(Comparator.comparing(Photo::getLikes))));

        cityLikesMap.entrySet().forEach(System.out::println);

        return cityLikesMap;
    }

    public Map<String, Long> countPhotosByCity(List<Photo> photos){
        return photos.stream().collect(Collectors.groupingBy(Photo::getCity,
                Collectors.counting()));
    }

    public Map<String, Double> avgLikesByCity(List<Photo> photos){
        return photos.stream().collect(Collectors.groupingBy(Photo::getCity,
                Collectors.averagingInt(Photo::getLikes)));
    }

    public static void main(String[] args) {
        List<Photo> photos = Photo.getSamplePhotos();
        Stats s = new Stats();
        s.getMostLikedPhoto(photos);
        System.out.println(s.avgLikesByCity(photos));
    }
}
