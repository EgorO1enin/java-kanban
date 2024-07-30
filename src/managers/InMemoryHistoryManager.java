package managers;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Map<Integer, Node> tasks = new HashMap<>();
    private Node head;
    private Node tail;

    @Override
    public void add(Task task) {
        int id = task.getId();
        Node node = tasks.get(id);
        if (node != null) { // Задача уже есть в списке
            removeNode(node);
        }
        linkLast(task);
        tasks.put(id, head);
    }


    public void removeNode(Node node) {
        if (node != null) {
            tasks.remove(node.task.getId());
            Node prev = node.prev;
            Node next = node.next;

            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }

            node.prev = null;
            node.next = null;

            if (node == head) {
                head = next;
            }
            if (node == tail) {
                tail = prev;
            }
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node curEl = head;
        while (curEl != null) {
            tasks.add(curEl.task);
            curEl = curEl.next;
        }
        return tasks;
    }

    @Override
    public void remove(int id) {
        Node node = null;
        for (Node n = head; n != null; n = n.next) {
            if (n.task.getId() == id) {
                node = n;
                break;
            }
        }
        if (node != null) {
            removeNode(node);
        }
    }

    void linkLast(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
            newNode.prev = last;
        }


    }
}
