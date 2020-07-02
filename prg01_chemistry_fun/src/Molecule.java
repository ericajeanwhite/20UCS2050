/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Student Names: Erica White && McKinnly Ingleby
 * Description: Prg01 - Molecule Class
 */

/**
 * Molecules form when two or more atoms form chemical bonds with each other.
 */
public class Molecule {

    private Node   head;
    private String name;

    // DONE: initialize the molecule with the information provided by the user
    public Molecule(final String name) {
        this.name = name;
    }

    // DONE: returns the name of the molecule
    public String getName() {
        return this.name;
    }

    // DONE: returns true/false depending whether the molecule is empty or not
    public boolean isEmpty() {
        if(head == null){
            return true;
        }
        return false;
    }

    // TODO: The elements in a molecule must be distinct;
    //  therefore, the add method should check if an element is already in the molecule before adding it;
    //  also, the elements in a molecule should respect the Hill system, which states that carbon atoms are listed first,
    //  hydrogen atoms next, and then all other elements are added in alphabetical order

    public void add(final Element element, int amount) {
        Node current = head;
        Node newNode = new Node (element, amount);
        Node alpha = head;
        Node previous = null;
        if(head == null){ // if the linkedList is empty
            head = newNode;
            return;
        }
        boolean isAlphaFound = false;
        while(current != null){
            String strCurrent = current.getElement().getAtomLetter();
            boolean isCarbon = current.getElement().getAtomLetter().equals("C");
            boolean isHydro = current.getElement().getAtomLetter().equals("H");
            if(strCurrent.equals(newNode.getElement().getAtomLetter())){
                return;
            }
            //check to see if current node is before newNode based on name
            //then set alpha == to current
            if(!isCarbon && !isHydro && !isAlphaFound && newNode.getElement().getAtomLetter().compareTo(strCurrent) < 0){
                alpha = previous;
                isAlphaFound = true;
            }
            previous = current;
            current = current.getNext();

        }


        if(newNode.getElement().getAtomLetter().equals("C")){
            newNode.setNext(head);
            head = newNode;
        } else if(newNode.getElement().getAtomLetter().equals("H")){
            if(head.getElement().getAtomLetter().equals("C")){
                Node temp = head.getNext();
                head.setNext(newNode);
                newNode.setNext(temp);
            } else{
                newNode.setNext(head);
                head = newNode;
            }
        } else if (!isAlphaFound) {
            previous.setNext(newNode);
        } else {
            Node temp = alpha.getNext();
            alpha.setNext(newNode);
            newNode.setNext(temp);

        }

    }

    // TODO: adds the given element, assuming that the amount is Node.DEFAULT_AMOUNT which is 1
    public void add(final Element element) {
        add( element, Node.DEFAULT_AMOUNT );
    }

    // TODO: returns true/false depending whether the target element can be found in the molecule or not
    public boolean contains(final Element target) {
        Node current = head;
        while(current != null){
            if(current.getElement().equals(target)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // TODO: returns the number of elements in the molecule
    public int size() {
        if(head == null){
            return 0;
        }
        Node current = head;
        int size = 0;
        while(current != null){
            size++;
            current = current.getNext();
        }
        return size;
    }

    // TODO: returns the element at the given index location (null if the index is invalid)
    public Element get(int index) {
        if(index == 0){
            return head.getElement();
        }
        if (index < 0){
            return null;
        }
        int count = 0;
        Node current = head;
        while(count <= index && count < this.size()){
            if (count == index){
                return current.getElement();
            }
            current = current.getNext();
            count ++;
        }
        return null;
    }



    // TODO: returns a textual representation of the molecule

    //CO_2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.name).append(": ");
        Node current = head;
        while(current != null){
            if(current.getAmount() == 1){
                sb.append(current.getElement().getAtomLetter());
            } else{
                sb.append(current.getElement().getAtomLetter()).append("_").append(current.getAmount());
            }

            current = current.getNext();
        }
        return sb.toString();
    }
}
