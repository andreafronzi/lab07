package it.unibo.inner.test.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T>  implements IterableWithPolicy<T>{

    private final T[] array;

    public IterableWithPolicyImpl(final T[] array){
        this.array = array;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{

        private int index;

        private ArrayIterator(){
            this.index = 0;
        }
        
        @Override
        public boolean hasNext() {
            if(this.index < IterableWithPolicyImpl.this.array.length){
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if(this.hasNext()){
                return IterableWithPolicyImpl.this.array[index++];
            }else{
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            Iterator.super.remove();
        }

        

        
    }

    
}
