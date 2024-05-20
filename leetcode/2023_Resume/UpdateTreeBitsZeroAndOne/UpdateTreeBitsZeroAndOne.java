/*
0 -                [0]
               ___/   \___
              /           \
1 -        [0]             [1]
          /   \           /   \
         /     \         /     \
2 -    [0]     [0]     [0]     [0]
       / \     / \     / \     / \
      /   \   /   \   /   \   /   \
3 -  [0] [0] [0] [1] [1] [0] [0] [0]
      |   |   |   |   |   |   |   |
      0   1   2   3   4   5   6   7

A parent node is 1 if and only if both child nodes are 1
bits[level][index]
N = 4

flip from start position, and length to be 3
clr_bits(5, 3)

idea:
just do it
                int a = i * 2;
                int b = i * 2 + 1;

the tree represented as 2 d array level and value
*/


class UpdateTreeBitsZeroAndOne {
    public void clr_bits(int start, int len) { // 5, 3
        int n = bits.length;
        // update last level
        for (int i = 0; i < len; i++) {
            bits[n - 1][start + i] = 0;
        }
        // updating the rest
        int levelStart = start; 
        int levelEnd = start + len;

        for (int level = n - 2; level >= 0; level--) {
            int nextLevel = level + 1;
            // update index of level to save time
            levelStart = levelStart / 2;
            levelEnd = levelEnd / 2;
            for (int i = levelStart; i <= levelEnd; i++) {
                // 0 -> 0, 1
                // 1 -> i * 2, i * 2 + 1
                int a = i * 2;
                int b = i * 2 + 1;
                if (bits[nextLevel][a] * bits[nextLevel][b] == 0) {
                    bits[level][i] = 0;
                } else {
                    bits[level][i] = 1;
                }
            }
        }
    }
}
