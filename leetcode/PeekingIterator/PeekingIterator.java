/*
Given an Iterator class interface with methods: next() and hasNext(), 
design and implement a PeekingIterator that support the peek() operation
it essentially peek() at the element that will be returned by the next call to next().

eg. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:
Think of "looking ahead". You want to cache the next element.
Is one variable sufficient? Why or why not?
Test your design with call order of peek() before next() vs next() before peek().
For a clean implementation, check out Google's guava library source code.

Follow up: How would you extend your design to be generic and work with all types, not just integer?

idea:
Approach is, use the passed iterator in constructor.
Have a field which points to current element, in order for peek() to work. 
Whenever peek is called, first check this field and if not empty return that. 
Otherwise, get the next element from parent iterator and store in it. 
Accordingly while accessing hasNext() and next() methods, consider the value of field before passing to parent iterator.

ArrayList extends AbstractList which implements Iterable
https://stackoverflow.com/questions/5849154/can-we-write-our-own-iterator-in-java
*/


// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    private Integer next = null;
    private Iterator<Integer> iter;
    
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer nextResult = next;
        next = iter.hasNext() ? iter.next() : null;

        return nextResult;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer current;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        this.current = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (current == null && iterator.hasNext()) {
            current = iterator.next();
        }
        return current;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (current != null) {
            Integer temp = current;
            current = null;
            return temp;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (current != null) {
            return true;
        }
        return iterator.hasNext();
    }
}

// similar to List<Integer> list = new ArrayList<>();
// Iterator iterator = list.iterator();
// this way makes XinList instance has .iterator() method
public class XinList<Type> implements Iterable<Type> {
    private Type[] arrayList;
    private int currentSize;

    public XinList(Type[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Type next() {
                return arrayList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}

// how to write own iterator
public class ExamplesIterator implements Iterator<Integer> {
    private List<Integer> examples;
    private int index;

    public ExamplesIterator(List<Integer> examples) {
        this.examples = examples;
        index = 0;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return examples.get(index++);
        } else {
            throw new NoSuchElementException("There are no elements size = " + examples.size());
        }
    }

    @Override
    public boolean hasNext() {
        return !(examples.size() == index);
    }

    @Override
    public void remove() {
        throw new IllegalStateException("You can't delete element before first next() method call");
    }
}