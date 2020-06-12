/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Description: Activity 08 - DynamicStackDriver
 */

public class DynamicStackDriver {

    public static void main(String[] args) {
        DynamicStackArray stack = new DynamicStackArray();
//        DynamicStackLinkedList stack = new DynamicStackLinkedList();
        System.out.println(stack);
        stack.push(5);
        System.out.println(stack);
        stack.push(8);
        System.out.println(stack);
        stack.push(3);
        System.out.println(stack);
        System.out.println("Top of the stack is: " +  stack.peek());
        int data = stack.pop();
        System.out.println(data + " was popped out from the stack!");
        System.out.println(stack);
        data = stack.pop();
        System.out.println(data + " was popped out from the stack!");
        System.out.println(stack);
        data = stack.pop();
        System.out.println(data + " was popped out from the stack!");
        System.out.println(stack);
    }
}
