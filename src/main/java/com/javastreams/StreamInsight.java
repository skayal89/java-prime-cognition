package com.javastreams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamInsight {

    public static void main(String[] args) {
        // count numbers
        int[] a = new int[] {2,1,5,3,4,2,3,1,6,4,4,7,7,7,7,7};
        Map<Integer, Long> countMap = Arrays.stream(a).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(countMap); // unordered map

        // first not repeating element
        Map<Integer, Long> countMapOrdered = Arrays.stream(a).boxed()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println(countMapOrdered);
        countMapOrdered.entrySet().stream()
                .filter(i -> i.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().ifPresent(System.out::println);

        // second highest number
        // Three Approaches -
        // 1) Sort in descending length,
        // 2) Max heap
        // 3) Min Heap
        int k = 2; // 2nd highest
        int[] b = new int[]{3,1,6,2,4};
        List<Integer> integerList = Arrays.stream(b).boxed()
                .sorted(Comparator.reverseOrder()) // Collections.reverseOrder() can also be used
                .skip(k-1) // skip the highest number. Second highest should be the first element
                .collect(Collectors.toList());
        System.out.println("second highest number " +integerList);

        // Min heap and max heap creation
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // default is min heap

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(b).boxed().forEach(maxHeap::add);
        System.out.println(maxHeap);
        maxHeap.poll();
        int secondHighest = maxHeap.poll();
        System.out.println("2nd highest "+secondHighest);


        //longest string from given array
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("hello");
        list.add("bye");
        list.add("somnath");

        // Two Approaches -
        // 1) Sort in descending length,
        // 2) Reduce the list to one element which should be max length

        String longestString = list.stream()
                .reduce((s1,s2)->s1.length()>s2.length() ? s1 : s2)
                .get();
        System.out.println(longestString);

        // numbers which starts with 1
        List<Integer> l  = new ArrayList<>();
        l.add(21);
        l.add(11);
        l.add(1);
        l.add(33);
        l.add(10);
        List<String> collect = l.stream()
                .map(String::valueOf)
                .filter(p -> p.startsWith("1"))
                .collect(Collectors.toList());
        System.out.println(collect);

        // create comma separated string from list of integer
        String commaSeparatedFromInt = l.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(commaSeparatedFromInt);

        // create comma separated string from list of string
        String joined = String.join(",", list);
        System.out.println(joined);

        // sort employees by salary in descending order
        List<String> sortedEmployeesBydSalary = testEmployeeData().stream()
                .sorted((e1, e2) -> e2.getSalary()-e1.getSalary())
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("Sorted by Salary - "+sortedEmployeesBydSalary);

        // group by department
        Collection<Employee> employees = testEmployeeData();
        Map<String, List<Employee>> eByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept));
        System.out.println(eByDept);

        // group by department and value should only contains employee name (not entire object which is default behavior)
        Map<String, Set<String>> eByDeptWithName = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept, // key of resultant Map
                        Collectors.mapping(Employee::getName, Collectors.toSet()) // value of resultant Map
                ));
        System.out.println(eByDeptWithName);

        // group by department and value should only contains employee name (not entire object which is default behavior)
        // sorted by dept (Map Key)
        Map<String, Set<String>> eByDeptSortedWithName = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept, // key of resultant Map
                        TreeMap::new,
                        Collectors.mapping(Employee::getName, Collectors.toSet()) // value of resultant Map
                ));
        System.out.println(eByDeptSortedWithName);

        // group by department and value should only contains employee name (not entire object which is default behavior)
        // sorted by dept (Map Key)
        // sorted by name (Map Value)
        Map<String, Set<String>> eByDeptSortedWithNameSorted = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept, // key of resultant Map
                        TreeMap::new,
                        Collectors.mapping(Employee::getName, Collectors.toCollection(TreeSet::new)) // value of resultant Map
                ));
        System.out.println(eByDeptSortedWithNameSorted);

        // group by department and value should only contains employee name (not entire object which is default behavior)
        // sorted by dept (Map Key)
        // sorted by salary highest to lowest (Map Value)
        Map<String, List<String>> eByDeptWithNameSortedBySalary = testEmployeeData().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept, // key of resultant Map
                        TreeMap::new, // resultant Map should TreeMap
                        Collectors.collectingAndThen(Collectors.toList(), e -> e.stream() // values of each group should be sorted by salary and only collect names
                                .sorted((e1,e2)->Integer.compare(e2.getSalary(),e1.getSalary()))
                                .map(Employee::getName)
                                .collect(Collectors.toList())
                        )
                ));
        System.out.println(eByDeptWithNameSortedBySalary);

        // create id to employee object map
        Map<Integer, Employee> idEmployeeMap = testEmployeeData().stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
        System.out.println(idEmployeeMap);

        // print employee name with max salary
        Employee maxSalaryEmp = testEmployeeData().stream().max(Comparator.comparing(Employee::getSalary)).orElse(null);
        System.out.println(maxSalaryEmp);

        // sort in asc order
        List<Integer> l1 = Arrays.asList(3,1,9,7,4,5,2);
        Collections.sort(l1);
        System.out.println("asc sort "+l1);

        // sort in desc order
        l1 = Arrays.asList(3,1,9,7,4,5,2);
        Collections.sort(l1, Collections.reverseOrder());
        System.out.println("desc sort "+l1);

        // search an element in unsorted array
        l1 = Arrays.asList(3,1,9,7,4,5,2);
        int target = 5;
        l1.stream().filter(i -> i == target).findFirst().ifPresent(i -> {
            System.out.println("Target found " + target);
        }); // execute a code block if optional has value

        int target2 = 50;
        l1.stream().filter(i -> i == target2).findFirst()
                .ifPresentOrElse(i -> {
                            System.out.println("Target found " + target);
                        },
                        () -> {
                            System.out.println("Target not found ");
                        }); // execute a code block if optional has value

        // binary search
        l1 = Arrays.asList(3,1,9,7,4,5,2);
        Collections.sort(l1);
        int binarySearchResult = Collections.binarySearch(l1, 5);
        System.out.println("binarySearchResult "+binarySearchResult);

        // reverse each word of a string
        String s = "I say hello world";
        String reversedWords = Arrays.stream(s.split(" "))
                .map(str -> new StringBuilder(str).reverse())
                .collect(Collectors.joining(" "));
        System.out.println("reverse each word of a string - "+reversedWords);

        // verify if two strings are anagram
        String s1 = "tea", s2 = "eat";
        s1 = s1.chars().boxed().map(Character::toString).map(String::toLowerCase).sorted().collect(Collectors.joining());
        s2 = s2.chars().boxed().map(Character::toString).map(String::toLowerCase).sorted().collect(Collectors.joining());
        boolean isAnagram = s1.equals(s2);
        System.out.println("isAnagram "+isAnagram);

        // count anagrams
        String[] sin = new String[]{"tea","tan","ate","eat","nat","bat"};
        Map<String, Long> countGroupedAnagram = Arrays.stream(sin)
                .map(str -> str.chars().boxed().map(Character::toString).map(String::toLowerCase).sorted().collect(Collectors.joining()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("count groupedAnagram - "+countGroupedAnagram);

        String[] sino = new String[]{"tea","tan","ate","eat","nat","bat"};
        Map<String, List<String>> groupedAnagrams = groupAllAnagrams(sino);
        System.out.println("groupedAnagrams - "+ groupedAnagrams);
        System.out.println("groupedAnagrams - "+ groupedAnagrams.values());

        sino = new String[]{"a"};
        groupedAnagrams = groupAllAnagrams(sino);
        System.out.println("groupedAnagrams - "+ groupedAnagrams);
        System.out.println("groupedAnagrams - "+ groupedAnagrams.values());
    }

    public static Map<String, List<String>> groupAllAnagrams(String[] sino){
        Map<String, List<String>> groupedAnagrams = IntStream.range(0, sino.length)
                .mapToObj(i -> new Entry(sino[i].toLowerCase().chars().boxed().map(Character::toString).sorted().collect(Collectors.joining()), i))
//                .sorted(Comparator.comparing(Entry::getWord))
                .collect(Collectors.groupingBy(Entry::getWord, Collectors.mapping(e -> sino[e.getIndex()], Collectors.toList())
//                                Collectors.collectingAndThen(Collectors.toList(), entries -> entries.stream().map(e -> sino[e.getIndex()]).collect(Collectors.toList()))
                        )
                );
        return groupedAnagrams;
    }

    @AllArgsConstructor
    @Getter
    static class Entry{
        String word;
        int index;
    }

    @ToString
    @Getter
    @Setter
    static class Employee {
        int id, salary;
        String name, dept;
        LocalDate dob;
    }

    private static Collection<Employee> testEmployeeData(){
        Employee e1 = new Employee();
        e1.id = 1;
        e1.salary = 16000;
        e1.name = "som22";
        e1.dept = "cse";
        e1.dob = LocalDate.of(1989, Month.APRIL, 12);

        Employee e2 = new Employee();
        e2.id = 2;
        e2.salary = 10000;
        e2.name = "som11";
        e2.dept = "cse";
        e2.dob = LocalDate.of(1999, Month.JANUARY, 10);

        Employee e3 = new Employee();
        e3.id = 3;
        e3.salary = 15000;
        e3.name = "riya";
        e3.dept = "AI";
        e3.dob = LocalDate.of(1992, Month.MARCH, 2);
        return List.of(e1,e2,e3);
    }
}
