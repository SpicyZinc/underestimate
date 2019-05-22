
// variation of LongestSubstringwithAtMostTwoDistinctCharacters
class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length <= 1) {
            return tree.length;
        }
        
        int n = tree.length;
        Map<Integer, Integer> hm = new HashMap<>();
        
        int left = 0;
        // the worst case all characters are different, so the length is k
        int k = 2;
        int maxLen = k;

        for (int right = 0; right < n; right++) {
            // char c = s.charAt(right);
            int val = tree[right];
            hm.put(val, hm.getOrDefault(val, 0) + 1);

            while (hm.size() > k) {
                // char leftChar = s.charAt(left);
                int leftNum = tree[left];
                int cnt = hm.get(leftNum);
                // note >= 2
                if (cnt >= 2) {
                    hm.put(leftNum, cnt - 1);
                } else {
                    hm.remove(leftNum);
                }
                
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}