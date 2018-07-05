/*
Your car starts at position 0 and speed +1 on an infinite number line. (Your car can go into negative positions.)
Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
When you get an instruction "A", your car does the following: position += speed, speed *= 2.
When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.
(Your position stays the same.)

For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.

Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.
 
Note:
1 <= target <= 10000.

idea:
BFS, need to go back for DP

http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-818-race-car/
https://blog.csdn.net/magicbean2/article/details/80333734
*/

class RaceCar {
    class Pair {
        int position;
        int speed;

        public Pair(int position, int speed) {
            this.position = position;
            this.speed = speed;
        }
    }
    
	public int racecar(int target) {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(0, 1));

		Set<String> visited = new HashSet<String>();
		visited.add("0_1");
		visited.add("0_-1");
		
		int steps = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pair car = queue.poll();
				int position = car.position;
				int speed = car.speed;

				// Accelerate
				{
					int pos1 = position + speed;
					int speed1 = speed * 2;
					Pair p1 = new Pair(pos1, speed1);
					// if reached to the target, return
					if (pos1 == target) {
						return steps + 1;
					}
					// this pruning, not understand, just use it
					if (p1.position > 0 && p1.position < 2 * target) {
						queue.add(p1);
					}
				}
				// Reverse
				{
					int speed2 = speed > 0 ? -1 : 1;
					Pair p2 = new Pair(position, speed2);
					String key2 = position + "_" + speed2;
					if (!visited.contains(key2)) {
						queue.add(p2);
						visited.add(key2);
					}
				}
			}

			steps++;
		}

		return -1;
	}
}