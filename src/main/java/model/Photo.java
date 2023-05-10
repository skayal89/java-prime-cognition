package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
public class Photo {
    private String name;
    private String city;
    private String newName;
    private LocalDateTime dateTime;
    private Integer likes;

    public Photo(String name, String city, LocalDateTime dateTime, Integer likes){
        this.name = name;
        this.city = city;
        this.dateTime = dateTime;
        this.likes = likes;
    }

    public static List<Photo> getSamplePhotos(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Photo p1 = new Photo("abc", "kol", LocalDateTime.parse("2022-01-03 13:09:08", formatter),5);
        Photo p2 = new Photo("def", "kol", LocalDateTime.parse("2018-01-03 13:09:08", formatter),23);
        Photo p3 = new Photo("pqr", "hyd", LocalDateTime.parse("2019-01-03 13:09:08", formatter),97);
        Photo p4 = new Photo("xyz", "hyd", LocalDateTime.parse("2017-01-03 13:09:08", formatter),8);
        Photo p5 = new Photo("wtey", "kol", LocalDateTime.parse("2021-01-03 13:09:08", formatter),100);
        Photo p6 = new Photo("azzn", "blr", LocalDateTime.parse("2022-01-03 13:09:08", formatter),80);
        Photo p7 = new Photo("yyfv", "blr", LocalDateTime.parse("2019-01-03 13:09:08", formatter),20);
        return List.of(p1,p2,p3,p4,p5,p6,p7);
    }
}