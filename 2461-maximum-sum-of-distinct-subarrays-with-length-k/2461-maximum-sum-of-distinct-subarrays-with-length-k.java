
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long currentSum = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            // Add the right element to current sum and update its count
            currentSum += nums[right];
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            // Once the window exceeds size k, shrink it from the left
            if (right >= k) {
                int leftVal = nums[right - k];
                currentSum -= leftVal;
                freqMap.put(leftVal, freqMap.get(leftVal) - 1);
                if (freqMap.get(leftVal) == 0) {
                    freqMap.remove(leftVal);
                }
            }

            // Check if the current window of size k has all distinct elements
            if (right >= k - 1 && freqMap.size() == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }
}