package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterablePlain<T> implements IterableWithPolicy<T> {

    private final List<T> elems;

    public IterablePlain(final T[] elements){
        elems = List.of(elements);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    class IteratorImpl implements Iterator<T>{

        private int current;

        public IteratorImpl(){
            super();
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return (current < elems.size() - 1 );
        }

        @Override
        public T next() {
            if (hasNext()){
                current+=1;
                return elems.get(current);
            } else {
                return null;
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return this.new IteratorImpl();
    }
}


