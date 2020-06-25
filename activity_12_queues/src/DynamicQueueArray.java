/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Student Names:
 * Description: Activity 12 - DynamicQueueArray Class
 */

public class DynamicQueueArray<T> extends StaticQueueArray<T> {

    public static final int CAPACITY_INCREMENT = 5;

    public DynamicQueueArray() {
        super();
    }

    public DynamicQueueArray(int capacity) {
        super(capacity);
    }

    // TODOd: implement the method
    @Override
    public void push(T data) {
        if (size == this.data.length) {
            Object newData[] = new Object[this.data.length + CAPACITY_INCREMENT];
            // CORRECTION: this is the right way to copy the arrays
            for (int i = 0, j = front, k = 0; i < this.data.length; i++, k++) {
                newData[k] = this.data[j];
                j = (j + 1) % this.data.length;
            }
            this.front = 0; // CORRECTION: don't forget to update front on the new array
            this.data = newData;
        }
        super.push(data);
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
