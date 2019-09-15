/**
 * Implementation of a minimum heap to understand how
 * the inners of PriorityQueue in Java works
 */
public class Heap {
    private int[] data; // The heap represented by an integer array
    private int currSize; // The current size of the heap
    private int maxSize; // The maximum size of the heap

    public Heap(int size) {
        this.data = new int[size];
        this.currSize = 0;
        this.maxSize = size;

        for (int i = 0; i < maxSize; ++i) {
            data[i] = Integer.MAX_VALUE;
        }
    }

    // Removes and returns the minimum element from the heap
    public int remove() {
        if (currSize <= 0) return Integer.MAX_VALUE;
        if (currSize == 1) {
            --currSize;
            return data[0];
        }

        // Get the minimum
        // Swap the first and last elements
        // Replace the now-updated last element to a deleted marker
        // e.g. Integer.MAX_VALUE
        int min = data[0];
        swap(0, maxSize - 1);
        data[maxSize - 1] = Integer.MAX_VALUE;
        --currSize;
        heapify(0);

        return min;
    }

    // Adds an element onto the heap
    // Time complexity: log(n) where n is the number of elements in heap
    public void add(int value) {
        if (currSize == maxSize) {
            System.out.println("The heap is full!");
            return;
        }

        data[currSize] = value;
        int temp = currSize;
        ++currSize;

        while (data[temp] < data[parent(temp)] && temp != 0) {
            swap(parent(temp), temp);
            temp = parent(temp);
        }
    }

    // Returns the parent node of the current node
    private int parent(int node) {
        return (node - 1) / 2;
    }

    // Returns the left child of the current node
    private int leftChild(int node) {
        return (2 * node) + 1;
    }

    // Returns the right child of the current node
    private int rightChild(int node) {
        return (2 * node) + 2;
    }

    //  Recursively apply the heap property to the data
    private void heapify(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < maxSize && data[left] < data[smallest]) {
            smallest = left;
        } else if (right < maxSize && data[right] < data[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(smallest, index);
            heapify(smallest);
        }
    }

    public void swap(int firstIndex, int secondIndex) {
        data[firstIndex] = data[firstIndex] ^ data[secondIndex];
        data[secondIndex] = data[secondIndex] ^ data[firstIndex];
        data[firstIndex] = data[firstIndex] ^ data[secondIndex];
    }

    public void print() {
        for (int e : data) {
            System.out.print(e + " ");
        }

        System.out.println();
    }

    public static void main (String[] args) {
        Heap heap = new Heap(5);

        heap.add(6);
        heap.print();
        heap.add(3);
        heap.print();
        heap.add(5);
        heap.print();
        heap.add(2);
        heap.print();
        heap.add(4);
        heap.print();
        System.out.println("Minimum extracted: " + heap.remove());
        heap.print();
        System.out.println("Minimum extracted: " + heap.remove());
        heap.print();
    }
}
