package com.other;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    private List<List<Integer>> out = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        powerSet(nums,0,new ArrayList<Integer>(),0);
        return out;
    }

    private void powerSet(int a[], int i, List<Integer> list, int j){
        if(i >= a.length){
            out.add(new ArrayList<>(list));
            return;
        }
        powerSet(a,i+1,list,j);
        list.add(j,a[i]);
        powerSet(a,i+1,list,j+1);
        list.remove(j);
    }
}
