package Fate;

import java.util.ArrayList;
import java.util.List;

public class Queue <E>{    private List<E> items;
    private int size;

    public Queue() {
        items = new ArrayList<>();
        size = 0;
    }

    public void enqueue(E item) {
        items.add(item);
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        E item = items.get(0);
        items.remove(0);
        size--;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        return items.get(0);
    }

    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size() {
        return size;
    }
}
