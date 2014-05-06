
package hashmap;

import java.util.LinkedList;

/**
 * @author capezzbr
 * @param <K> is the key type
 * @param <V> is the value type
 */
public class HashMap<K, V> {
   
   class Item<K, V> {
      private final K key;
      private V value;
      
      public Item(K key, V value) {
         this.key = key; this.value = value;
      }
      
      public K getKey() {
         return this.key;
      }
      
      public V getValue() {
         return this.value;
      }
      
      public void setValue(V value) {
         this.value = value;
      }
      
      public boolean hasSameKey(K key) {
         return this.key == null ? this.key == key : this.key.equals(key);
      }
   }
   
   private final static int DEFAULT_SIZE = 32;
   private final int size;
   private final LinkedList<Item<K, V>>[] elements;
   
   public HashMap() {
      this(DEFAULT_SIZE);
   }
   
   public HashMap(int size) {
      this.size = size;
      this.elements = (LinkedList<Item<K, V>>[])new LinkedList[size];
   }
   
   // http://www.eternallyconfuzzled.com/tuts/algorithms/jsw_tut_hashing.aspx
   private int simpleHash(K key) {
      if ( key == null ) return 0;
      else {
         int hash = 7;
         String strKey = key.toString();
         for (int i=0; i < strKey.length(); i++) {
            hash = hash * 31 + strKey.charAt(i);
         }
         return hash;
      }
   }
   
   public boolean hasElement(K key) {
      int index = simpleHash(key) % size;
      if ( elements[index] == null ) {
         return false;
      }
      
      for ( Item<K, V> i : elements[index] ) {
         if ( i.hasSameKey(key) ) {
            return true;
         }
      }
      return false;
   }
   
   public V get(K key) {
      int index = simpleHash(key) % size;
      if ( elements[index] == null ) {
         return null;
      }
      
      for ( Item<K, V> i : elements[index] ) {
         if ( i.hasSameKey(key) ) {
            return i.getValue();
         }
      }
      return null;
   }
   
   public void put(K key, V value) {
      int index = simpleHash(key) % size;
      if ( elements[index] == null ) {
         elements[index] = new LinkedList<>();
      }
      
      for ( Item<K, V> i : elements[index] ) {
         if ( i.hasSameKey(key) ) {
            elements[index].remove(i);
            break;
         }
      }
      
      elements[index].add( new Item<>(key, value) );
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      int elementIndex;
      for ( int k = 0; k < size; k++ ) {
         if ( elements[k] != null ) {
            elementIndex = 0;
            sb.append("[").append(k).append("] -> {");
            for ( Item<K, V> i : elements[k] ) {
               sb.append(i.getKey()).append(" = ").append(i.getValue());
               if ( elementIndex < elements[k].size() -1 ) {
                  sb.append(", ");
               }
               elementIndex++;
            }
            sb.append("}\n");
         }
      }
      return sb.toString();
   }
   
}