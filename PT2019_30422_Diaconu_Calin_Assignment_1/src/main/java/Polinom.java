import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Polinom {
    private List<Monom> monomList = new List<Monom>() {
        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean contains(Object o) {
            return false;
        }

        public Iterator<Monom> iterator() {
            return null;
        }

        public Object[] toArray() {
            return new Object[0];
        }

        public <T> T[] toArray(T[] a) {
            return null;
        }

        public boolean add(Monom monom) {
            return false;
        }

        public boolean remove(Object o) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return false;
        }

        public boolean addAll(Collection<? extends Monom> c) {
            return false;
        }

        public boolean addAll(int index, Collection<? extends Monom> c) {
            return false;
        }

        public boolean removeAll(Collection<?> c) {
            return false;
        }

        public boolean retainAll(Collection<?> c) {
            return false;
        }

        public void clear() {

        }

        public Monom get(int index) {
            return null;
        }

        public Monom set(int index, Monom element) {
            return null;
        }

        public void add(int index, Monom element) {

        }

        public Monom remove(int index) {
            return null;
        }

        public int indexOf(Object o) {
            return 0;
        }

        public int lastIndexOf(Object o) {
            return 0;
        }

        public ListIterator<Monom> listIterator() {
            return null;
        }

        public ListIterator<Monom> listIterator(int index) {
            return null;
        }

        public List<Monom> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public Polinom() {
        monomList.add(new Monom(3, 2));
    }
}
