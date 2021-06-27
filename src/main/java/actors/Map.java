package actors;

public class Map<T1, T2> {
    private final T1 firstElement;
    private final T2 secondElement;

    public Map(T1 firstElement, T2 secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public T1 getFirstElement() {
        return firstElement;
    }

    public T2 getSecondElement() {
        return secondElement;
    }

}
