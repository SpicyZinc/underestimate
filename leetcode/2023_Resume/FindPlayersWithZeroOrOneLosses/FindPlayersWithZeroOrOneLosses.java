/*
You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.

Return a list answer of size 2 where:
answer[0] is a list of all players that have not lost any matches.
answer[1] is a list of all players that have lost exactly one match.
The values in the two lists should be returned in increasing order.

Note:
You should only consider the players that have played at least one match.
The testcases will be generated such that no two matches will have the same outcome.

Example 1:
Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].

Example 2:
Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].

Constraints:
1 <= matches.length <= 105
matches[i].length == 2
1 <= winneri, loseri <= 105
winneri != loseri
All matches[i] are unique.

idea:
just use hashmap
*/

class FindPlayersWithZeroOrOneLosses {
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> noLose = new ArrayList<>();
        List<Integer> loseOne = new ArrayList<>();

        result.add(noLose);
        result.add(loseOne);

        Map<Integer, List<Integer>> hm = new HashMap<>();
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            hm.computeIfAbsent(winner, x -> new ArrayList<Integer>()).add(1);
            hm.computeIfAbsent(loser, x -> new ArrayList<Integer>()).add(-1);
        }

        for (Map.Entry<Integer, List<Integer>> entry : hm.entrySet()) {
            int player = entry.getKey();
            List<Integer> value = entry.getValue();

            if (getLostCount(value) == 0) {
                noLose.add(player);
            }

            if (getLostCount(value) == 1) {
                loseOne.add(player);
            }
        }

        Collections.sort(noLose);
        Collections.sort(loseOne);

        return result;
    }

    public int getLostCount(List<Integer> value) {
        int count = 0;
        for (int num : value) {
            if (num == -1) {
                count++;
            }
        }
        return count;
    }
}
