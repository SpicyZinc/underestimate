/*
A company registers an IPO on a website sellshares.com. All the shares on this website are available for bidding for a particular time frame called the bidding window.
At the end of the bidding window an auction logic is used to decide how many of the available shares go to which bidder until all the shares that are available have been allotted,
or all the bidders have received the shares they bid for, whichever comes earlier.

The bids arrive from the users in the form of <user Id, number of shares, bidding price, timestamp> until the bidding window is closed.
The auction logic assigns shares to the bidders as follows:
The bidder with the highest price gets the number of shares they bid for
If multiple bidders have bid at the same price, the bidders are assigned shares as follows: Each bidder in the same price group gets assigned one share each consecutively, with each bidder being arranged inside the group based on their timestamp.
Once a bidder gets the number of shares they bid for, they will be removed from the above iterative process and the process which then continues until all bidders are removed or the shares get exhausted, whichever comes first.
List the user Id's of all users who did not get even one share after the shares have been allocated.

Input
bids: a 2D array of arrays of integers, Id, shares, price, timestamp named u, sc, bp, ts going forward
total_shares: an integer, the total shares to allocate
Output
a list of integers, each an Id for those bidders who receive no shares, sorted ascending

Examples
Example 1:
Input:
bids = [[1, 5, 5, 0], [2, 7, 8, 1], [3, 7, 5, 1], [4, 10, 3, 3]]
total_shares = 18
Output: 4
Explanation:
The highest price bid is for user Id 2 for 7 shares at a price of 8, so that user gets 7 shares leaving 11 to allocate to lower prices.
Users with Id's 1 and 3 each bid 5 for 5 and 7 shares, with bidder 1 having the earlier timestamp.
After 5 iterations, 10 shares have been allocated with 5 shares going to each of these two bidders.
Bidder 1 has the full allotment, bidder 3 has 2 more shares to buy and there is 1 share left to allocate.
It goes to bidder 3 and all shares have been allotted. Bidder 4 is the only bidder who gets no shares.

Constraints
1<=n<=10^4
1<=u, sc, bp, ts, total_shares<=10^9

idea:
sort the array of bid object by bid price and timestamp

- 先按照price(高到低）, timestamp(小到大）sort
- 构造hashmap: {price: [bids]} 这个是这个price的一个group 不用真构造 hashmap
- 遍历hashmap: 多个 users时遵循round robin分配

*/

import java.util.*;

class IPO {
    public static List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        // Id, shares, price, timestamp
        Collections.sort(bids, (b1, b2) -> b1.get(2) != b2.get(2) ? b2.get(2) - b1.get(2) : b1.get(3) - b2.get(3));
        int size = bids.size();
        int i = 0;

        while (totalShares > 0 && i < size) {
            int groupTotalShares = 0;
            int j = i;

            // round robin
            // 其实最后就是把每个bid想买的数量都买了
            while (j < size && bids.get(i).get(2) == bids.get(j).get(2)) {
                groupTotalShares += bids.get(j).get(1);
                j++;
            }

            int groupSize = j - i;
            // 每次一个share 5 whereas 7
            // 第一次 就 break
            // 1 * groupSize 就是 第一次 round robin 结果
            if (totalShares < groupSize) {
                i += totalShares;
                break;
            } else {
                totalShares -= groupTotalShares;
            }

            i = j;
        }

        List<Integer> result = new ArrayList<>();
        // 所有i 之后的 都投入
        while (i < size) {
            result.add(bids.get(i).get(0));
            i++;
        }

        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> bids = new ArrayList<>();
        Integer[] a = {1, 5, 5, 0};
        Integer[] b = {2, 7, 8, 1};
        Integer[] c = {3, 7, 5, 1};
        Integer[] d = {4, 10, 3, 3};

        // Integer[] a = {1, 2, 4, 0};
        // Integer[] b = {2, 7, 2, 1};
        // Integer[] c = {3, 2, 4, 2};
        // Integer[] d = {4, 10, 1, 3};

        List<Integer> user1 = Arrays.asList(a);
        List<Integer> user2 = Arrays.asList(b);
        List<Integer> user3 = Arrays.asList(c);
        List<Integer> user4 = Arrays.asList(d);
        int totalShares = 18;
        // int totalShares = 2;

        bids.add(user1);
        bids.add(user2);
        bids.add(user3);
        bids.add(user4);


        // Scanner scanner = new Scanner(System.in);
        // int bidsLength = Integer.parseInt(scanner.nextLine());
        // List<List<Integer>> bids = new ArrayList<>();
        // for (int i = 0; i < bidsLength; i++) {
        //     bids.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        // }
        // int totalShares = Integer.parseInt(scanner.nextLine());
        // scanner.close();
        List<Integer> res = getUnallottedUsers(bids, totalShares);
        // System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
