
package com.mycompany.lis_sorted_subarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class LIS_Sorted_Subarray {
    public static List<Integer>lis(int[]nums) {
        int n =nums.length;
        int[] dp =new int[n];
        int[] prev =new int[n];
        Arrays.fill(prev, -1);
        int max= 1;
        int endIndex = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                endIndex = i;
            }
        }
        List<Integer> lis = new ArrayList<>();
        while (endIndex != -1) {
            lis.add(nums[endIndex]);
            endIndex = prev[endIndex];
        }
        lis.sort(null);
        return lis;
    }
    public static void main(String[] args) {
        int[] nums = {7,6,5,4,3,2,1};
        List<Integer> lis = lis(nums);

        System.out.println("Output " + lis);
    }
}

