package org.uma.ed.datastructures.hashtable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LPHashTableWithLogicalDeletion<K> implements HashTable<K> {
  private static final int DEFAULT_NUM_CELLS = HashPrimes.primeGreaterThan(32);
  private static final double DEFAULT_MAX_LOAD_FACTOR = 0.5;
  private K[] keys;
  private boolean[] deleted;  // Array de marcas de borrado
  private int size;           // Número de elementos NO borrados
  private final double maxLoadFactor;

  @SuppressWarnings("unchecked")
  public LPHashTableWithLogicalDeletion(int numCells,
                                        double maxLoadFactor) {
    if (numCells <= 0) {
      throw new IllegalArgumentException("initial number of cells must be greater than 0");
    }
    this.keys = (K[]) new Object[numCells]; // Inicialmente todas a null
    this.deleted = new boolean[numCells];   // Inicialmente todas a false
    this.size = 0;
    this.maxLoadFactor = maxLoadFactor;
  }





  // IMPLEMENTAR ESTOS DOS MÉTODOS DENTRO DE LOS COMENTARIOS

  //-----------------------------------------------------------------------------
  //-- Nombre: [Tu nombre y apellido]
  //-- DNI: [Tu DNI completo]
  //-----------------------------------------------------------------------------

  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 3
  private int searchIndex(K key) {
      int n = -1;
      if(key != null){

          int current = Math.abs(key.hashCode()) % keys.length;
          int primBorrado = -1;
          boolean stop = false;

          while(!stop){
              if(keys[current] != null){
                  if(keys[current].equals(key)){
                      n = current;
                      stop = true;
                  }

              }else{
                  if(deleted[current]){
                      if(primBorrado == -1){
                          primBorrado = current;
                      }
                  }else{
                      if(primBorrado != -1){
                          n = primBorrado;
                      }else{
                          n = current;
                      }
                      stop = true;

                  }
              }

              if(!stop){
                  current = (current + 1) % keys.length;
              }



          }




      }


      return n;

  }
  //-- FIN EJERCICIO 3
  //-----------------------------------------------------------------------------



  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 4
  /**
   * Removes all keys from the hash table, making it empty.
   */
  @Override
  public void clear() {
    for(int i = 0; i < keys.length; i++){
        keys[i] = null;
        deleted[i] = false;
    }
      this.size = 0;
  }
  //-- FIN EJERCICIO 4
  //-----------------------------------------------------------------------------







































  /*






   NO TOCAR NADA A PARTIR DE AQUí






   NO TOCAR NADA A PARTIR DE AQUí








   */


  /**
   * Creates an empty linear probing hash table.
   * <p> Time complexity: O(1)
   */
  public LPHashTableWithLogicalDeletion() {
    this(DEFAULT_NUM_CELLS, DEFAULT_MAX_LOAD_FACTOR);
  }

  /**
   * Creates an empty linear probing hash table.
   *
   * @return a new LinearProbingHashTable with given capacity.
   * <p> Time complexity: O(1)
   */
  public static <K> LPHashTableWithLogicalDeletion<K> empty() {
    return new LPHashTableWithLogicalDeletion<>();
  }

  /**
   * Creates an empty linear probing hash table for accommodating size elements so that no rehashing is initially done.
   *
   * @param size number of elements to accommodate.
   *
   * @return a new LinearProbingHashTable with given capacity.
   *
   * @throws IllegalArgumentException if initial capacity (size) is less than 1.
   * <p> Time complexity: O(1)
   */
  public static <K> LPHashTableWithLogicalDeletion<K> withCapacity(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("initial capacity must be greater than 0");
    }
    return new LPHashTableWithLogicalDeletion<>(HashPrimes.primeGreaterThan((int) (size / DEFAULT_MAX_LOAD_FACTOR)),
            DEFAULT_MAX_LOAD_FACTOR);
  }

  // hash function for keys
  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % keys.length;
  }

  // current load factor of hash table
  private double loadFactor() {
    return (double) size / (double) keys.length;
  }

  // circular index increment
  private int advance(int index) {
    return (index + 1) % keys.length;
  }

  /**
   * Checks if the hash table is empty.
   *
   * @return {@code true} if the hash table contains no keys, {@code false} otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size==0;
  }

  /**
   * Retrieves the total number of keys in the hash table.
   *
   * @return The number of keys in the hash table.
   */
  @Override
  public int size() {
    return size;
  }

  @SuppressWarnings("unchecked")
  private void rehashing() {
    // compute new table size
    int newCapacity = HashPrimes.primeDoubleThan(keys.length); //Obtenemos el nuevo tamaño

    K[] oldKeys = keys;

    // allocate new table
    keys = (K[]) new Object[newCapacity];
    deleted = new boolean[newCapacity]; //arranca con false por defecto https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.12.5

    for (K keyToInsert : oldKeys) {
      if (keyToInsert != null) {
        keys[searchIndex(keyToInsert)] = keyToInsert;
      }
    }
  }

  /**
   * Inserts a new key into the hash table. If a key that equals the new key is already present in the hash table, the
   * old key is replaced with the new key. Duplicate keys are not supported.
   *
   * @param key The key to be inserted.
   */
  @Override
  public void insert(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    if(loadFactor()>maxLoadFactor) rehashing();

    int i = searchIndex(key);
    if (keys[i]==null) size++;

    keys[i]=key;
    deleted[i]=false;
  }

  /**
   * Searches for a key in the hash table and returns it if found. If the key is not found, returns {@code null}.
   *
   * @param key The key to search for.
   * @return The key from the hash table if found, {@code null} otherwise.
   */
  @Override
  public K search(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    return keys[searchIndex(key)];
  }

  /**
   * Checks if a key is present in the hash table.
   *
   * @param key The key to check for.
   * @return {@code true} if the key is present in the hash table, {@code false} otherwise.
   */
  @Override
  public boolean contains(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    return key.equals(keys[searchIndex(key)]);
  }

  /**
   * Removes a key from the hash table. If the key is not present, the hash table remains unchanged.
   *
   * @param key The key to be removed.
   */
  @Override
  public void delete(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    int index = searchIndex(key);
    K foundKey = keys[index];
    if (foundKey != null){
      deleted[index] = true;
      keys[index] = null;
      size--;
    }
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<K> iterator() {
    return new LinearProbingHashTableIterator();
  }

  // An iterator on keys stored in hash table
  private final class LinearProbingHashTableIterator implements Iterator<K> {
    int yielded; // number of elements already yielded by this iterator
    int previousIndex; // index of last element yielded by this iterator

    public LinearProbingHashTableIterator() {
      yielded = 0;
      previousIndex = -1; // so that after first increment it becomes 0
    }

    // advance nextIndex to index of next to be yielded element
    public void moveForward() {
      do {
        previousIndex = advance(previousIndex);
      } while(keys[previousIndex] == null);
    }

    public boolean hasNext() {
      return yielded< size;
    }

    public K next() {
      if (!hasNext()){
        throw new NoSuchElementException("there are no more elements on the hash table");
      }
      moveForward();
      yielded++;
      return keys[previousIndex];
    }
  }
}
