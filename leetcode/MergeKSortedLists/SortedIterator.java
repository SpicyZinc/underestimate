
public class SortedIterator implements Iterator<Integer> {
    private PriorityQueue<Integer> pq;

    // Constructor adds lists contents into a minHeap
    public SortedIterator(List<List<Integer>> lists) {
        pq = new PriorityQueue<>();
        for (int i = 0; i < lists.size(); i++) {
            for (int j : lists.get(i)) {
                pq.offer(j);
            }
        }
    }

    private List<Integer> sortedList() {
        List<Integer> list = new ArrayList<>();
        while (hasNext()) {
            list.add(next());
        }

        return list;
    }

    @Override
    public boolean hasNext() {
        return pq.size() > 0;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return pq.poll();
        }

        return null;
    }
}
