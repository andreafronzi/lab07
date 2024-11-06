package it.unibo.inner.test.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T>  implements IterableWithPolicy<T>{

    
    private Predicate<T> predicate;
    private final T[] array;

    public IterableWithPolicyImpl(final T[] array){
        this(array, 
            new Predicate<T>(){
                
                @Override     
                public boolean test(T elem){
                    return true;
                }
            });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate){
        this.array = array;
        this.predicate = predicate;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
        
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{

        private int index;

        @Override
        public boolean hasNext() {
            while(this.index < array.length){
                final T elem = array[index];
                if(predicate.test(elem)){
                    return true;
                }
                this.index++;
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
