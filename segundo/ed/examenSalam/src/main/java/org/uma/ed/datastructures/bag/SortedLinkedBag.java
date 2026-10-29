package org.uma.ed.datastructures.bag ;

import java.util.Comparator;
import java.util.Iterator;

public class SortedLinkedBag<T> extends AbstractSortedBag<T> implements SortedBag<T> {

  private static final class Node<E> {
    E element;
    int occurrences;
    Node<E> next;

    Node(E element, int occurrences, Node<E> next) {
      this.element = element;
      this.occurrences = occurrences;
      this.next = next;
    }
  }

  private final Comparator<T> comparator; // Comparator defining order of elements in sorted linked structure.
  private Node<T> first;                  // Reference to first node in sorted linked structure or null if bag is empty.
  private Node<T> last;                   // Reference to last node in sorted linked structure or null if bag is empty.
  private int size;                       // Number of elements stored in this bag

  public SortedLinkedBag(Comparator<T> comparator) {
    this.comparator = comparator;
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  @SafeVarargs
  public static <T> SortedLinkedBag<T> of(Comparator<T> comparator, T... elements) {
    SortedLinkedBag<T> sortedLinkedBag = new SortedLinkedBag<>(comparator);
    sortedLinkedBag.insert(elements);
    return sortedLinkedBag;
  }

  //-----------------------------------------------------------------------------
  //-- NO MODIFIQUES ABSOLUTAMENTE NADA ANTERIOR A ESTE COMENTARIO
  //-----------------------------------------------------------------------------









  //-----------------------------------------------------------------------------
  //-- Nombre: [Pon aquí tu nombre y apellidos]
  //-- DNI:    [pon aquí tu DNI o pasaporte]
  //-----------------------------------------------------------------------------


  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 1
  @Override
  public int occurrences(T element) {
    Node<T> aux = this.first;

    while(aux != null){
        if(comparator.compare(aux.element, element) == 0){
            return aux.occurrences;
        }
        aux = aux.next;
    }

    return 0;

  }
  //-- FIN EJERCICIO 1
  //-----------------------------------------------------------------------------

  //-----------------------------------------------------------------------------
  //-- COMIENZO EJERCICIO 2
  public static <T> boolean isBag(SortedLinkedBag<T> bag) {
      Node<T> aux = bag.first;
      Node<T> prev = null;
      int oc = 0;

      while(aux != null){
          if(aux.element == null || aux.occurrences < 1) return false;

          if(prev != null && (bag.comparator.compare(aux.element, prev.element) < 0)){
              return false;
          }
          oc += aux.occurrences;
          prev = aux;
          aux = aux.next;

      }

      if(prev != null && bag.comparator.compare(prev.element, bag.last.element) != 0){
          return false;
      }

      if(oc != bag.size){
          return false;
      }

      return true;
  }
  //-- FIN EJERCICIO 2
  //-----------------------------------------------------------------------------









  //-----------------------------------------------------------------------------
  //-- NO MODIFIQUES ABSOLUTAMENTE NADA POSTERIOR A ESTE COMENTARIO
  //-----------------------------------------------------------------------------

  @Override
  public Comparator<T> comparator() {
    return comparator;
  }

  @Override
  public boolean isEmpty() {
    return first==null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    first = last = null;
    size = 0;
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
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("No se debe usar");
  }

  @Override
  public void delete(T element) {
    throw new UnsupportedOperationException("No se debe usar");
  }

  @Override
  public void insert(T a) {
    Node<T> p = null;
    var c = first;
    var d = 0;
    var s = 17;
    for (;;) {
      switch (s) {
        case 17:
          if (c == null) { s = 42; continue; }
          d = comparator.compare(a, c.element);
          if (d > 0) { s = 25; continue; }
          s = 42; continue;
        case 25:
          p = c;
          c = c.next;
          s = 17; continue;
        case 42:
          if (c != null && d == 0) { s = 88; continue; }
          s = 51; continue;
        case 88:
          c.occurrences++;
          s = 99; continue;
        case 51:
          if (p == null) { s = 64; continue; }
          s = 70; continue;
        case 64:
          var x = new Node<>(a, 1, first);
          first = x;
          if (last == null) { last = x; }
          s = 99; continue;
        case 70:
          var y = new Node<>(a, 1, c);
          if (c == null) { last = y; }
          p.next = y;
          s = 99; continue;
        case 99:
          size++;
      }
      break;
    }
  }

  void modifyNodeAt(int i, T ne, int no) {
    var c = first;
    var e = 0;
    var s = 10;
    for (;;) {
      switch (s) {
        case 10:
          if (i < 0) { s = 11; continue; }
          s = 20; continue;
        case 11:
          throw new IndexOutOfBoundsException("Index cannot be negative: " + i);
        case 20:
          if (c != null && e < i) { s = 25; continue; }
          s = 30; continue;
        case 25:
          c = c.next;
          e++;
          s = 20; continue;
        case 30:
          if (c == null) { s = 31; continue; }
          s = 40; continue;
        case 31:
          throw new IndexOutOfBoundsException("Index: " + i + ", Number of nodes: " + e);
        case 40: // Modificar elemento
          c.element = ne;
          s = 50; continue;
        case 50:
          if (no >= 0) { s = 60; continue; }
          s = 70; continue;
        case 60:
          var d = no - c.occurrences;
          c.occurrences = no;
          size += d;
          s = 99; continue;
        case 70:
          size -= c.occurrences;
          c.occurrences = no;
          s = 99; continue;
        case 99:
      }
      break;
    }
  }
}
