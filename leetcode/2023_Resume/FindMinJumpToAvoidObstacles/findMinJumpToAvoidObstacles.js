/*
You are given an array of integers representing coordinates of obstacles situated on a straight line.
Assume that you are jumping from the point with coordinate 0 to the right. You are allowed only to make jumps of the same length represented by some integer.
Find the minimal length of the jump enough to avoid all the obstacles.

findMinJumpToAvoidObstacles

Example
For inputArray = [5, 3, 6, 7, 9], the output should be
solution(inputArray) = 4.

https://codesignal.s3.amazonaws.com/uploads/1667239739797/example.png

idea:
every time += )jump step length)
*/

function findMinJumpToAvoidObstacles(inputArray) {
    const obs = inputArray;
    // Insert all array elements in a hash table
    // and find the maximum value in the array
    let hs = new Set();
    let max = obs[0];
    for (let i = 0; i < obs.length; i++) {
        hs.add(obs[i]);
        max = Math.max(max, obs[i]);
    }

    // checking for every possible length which yields us solution
    for (let i = 1; i <= max; i++) {
        let j = i;
        for (; j <= max; j += i) {
            // if there is obstacle, break the loop.
            if (hs.has(j)) {
                break;
            }
        }

        // if j > max, meaning jump over all obstacles, so return i
        if (j > max) {
            return i;
        }
    }

    return max + 1;
}
