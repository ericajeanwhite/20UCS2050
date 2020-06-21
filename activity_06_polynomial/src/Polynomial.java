/*
 * CS 2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Description: Activity 06 - Polynomial
 */

public class Polynomial {

    private Term firstTerm;

    public Polynomial() {
        firstTerm = null;
    }

    // TODOd: return the maximum exponent value of the polynomial
    public int degree() {
        if (firstTerm != null)
            return firstTerm.getExponent();
        return 0;
    }

    // TODOd: return the number of terms of the polynomial
    public int size() {
        Term current = firstTerm;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // TODO: add the given term to the polynomial (see activity instructions for more detailed information)
    void addTerm(final Term term) {
        // 3x2 + 5 -> 8x3 + 3x2 + 5
        // newTerm -> [8x3]
        // firstTerm -> [3x2]
        Term newTerm = (Term) term.clone();
        // if the polynomial has zero terms, make the “new term” object the “first term”;
        if (firstTerm == null)
            firstTerm = newTerm;
        else if (newTerm.getExponent() > firstTerm.getExponent()){
            newTerm.setNext(firstTerm);
            firstTerm = newTerm;
        }
        else {
            Term previous = null;
            Term current = firstTerm;
            while (current != null) {
                if (newTerm.getExponent() > current.getExponent()) {
                    newTerm.setNext(current);
                    previous.setNext(newTerm);
                    return;
                }
                else if (newTerm.getExponent() == current.getExponent()) {
                    current.add(newTerm);
                    return;
                }
                else {
                    previous = current;
                    current = current.getNext();
                }
            }
            // 3x4 + 2x2
            // add 5x
            previous.setNext(newTerm);
        }
    }

    // TODO: add the terms of the given polynomial to the callee polynomial; hint: traverse the given polynomial and call addTerm to add each of its terms to the callee polynomial
    void add(final Polynomial other) {
        Term current = other.firstTerm;
        while (current != null) {
            addTerm(current);
            current = current.getNext();
        }
    }

    // TODO: traverse the callee polynomial and call toString from each of its terms to generate a string representation of the polynomial; use the following examples to figure it out the format to use:
    //  8x^3 -2x^2 + 7x + 3.2
    @Override
    public String toString() {
        String out = "";
        Term current = firstTerm;
        while (current != null) {
            if (current.getCoefficient() >= 0)
                out += " + ";
            out += current.toString();
            current = current.getNext();
        }
        return out;
    }

    // TODOd: return the term with the given exponent; null if the term does not exist
    Term getTerm(int exponent) {
        Term current = firstTerm;
        while (current != null) {
            if (exponent > current.getExponent())
                return null;
            if (exponent ==  current.getExponent())
                return current;
            current = current.getNext();
        }
        return null;
    }
}