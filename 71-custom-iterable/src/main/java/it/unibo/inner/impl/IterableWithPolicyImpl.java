package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements){
        this(
            elements,
            new Predicate<>() {
                @Override
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    public IterableWithPolicyImpl(final T[] elements, Predicate<T> filter){
        this.elements = List.of(elements);
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T>{
        private int currentIndex=0;

        @Override
        public boolean hasNext() {
            while (this.currentIndex < elements.size()){
                T element = elements.get(this.currentIndex);
                if (filter.test(element)){
                    return true;
                }
                this.currentIndex++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()){
                return elements.get(this.currentIndex++);
            } else {
                throw new NoSuchElementException();
            }
        }

    }
}


