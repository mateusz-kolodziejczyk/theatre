package Utilities;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class TheatreLinkedList<T> implements Iterable<T>{

    private Node head;
    private class Node{
        private T data;
        private Node next = null;
        Node(T data){
           this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        else{
            return false;
        }
    }


    public void addFront(T data){
        var newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
    }

    public void append(T data){

    }

    public int size(){
            var tempNode = head;
            // Continue loop until end of list when it should return the number
            for (int i = 0;;i++){
               if (tempNode == null){
                   return i;
               }
               else{
                   tempNode = tempNode.getNext();
               }
            }
    }

    public void removeAll(){
        // Removing all is very easy, setting head to null will make all of its children disappear;
       head = null;
    }

    public void removeIndex(int index) throws  IndexOutOfBoundsException{
            var tempNode = head;
            if(index == 0){
                head = head.getNext();
            }
            for (int i = 0; tempNode != null && index >= 0; i++){
                // Stop at the node before
                if (i == index - 1 && tempNode.getNext() != null){
                    // Set the next of the current node to be the next of the following node
                   tempNode.setNext(tempNode.getNext().getNext());
                    return;
                }
                else{
                    tempNode = tempNode.getNext();
                }
            }
        // If no return happened at this point an exception can be thrown
        throw new IndexOutOfBoundsException("Index " + index + " is not within the bounds of the list." );
    }

    public boolean removeItem(T item){
        var tempNode = head;
        // If the item is at the front
        if (head != null && head.getData().equals(item)) {
            head = head.next;
            return true;
        }
        while(tempNode != null){
            // Prevent null pointers
            if(tempNode.getNext() != null){
                if(tempNode.getNext().getData().equals(item)){
                    tempNode.setNext(tempNode.getNext().getNext());
                    return true;
                }
            }
            tempNode = tempNode.getNext();
        }
        // If no item found, return false;
        return false;
    }

    public T get(int index) throws IndexOutOfBoundsException{
        var tempNode = head;
        // if there is no check for negative index then the loop would go on forever
        for (int i = 0; tempNode != null && index >= 0; i++){
            if(i == index){
                return tempNode.data;
            }
            // Chain won't break because there can't be gaps
            tempNode = tempNode.getNext();
        }
        // If no return happened at this point an exception can be thrown
        throw new IndexOutOfBoundsException("Index " + index + " is not within the bounds of the list." );
    }



    public Iterator<T> iterator() {
        return new TheatreIterator(this);
    }
    // Implementation of iterable/iterator based on https://codereview.stackexchange.com/questions/141560/an-iterable-implementation-of-linkedlist
    // and https://www.geeksforgeeks.org/java-implementing-iterator-and-iterable-interface/
    private class TheatreIterator implements Iterator<T>{
        private Node current;
        public TheatreIterator(TheatreLinkedList<T> list){
           current = list.getHead();
        }
        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException("Iterator Exceeded");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    private Node getHead() {
        return head;
    }
}
