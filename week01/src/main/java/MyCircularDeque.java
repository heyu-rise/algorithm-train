/**
 * 设计循环双端队列（Medium）
 *
 * @author heyu
 * @date 2021/11/21
 */
public class MyCircularDeque {

    private final int length;

    private int currentLength = 0;

    Node<Integer> first = null;

    Node<Integer> last = null;

    public MyCircularDeque(int k) {
        this.length = k;
    }

    public boolean insertFront(int value) {
        if (length == 0) {
            return false;
        }
        if (currentLength == length) {
            return false;
        }
        currentLength++;
        if (currentLength == 1) {
            Node<Integer> current = new Node<>(null, value, null);
            first = current;
            last = current;
            return true;
        }
        Node<Integer> item = new Node<>(null, value, first);
        first.prev = item;
        first = item;
        return true;
    }

    public boolean insertLast(int value) {
        if (length == 0) {
            return false;
        }
        if (currentLength == length) {
            return false;
        }
        currentLength++;
        if (currentLength == 1) {
            Node<Integer> current = new Node<>(null, value, null);
            first = current;
            last = current;
            return true;
        }
        Node<Integer> item = new Node<>(last, value, null);
        last.next = item;
        last = item;
        return true;
    }

    public boolean deleteFront() {
        if (currentLength == 0) {
            return false;
        }
        currentLength--;
        if (currentLength == 0) {
            last = null;
            first = null;
            return true;
        }
        first = first.next;
        first.prev = null;
        return true;
    }

    public boolean deleteLast() {
        if (currentLength == 0) {
            return false;
        }
        currentLength--;
        if (currentLength == 0) {
            last = null;
            first = null;
            return true;
        }
        last = last.prev;
        last.next = null;
        return true;
    }

    public int getFront() {
        if (currentLength == 0) {
            return -1;
        }
        return first.item;
    }

    public int getRear() {
        if (currentLength == 0) {
            return -1;
        }
        return last.item;
    }

    public boolean isEmpty() {
        return currentLength == 0;
    }

    public boolean isFull() {
        return currentLength == length;
    }


    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
