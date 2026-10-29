package org.uma.ed.datastructures.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.uma.ed.datastructures.set.Set;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de pruebas completa y optimizada para WeightedGraphUtils.
 * Integra casos básicos, topologías complejas y trampas algorítmicas (Dijkstra vs BFS).
 */
@DisplayName("WeightedGraphUtils Tests - Suite Integrada")
class WeightedGraphUtilsTests {

    private WeightedGraph<String, Integer> graph;

    @BeforeEach
    void setUp() {
        graph = DictionaryWeightedGraph.empty();
    }

    /**
     * Helper para añadir aristas NO DIRIGIDAS (bidireccionales).
     * Simplifica la creación de grafos para pruebas de conectividad y distancia.
     */
    private void addUndirectedEdge(String u, String v, int weight) {
        if (!graph.vertices().contains(u)) graph.addVertex(u);
        if (!graph.vertices().contains(v)) graph.addVertex(v);
        graph.addEdge(u, v, weight);
        graph.addEdge(v, u, weight);
    }

    // =================================================================================
    // BLOQUE 1: PRUEBAS PARA esConexo
    // =================================================================================

    @Nested
    @DisplayName("1. Pruebas de Conectividad (esConexo)")
    class EsConexoTests {

        @Nested
        @DisplayName("Casos Triviales y Básicos")
        class TrivialCases {
            @Test
            @DisplayName("Grafo null debe retornar true")
            void esConexo_grafoNull() {
                assertTrue(WeightedGraphUtils.esConexo(null));
            }

            @Test
            @DisplayName("Grafo vacío debe retornar true")
            void esConexo_grafoVacio() {
                assertTrue(WeightedGraphUtils.esConexo(graph));
            }

            @Test
            @DisplayName("Grafo con un solo vértice es conexo")
            void esConexo_unVertice() {
                graph.addVertex("A");
                assertTrue(WeightedGraphUtils.esConexo(graph));
            }
        }

        @Nested
        @DisplayName("Topologías Simples")
        class SimpleTopologies {
            @Test
            @DisplayName("Dos vértices conectados")
            void esConexo_dosVertices() {
                addUndirectedEdge("A", "B", 1);
                assertTrue(WeightedGraphUtils.esConexo(graph));
            }

            @Test
            @DisplayName("Cadena Lineal (A-B-C-D)")
            void esConexo_grafoLineal() {
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("B", "C", 2);
                addUndirectedEdge("C", "D", 3);
                assertTrue(WeightedGraphUtils.esConexo(graph));
            }

            @Test
            @DisplayName("Grafo Estrella (Centro conectado a todos)")
            void esConexo_grafoEstrella() {
                addUndirectedEdge("Centro", "N1", 1);
                addUndirectedEdge("Centro", "N2", 1);
                addUndirectedEdge("Centro", "N3", 1);
                assertTrue(WeightedGraphUtils.esConexo(graph));
            }

            @Test
            @DisplayName("Grafo Completo (Todos con todos)")
            void esConexo_grafoCompleto() {
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("A", "C", 1);
                addUndirectedEdge("B", "C", 1);
                assertTrue(WeightedGraphUtils.esConexo(graph));
            }
        }

        @Nested
        @DisplayName("Casos No Conexos")
        class DisconnectedCases {
            @Test
            @DisplayName("Dos vértices sin aristas")
            void esConexo_dosVerticesAislados() {
                graph.addVertex("A");
                graph.addVertex("B");
                assertFalse(WeightedGraphUtils.esConexo(graph));
            }

            @Test
            @DisplayName("Dos componentes separadas (A-B y C-D)")
            void esConexo_dosComponentes() {
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("C", "D", 1);
                assertFalse(WeightedGraphUtils.esConexo(graph));
            }

            @Test
            @DisplayName("Grafo con un vértice aislado (A-B-C y D)")
            void esConexo_verticeAislado() {
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("B", "C", 1);
                graph.addVertex("D"); // Aislado
                assertFalse(WeightedGraphUtils.esConexo(graph));
            }
        }

        @Nested
        @DisplayName("Casos Complejos y Genéricos")
        class ComplexAndGenerics {
            @Test
            @DisplayName("Funciona con tipos genéricos Integer/Double")
            void esConexo_genericos() {
                WeightedGraph<Integer, Double> graphInt = DictionaryWeightedGraph.empty();
                graphInt.addVertex(1);
                graphInt.addVertex(2);
                graphInt.addEdge(1, 2, 1.5);
                graphInt.addEdge(2, 1, 1.5);
                assertTrue(WeightedGraphUtils.esConexo(graphInt));
            }

            @Test
            @DisplayName("El Puente (Bridge): Conexión crítica entre clusters")
            void testBridge() {
                // Cluster Izquierdo
                addUndirectedEdge("L1", "L2", 1);
                addUndirectedEdge("L2", "L3", 1);
                addUndirectedEdge("L3", "L1", 1);
                // Cluster Derecho
                addUndirectedEdge("R1", "R2", 1);
                addUndirectedEdge("R2", "R3", 1);
                addUndirectedEdge("R3", "R1", 1);

                assertFalse(WeightedGraphUtils.esConexo(graph), "Clusters separados deben retornar false");

                // Puente
                addUndirectedEdge("L1", "R1", 10);
                assertTrue(WeightedGraphUtils.esConexo(graph), "Clusters unidos por puente deben retornar true");
            }

            @Test
            @DisplayName("Grid 3x4 (Malla)")
            void testGridConnectivity() {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 4; c++) {
                        String node = r + "_" + c;
                        graph.addVertex(node);
                        if (r > 0) addUndirectedEdge(node, (r - 1) + "_" + c, 1);
                        if (c > 0) addUndirectedEdge(node, r + "_" + (c - 1), 1);
                    }
                }
                assertTrue(WeightedGraphUtils.esConexo(graph));

                // Simular desconexión eliminando y reinsertando aislado
                graph.deleteVertex("0_0");
                graph.addVertex("0_0");
                assertFalse(WeightedGraphUtils.esConexo(graph));
            }
        }
    }

    // =================================================================================
    // BLOQUE 2: PRUEBAS PARA verticesADistancia
    // =================================================================================

    @Nested
    @DisplayName("2. Pruebas de Distancia (verticesADistancia)")
    class VerticesADistanciaTests {

        @Nested
        @DisplayName("Validación de Entradas y Casos Borde")
        class InputValidation {
            @Test
            @DisplayName("Entradas inválidas (Null, Vacío, No existente)")
            void testInvalidInputs() {
                assertTrue(WeightedGraphUtils.verticesADistancia(null, "A", 5).isEmpty());

                WeightedGraph<String, Integer> emptyG = DictionaryWeightedGraph.empty();
                assertTrue(WeightedGraphUtils.verticesADistancia(emptyG, "A", 5).isEmpty());

                graph.addVertex("A");
                assertTrue(WeightedGraphUtils.verticesADistancia(graph, "Z", 5).isEmpty());
            }

            @Test
            @DisplayName("Peso límite 0 retorna solo el vértice inicial")
            void testWeightZero() {
                addUndirectedEdge("A", "B", 1);
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 0);
                assertEquals(1, res.size(), "Solo debe contener el origen");
                assertTrue(res.contains("A"));
            }

            @Test
            @DisplayName("Grafo con un solo vértice retorna ese vértice")
            void testSingleVertex() {
                graph.addVertex("A");
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 10);
                assertEquals(1, res.size());
                assertTrue(res.contains("A"));
            }
        }

        @Nested
        @DisplayName("Comprobación de Pesos y Límites")
        class SimpleWeights {
            @Test
            @DisplayName("Límite exacto para alcanzar vecino (Inclusión)")
            void testExactLimit() {
                addUndirectedEdge("A", "B", 5);
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 5);
                assertEquals(2, res.size());
                assertTrue(res.contains("A"));
                assertTrue(res.contains("B"));
            }

            @Test
            @DisplayName("Límite insuficiente para alcanzar vecino (Exclusión)")
            void testInsufficientLimit() {
                addUndirectedEdge("A", "B", 10);
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 9);
                assertEquals(1, res.size());
                assertTrue(res.contains("A"));
                assertFalse(res.contains("B"));
            }

            @Test
            @DisplayName("Aristas con peso cero se comportan como el mismo nodo en coste")
            void testZeroWeightEdges() {
                // A --0-- B --0-- C
                addUndirectedEdge("A", "B", 0);
                addUndirectedEdge("B", "C", 0);
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 0);
                assertEquals(3, res.size());
                assertTrue(res.contains("A"));
                assertTrue(res.contains("B"));
                assertTrue(res.contains("C"));
            }
        }

        @Nested
        @DisplayName("Topologías Básicas")
        class BasicTopologies {
            @Test
            @DisplayName("Lineal: Alcanzar subconjunto de nodos")
            void testLinearPartial() {
                // A(0)--2--B(2)--3--C(5)--4--D(9)
                addUndirectedEdge("A", "B", 2);
                addUndirectedEdge("B", "C", 3);
                addUndirectedEdge("C", "D", 4);

                // Con 5 alcanzamos A, B y C (exacto)
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 5);
                assertEquals(3, res.size());
                assertTrue(res.contains("C"));
                assertFalse(res.contains("D"));
            }

            @Test
            @DisplayName("Estrella: Navegación desde el centro")
            void testStarFromCenter() {
                // Centro A conectado a B(1), C(2), D(3)
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("A", "C", 2);
                addUndirectedEdge("A", "D", 3);

                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 2);
                assertEquals(3, res.size());
                assertTrue(res.contains("B"));
                assertTrue(res.contains("C"));
                assertFalse(res.contains("D"));
            }

            @Test
            @DisplayName("Estrella: Navegación desde un extremo")
            void testStarFromLeaf() {
                // B(1)--A--C(2)
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("A", "C", 2);
                addUndirectedEdge("A", "D", 5);

                // Desde B con 3: B->A (1) -> C (1+2=3). D queda lejos (1+5=6).
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "B", 3);
                assertEquals(3, res.size());
                assertTrue(res.contains("B"));
                assertTrue(res.contains("A"));
                assertTrue(res.contains("C"));
                assertFalse(res.contains("D"));
            }

            @Test
            @DisplayName("Múltiples caminos: Debe usar el camino más corto")
            void testMultiplePaths() {
                //     B
                //   /   \
                // A       D
                //   \   /
                //     C
                // Camino A-B-D coste 2 (1+1)
                // Camino A-C-D coste 10 (5+5)
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("B", "D", 1);
                addUndirectedEdge("A", "C", 5);
                addUndirectedEdge("C", "D", 5);

                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 2);
                assertEquals(3, res.size());
                assertTrue(res.contains("D")); // Se alcanza por B
                assertTrue(res.contains("B"));
                assertFalse(res.contains("C")); // C cuesta 5
            }

            @Test
            @DisplayName("Componentes no conexas - solo alcanza la componente del inicio")
            void verticesADistancia_componentesNoConexas() {
                // Componente 1: A --1-- B
                // Componente 2: C --1-- D
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("C", "D", 1);

                Set<String> resultado = WeightedGraphUtils.verticesADistancia(graph, "A", 100);
                assertEquals(2, resultado.size());
                assertTrue(resultado.contains("A"));
                assertTrue(resultado.contains("B"));
                assertFalse(resultado.contains("C"));
                assertFalse(resultado.contains("D"));
            }
        }

        @Nested
        @DisplayName("Corrección Algorítmica (Dijkstra vs BFS)")
        class AlgorithmicCorrectness {

            @Test
            @DisplayName("Trampa BFS: Camino largo en saltos pero barato en peso")
            void testCheapLongPath() {
                // A->Target directo cuesta 100 (1 salto).
                // A->B->C->Target cuesta 1+1+1 = 3 (3 saltos).
                addUndirectedEdge("A", "Target", 100);
                addUndirectedEdge("A", "B", 1);
                addUndirectedEdge("B", "C", 1);
                addUndirectedEdge("C", "Target", 1);

                // BFS normal vería Target a 1 salto y pararía si no pondera.
                // Dijkstra debe ver el camino largo.
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 10);
                assertEquals(4, res.size()); // A, B, C, Target
                assertTrue(res.contains("Target"));
            }

            @Test
            @DisplayName("Relajación: Camino mejor encontrado después")
            void testRelaxation() {
                // S->Mid (10), Mid->End (10) => Coste inicial a End es 20.
                // S->A (1), A->Mid (1) => Nuevo camino a Mid es 2. Total a End es 12.
                addUndirectedEdge("S", "Mid", 10);
                addUndirectedEdge("Mid", "End", 10);
                addUndirectedEdge("S", "A", 1);
                addUndirectedEdge("A", "Mid", 1);

                // Con 15, por el camino directo (20) no llega. Por A (12) sí.
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "S", 15);
                assertTrue(res.contains("End"), "Debe actualizar coste de Mid y expandir a End");
                assertTrue(res.contains("Mid"));
                assertTrue(res.contains("A"));
            }

            @Test
            @DisplayName("Ciclos de coste cero (Evitar bucles infinitos)")
            void testZeroWeightCycles() {
                addUndirectedEdge("A", "B", 0);
                addUndirectedEdge("B", "C", 0);
                addUndirectedEdge("C", "A", 0);
                addUndirectedEdge("C", "D", 5);

                assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
                    Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "A", 0);
                    assertEquals(3, res.size());
                    assertTrue(res.contains("A"));
                    assertTrue(res.contains("B"));
                    assertTrue(res.contains("C"));
                    assertFalse(res.contains("D"));
                });
            }
        }

        @Nested
        @DisplayName("Escala y Topologías Grandes")
        class LargeScaleTests {

            @Test
            @DisplayName("Cadena Larga (15 nodos): Límite exacto")
            void testLongChain() {
                for (int i = 0; i < 14; i++) {
                    addUndirectedEdge("N" + i, "N" + (i + 1), 1);
                }
                // Presupuesto 7 desde N0 -> Llega hasta N7 (inclusive)
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "N0", 7);
                assertEquals(8, res.size()); // N0..N7
                for(int i = 0; i < 8; i++) {
                    assertTrue(res.contains("N"+i));
                }
                for(int i = 8; i < 14; i++) {
                    assertFalse(res.contains("N"+i));
                }
            }

            @Test
            @DisplayName("Árbol Binario: Profundidad dependiente del peso")
            void testBinaryTree() {
                // Ramas izquierdas peso 1, derechas peso 5
                // N1 -> N2(1), N3(5)
                // N2 -> N4(1), N5(5)
                for (int i = 1; i <= 7; i++) {
                    addUndirectedEdge("N" + i, "N" + (2 * i), 1);
                    addUndirectedEdge("N" + i, "N" + (2 * i + 1), 5);
                }
                // Desde raíz (N1), coste 3.
                // N1(0) -> N2(1) -> N4(2) -> N8(3).
                // N3 cuesta 5 (fuera). N5 cuesta 1+5=6 (fuera).
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "N1", 3);
                assertEquals(4, res.size());
                assertTrue(res.contains("N1"));
                assertTrue(res.contains("N2"));
                assertTrue(res.contains("N4"));
                assertTrue(res.contains("N8"));
                assertFalse(res.contains("N3"));
                assertFalse(res.contains("N5"));
                assertFalse(res.contains("N7"));
            }

            @Test
            @DisplayName("La Telaraña (Spider Web): Atajos internos")
            void testSpiderWeb() {
                String[] inner = {"I1", "I2", "I3", "I4"};
                String[] outer = {"O1", "O2", "O3", "O4"};

                // Radios (2) y conexiones Outer (10 - Muro)
                for (int i = 0; i < 4; i++) {
                    addUndirectedEdge("C", inner[i], 2);
                    addUndirectedEdge(inner[i], outer[i], 2);
                    if (i < 3) addUndirectedEdge(outer[i], outer[i+1], 10);
                }
                // Anillo Interno (1 - Atajo)
                addUndirectedEdge("I1", "I2", 1);
                addUndirectedEdge("I2", "I3", 1);
                addUndirectedEdge("I3", "I4", 1);

                // De O1 a O3.
                // Camino externo: O1-O2(10)-O3(10) -> Coste 20.
                // Camino interno: O1(0)-I1(2)-I2(1)-I3(1)-O3(2) -> Coste 6.
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "O1", 7);
                assertTrue(res.contains("O3"), "Debe encontrar el atajo por el anillo interno");
            }

            @Test
            @DisplayName("Snakes and Ladders (Atajos largos)")
            void testSnakesAndLadders() {
                // Camino lento: S0-S1-S2...S10 (peso 10 c/u)
                for(int i=0; i<10; i++) addUndirectedEdge("S"+i, "S"+(i+1), 10);
                // Atajos (escaleras/serpientes)
                addUndirectedEdge("S0", "S5", 5); // S0->S5 cuesta 5
                addUndirectedEdge("S5", "S8", 2); // S5->S8 cuesta 2

                // Total S0 -> S8 es 7.
                // Por camino normal sería 80.
                Set<String> res = WeightedGraphUtils.verticesADistancia(graph, "S0", 9);
                assertTrue(res.contains("S8"));
                assertTrue(res.contains("S5"));
                for(int i=1; i<10; i++) {
                    if (i!=8 && i!=5) {
                        assertFalse(res.contains("S"+i)); // Coste 10
                    }
                }
            }
        }
    }
}