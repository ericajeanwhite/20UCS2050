/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Student Names:
 * Description: Activity 12 - DynamicQueueLinkedList Class
 */

import java.util.NoSuchElementException;

public class DynamicQueueLinkedList<T> extends Queue<T> {

    private Node<T> front, rear;

    public DynamicQueueLinkedList() {
        front = rear = null;
    }

    // TODOd: implement the method
    @Override
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty())
            front = rear = newNode;
        else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    // TODOd: implement the method
    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        T data = front.getData();
        Node<T> temp = front;
        front = front.getNext();
        if (front == null)
            rear = null;
        temp.setNext(null);
        return data;
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return front.getData();
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> current = front;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public String toString() {
        String out = "(front)";
        Node current = front;
        while (current != null) {
            out += " " + current.toString();
            current = current.getNext();
        }
        out += " (rear)";
        return out;
    }
}