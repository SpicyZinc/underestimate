# Pinterest Karat

## graph ancestor

描述：

1   2    3       

\  /    /  \     

  4    5    6

   \  /  \    \

​    7    8    9

上面的node是下面的node的parent, 以edge的方式給, e.g. 上圖=[(1,4),(2,4),(4,7),(3,5),(5,7),(5,8),(3,6),(6,9)]，不考慮cycle

1. 

找nodes that have no parents, and nodes that have 1 parent

上圖=>.本文原创自1point3acres论坛

[

  [1,2,3],

  [5,6,8,9],

]

2. 找graph中兩個node有無common ancestor (自己不算自己的ancestor, so input = (2,4), return false)

e.g.

(4,7) => true (common ancestor = 1)

(7,8) => true

(1,4) => false

3. 找furthest ancestor from node, return any one of the furthest parents, return -1 if node has no parent

e.g.

7=>1, 2 or 3

1=>-1

8=>3

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <sstream>
#include <iterator>
#include <set>
#include <unordered_set>
#include <map>
#include <unordered_map>
#include <queue>
#include <utility>

using namespace std;

class Solution {
public:
    int solution(int edges[][2], int num){
        unordered_map<int, vector<int>> father;
        vector<vector<int>> ans;
        for(int i = 0; i < 8; i++){
            if(!father.count(edges[i][0])) father[edges[i][0]] = vector<int>();
            if(!father.count(edges[i][1])) father[edges[i][1]] = vector<int>();
            father[edges[i][1]].push_back(edges[i][0]);
        }

//         find nodes that have no parents, and nodes have 1 parent
//         return <vector<vector<int>> ans;
//        vector<int> no_parent;
//        vector<int> one_parent;
//        for(auto node: father){
//            if(node.second.size() == 0) no_parent.push_back(node.first);
//            if(node.second.size() == 1) one_parent.push_back(node.first);
//        }
//        ans.push_back(no_parent);
//        ans.push_back(one_parent);
//        return ans;

//            find if two nodes in graph have common ancestor
//            return boolean
//        unordered_set<int> parent_first = getParent(num_1, father);
//        unordered_set<int> parent_second = getParent(num_2, father);
//        for(auto ele: parent_first)
//            if(parent_second.count(ele)) return true;
//        return false;

//        find the most distant ancestor
        queue<int> q;
        int longest_parent;
        q.push(num);
        while(!q.empty()){
            int top = q.front();
            q.pop();
            longest_parent = top;
            for(auto f: father[top])
                q.push(f);
        }
        if(longest_parent == num) return -1;
        else return longest_parent;
    }
private:
    unordered_set<int> getParent(int i, unordered_map<int, vector<int>> father){
        queue<int> q;
        if(!father.count(i)) return unordered_set<int>();
        unordered_set<int> ans;
        q.push(i);
        while(!q.empty()){
            int top = q.front();
            ans.insert(top);
            q.pop();
            vector<int> fathers = father[top];
            for(auto f: fathers)
                q.push(f);
        }
        ans.erase(i);
        return ans;
    }
};

int main(){
    Solution solution;
    int a[8][2] = {{1,4},{2,4},{4,7},{3,5},{5,7},{5,8},{3,6},{6,9}};
    int ans = solution.solution(a, 1);
    cout << ans;
    return 0;
}
```

## calculator

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <sstream>
#include <iterator>
#include <set>
#include <unordered_set>
#include <map>
#include <unordered_map>
#include <queue>
#include <utility>

using namespace std;

class Solution {
public:
    // question 1
    string solution(string str){
        int ans = 0;
        int sign = 1;
        int current = 0;
        for(auto c: str){
            if(isdigit(c)) current = current * 10 + c - '0';
            else{
                if(c == '-') {
                    ans += sign * current;
                    sign = -1;
                    current = 0;
                }
                else{
                    ans += sign * current;
                    sign = 1;
                    current = 0;
                }
            }
        }
        ans += sign * current;
        return to_string(ans);
    }
    
    // question 2
	    string solution(string str){
        int ans = 0;
        int sign = 1;
        int current = 0;
        stack<int> s;
        for(auto c: str){
            if(isdigit(c)) current = current * 10 + c - '0';
            else{
                if(c == '-') {
                    ans += sign * current;
                    sign = -1;
                    current = 0;
                }
                if(c == '+'){
                    ans += sign * current;
                    sign = 1;
                    current = 0;
                }
                if(c == '('){
                    s.push(ans);
                    s.push(sign);
                    sign = 1;
                    current = 0;
                    ans = 0;
                }
                if(c == ')'){
                    ans += sign * current;
                    int top_sign = s.top();
                    s.pop();
                    int top_num = s.top();
                    s.pop();
                    ans = top_sign * ans + top_num;
                    current = 0;
                    sign = 1;
                }
            }
        }
        ans += sign * current;
        return to_string(ans);
    }
};

int main(){
    Solution solution;
    string str = "1+25-3+10";
    string ans = solution.solution(str);
    cout << ans;
    return 0;
}


```

## domain counter (LC811)

```c++
class Solution {
public:
    vector<string> subdomainVisits(vector<string>& cpdomains) {
        vector<string> ans;
        if(cpdomains.empty()) return ans;
        unordered_map<string, int> m;
        for(auto cpdomain: cpdomains){
            int split = cpdomain.find(' ');
            int times = stoi(cpdomain.substr(0, split));
            string domain = cpdomain.substr(split);
            // 注意不包含' '
            m[domain.substr(1)] += times;
            for(int i = 0; i < domain.size(); i++)
                if(domain[i] == '.') m[domain.substr(i+1)] += times;
            }
        for(auto p: m)
            ans.push_back(to_string(p.second) + " " + p.first);
        return ans;
        }
};
```

## longest common click

## longest word in a dictionary

## sparse vector

```c++
class sparseVector{
public:
    unordered_map<int, double> sv;
    int sv_size;
    sparseVector(int size){
        sv_size = size;
    }
    sparseVector(const sparseVector& v){
        sv = v.sv;
        sv_size = v.sv_size;
    }
    void set(int index, double value){
        if(index < sv_size)
            sv[index] = value;
        else throw invalid_argument("index out of range");

    }
    double get(int index){
        if(index >= sv_size || sv.count(index) == 0) return 0.0;
        else return sv[index];
    }
    sparseVector add(sparseVector to_add){
        sparseVector added(to_add);
        for(auto p: sv){
            if(added.sv.count(p.first)) added.sv[p.first] += p.second;
            else added.sv[p.first] = p.second;
        }
        return added;
    }
    double product(sparseVector to_prod){
        double ans = 0.0;
        for(auto p: to_prod.sv){
            if(sv.count(p.first)) ans += p.second * sv[p.first];
        }
        return ans;
    }
    double norm(){
        double ans = 0.0;
        for(auto p: sv) ans += p.second * p.second;
        return sqrt(ans);

    }
    double cosine(sparseVector to_cos){
        double upper = this->product(to_cos);
        double lower = sqrt(this->norm() * to_cos.norm());
        return upper / lower;
    }
    friend ostream& operator<<(ostream& out, sparseVector sv)
	{
		for(int i = 0; i < sv.sv_size; i++){
		    out << sv.get(i) << " ";
		}
		return out;
	}

};

int main(){
    sparseVector test(5);
    test.set(0, 1.0);
    test.set(3, 2.0);
    test.set(6, 21);
    cout << test.product(test);
    return 0;
}
```



## friend circle

## meeting rooms

第一题：类似meeting rooms，输入是一个int[][] meetings, int start, int end, 每个数都是时间，13：00 =》 1300， 9：30 =》 18930， 看新的meeting 能不能安排到meetings
ex: {[1300, 1500], [930, 1200],[830, 845]}, 新的meeting[820, 830], return true; [1450, 1500] return false;

Int[] start = new int[meetings.length];
Int[] end = new

第二题：类似merge interval，唯一的区别是输出，输出空闲的时间段，merge完后，再把两两个之间的空的输出就好，注意要加上0 - 第一个的start time

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <sstream>
#include <iterator>
#include <set>
#include <unordered_set>
#include <map>
#include <unordered_map>
#include <queue>
#include <utility>

using namespace std;

class Solution {
private:
    bool myCompare(vector<int> v, vector<int> w){
        return v[0] < w[0];
    }
public:
    bool newMeeting(int meetings[][2], int start, int end){
        int ele_num = sizeof(meetings) / sizeof(int);
        int width = sizeof(meetings[0]) / sizeof(int);
        int height = ele_num / width;
        vector<vector<int>> meeting_vec(height, vector<int>(width, 0));
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                meeting_vec[i][j] = meetings[i][j];
        sort(meeting_vec.begin(), meeting_vec.end(), [](vector<int>& v, vector<int>& w){return v[0] < w[0];});
        int empty_s = 0;
        int empty_e;
        for(int i = 0; i < meeting_vec.size(); i++){
            empty_e = meeting_vec[i][0];
            if(start >= empty_s && end <= empty_e) return true;
            else empty_s = meeting_vec[i][0];
        }
        if(start >= empty_s) return true;
        return false;
    }

    void interval(int meetings[][2]){
        int height = 3, width = 2;
        vector<vector<int>> meeting_vec(height, vector<int>(width, 0));
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                meeting_vec[i][j] = meetings[i][j];
        sort(meeting_vec.begin(), meeting_vec.end(), [](vector<int>& v, vector<int>& w){return v[0] < w[0];});
        vector<vector<int>> merged;
        vector<int> current;
        int current_start = meeting_vec[0][0];
        int current_end = meeting_vec[0][1];
        for(int i = 1; i < meeting_vec.size(); i++) {
            if(meeting_vec[i][0] > current_end){
                current.push_back(current_start);
                current.push_back(current_end);
                merged.push_back(current);
                current.clear();
                current_start = meeting_vec[i][0];
                current_end = meeting_vec[i][1];
            }
            else{
                current_end = max(meeting_vec[i][1], current_end);
            }
        }
        current.push_back(current_start);
        current.push_back(current_end);
        merged.push_back(current);
        for(auto meeting: merged)
            cout << meeting[0] << " " << meeting[1] << endl;
    }
};

int main(){
    Solution solution;
    int test[3][2] = {{1300, 1500}, {30, 1200},{830, 845}};
    solution.interval(test);
    return 0;
}
```