/*
Suppose you have a random list of people standing in a queue.
Each person is described by a pair of integers (h, k),
where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example
Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

idea:
basically this is a sorting problem
sort the array based on height number descending,
if the same height, sort by number ascending

Comparator could be used, bubble sort could be used
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]] =>
[[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]

After sort, the number of people k in ([h, k]) is the exact position for this person to be at
note:
list.add(index, element)
*/

import java.util.*;

public class QueueReconstructionByHeight {
	public static void main(String[] args) {
		QueueReconstructionByHeight eg = new QueueReconstructionByHeight();
		int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		eg.reconstructQueue(people);
	}
	// using Comparator
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[0] != p2[0] ? Integer.compare(p2[0], p1[0]) : Integer.compare(p1[1], p2[1]);
            }
        });

        List<int[]> result = new ArrayList<int[]>();
        for (int[] person : people) {
            // person[1] exact position to add
            result.add(person[1], person);
        }

        return result.toArray(new int[people.length][]);
    }
    // using bubble sort
    public int[][] reconstructQueue(int[][] people) {
    	if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }

    	int n = people.length;
        for (int i = 0; i < n; i++) {
        	for (int j = 1; j < n; j++) {
        		int preHeight = people[j-1][0];
        		int preNumber = people[j-1][1];
        		int height = people[j][0];
        		int number = people[j][1];

        		if (preHeight < height) {
        			swap(people, j-1, j);
        		} else if (preHeight == height) {
        			if (preNumber > number) {
        				swap(people, j-1, j);
        			}
        		}
        	}
        }

        List<int[]> list = new ArrayList<int[]>();
        for (int[] ppl : people) {
        	list.add(ppl[1], ppl);
        }
        
        return list.toArray(new int[people.length][]);
    }
    // helper function to swap 2D array
    public void swap(int[][] a, int x, int y) {
    	int[] temp = a[x];
    	a[x] = a[y];
    	a[y] = temp;
    }
}