
class SortedIterator {
    int n;
    List<Integer> indexs;
    List<List<Integer>> lists;

    public SortedIterator(List<List<Integer>> lists) {
        n = lists.size();
        indexs = new ArrayList<>();
        for (List<Integer> list: lists) {
            indexs.add(0);
        }

        this.lists = lists;
    }

    public boolean hasNext() {
        return n != 0;
    }

    public Integer next() {
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lists.size(); i++) {
            if (indexs.get(i) == lists.get(i).size()) {
                continue;
            }
            int temp = lists.get(i).get(indexs.get(i));
            if (temp < min) {
                min = temp;
                index = i;
            }
        }

        indexs.set(index, indexs.get(index) + 1);

        if (indexs.get(index) == lists.get(index).size()){
            lists.remove(index);
            indexs.remove(index);
            n--;
        }

        return min;
    }    
}
