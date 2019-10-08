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

    public int length(){
            var tempNode = head;
            // Continue loop until end of list when it should return the number
            for (int i = 0;;i++){
               if (tempNode == null){
                   return i;
               }
               else{
                   tempNode = tempNode.next;
               }
            }
    }

    public void removeAll(){
        // Removing all is very easy, setting head to null will make all of its children disappear;
       head = null;
    }

    public void remove(int index) throws  IndexOutOfBoundsException{
            var tempNode = head;
            if(index == 0){
                head = head.next;
            }
            for (int i = 0; tempNode != null && index >= 0; i++){
                // Stop at the node before
                if (i == index - 1 && tempNode.next != null){
                    // Set the next of the current node to be the next of the following node
                   tempNode.next = tempNode.next.next;
                   return;
                }
                else{
                    tempNode = tempNode.next;
                }
            }
        // If no return happened at this point an exception can be thrown
        throw new IndexOutOfBoundsException("Index " + index + " is not within the bounds of the list." );
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
