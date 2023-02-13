/*
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:

A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity.
Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
Return an n x m matrix representing the box after the rotation described above.

Example 1:
https://assets.leetcode.com/uploads/2021/04/08/rotatingtheboxleetcodewithstones.png

Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]

Example 2:
https://assets.leetcode.com/uploads/2021/04/08/rotatingtheboxleetcode2withstones.png

Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]

Example 3:
https://assets.leetcode.com/uploads/2021/04/08/rotatingtheboxleetcode3withstone.png

Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 

Constraints:
m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.

idea:
just some brute force and understanding
*/

class RotatingTheBox {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] rotatedBox = new char[n][m];

        for (int i = 0; i < m; i++) {
            int emptyIndex = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                // Obstacle index
                if (box[i][j] == '*') {
                    emptyIndex = j - 1;
                }

                if (box[i][j] == '#') {
                    // Falls, so it become '.'
                    box[i][j] = '.';
                    box[i][emptyIndex] = '#';
                    // Continue falling
                    emptyIndex--;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                rotatedBox[j][m - i - 1] = box[i][j];
            }
        }

        return rotatedBox;
    }
}
