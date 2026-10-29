package org.uma.ed.datastructures.bag;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class SortedLinkedBagTest {

    // ============================================
    // TESTS PARA occurrences()
    // ============================================

    @Nested
    @DisplayName("Tests del método occurrences()")
    class SortedLinkedBagOcurrencesTest {
        @Test
        @DisplayName("occurrences() en bag vacío debe retornar 0")
        void testOccurrences_EmptyBag() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            assertEquals(0, bag.occurrences(1));
            assertEquals(0, bag.occurrences(100));
        }

        @Test
        @DisplayName("occurrences() con elemento no presente debe retornar 0")
        void testOccurrences_ElementNotPresent() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 5, 10, 10, 10, 15);

            assertEquals(0, bag.occurrences(7));
            assertEquals(0, bag.occurrences(20));
            assertEquals(0, bag.occurrences(1));
        }

        @Test
        @DisplayName("occurrences() con elemento en primer nodo")
        void testOccurrences_FirstNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 5, 5, 10, 10);

            assertEquals(3, bag.occurrences(5));
        }

        @Test
        @DisplayName("occurrences() con elemento en último nodo")
        void testOccurrences_LastNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 10, 10, 15, 15, 15, 15);

            assertEquals(4, bag.occurrences(15));
        }

        @Test
        @DisplayName("occurrences() con elemento en nodo intermedio")
        void testOccurrences_MiddleNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 10, 10, 10, 10, 10, 10, 10, 15, 15);

            assertEquals(7, bag.occurrences(10));
        }

        @Test
        @DisplayName("occurrences() con una sola ocurrencia")
        void testOccurrences_SingleOccurrence() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 10);

            assertEquals(1, bag.occurrences(5));
            assertEquals(1, bag.occurrences(10));
        }

        @Test
        @DisplayName("occurrences() con múltiples ocurrencias")
        void testOccurrences_MultipleOccurrences() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            // Insertar 10 veces el 5
            for (int i = 0; i < 10; i++) {
                bag.insert(5);
            }

            // Insertar 25 veces el 10
            for (int i = 0; i < 25; i++) {
                bag.insert(10);
            }

            assertEquals(10, bag.occurrences(5));
            assertEquals(25, bag.occurrences(10));
        }

        @Test
        @DisplayName("occurrences() buscando elemento menor que el mínimo")
        void testOccurrences_ElementSmallerThanMinimum() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 10, 10, 20, 20, 20);

            assertEquals(0, bag.occurrences(5));
            assertEquals(0, bag.occurrences(1));
        }

        @Test
        @DisplayName("occurrences() buscando elemento mayor que el máximo")
        void testOccurrences_ElementLargerThanMaximum() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 10, 10, 20, 20, 20);

            assertEquals(0, bag.occurrences(25));
            assertEquals(0, bag.occurrences(100));
        }

        @Test
        @DisplayName("occurrences() con elemento entre dos nodos")
        void testOccurrences_ElementBetweenNodes() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 10, 10, 30, 30, 30);

            assertEquals(0, bag.occurrences(15));
            assertEquals(0, bag.occurrences(20));
            assertEquals(0, bag.occurrences(25));
        }

        @Test
        @DisplayName("occurrences() con un solo nodo")
        void testOccurrences_SingleNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 42, 42, 42, 42, 42);

            assertEquals(5, bag.occurrences(42));
            assertEquals(0, bag.occurrences(40));
            assertEquals(0, bag.occurrences(50));
        }

        @Test
        @DisplayName("occurrences() con Strings")
        void testOccurrences_WithStrings() {
            SortedLinkedBag<String> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    "apple", "apple", "banana", "banana", "banana", "cherry"
            );

            assertEquals(2, bag.occurrences("apple"));
            assertEquals(3, bag.occurrences("banana"));
            assertEquals(1, bag.occurrences("cherry"));
            assertEquals(0, bag.occurrences("dog"));
            assertEquals(0, bag.occurrences("aaa"));
            assertEquals(0, bag.occurrences("zzz"));
        }

        @Test
        @DisplayName("occurrences() con comparador reverso")
        void testOccurrences_ReverseComparator() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.reverseOrder(),
                    20, 20, 10, 10, 10, 5
            );

            assertEquals(2, bag.occurrences(20));
            assertEquals(3, bag.occurrences(10));
            assertEquals(1, bag.occurrences(5));
            assertEquals(0, bag.occurrences(15));
        }

        @Test
        @DisplayName("occurrences() con muchos nodos")
        void testOccurrences_ManyNodes() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            // Crear nodos con diferentes cantidades: 10(1), 20(2), 30(3), 40(4), 50(5)
            bag.insert(10);

            bag.insert(20);
            bag.insert(20);

            bag.insert(30);
            bag.insert(30);
            bag.insert(30);

            bag.insert(40);
            bag.insert(40);
            bag.insert(40);
            bag.insert(40);

            bag.insert(50);
            bag.insert(50);
            bag.insert(50);
            bag.insert(50);
            bag.insert(50);

            assertEquals(1, bag.occurrences(10));
            assertEquals(2, bag.occurrences(20));
            assertEquals(3, bag.occurrences(30));
            assertEquals(4, bag.occurrences(40));
            assertEquals(5, bag.occurrences(50));
            assertEquals(0, bag.occurrences(35));
        }

        @Test
        @DisplayName("occurrences() después de múltiples inserciones")
        void testOccurrences_AfterMultipleInserts() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            assertEquals(0, bag.occurrences(5));

            bag.insert(5);
            assertEquals(1, bag.occurrences(5));

            bag.insert(5);
            assertEquals(2, bag.occurrences(5));

            bag.insert(5);
            assertEquals(3, bag.occurrences(5));

            bag.insert(10);
            assertEquals(3, bag.occurrences(5));
            assertEquals(1, bag.occurrences(10));
        }
    }
    // ============================================
    // TESTS PARA isBag()
    // ============================================

    @Nested
    @DisplayName("Tests del método isBag()")
    class SortedLinkedBagIsBagTest {
        @Test
        @DisplayName("isBag() con bag vacío es válido")
        void testIsBag_EmptyBag() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() con un solo elemento válido")
        void testIsBag_SingleElementValid() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 42);

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() con un solo elemento múltiples veces")
        void testIsBag_SingleElementMultipleTimes() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 42, 42, 42, 42, 42);

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() con múltiples elementos en orden correcto")
        void testIsBag_MultipleElementsValidOrder() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15, 20, 20, 20, 20
            );

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() con todos elementos diferentes")
        void testIsBag_AllDifferentElements() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10
            );

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() después de inserciones aleatorias")
        void testIsBag_AfterRandomInserts() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            bag.insert(50);
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(20);
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(80);
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(10);
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(50);
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(20);
            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() válido con muchos elementos")
        void testIsBag_ManyElementsValid() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            for (int i = 1; i <= 20; i++) {
                bag.insert(i * 5);
            }

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() con Strings en orden correcto")
        void testIsBag_StringsValidOrder() {
            SortedLinkedBag<String> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    "apple", "apple", "banana", "banana", "banana", "cherry", "dog", "dog", "dog", "dog"
            );

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() con comparador reverso válido")
        void testIsBag_ReverseComparatorValid() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.reverseOrder(),
                    20, 20, 15, 15, 15, 10, 5, 5, 5, 5
            );

            assertTrue(SortedLinkedBag.isBag(bag));
        }

        // ============================================
        // TESTS PARA isBag() con modifyNodeAt() - CASOS INVÁLIDOS
        // ============================================

        @Test
        @DisplayName("isBag() inválido - primer nodo con element null")
        void testIsBag_FirstNodeNullElement() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5);

            bag.modifyNodeAt(0, null, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - primer nodo con occurrences 0")
        void testIsBag_FirstNodeZeroOccurrences() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5);

            bag.modifyNodeAt(0, 5, 0);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - primer nodo con occurrences negativas")
        void testIsBag_FirstNodeNegativeOccurrences() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5);

            bag.modifyNodeAt(0, 5, -3);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - elementos duplicados en nodos diferentes")
        void testIsBag_DuplicateElements() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Hacer que el tercer nodo (15) tenga el mismo elemento que el segundo (10)
            bag.modifyNodeAt(2, 10, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - elementos iguales consecutivos")
        void testIsBag_ConsecutiveEqualElements() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Hacer que el segundo nodo tenga el mismo elemento que el primero
            bag.modifyNodeAt(1, 5, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - orden incorrecto (segundo menor que primero)")
        void testIsBag_WrongOrderSecondSmaller() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    10, 10, 20, 20, 20
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Hacer que el segundo nodo sea menor que el primero
            bag.modifyNodeAt(1, 5, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - orden incorrecto en medio")
        void testIsBag_WrongOrderInMiddle() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 20, 20, 20, 30
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Hacer que el tercer nodo (30) sea menor que el segundo (20)
            bag.modifyNodeAt(2, 15, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - occurrences 0 en nodo intermedio")
        void testIsBag_ZeroOccurrencesMiddleNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Poner occurrences a 0 en el segundo nodo
            bag.modifyNodeAt(1, 10, 0);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - occurrences negativas en nodo intermedio")
        void testIsBag_NegativeOccurrencesMiddleNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Poner occurrences negativas en el segundo nodo
            bag.modifyNodeAt(1, 10, -5);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - element null en nodo intermedio")
        void testIsBag_NullElementMiddleNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Poner element a null en el segundo nodo
            bag.modifyNodeAt(1, null, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - element null en último nodo")
        void testIsBag_NullElementLastNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Poner element a null en el último nodo
            bag.modifyNodeAt(2, null, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - occurrences 0 en último nodo")
        void testIsBag_ZeroOccurrencesLastNode() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Poner occurrences a 0 en el último nodo
            bag.modifyNodeAt(2, 15, 0);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - orden completamente invertido")
        void testIsBag_CompletelyReversedOrder() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15, 20, 20, 20, 20
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Invertir orden: primer nodo más grande
            bag.modifyNodeAt(0, 20, -1);
            bag.modifyNodeAt(3, 5, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - todos los elementos iguales en nodos diferentes")
        void testIsBag_AllElementsSame() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 10, 15
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Hacer que todos sean iguales
            bag.modifyNodeAt(1, 5, -1);
            bag.modifyNodeAt(2, 5, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - Strings en orden incorrecto")
        void testIsBag_StringsWrongOrder() {
            SortedLinkedBag<String> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    "apple", "apple", "cherry", "dog"
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Romper el orden: cherry viene después de dog
            bag.modifyNodeAt(2, "banana", -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("isBag() inválido - comparador reverso con orden incorrecto")
        void testIsBag_ReverseComparatorInvalid() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.reverseOrder(),
                    20, 20, 15, 10
            );

            assertTrue(SortedLinkedBag.isBag(bag));

            // Romper el orden: 15 debería ser > 10 en reverseOrder
            bag.modifyNodeAt(1, 5, -1);

            assertFalse(SortedLinkedBag.isBag(bag));
        }
    }

    // ============================================
    // TESTS DE INTEGRACIÓN
    // ============================================

    @Nested
    @DisplayName("Tests ambos métodos ")
    class SortedLinkedBagIntegrationTest {
        @Test
        @DisplayName("Integración: insert + occurrences + isBag")
        void testIntegration_InsertOccurrencesIsBag() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(50);
            assertEquals(1, bag.occurrences(50));
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(30);
            assertEquals(1, bag.occurrences(30));
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(50);
            assertEquals(2, bag.occurrences(50));
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(10);
            assertEquals(1, bag.occurrences(10));
            assertTrue(SortedLinkedBag.isBag(bag));

            bag.insert(30);
            bag.insert(30);
            assertEquals(3, bag.occurrences(30));
            assertTrue(SortedLinkedBag.isBag(bag));

            assertEquals(0, bag.occurrences(100));
            assertEquals(0, bag.occurrences(5));
        }

        @Test
        @DisplayName("Integración: escenario complejo con Strings")
        void testIntegration_ComplexStringScenario() {
            SortedLinkedBag<String> bag = new SortedLinkedBag<>(Comparator.<String>naturalOrder());

            bag.insert("dog");
            bag.insert("cat");
            bag.insert("bird");
            bag.insert("dog");
            bag.insert("cat");
            bag.insert("elephant");

            assertEquals(6, bag.size());
            assertEquals(2, bag.occurrences("dog"));
            assertEquals(2, bag.occurrences("cat"));
            assertEquals(1, bag.occurrences("bird"));
            assertEquals(1, bag.occurrences("elephant"));
            assertTrue(SortedLinkedBag.isBag(bag));

            // Corromper el bag
            bag.modifyNodeAt(1, "zebra", -1);
            assertFalse(SortedLinkedBag.isBag(bag));
        }

        @Test
        @DisplayName("Integración: modifyNodeAt cambia occurrences")
        void testIntegration_ModifyNodeAtChangesOccurrences() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(
                    Comparator.naturalOrder(),
                    5, 5, 10, 10, 10, 15
            );

            assertEquals(2, bag.occurrences(5));
            assertEquals(3, bag.occurrences(10));

            // Modificar occurrences del nodo 10
            bag.modifyNodeAt(1, 10, 10);

            assertEquals(10, bag.occurrences(10));
            assertTrue(SortedLinkedBag.isBag(bag));
        }

        // ============================================
        // TESTS PARA modifyNodeAt() - Excepciones
        // ============================================

        @Test
        @DisplayName("modifyNodeAt() - IndexOutOfBoundsException con índice negativo")
        void testModifyNodeAt_NegativeIndex() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 5);

            assertThrows(IndexOutOfBoundsException.class, () -> {
                bag.modifyNodeAt(-1, 10, -1);
            });
        }

        @Test
        @DisplayName("modifyNodeAt() - IndexOutOfBoundsException con índice muy grande")
        void testModifyNodeAt_IndexTooLarge() {
            SortedLinkedBag<Integer> bag = SortedLinkedBag.of(Comparator.naturalOrder(), 5, 5, 10, 10, 10);

            assertThrows(IndexOutOfBoundsException.class, () -> {
                bag.modifyNodeAt(5, 20, -1);
            });
        }

        @Test
        @DisplayName("modifyNodeAt() - IndexOutOfBoundsException en bag vacío")
        void testModifyNodeAt_EmptyBag() {
            SortedLinkedBag<Integer> bag = new SortedLinkedBag<>(Comparator.<Integer>naturalOrder());

            assertThrows(IndexOutOfBoundsException.class, () -> {
                bag.modifyNodeAt(0, 10, -1);
            });
        }
    }
}