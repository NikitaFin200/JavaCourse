package ru.academits.findyurov.list.list;

import ru.academits.findyurov.list.node.ListNode;

public class LinkedList<T> {
    private ListNode<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public T getFirstValue() {
        if (head == null) {
            return null;
        }

        return head.getValue();
    }

    public T getValue(int index) {
        ListNode<T> currentNode = head;
        int currentIndex = 0;

        while (currentNode != null) {
            if (currentIndex == index) {
                return currentNode.getValue();
            }

            currentNode = currentNode.getNext();
            currentIndex++;
        }

        return null;
    }

    public T setValue(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        ListNode<T> currentNode = head;
        int currentIndex = 0;

        while (currentNode != null) {
            if (currentIndex == index) {
                System.out.println("Old element:" + currentNode.getValue());
                currentNode.setValue(value);
                System.out.println("New element:" + currentNode.getValue());
                return currentNode.getValue();
            }

            currentNode = currentNode.getNext();
            currentIndex++;
        }

        return null;
    }

    public void addValue(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        ListNode<T> currentNode = head;

        if (head == null) {
            head = newNode;
            size++;
        } else {
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                size++;
            }

            currentNode.setNext(newNode);
        }
    }

    public void print() {
        ListNode<T> currentNode = head;

        if (head != null) {
            System.out.println(head.getValue());
        }

        if (currentNode != null) {
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                System.out.println(currentNode.getValue());
            }
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        ListNode<T> currentNode = head;
        ListNode<T> previousNode = null;
        int currentIndex = 0;

        while (currentNode.getNext() != null) {
            if (currentIndex == index) {
                if (currentIndex == 0) {
                    head = currentNode.getNext();
                } else {
                    previousNode.setNext(currentNode.getNext());
                }
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
            currentIndex++;
        }
    }

    public void enterElementFirst(T value) {
        ListNode<T> node = new ListNode<>(value);
        node.setNext(head);
        head = node;
        size++;
    }
}
