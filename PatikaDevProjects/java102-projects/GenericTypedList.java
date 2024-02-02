import java.util.Arrays;

public class GenericTypedList<T> {
    private int capacity = 10, lastIndex = -1;
    private Object[] list;

    public GenericTypedList() {
        this.list = new Object[capacity];
    }

    public GenericTypedList(int capacity) {
        this.capacity = capacity;
        this.list = new Object[capacity];
    }

    public int size() {
        return this.lastIndex + 1;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void add(T data) {
        if (lastIndex + 1 == capacity) {
            capacity *= 2;
            Object[] temp = Arrays.copyOf(list, list.length);
            list = new Object[capacity];
            for (int i = 0; i < temp.length; i++) {
                list[i] = temp[i];
            }
        }
        list[++lastIndex] = data;
    }

    public T get(int index) {
        return (T) list[index];
    }

    public void remove(int index) {
        if (index < 0 || index > lastIndex) {
            System.out.println("No item found at index " + index);
        } else {
            for (int i = index; i < lastIndex; i++) {
                list[i] = list[i + 1];
            }
            lastIndex--;
        }
    }

    public <T> void set(int index, T data) {
        if (index < 0 || index > lastIndex) {
            System.out.println("No item found at index " + index);
        }
        list[index] = data;
    }

    public void printList() {
        if (lastIndex == -1) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < list.length; i++) {
                if (i == lastIndex) {
                    System.out.println(list[i] + "]");
                    break;
                } else {
                    System.out.print(list[i] + ", ");
                }
            }
        }
    }

    public int indexOf(T data) {
        int index = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int lastIndexOf(T data) {
        int index = -1;
        for (int i = lastIndex; i >= 0; i--) {
            if (list[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean isEmpty() {
        return lastIndex == -1;
    }

    public T[] toArray() {
        return (T[]) list;
    }

    public void clear() {
        capacity = 10;
        lastIndex = -1;
        list = new Object[capacity];
    }

    public GenericTypedList<T> subList(int start, int finish) {
        GenericTypedList<T> subList = new GenericTypedList<>(finish - start + 1);
        for (int i = 0; i < finish - start + 1; i++) {
            subList.add((T) list[i]);
        }
        return subList;
    }

    public boolean contains(T data) {
        for (int i = 0; i < list.length; i++) {
            if (data == list[i])
                return true;
        }
        return false;
    }
}
