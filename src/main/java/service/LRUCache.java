package service;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
public class LRUCache <K, V> extends LinkedHashMap<K, V> {

    private int capacity; //Maximum number of items in the cache

    public LRUCache(int capacity){
        super(capacity + 1, 1.0f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Entry entry){
        return (size() > this.capacity);
    }
}
