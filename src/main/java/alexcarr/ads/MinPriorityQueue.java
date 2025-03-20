package alexcarr.ads;

public class MinPriorityQueue {
    private int[] queue;
    private int size = 0;
    private int maxSize;

    public MinPriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
    }

    private boolean isLeaf(int i) {
        if (i > (size/2)) {
            return true;
        } else {
            return false;
        }
    }

    private void swap(int i, int j) {
        int temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    private int parent(int i) {
        return i/2;
    }

    private int right(int i)
    {
        return 2*i + 1;
    }

    private int left(int i) {
        return 2*i;
    }

    // Implement the queue as a min-heap to keep the smallest key at the root node
    private void minHeapify(int i) {
        if(!isLeaf(i)) {
            int newIndex = i;
            if(right(i) <= size) {
                if (queue[left(i)] < queue[right(i)]) {
                    newIndex = left(i);
                } else {
                    newIndex = right(i);
                }
            }
            else {
                newIndex = left(i);
            }

            if (queue[i] > queue[left(i)] || queue[i] > queue[right(i)]) {
                swap(i, newIndex);
                minHeapify(newIndex);
            }
        }
    }

    public void insert(int key) {
        if (size >= maxSize) {
            return;
        }

        size++;
        queue[size] = key;
        int i = size;

        while (queue[i] < queue[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public static void insert(MinPriorityQueue Q, int x) {
        Q.insert(x);
    }

    public static int min(MinPriorityQueue Q) {
        return Q.min();
    }

    public static int extractMin(MinPriorityQueue Q) {
        return Q.remove();
    }

    public int min() {
        return queue[1];
    }

    public int remove()
    {
        int key = queue[1];
        size--;
        queue[1] = queue[size];
        minHeapify(1);

        return key;
    }

    public static void main(String[] args) {
        MinPriorityQueue minPriorityQueue = new MinPriorityQueue(10);
        minPriorityQueue.insert(10);
        minPriorityQueue.insert(20);
        minPriorityQueue.insert(30);
        minPriorityQueue.insert(40);
        System.out.println(minPriorityQueue.min());
        System.out.println(minPriorityQueue.remove());
        System.out.println(minPriorityQueue.min());
    }
}
