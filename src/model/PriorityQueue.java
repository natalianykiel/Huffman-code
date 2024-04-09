package model;

public class PriorityQueue {
    private Node[] heap;
    private int size;

    public PriorityQueue() {
        heap = new Node[16];
        size = 0;
    }

    public void add(Node node) {
        if (size >= heap.length) {
            Node[] newHeap = new Node[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
        heap[size] = node;
        size++;
        int index = size - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].compareTo(heap[parentIndex]) >= 0) {
                break;
            }
            Node temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
        }
    }

    public Node remove() {
        Node min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        int index = 0;
        while (index * 2 + 1 < size) {
            int minIndex = index * 2 + 1;
            if (index * 2 + 2 < size && heap[index * 2 + 2].compareTo(heap[minIndex]) < 0) {
                minIndex = index * 2 + 2;
            }
            if (heap[index].compareTo(heap[minIndex]) <= 0) {
                break;
            }
            Node temp = heap[index];
            heap[index] = heap[minIndex];
            heap[minIndex] = temp;
            index = minIndex;
        }
        return min;
    }
    public int size() {
        return size;
    }




}
