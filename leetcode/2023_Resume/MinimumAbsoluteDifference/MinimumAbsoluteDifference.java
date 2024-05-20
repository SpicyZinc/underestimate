/*
Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows

a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr

idea:
sort
find min diff
then any equal to min diff, add to list
*/

class MinimumAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int min = arr[1] - arr[0];
        for (int i = 2; i <= arr.length - 1; i++) {
            if (arr[i] - arr[i - 1] < min) {
                min = arr[i] - arr[i - 1];
            }
        }

        for (int i = 1; i <= arr.length - 1; i++) {
            if (min == arr[i] - arr[i - 1]) {
                result.add(Arrays.asList(arr[i - 1], arr[i])); 
            }
        }

        return result;
    }
}
