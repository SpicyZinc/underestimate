
/*
leetcode 944
idea: need to come back to this poorly described problem

*/

class DeleteColumnsToMakeSorted {
    public int minDeletionSize(String[] A) {
        if (A.length == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 0; j < A.length - 1; j++) {
                // once we find violation of current column, break the loop, then check next column
                if (A[j].charAt(i) > A[j + 1].charAt(i)) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
}

