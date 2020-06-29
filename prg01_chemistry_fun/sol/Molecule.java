/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Student Names:
 * Description: Prg01 - Molecule Class
 */

/**
 * Molecules form when two or more atoms form chemical bonds with each other.
 */
public class Molecule {

    private Node   head;
    private String name;

    // TODO: initialize the molecule with the information provided by the user
    public Molecule(final String name) {
        this.name = name;
        head = null;
    }

    // TODO: returns the name of the molecule
    public String getName() {
        return name;
    }

    // TODO: returns true/false depending whether the molecule is empty or not
    public boolean isEmpty() {
        return head == null;
    }

//    public void add(final Element element, int amount) {
//        if (isEmpty())
//            head = new Node(element, amount);
//        else {
//            String atomLetter = element.getAtomLetter();
//            Element headElement = head.getElement();
//            String headAtomLetter = headElement.getAtomLetter();
//            // special case adding "C"
//            if (atomLetter.equals("C")) {
//                // and current head is NOT already "C"
//                if (headAtomLetter.equals("C"))
//                    return;
//                // add the element as the new head
//                Node newNode = new Node(element, amount);
//                newNode.setNext(head);
//                head = newNode;
//                return;
//            }
//            // other cases...
//            if (atomLetter.compareTo(headAtomLetter) < 0) {
//                Node newNode = new Node(element, amount);
//                newNode.setNext(head);
//                head = newNode;
//            }
//            else {
//                Node current = head.getNext();
//                Node previous = head;
//                while (current != null) {
//                    Element currentElement = current.getElement();
//                    String currentAtomLetter = currentElement.getAtomLetter();
//                    if (atomLetter.compareTo(currentAtomLetter) < 0) {
//                        Node newNode = new Node(element, amount);
//                        previous.setNext(newNode);
//                        newNode.setNext(current);
//                        return;
//                    }
//                    else if (atomLetter.compareTo(currentAtomLetter) == 0)
//                        return;
//                    else {
//                        previous = current;
//                        current = current.getNext();
//                    }
//                }
//                Node newNode = new Node(element, amount);
//                previous.setNext(newNode);
//            }
//        }
//    }

    // TODO: The elements in a molecule must be distinct; therefore, the add method should check if an element is already in the molecule before adding it; also, the elements in a molecule should respect the Hill system, which states that carbon atoms are listed first, hydrogen atoms next, and then all other elements are added in alphabetical order
    public void add(final Element element, int amount) {
        // create new node from element
        Node newNode = new Node(element, amount);
        // if list is empty, just set the new node to be the head
        if (isEmpty())
            head = newNode;
        else {
            // create carbon and hydrogen elements for easy comparison
            Element carbon   = new Element("C");
            Element hydrogen = new Element("H");
            // check carbon addition
            if (element.equals(carbon)) {
                // if head is already carbon, return
                if (head.getElement().equals(carbon))
                    return;
                // if not, add the element as the new head
                newNode.setNext(head);
                head = newNode;
            }
            // if not, check hydrogen addition
            else if (element.equals(hydrogen)) {
                // if head is carbon, then hydrogen should be added after it
                if (head.getElement().equals(carbon)) {
                    Node current = head.getNext();
                    // if there is no element after head, add the hydrogen element right after it
                    if (current == null)
                        head.setNext(newNode);
                    // there is an element after head and the element is hydrogen, return
                    else if (current.getElement().equals(hydrogen))
                        return;
                    // there is an element after head and the element is NOT hydrogen
                    else {
                        newNode.setNext(current);
                        head.setNext(newNode);
                    }
                }
                // head is NOT carbon, check if head is hydrogen
                else if (head.getElement().equals(hydrogen))
                    return;
                // head is not hydrogen
                else {
                    newNode.setNext(head);
                    head = newNode;
                }
            }
            // all other cases
            else {
                Node current = head;
                Node previous = null;
                while (current != null) {
                    if (current.getElement().equals(carbon) || current.getElement().equals(hydrogen)) {
                        previous = current;
                        current = current.getNext();
                    }
                    else if (element.getAtomLetter().compareTo(current.getElement().getAtomLetter()) > 0) {
                        previous = current;
                        current = current.getNext();
                    }
                    else if (element.getAtomLetter().compareTo(current.getElement().getAtomLetter()) == 0)
                        return;
                    else {
                        newNode.setNext(current);
                        previous.setNext(newNode);
                        return;
                    }
                } // end while
                previous.setNext(newNode);
            }
        }
    }

    // TODO: adds the given element, assuming that the amount is Node.DEFAULT_AMOUNT which is 1
    public void add(final Element element) {
        this.add(element, Node.DEFAULT_AMOUNT);
    }

    // TODO: returns true/false depending whether the target element can be found in the molecule or not
    public boolean contains(final Element target) {
        Node current = head;
        while (current != null) {
            Element element = current.getElement();
            if (element.equals(target))
                return true;
        }
        return false;
    }

    // TODO: returns the number of elements in the molecule
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // TODO: returns the element at the given index location (null if the index is invalid)
    public Element get(int index) {
        if (index < 0 || index >= size())
            return null;
        int i = 0;
        Node current = head;
        while (i < index) {
            i++;
            current = current.getNext();
        }
        return current.getElement();
    }

    // TODO: returns a textual representation of the molecule
    @Override
    public String toString() {
        String str = "\"" + name + "\": ";
        Node current = head;
        while (current != null) {
            Element element = current.getElement();
            str += element.getAtomLetter();
            if (current.getAmount() > Node.DEFAULT_AMOUNT)
                str += "_" + current.getAmount();
            current = current.getNext();
        }
        return str;
    }
}
