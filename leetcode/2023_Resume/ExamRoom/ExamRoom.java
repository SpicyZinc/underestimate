/*
There is an exam room with n seats in a single row labeled from 0 to n - 1.
When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats, they sit in the seat with the lowest number.
If no one is in the room, then the student sits at seat number 0.
Design a class that simulates the mentioned exam room.

Implement the ExamRoom class:
ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
int seat() Returns the label of the seat at which the next student will set.
void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.


Example 1:
Input
["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
[[10], [], [], [], [], [4], []]
Output
[null, 0, 9, 4, 2, null, 5]

Explanation
ExamRoom examRoom = new ExamRoom(10);
examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
examRoom.seat(); // return 9, the student sits at the last seat number 9.
examRoom.seat(); // return 4, the student sits at the last seat number 4.
examRoom.seat(); // return 2, the student sits at the last seat number 2.
examRoom.leave(4);
examRoom.seat(); // return 5, the student sits at the last seat number 5.

Constraints:
1 <= n <= 109
It is guaranteed that there is a student sitting at seat p.
At most 104 calls will be made to seat and leave.

idea:
need to come back
*/

import java.util.*;

class ExamRoom {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0, 1);
        list.add(0, 2);
        list.add(1, 5);
        list.add(1, 6);
        System.out.println(list);
    }

    int capacity;
     // why treeset, because seats will be sorted automatically and get first()/last() element in log(n) time. 
    TreeSet<Integer> seats; 
    
    public ExamRoom(int n) {
        this.capacity = n;
        this.seats = new TreeSet<>();
    }
    
    // Return 0 for first attempt (as mentioned in question)
    // Otherwise, we need to calculate the max distance by checking the whole treeset : O(n) time. 
    // Note that "distance" variable is initialized to first appearing seat,
    // why because the distance calculation is based on current seat and the seat before that. 
    // Find the maximum distance and update the seat number accordingly.
    // distance calculation -> (current seat - previous seat ) / 2
    // Update the max distance at each step.
    // New seat number will be ->  previous seat number + max distance 

    // Now, before returning the end result, check for one more edge case:
    // That is, if the max distance calculated is less than ->  capacity-1-seats.last()

    // Why because -> if last seat number in treeset is far from last position, 
    // then  the largest distance possible is the last position.
    public int seat() {
        int seatNumber = 0;
        if (seats.size() > 0) {
            Integer prev = null;
            int distance = seats.first();
            for (Integer seat : seats) {
                if (prev != null) {
                    int d = (seat - prev) / 2;
                    if (distance < d) {
                        distance = d;
                        seatNumber = prev + distance;
                    }
                }
                prev = seat;
            }
            
            if (distance < capacity - 1 - seats.last()) {
                seatNumber = capacity - 1;
            }
        }

        seats.add(seatNumber);

        return seatNumber;
    }
    
    // Simply remove the seat number from treeset and treeset will be automatically adjust its order in log(n) time. 
    public void leave(int p) {
        seats.remove(p);
    }

// ===========================================
    int n;
    List<Integer> list;

    public ExamRoom(int n) {
        this.n = n;
        list = new ArrayList<>();
    }
    
    public int seat() {
        if (list.isEmpty()) {
            list.add(0);
            return 0;
        }
        
        // get max distance of [left edge <-> 1st element] and [last element <-> right edge]
        int distance = Math.max(list.get(0) - 0, n - 1 - list.get(list.size() - 1));
        for (int i = 0; i < list.size() - 1; i++) {
            // find the biggest distance in the middle
            distance = Math.max(distance, (list.get(i + 1) - list.get(i)) / 2);
        }
        
        if (list.get(0) == distance) { // for instance, list = [9]
            list.add(0, 0);
            return 0;
        }
        
        // find the range that contribute the largest distance and add the index
        for (int i = 0; i < list.size()-1; i++) {
            if ((list.get(i + 1) - list.get(i)) / 2 == distance) {
                list.add(i + 1, (list.get(i) + list.get(i + 1)) / 2);
                return list.get(i + 1);
            }
        }
        
        list.add(n - 1);
        return n - 1;
    }
    
    public void leave(int p) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == p) {
                list.remove(i);
            }
        }
    }
}
