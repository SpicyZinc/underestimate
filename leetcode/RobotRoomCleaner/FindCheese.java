/*
https://leetcode.com/discuss/interview-question/633689/facebook-phonevideo-find-cheese
Given a mouse with 2 APIs in a maze. Design an algorithm to find a cheese in the maze using only the 2 given APIs shown below.

*/

class FindCheese {
    Map<Integer, Set<Integer>> visited = new HashMap<>();
    /**
    * Moves to one of the directions (left, right, up, down) and returns false if you can't move and true if you can.
    */
    public boolean move(Direction direction);

    /**
    * Returns true if there is a cheese in the current cell.
    */
    public boolean hasCheese();

    /**
    * Should return true and leave the mouse at that location or false if we can't find cheese and return the mouse back to where it started.
    */
    public boolean getCheese() {
        return dfs(0, 0, null);
    }

    private boolean dfs(int x, int y, Direction prevDir) {
        if (hasCheese()) {
            return true;
        }

        int[][] directions = {
            {0,-1},
            {-1,0},
            {0,1},
            {1,0}
        };
   
        if (!visited.containsKey(x) || !visited.get(x).contains(y)) {
            visited.put(x).add(y);

            if (move(Direction.up) && dfs(x, y + 1, Direction.up)) {
                return true;
            }

            if (move(Direction.left) && dfs(x - 1, y, Direction.left)) {
                return true;
            }

            if (move(Direction.down) && dfs(x, y - 1, Direction.down)) {
                return true;
            }

            if (move(Direction.right) && dfs(x + 1, y, Direction.right)) {
                return true;
            }
        }

        // found nothing since backtrack, should move back
        if (prevDir != null) {
            if (prevDir == Direction.up) {
                move(Direction.down);
            } else if (prevDir == Direction.down) {
                move(Direction.up);
            } else if (prevDir == Direction.left) {
                move(Direction.right);
            } else (prevDir == Direction.right) {
                move(Direction.left);
            }
        }

        return false;
    }
}
