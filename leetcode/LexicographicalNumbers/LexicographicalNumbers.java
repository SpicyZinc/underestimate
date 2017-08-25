/*
Given an integer n, return 1 - n in lexicographical order.
For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

idea: DFS, recursion
n % 10 is to get the last digit of number n

take 150 as an example,
the result should be
1
10
100
101
102
103
104
105
106
107
108
109
11
110
111
112
113
114
115
116
117
118
119
12
120
121
122
123
124
125
126
127
128
129
13
130
131
132
133
134
135
136
137
138
139
14
140
141
142
143
144
145
146
147
148
149
15
150
16
17
18
19
2
20
21
22 
*/
import java.util.*;

public class LexicographicalNumbers {
    public static void main(String[] args) {
        LexicographicalNumbers eg = new LexicographicalNumbers();
        List<Integer> result = eg.lexicalOrder(150);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        helper(1, n, ret);
        return ret;
    }

    public void helper(int m, int n, List<Integer> ret) {
        ret.add(m);
        if (m * 10 <= n) {
            helper(m * 10, n, ret);
        }
        if (m < n && m % 10 < 9) {
            helper(m + 1, n, ret);
        }
    }
}