package com.javastreams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTutorial {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(3,1,4,2,3,1,2,2,6,5,6);

        System.out.println(new StreamTutorial().findThreeMin(nums));
    }

    public int sumUptoN(int n){
        return IntStream.rangeClosed(1, n).sum();
    }

    public List<Integer> multipleOfFive(List<Integer> nums){
        return nums.stream().filter(i -> i % 5 == 0).collect(Collectors.toList());
    }

    public List<Integer> removeDuplicates(List<Integer> nums){
        return nums.stream().distinct().collect(Collectors.toList());
    }

    public Map<Integer, Long> frequency(List<Integer> nums){
        return nums.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<Character, Long> frequencyOfChars(String word){
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<Integer> commonElements(List<Integer> a, List<Integer> b){
        return a.stream().filter(b::contains).collect(Collectors.toList());
    }

    public List<Integer> reverseSort(List<Integer> a){
        return a.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public Set<Integer> findDuplicates(List<Integer> a){
        Set<Integer> set = new HashSet<>();
        return a.stream().filter(i -> !set.add(i)).collect(Collectors.toSet());
    }

    public Set<String> findWordStartWithNumber(List<String> words){
        return words.stream().filter(s -> Character.isDigit(s.charAt(0))).collect(Collectors.toSet());
    }

    public Integer findMax(List<Integer> nums){
        return nums.stream().max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
    }

    public Integer findMin(List<Integer> nums){
        return nums.stream().min(Comparator.naturalOrder()).orElse(null);
    }

    public List<Integer> findThreeMin(List<Integer> nums){
        return nums.stream().sorted().limit(3).collect(Collectors.toList());
    }

    public List<Integer> findThreeMax(List<Integer> nums){
        return nums.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
    }

    public Integer secondLargestNumber(List<Integer> nums){
        return nums.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);
    }

    public Integer kthLargestNumber(List<Integer> nums, int k){
        return nums.stream().sorted(Comparator.reverseOrder()).skip(k-1).findFirst().orElse(null);
    }

    public String longestWord(List<String> words){
        return words.stream()
                .reduce((s1,s2)->s1.length()>s2.length() ? s1 : s2)
                .orElse("");
    }

    public List<String> sortByLength(List<String> words){
        return words.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
    }

    public List<String> sortByLengthReverse(List<String> words){
        return words.stream().sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).collect(Collectors.toList());
    }
}
