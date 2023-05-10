package javastreams;

import model.Photo;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public void buildMinHeap(){
        // a.compareTo(b) is equivalent to a - b, which is for ascending order comparator
        // Alternatively we can use Integer::compare which is Integer.compare(a,b)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a.compareTo(b));
        minHeap.add(10);
        minHeap.add(11);
        minHeap.add(1);
        minHeap.add(7);
        minHeap.add(5);
        minHeap.add(6);
        System.out.println(minHeap.poll());

    }

    public void buildMaxHeap(){
        // a.compareTo(b) is equivalent to a - b, which is for ascending order comparator
        // Alternatively we can use Integer::compare which is Integer.compare(a,b)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));
        maxHeap.add(10);
        maxHeap.add(11);
        maxHeap.add(1);
        maxHeap.add(7);
        maxHeap.add(5);
        maxHeap.add(6);
        System.out.println(maxHeap.poll());

    }

    public Photo getLatestPhotoByDate(){
        List<Photo> photos = Photo.getSamplePhotos();
        PriorityQueue<Photo> maxHeap = new PriorityQueue<>(Comparator.comparing(Photo::getDateTime));
        maxHeap.addAll(photos); // O(nlog n)
        return maxHeap.peek();
    }

    public Photo getOldestPhotoByDate(){
        List<Photo> photos = Photo.getSamplePhotos();
        PriorityQueue<Photo> minHeap = new PriorityQueue<>(Comparator.comparing(Photo::getDateTime, Comparator.reverseOrder()));
        minHeap.addAll(photos); // O(nlog n)
        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println(new PriorityQueueTest().getLatestPhotoByDate());
        System.out.println(new PriorityQueueTest().getOldestPhotoByDate());
    }
}
