
package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.priorityqueue.JDKPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.PriorityQueue;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.tuple.Tuple2;

/**
 * Class for computing shortest paths in a weighted graph using Dijkstra's algorithm.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Dijkstra {
   /**
   * Computes costs of shortest paths from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source The source vertex.
   * @param <V> The type of the vertices in the graph.
   *
   * @return a dictionary where keys are vertices and values are the minimum cost to reach them from the source.
   */
  public static <V> Dictionary<V, Integer> dijkstra(
      WeightedGraph<V, Integer> weightedGraph, V source) {
 
    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost) implements Comparable<Extension<V>> {
      @Override
      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost) {
        return new Extension<>(source, destination, totalCost);
      }
    }

    Set<V> verticesOpt = new JDKHashSet<>();
    Set<V> vertices = new JDKHashSet<>();
    Dictionary<V, Integer> costOpt = new JDKHashDictionary<>();
    PriorityQueue<Extension<V>> pq = new JDKPriorityQueue<>(Extension :: compareTo);

    for(V v : weightedGraph.vertices()){
        if(!v.equals(source)) vertices.insert(v);
    }

    verticesOpt.insert(source);
    costOpt.insert(source, 0);

    for(WeightedGraph.Successor<V, Integer> suc : weightedGraph.successors(source)){
        pq.enqueue(Extension.of(source, suc.vertex(), suc.weight()));
    }

    while(!vertices.isEmpty() && !pq.isEmpty()){
        Extension<V> ext = pq.first();
        pq.dequeue();
        V dest = ext.destination();
        if(verticesOpt.contains(dest)){
            continue;
        }

        verticesOpt.insert(dest);
        vertices.delete(dest);
        costOpt.insert(dest, ext.totalCost());

        for(WeightedGraph.Successor<V, Integer> suc : weightedGraph.successors(dest)){
            V next = suc.vertex();

            if(!verticesOpt.contains(next)){
                pq.enqueue(Extension.of(dest, next, ext.totalCost() + suc.weight()));
            }
        }




    }



        return costOpt;
  }

  /**
   * Computes shortest paths (and their costs) from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source The source vertex.
   * @param <V> The type of the vertices in the graph.
   *
   * @return a dictionary where keys are vertices and values are pairs with the minimum cost to reach them from the
   * source and the path to reach them.
   */
  public static <V> Dictionary<V, Tuple2<Integer, List<V>>> dijkstraPaths(
      WeightedGraph<V, Integer> weightedGraph, V source) {
      
    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination plus path from source to destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost, List<V> path) implements Comparable<Extension<V>> {
      @Override
      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost, List<V> path) {
        return new Extension<>(source, destination, totalCost, path);
      }
    }

    Set<V> verticesOpt = new JDKHashSet<>();
    Set<V> vertices = new JDKHashSet<>();
    Dictionary<V, Tuple2<Integer, List<V>>> result = new JDKHashDictionary<>();
    PriorityQueue<Extension<V>> pq = new JDKPriorityQueue<>(Extension :: compareTo);

    for(V v : weightedGraph.vertices()){
        if(!v.equals(source)) vertices.insert(v);
    }

    verticesOpt.insert(source);

    List<V> initialPath = new JDKArrayList<>();
    initialPath.insert(0, source);

    verticesOpt.insert(source);
    result.insert(source, new Tuple2<>(0, initialPath));

    for(WeightedGraph.Successor<V, Integer> suc : weightedGraph.successors(source)){
        List<V> path = new JDKArrayList<>();
        path.append(source);
        path.append(suc.vertex());

        pq.enqueue(Extension.of(source, suc.vertex(), suc.weight(), path));
    }


    while(!vertices.isEmpty() && !pq.isEmpty()){
        Extension<V> ext = pq.first();
        pq.dequeue();

        V dest = ext.destination();

        if(verticesOpt.contains(dest)){
            continue;
        }

        verticesOpt.insert(dest);
        vertices.delete(dest);
        result.insert(dest, new Tuple2<>(ext.totalCost(), ext.path()));

        for(WeightedGraph.Successor<V, Integer> suc : weightedGraph.successors(dest)){
            V next = suc.vertex();
            if(!verticesOpt.contains(next)){
                List<V> newPath = new JDKArrayList<>();
                for(V v : ext.path()){
                    newPath.append(v);
                }
                newPath.append(next);

                pq.enqueue(Extension.of(dest, next, ext.totalCost() + suc.weight(), newPath));
            }
        }



    }



    return result;
  }
}
