package Utilities;

public class TheatreLinkedList<T>{

    private Node head;
    private class Node{
        public Node(T data){
           this.data = data;
        }
        public T data;
        public Node next = null;
    }

    public void addFront(T data){
        var newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void append(T data){

    }

    public T get(int index) throws IndexOutOfBoundsException{
        var tempNode = head;
        // if there is no check for negative index then the loop would go on forever
        for (int i = 0; tempNode != null && index >= 0; i++){
            if(i == index){
                return tempNode.data;
            }
            // Chain won't break because there can't be gaps
            tempNode = tempNode.next;
        }
        // If no return happened at this point an exception can be thrown
        throw new IndexOutOfBoundsException("Index " + index + " is not within the bounds of the list." );
    }

}
