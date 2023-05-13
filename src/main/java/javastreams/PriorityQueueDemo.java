package javastreams;

import model.Photo;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public PriorityQueue<Integer> buildMinHeap(List<Integer> values){
        // a.compareTo(b) is equivalent to a - b, which is for ascending order comparator
        // Alternatively we can use Integer::compare which is Integer.compare(a,b)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(Comparator.naturalOrder());
//        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a.compareTo(b));
        minHeap.addAll(values);
        System.out.println(minHeap);
        return minHeap;
    }

    public void buildMaxHeap(){
        // a.compareTo(b) is equivalent to a - b, which is for ascending order comparator
        // Alternatively we can use Integer::compare which is Integer.compare(a,b)
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        maxHeap.add(10);
        maxHeap.add(11);
        maxHeap.add(1);
        maxHeap.add(7);
        maxHeap.add(5);
        maxHeap.add(6);
        System.out.println(maxHeap.poll());

    }

    public int getKMin(List<Integer> values, int k){
        PriorityQueue<Integer> minHeap = buildMinHeap(values);
        validate(minHeap, k);
        return pollKMin(minHeap, k);
    }

    public int pollKMin(PriorityQueue<Integer> minHeap, int k){
        int min = 0;
        for(int i = 0; i < k && !minHeap.isEmpty(); i++){
            min = minHeap.poll();
        }
        return min;
    }

    protected void validate(PriorityQueue<Integer> minHeap, int k){
        if(minHeap == null || minHeap.isEmpty() || minHeap.size() < k) {
            throw new IllegalArgumentException("k is larger than input");
        }
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
//        System.out.println(new PriorityQueueTest().getLatestPhotoByDate());
//        System.out.println(new PriorityQueueTest().getOldestPhotoByDate());

    }
}
