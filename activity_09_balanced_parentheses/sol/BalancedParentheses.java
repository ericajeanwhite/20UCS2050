/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Description: Activity 09 - BalancedParentheses
 */

import java.util.Scanner;

public class BalancedParentheses {

    // TODO: implement isBalanced using a stack
    public static boolean isBalanced(String exp) {
        DynamicStackLinkedList stack = new DynamicStackLinkedList();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == '(')
                stack.push(c);
            else if (c == ')') {
                char top = stack.pop();
                if (top != '(')
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("? ");
        String exp = in.nextLine();
        System.out.print("The parentheses in expression \"" + exp + "\" are ");
        if (isBalanced(exp))
            System.out.println("balanced!");
        else
            System.out.println("not balanced!");
    }
}
