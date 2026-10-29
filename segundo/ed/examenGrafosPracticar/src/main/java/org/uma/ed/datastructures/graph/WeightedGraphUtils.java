package org.uma.ed.datastructures.graph;

import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.Dictionary.Entry;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.priorityqueue.JDKPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.PriorityQueue;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.stack.JDKStack;
import org.uma.ed.datastructures.stack.Stack;
import org.uma.ed.datastructures.tuple.Tuple2;

import java.util.Comparator;

public class WeightedGraphUtils {

    // IMPLEMENTAR ESTOS DOS MÉTODOS DENTRO DE LOS COMENTARIOS

    //-----------------------------------------------------------------------------
    //-- Nombre: [Tu nombre y apellido]
    //-- DNI: [Tu DNI completo]
    //-----------------------------------------------------------------------------

    //-----------------------------------------------------------------------------
    //-- COMIENZO EJERCICIO 1
    public static <V,W> boolean esConexo(WeightedGraph<V,W> graph){
        if(graph == null || graph.isEmpty()) return true;

        Set<V> verticesVisitados = JDKHashSet.empty();

        Stack<V> pila = new JDKStack<>();

        V primero = graph.vertices().iterator().next();
        verticesVisitados.insert(primero);

        pila.push(primero);

        while(!pila.isEmpty()){
            V act = pila.top();
            pila.pop();

            for(WeightedGraph.Successor<V, W> sucesor : graph.successors(act)){
                if(!verticesVisitados.contains(sucesor.vertex())){
                    pila.push(sucesor.vertex());
                    verticesVisitados.insert(sucesor.vertex());
                }
            }


        }

       return verticesVisitados.size() == graph.numberOfVertices();
    }
    //-- FIN EJERCICIO 1
    //-----------------------------------------------------------------------------



    //-----------------------------------------------------------------------------
    //-- COMIENZO EJERCICIO 2
    public static <V> Set<V> verticesADistancia(WeightedGraph<V,Integer> graph, V init, Integer distance){
        if(graph == null || graph.isEmpty() || !graph.vertices().contains(init)) return JDKHashSet.empty();
        Set<V> verticesVisitados = JDKHashSet.empty();

        Comparator<Tuple2<V, Integer>> orden = Comparator.comparingInt(Tuple2::_2);

        PriorityQueue<Tuple2<V, Integer>> pq = new JDKPriorityQueue<>(orden);

        pq.enqueue(Tuple2.of(init, 0));

        while(!pq.isEmpty()){
            Tuple2 act = pq.first();
            pq.dequeue();
            V v = (V) act._1();
            Integer peso = (Integer) act._2();

            if(!verticesVisitados.contains(v)){
                verticesVisitados.insert(v);

                for(WeightedGraph.Successor<V, Integer> suc : graph.successors(v)){
                    if(suc.weight() + peso <= distance) pq.enqueue(Tuple2.of(suc.vertex(), suc.weight() + peso));

                }

            }

        }

        return verticesVisitados;
    }
    //-- FIN EJERCICIO 2
    //-----------------------------------------------------------------------------
}

