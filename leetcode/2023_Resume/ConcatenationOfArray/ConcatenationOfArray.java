
class ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        int size = nums.length;

        int[] duplicate = new int[size * 2];

        for (int i = 0; i < size; i++) {
            duplicate[i] = nums[i];
            duplicate[i + size] = nums[i];
        }

        return duplicate;
    }
}
