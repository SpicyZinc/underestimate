/*
You are given an integer n representing the size of a 0-indexed memory array. All memory units are initially free.
You have a memory allocator with the following functionalities:

Allocate a block of size consecutive free memory units and assign it the id mID.
Free all memory units with the given id mID.
Note that:

Multiple blocks can be allocated to the same mID.
You should free all the memory units with mID, even if they were allocated in different blocks.
Implement the Allocator class:

Allocator(int n) Initializes an Allocator object with a memory array of size n.
int allocate(int size, int mID) Find the leftmost block of size consecutive free memory units and allocate it with the id mID. Return the block's first index. If such a block does not exist, return -1.
int free(int mID) Free all memory units with the id mID. Return the number of memory units you have freed.
 

Example 1:
Input
["Allocator", "allocate", "allocate", "allocate", "free", "allocate", "allocate", "allocate", "free", "allocate", "free"]
[[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
Output
[null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]

Explanation
Allocator loc = new Allocator(10); // Initialize a memory array of size 10. All memory units are initially free.
loc.allocate(1, 1); // The leftmost block's first index is 0. The memory array becomes [1,_,_,_,_,_,_,_,_,_]. We return 0.
loc.allocate(1, 2); // The leftmost block's first index is 1. The memory array becomes [1,2,_,_,_,_,_,_,_,_]. We return 1.
loc.allocate(1, 3); // The leftmost block's first index is 2. The memory array becomes [1,2,3,_,_,_,_,_,_,_]. We return 2.
loc.free(2); // Free all memory units with mID 2. The memory array becomes [1,_, 3,_,_,_,_,_,_,_]. We return 1 since there is only 1 unit with mID 2.
loc.allocate(3, 4); // The leftmost block's first index is 3. The memory array becomes [1,_,3,4,4,4,_,_,_,_]. We return 3.
loc.allocate(1, 1); // The leftmost block's first index is 1. The memory array becomes [1,1,3,4,4,4,_,_,_,_]. We return 1.
loc.allocate(1, 1); // The leftmost block's first index is 6. The memory array becomes [1,1,3,4,4,4,1,_,_,_]. We return 6.
loc.free(1); // Free all memory units with mID 1. The memory array becomes [_,_,3,4,4,4,_,_,_,_]. We return 3 since there are 3 units with mID 1.
loc.allocate(10, 2); // We can not find any free block with 10 consecutive free memory units, so we return -1.
loc.free(7); // Free all memory units with mID 7. The memory array remains the same since there is no memory unit with mID 7. We return 0.

Constraints:
1 <= n, size, mID <= 1000
At most 1000 calls will be made to allocate and free.

idea:
use array, fill with mID
*/

class Allocator {
    int[] array;

    public Allocator(int n) {
        array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }

    public int allocate(int size, int mID) {
        int n = array.length;
        if (size > n) {
            return -1;
        }

        int count = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] == -1) {
                count++;
                if (count == size) {
                    endIndex = i;
                    break;
                }
            } else {
                count = 0;
                startIndex = i + 1;
            }
        }
        // Did not find the available space
        if (startIndex > endIndex) {
            return -1;
        } else {
            for (int i = startIndex; i < startIndex + size; i++) {
                array[i] = mID;
            }
            return startIndex;
        }
    }

    public int free(int mID) {
        int count = 0;   
        for (int i = 0; i < array.length; i++) {
            if (array[i] == mID) {
                count++;
                array[i] = -1;
            }
        }

        return count;
    }


    // Two maps
    private TreeMap<Integer, Integer> startIndexToSize = new TreeMap<>();
    private Map<Integer, List<Integer>> mIDToStartPositions = new HashMap<>();
    private int n = 0;
    private int end = 0;
    
    public Allocator(int n) {
        this.n = n;
        end = n;
    }
    
    public int allocate(int size, int mID) {
        if (n < size) return -1;
        int ans = -1;
        int prev = 0;
        for (int start : startIndexToSize.keySet()) {
            if (start - prev >= size) {
                ans = prev;
                break;
            }
            prev = start + startIndexToSize.get(start);
        }
        // check space from the end
        if (ans == -1) {
            if (end - prev >= size) {
                ans = prev;
            }
        }
        if (ans != -1) {
            n -= size;
            startIndexToSize.put(ans, size);
            if (mIDToStartPositions.get(mID) == null) {
                mIDToStartPositions.put(mID, new LinkedList<>());
            }
            mIDToStartPositions.get(mID).add(ans);
        }
        return ans;
    }
    
    public int free(int mID) {
        List<Integer> starts = mIDToStartPositions.get(mID);
        int reclaimSize = 0;
        if (starts == null) return reclaimSize;
        for (int start : starts) {
            reclaimSize += startIndexToSize.get(start);
            startIndexToSize.remove(start);
        }
        mIDToStartPositions.remove(mID);
        n += reclaimSize;
        return reclaimSize;
    }
}

