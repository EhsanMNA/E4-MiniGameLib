package ir.e4Team.e4minigamelib.Arena;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

public class ArenaList {

    private int size = 0;
    private Arena[] arenas;

    public ArenaList() {
        this.arenas = new Arena[]{};
    }

    public void add(Arena arena) {
        if (size == arenas.length) {
            ensureCapacity();
        }
        arenas[size++] = arena;
    }

    public Object get(int index) {
        checkIndex(index);
        return arenas[index];
    }

    public void remove(int index) {
        checkIndex(index);
        Object item = arenas[index];
        int numEls = arenas.length - (index + 1);
        System.arraycopy(arenas, index + 1, arenas, index, numEls);
        size--;
    }
    public boolean remove(Arena arena) {
        int i = indexOf(arena);
        if(i != -1) {
            remove(i);
            return true;
        }
        return false;
    }

    public void set(int index, Arena arena) {
        checkIndex(index);
        arenas[index] = arena;
    }

    public void forEach(Consumer<Arena> action) {
        Objects.requireNonNull(action);
        for (Arena ob : arenas) {
            action.accept(ob);
        }
    }

    public int indexOf(Arena arena) {
        for(int i = 0; i < arenas.length; i++) {
            if(arenas[i].equals(arena)) return i;
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(arenas, null);
        size = 0;
    }

    public boolean contains(Arena arena) {
        return indexOf(arena) >= 0;
    }

    public boolean containsAll(Collection<Arena> c) {
        for (Arena objects : c)
            if (!contains(objects))
                return false;
        return true;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        int newSize = arenas.length + 1;
        Arena[] newArenas = new Arena[newSize];
        System.arraycopy(arenas, 0, newArenas, 0, arenas.length);
        arenas = newArenas;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(arenas[i].toString());
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
