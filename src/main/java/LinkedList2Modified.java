import java.util.ArrayList;

public class LinkedList2Modified
{
    private DummyNode head;
    private DummyNode tail;

    public LinkedList2Modified()
    {
        head = new DummyNode();
        tail = new DummyNode();
        head.next = tail;
        tail.prev = head;
    }

    public Node getHead() {
        return head.next == tail ? null : head.next;
    }

    public Node getTail() {
        return tail.prev == head ? null : tail.prev;
    }

    public void addInTail(Node _item)
    {
        _item.next = tail;
        _item.prev = tail.prev;
        tail.prev.next = _item;
        tail.prev = _item;
    }

    public Node find(int _value)
    {
        for (Node node = head.next; node != null && node.getClass() != DummyNode.class; node = node.next) {
            if (node.value == _value) {
                return node;
            }
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (Node node = head.next; node != null && node.getClass() != DummyNode.class; node = node.next) {
            if (node.value == _value)
                nodes.add(node);
        }
        return nodes;
    }

    public boolean remove(int _value)
    {
        for (Node node = head.next; node != null && node.getClass() != DummyNode.class; node = node.next) {
            if (node.value == _value) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                return true;
            }
        }

        return false;
    }

    public void removeAll(int _value)
    {
        for (Node node = head.next; node != null && node.getClass() != DummyNode.class; node = node.next) {
            if (node.value == _value) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
    }

    public void clear()
    {
        head.next = tail;
        tail.prev = head;
    }

    public int count()
    {
        int count = 0;
        for (Node node = head.next; node != null && node.getClass() != DummyNode.class; node = node.next) {
            count++;
        }
        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if (_nodeToInsert == null) {
            return;
        }

        if (_nodeAfter == null) {
            _nodeToInsert.next = head.next;
            head.next.prev = _nodeToInsert;
            head.next = _nodeToInsert;
            return;
        }

        for (Node node = head.next; node != null && node.getClass() != DummyNode.class; node = node.next) {
            if (node == _nodeAfter) {
                _nodeToInsert.next = node.next;
                _nodeToInsert.prev = node;
                node.next.prev = _nodeToInsert;
                node.next = _nodeToInsert;
                return;
            }
        }

    }
}

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

class DummyNode extends Node {
    public DummyNode() {
        super(-1);
    }
}

