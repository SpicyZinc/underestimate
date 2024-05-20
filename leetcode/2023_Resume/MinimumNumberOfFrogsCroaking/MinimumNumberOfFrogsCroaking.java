/*
You are given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple "croak" are mixed.
Return the minimum number of different frogs to finish all the croaks in the given string.
A valid "croak" means a frog is printing five letters 'c', 'r', 'o', 'a', and 'k' sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of a valid "croak" return -1.

Example 1:
Input: croakOfFrogs = "croakcroak"
Output: 1 
Explanation: One frog yelling "croak" twice.

Example 2:
Input: croakOfFrogs = "crcoakroak"
Output: 2 
Explanation: The minimum number of frogs is two. 
The first frog could yell "crcoakroak".
The second frog could yell later "crcoakroak".

Example 3:
Input: croakOfFrogs = "croakcrook"
Output: -1
Explanation: The given string is an invalid combination of "croak" from different frogs.

Constraints:
1 <= croakOfFrogs.length <= 105
croakOfFrogs is either 'c', 'r', 'o', 'a', or 'k'.

idea:

*/

class MinimumNumberOfFrogsCroaking {
    public int minNumberOfFrogs(String croakOfFrogs) {
        
    }
}

/**
 * @param {string} croakOfFrogs
 * @return {number}
 */
var minNumberOfFrogs = function(croakOfFrogs) {
    let c=0
    let r=0
    let o=0
    let a=0
    let k=0
    // Make characters and sizes that are not in sequence string return -1
    for (let croakOfFrog of croakOfFrogs) {
        if(croakOfFrog =='c') c++
        else if(croakOfFrog =='r') r++
        else if(croakOfFrog =='o') o++
        else if(croakOfFrog =='a') a++
        else if(croakOfFrog =='k') k++
        else { return -1}

        if (c<r || r<o || o<a || a<k) return -1
    }

    if(c !== r || r!== o || o!== a || a!==k) return -1

    // current # of frogs starting to call
    let temp_c = 0;
    // current # of frogs have most frogs
    let max_c = 0;

    for (let croakOfFrog of croakOfFrogs) {
        if (croakOfFrog =='c') {
           temp_c++;
           max_c = Math.max(temp_c, max_c);
        }
        if (croakOfFrog =='k') {
            temp_c--;
        }
    }

    return max_c;
};