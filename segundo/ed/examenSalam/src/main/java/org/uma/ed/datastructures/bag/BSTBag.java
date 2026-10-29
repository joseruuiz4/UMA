package org.uma.ed.datastructures.bag ;


import org.uma.ed.datastructures.stack.ArrayStack;
import org.uma.ed.datastructures.stack.Stack;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<T> extends AbstractSortedBag<T> implements SortedBag<T> {

  private static final class Node<T> {
    T key;
    Node<T> left;
    Node<T> right;
    int occurrences;

    Node(T key) {
      this.key = key;
      this.left = null;
      this.right = null;
      this.occurrences = 1;
    }
  }

  /*
   INVARIANT:
   - Keys in left child are smaller than key in node.
   - Keys in right child are greater than key in node.
   - There are no duplicate keys in tree.
   - size is the total number of elements in the bag (sum of all occurrences).
  */

  private final Comparator<T> comparator;
  private Node<T> root;

  public BSTBag(Comparator<T> comparator) {
    this(comparator, null);
  }

  private BSTBag(Comparator<T> comparator, Node<T> root) {
    this.comparator = comparator;
    this.root = root;
  }

  //-----------------------------------------------------------------------------
  //-- NO MODIFIQUES ABSOLUTAMENTE NADA ANTERIOR A ESTE COMENTARIO
  //-----------------------------------------------------------------------------




  //-----------------------------------------------------------------------------
  //-- Nombre: [Pon aquí tu nombre y apellidos]
  //-- DNI:    [pon aquí tu DNI o pasaporte]
  //-----------------------------------------------------------------------------


  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 3
  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public int size() {
    return size(this.root);
  }

  private int size(Node<T> node){
      if(node == null) return 0;

      int i = node.occurrences;


      return i + size(node.left) + size(node.right);



  }
  //-- FIN EJERCICIO 3
  //-----------------------------------------------------------------------------


  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 4
  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public int occurrences(T element) {
    int i = 0;
    boolean encontrado = false;
    Node<T> aux = this.root;

    if(aux == null) return 0;

    while(aux != null && !encontrado){
        int cmp = comparator.compare(element, aux.key);
        if(cmp > 0){
            aux = aux.right;
        }else if(cmp < 0){
            aux = aux.left;
        }else{
            encontrado = true;
            i = aux.occurrences;
        }

    }


    return i;
  }
  //-- FIN EJERCICIO 4
  //-----------------------------------------------------------------------------


  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 5
  private class InOrderIterator implements Iterator<T> {
    // Añade los atributos necesarios
      private Node<T> current;
    
    public InOrderIterator() {
      throw new UnsupportedOperationException("¡A implementar en el examen!");
    }

    @Override
    public boolean hasNext() {
      throw new UnsupportedOperationException("¡A implementar en el examen!");
    }

    @Override
    public T next() {
        return null;
    }
  }
  //-- FIN EJERCICIO 5
  //-----------------------------------------------------------------------------








  //-----------------------------------------------------------------------------
  //-- NO MODIFIQUES ABSOLUTAMENTE NADA POSTERIOR A ESTE COMENTARIO
  //-----------------------------------------------------------------------------

  @Override
  public Iterator<T> iterator() {
    return new InOrderIterator();
  }

  @SafeVarargs
  public static <T> BSTBag<T> of(Comparator<T> comparator, T... elements) {
    BSTBag<T> BSTBag = new BSTBag<>(comparator);
    BSTBag.insert(elements);
    return BSTBag;
  }

  @SafeVarargs
  public static <T extends Comparable<? super T>> BSTBag<T> of(T... elements) {
    return BSTBag.of(Comparator.naturalOrder(), elements);
  }

  @Override
  public Comparator<T> comparator() {
    return comparator;
  }

  @Override
  public boolean isEmpty() {
    return root == null;
  }

  @Override
  public void delete(T element) {
    throw new UnsupportedOperationException("No se debe usar");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("No se debe usar");
  }

  @Override
  public T minimum() {
    throw new UnsupportedOperationException("No se debe usar");
  }

  @Override
  public T maximum() {
    throw new UnsupportedOperationException("No se debe usar");
  }

  @Override
  public void insert (T k) {
    var n = root;
    int c;
    var s = 0;
    for (;;) {
      switch (s) {
        case 0:
          if (k == null) throw new IllegalArgumentException("Clave no puede ser nula");
          s = (root == null) ? 1 : 2;
         continue;
        case 1:
          root = new Node<>(k);
          s = 99;
          continue;
        case 2:
          s = 3;
          continue;
        case 3:
          s = (c = comparator.compare(k, n.key)) == 0 ? 6 : (c < 0 ? 4 : 5);
          continue;
        case 4:
          if (n.left == null) {
            n.left = new Node<>(k);
            s = 99;
          } else {
            n = n.left;
            s = 3;
          }
          continue;
        case 5:
          if (n.right == null) {
            n.right = new Node<>(k);
            s = 99;
          } else {
            n = n.right;
            s = 3;
          }
          continue;
        case 6:
          n.occurrences++;
          s = 99;
          continue;
        case 99:
      }
      break;
    }
  }
}
