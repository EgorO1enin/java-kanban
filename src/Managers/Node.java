package Managers;

import Tasks.Task;

public class Node {
    Task task;
    Node next;
    Node prev;

    public Node(Task task) {
        this.task = task;
        next = null;
        prev = null;
    }
}
