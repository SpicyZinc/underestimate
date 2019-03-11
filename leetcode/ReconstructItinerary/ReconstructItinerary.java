/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

idea:
data structure is hashmap<String, PriorityQueue<String>>
use PriorityQueue is to get hashmap in lexical order
e.g. {MUC=[LHR], JFK=[MUC], LHR=[SFO], SFO=[SJC]}
e.g. {JFK=[ATL, SFO], SFO=[ATL], ATL=[JFK, SFO]}
then use DFS to search to add to result list
But addFirst is key
*/

import java.util.*;

public class ReconstructItinerary {

	public static final String START = "JFK";

	public static void main(String[] args) {
		ReconstructItinerary eg = new ReconstructItinerary();
		String[][] ticketsOne = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		String[][] ticketsTwo = {{"JFK", "SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};

		List<String> resultOne = eg.findItinerary(ticketsOne);
		List<String> resultTwo = eg.findItinerary(ticketsTwo);

		for (String s : resultOne) {
			System.out.println(s);
		}

        for (String s : resultTwo) {
            System.out.println(s);
        }
	}

    public List<String> findItinerary(String[][] tickets) {
    	LinkedList<String> result = new LinkedList<String>();
    	Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();

    	if (tickets == null || tickets.length == 0) {
    		return result;
    	}

    	for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];

    		if (!map.containsKey(from)) {
    			map.put(from, new PriorityQueue<String>());
    		}
    		map.get(from).offer(to);
    	}

    	dfs(START, map, result);

    	return result;
    }

    public void dfs(String start, Map<String, PriorityQueue<String>> map, LinkedList<String> result) {
    	while (map.containsKey(start) && !map.get(start).isEmpty()) {
    		dfs(map.get(start).poll(), map, result);
    	}
    	result.addFirst(start);

        // can use stack as well, but not very understand this
        // String now = "JFK";
        // Stack<String> s = new Stack<String>();
        // for(int i = 0; i < tickets.length; i++) {
        //     while(!myMap.containsKey(now) || myMap.get(now).isEmpty()) {
        //         s.push(now);
        //         now = ans.remove(ans.size()-1);
        //     }
        //     ans.add(now);
        //     now = myMap.get(now).poll();
        // }
        // ans.add(now);
        // while(!s.isEmpty()) ans.add(s.pop());
        // return ans;
    }
}

