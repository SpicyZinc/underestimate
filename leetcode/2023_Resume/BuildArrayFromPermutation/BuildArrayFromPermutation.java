
// Thu Mar 16 23:27:51 2023
class BuildArrayFromPermutation {
    public int[] buildArray(int[] nums) {
        int size = nums.length;
        int[] copy = new int[size];

        for (int i = 0; i < size; i++) {
            int position = nums[i];
            copy[i] = nums[position];
        }

        return copy;
    }

    public int[] buildArray(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // this is done to keep both old and new value together. 
            // going by the example of [5,0,1,2,3,4]
            // after this nums[0] will be 5 + 6*(4%6) = 5 + 24 = 29;
            // now for next index calculation we might need the original value of num[0] which can be obtain by num[0]%6 = 29%6 = 5;
            // if we want to get just he new value of num[0], we can get it by num[0]/6 = 29/6 = 4
            nums[i] = nums[i] + n * (nums[nums[i]] % n);
        }
        
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] / n;
        }
        
        return nums;
    }
}
