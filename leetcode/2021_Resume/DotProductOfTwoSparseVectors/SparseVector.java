/*
Given two sparse vectors, compute their dot product.

Implement class SparseVector:
SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
 
Constraints:
n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100

idea:
use index-value map to store non-zero values
*/

// naive method
class SparseVector{
    int[] list;
    SparseVector(int[] nums) {
        list = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = nums[i];
        }
    }
    
    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        for (int i = 0; i < this.list.length; i++) {
            if (vec.list[i] != 0 || this.list[i] != 0) {
                result += vec.list[i] * this.list[i];
            }
        }

        return result;
    }
}

// map for sparse vector, only save non zero value
class SparseVector {
    Map<Integer, Integer> nonZeroMap = new HashMap<>();

    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroMap.put(i, nums[i]);
            }
        }
    }
    
    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int productSum = 0;
        for (Map.Entry<Integer, Integer> entry : nonZeroMap.entrySet()) {
            int index = entry.getKey();
            int value = entry.getValue();
            if (vec.nonZeroMap.containsKey(index)) {
                productSum += value * vec.nonZeroMap.get(index);
            }
        }

        return productSum;
    }
}

// if using map, when data is too big, hashmap is not efficient
class SparseVector {
    List<int[]> list;
    SparseVector(int[] nums) {
        list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 存不是0 的value 和 index
            if (nums[i] != 0) {
                list.add(new int[] {i, nums[i]});
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int dotProduct = 0;
        int p = 0, q = 0;
        while (p < list.size() && q < vec.list.size()) {
            if (list.get(p)[0] == vec.list.get(q)[0]) {
                dotProduct += list.get(p)[1] * vec.list.get(q)[1];
                p++;
                q++;
            } else if(list.get(p)[0] < vec.list.get(q)[0]) {
                p++;
            } else {
                q++;
            }
        }

        return dotProduct;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);


