package com.ywendeng.algorithm.maxsubsequence;

/**
 * @ Author ：ywendeng
 * @ Date: Created in 14:57 2018/1/7
 * @ Description: 最大子序列求解
 */
public class MaxSubSeq {
    public static void main(String[] args) {
        int[] arr = {-2, 11, -4, 13, -5, -2};
        //int[] arr = {9, -11, -4, -13, -5, 2};
        algorithm3(arr);
    }

    /**
     * @param arr 待查找排序的数组
     *            时间复杂度为：O(N^2)
     */
    private static void algorithm1(int[] arr) {
        int maxSum = 0;
        //start、end 表示最大子序列的开始和结束坐标
        int left = 0;
        int right = 0;
        for (int i = 0; i < arr.length; i++) {
            int subSeqSum = 0;
            for (int j = i; j < arr.length; j++) {
                subSeqSum += arr[j];
                if (maxSum < subSeqSum) {
                    maxSum = subSeqSum;
                    left = i;
                    right = j;
                }
            }

        }
        System.out.printf("maxSum:%d\tstart:%d\tend:%d\n", maxSum, left, right);

    }

    /**
     * @param arr 采用分治策略———最大子序列可能出现在左边和右边或者跨越输入数据的中部从而位于左右两半部分之中
     */
    private static void algorithm2(int[] arr) {
        int subSum = maxSubSum(arr, 0, arr.length - 1);
        System.out.println(subSum);

    }

    private static int maxSubSum(int[] arr, int left, int right) {
        //处理基本情形
        if (left == right)
            return arr[left] >= 0 ? arr[left] : 0;
        // 不断推进
        int mid = (left + right) / 2;
        int maxLeftSum = maxSubSum(arr, left, mid);
        int maxRightSum = maxSubSum(arr, mid + 1, right);
        /**
         * 设计法则
         */
        //计算左边的最大子序列
        int leftBorderSum = 0, maxLeftBorderSum = 0;
        for (int i = mid; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }
        //计算有右边的最大子序列
        int rightBorderSum = 0, maxRightBorderSum = 0;
        for (int j = mid + 1; j <= right; j++) {
            rightBorderSum += arr[j];
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }
        int maxSubSeqSum = maxLeftBorderSum + maxRightBorderSum;
        if (maxLeftSum > maxSubSeqSum) {
            maxSubSeqSum = maxLeftSum;
        } else if (maxRightSum > maxSubSeqSum) {
            maxSubSeqSum = maxRightSum;
        }
        return maxSubSeqSum;
    }

    private static void algorithm3(int[] arr) {
        int maxSubSum = 0;
        int subSum = 0;
        for (int i = 0; i < arr.length; i++) {
            subSum += arr[i];
            if (subSum > maxSubSum)
                maxSubSum = subSum;
            else if (subSum < 0)
                // 负数不会作为最大子序列的最大开头元素,同时期望找到更大的子系列
                subSum = 0;
        }
        System.out.println("maxSubSum is :" + maxSubSum);
    }
}

