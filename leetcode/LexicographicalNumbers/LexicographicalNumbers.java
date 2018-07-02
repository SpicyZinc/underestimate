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

idea:
https://blog.csdn.net/Cloudox_/article/details/70224397

1 如果一个数乘以十以后没有超过n, 那它后面紧挨着的应该是它的十倍, 比如1,10,100.
2 如果不满足1, 那就应该是直接加一, 比如n为13的时候, 前一个数为12, 120超过了n, 那接着的应该是13.
但是这里要注意如果前一个数的个位已经是9或者是它就是n了, 那就不能加一了, 比如 n = 25, 前一个数为19, 下一个数应该为2而不是19+1=20.
25的下一个也没有26了.
3 如果不满足2, 比如19后面应该接2而不是20, 这时候应该将19除以10再加一, 比如n=500, 399的下一个应该是4, 那就是除以十, 个位还是9, 继续除以10, 得到3, 加一得到4.

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

    // iteration
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<Integer>();
        // current stands for the number sequentially inserted to result array
        int current = 1;
        // after all, n 个数是不变的
        for (int i = 0; i < n; i++) {
            result.add(current);

            if (current * 10 <= n) {
                current *= 10;
            } else if (current % 10 != 9 && current + 1 <= n) {
                current += 1;
            } else {
                // 找到不是9的 digit
                // n = 20
                // 记住多了一步/10, 实际是倒数第二位
                while ((current / 10) % 10 == 9) {
                    current /= 10;
                }
                current = current / 10 + 1;
                
            }
        }

        return result;
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