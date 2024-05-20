/*
A sewer drainage system is structured as a tree. Water enters the system at n nodes numbered from 0 to n-1 and flows through the tree to the root, which has the number 0.
The tree structure is defined by an array parent, where parent[i] = j means that water flows from node i to its direct parent node j.
Water exits the ‍‍‌‌‍system after it flows through the root, so the root has no parent, represented as parent[0] = -1.
The value in input[i] denotes the amount of water that enters the sewer system at node i. This excludes water that flows into i from its children.
The total flow through a node is thus the flow that enters at that node, plus the sum of the total flows of all of its children.
Your task is to divide the system into two smaller pieces by cutting one branch so that the total flows of the resulting sub trees are as close as possible.

Example:
parent = [-1, 0, 0, 1, 1, 2]
input = [1, 2, 2, 1, 1, 1]
Input
Cut the branch between nodes 1 and 0.
The partition {0, 2, 5) has total flow input[0] + input[2] + input[5] = 1 + 2 + 1 = 4.
The partition {1, 3, 4) has total flow input[1] + input[3] + input[4] = 2 + 1 + 1 = 4.
The absolute difference between the total flows of the two new sewer systems is 4 -4 = 0. It's not possible for a different division to achieve a smaller difference than 0, so the final answer is 0.

idea:
先construct nodes: nodes = [TreeNode(inputs[i]) for i in range(len(parent)]
然后用parents construct tree
用DFS 算出每个节点的总出水量
iterate nodes, nodes[0]是总出水量, node[i] 是 i node 的总出水量, 出水量差 = abs(nodes[0].flow - 2 * node[i].flow)

bfs: https://www.1point3acres.com/bbs/thread-847207-1-1.html
*/

import java.util.*;

class Sewer {
    public static void main(String[] args) {
        Sewer eg = new Sewer();
        Integer[] p = {-1, 0, 0, 1, 1, 2};
        Integer[] i = {1, 2, 2, 1, 1, 1};
        List<Integer> parents = Arrays.asList(p);
        List<Integer> inputs = Arrays.asList(i);

        int result = eg.drainagePartition(parents, inputs);
        int result2 = eg.drainagePartition2(parents, inputs);
        System.out.println(result);
        System.out.println(result2);
    }

    int drainagePartition(List<Integer> parents, List<Integer> inputs) {
        int size = parents.size();
        int[] sum = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            int parent = parents.get(i);
            int child = inputs.get(i);

            sum[i] += child;
            if (parent == -1) {
                continue;
            }
            sum[parent] += sum[i];
        }

        int totalSum = sum[0];
        int minDiff = sum[0];

        for (int value : sum) {
            minDiff = Math.min(minDiff, Math.abs(totalSum - value * 2));
        }

        return minDiff;
    }

    int drainagePartition2(List<Integer> parents, List<Integer> inputs) {
        int size = parents.size();

        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            tree.add(new ArrayList<Integer>());
        }

        // where parent[i] = j means that water flows from node i to its direct parent node j
        for (int i = 0; i < size; i++) {
            int parent = parents.get(i);
            if (parent == -1) {
                continue;
            }

            tree.get(parent).add(i);
        }

        int[] waterSum = new int[size];

        int totalSum = dfs(inputs, tree, waterSum, 0);

        int result = Integer.MAX_VALUE;
        for (int subTreeSum : waterSum) {
            result = Math.min(result, Math.abs(totalSum - subTreeSum * 2));
        }

        return result;
    }

    int dfs(List<Integer> inputs, List<List<Integer>> tree, int[] waterSum, int nodeIndex) {
        int nodeValue = inputs.get(nodeIndex);

        if (tree.get(nodeIndex).size() == 0) {
            waterSum[nodeIndex] = nodeValue;
            return nodeValue;
        }

        for (int child : tree.get(nodeIndex)) {
            nodeValue += dfs(inputs, tree, waterSum, child);
        }

        waterSum[nodeIndex] = nodeValue;

        return nodeValue;
    }
}

