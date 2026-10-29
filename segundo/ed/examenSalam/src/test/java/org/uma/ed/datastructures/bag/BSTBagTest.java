package org.uma.ed.datastructures.bag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@DisplayName("BSTBag Tests")
public class BSTBagTest {

    @Nested
    @DisplayName("Tests para size()")
    class SizeTests {

        @Test
        @DisplayName("Árbol vacío")
        void testSizeEmpty() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            assertEquals(0, bag.size());
        }

        @Test
        @DisplayName("Un solo elemento sin duplicados")
        void testSizeOneElement() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            assertEquals(1, bag.size());
        }

        @Test
        @DisplayName("Un elemento con múltiples ocurrencias")
        void testSizeOneElementMultipleOccurrences() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(5);
            bag.insert(5);
            assertEquals(3, bag.size());
        }

        @Test
        @DisplayName("Varios elementos sin duplicados")
        void testSizeMultipleElementsNoDuplicates() {
            BSTBag<Integer> bag = BSTBag.of(5, 3, 7, 1, 9);
            assertEquals(5, bag.size());
        }

        @Test
        @DisplayName("Varios elementos con duplicados")
        void testSizeMultipleElementsWithDuplicates() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(3);
            bag.insert(5);
            bag.insert(3);
            // 5(x2), 3(x3), 7(x1) = 6 elementos totales
            assertEquals(6, bag.size());
        }

        @Test
        @DisplayName("Árbol desbalanceado hacia la izquierda")
        void testSizeUnbalancedLeft() {
            BSTBag<Integer> bag = BSTBag.of(10, 8, 6, 4, 2);
            assertEquals(5, bag.size());
        }

        @Test
        @DisplayName("Árbol desbalanceado hacia la derecha")
        void testSizeUnbalancedRight() {
            BSTBag<Integer> bag = BSTBag.of(2, 4, 6, 8, 10);
            assertEquals(5, bag.size());
        }

        @Test
        @DisplayName("Árbol con muchos elementos y duplicados")
        void testSizeLargeTree() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            for (int i = 1; i <= 10; i++) {
                for (int j = 0; j < i; j++) {
                    bag.insert(i);
                }
            }
            // 1(x1) + 2(x2) + 3(x3) + ... + 10(x10) = 55
            assertEquals(55, bag.size());
        }
    }

    @Nested
    @DisplayName("Tests para occurrences()")
    class OccurrencesTests {

        @Test
        @DisplayName("Elemento no existe en árbol vacío")
        void testOccurrencesEmpty() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            assertEquals(0, bag.occurrences(5));
        }

        @Test
        @DisplayName("Elemento existe sin duplicados")
        void testOccurrencesElementExistsOnce() {
            BSTBag<Integer> bag = BSTBag.of(5, 3, 7);
            assertEquals(1, bag.occurrences(5));
            assertEquals(1, bag.occurrences(3));
            assertEquals(1, bag.occurrences(7));
        }

        @Test
        @DisplayName("Elemento no existe en árbol no vacío")
        void testOccurrencesElementNotExists() {
            BSTBag<Integer> bag = BSTBag.of(5, 3, 7);
            assertEquals(0, bag.occurrences(10));
            assertEquals(0, bag.occurrences(1));
            assertEquals(0, bag.occurrences(6));
        }

        @Test
        @DisplayName("Elemento con múltiples ocurrencias")
        void testOccurrencesMultiple() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(5);
            bag.insert(5);
            bag.insert(5);
            assertEquals(4, bag.occurrences(5));
        }

        @Test
        @DisplayName("Varios elementos con diferentes ocurrencias")
        void testOccurrencesMixed() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(3);
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);

            assertEquals(2, bag.occurrences(5));
            assertEquals(3, bag.occurrences(3));
            assertEquals(2, bag.occurrences(7));
            assertEquals(0, bag.occurrences(10));
        }

        @Test
        @DisplayName("Búsqueda en la raíz")
        void testOccurrencesRoot() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(10);
            bag.insert(5);
            bag.insert(15);
            bag.insert(10);
            bag.insert(10);

            assertEquals(3, bag.occurrences(10));
        }

        @Test
        @DisplayName("Búsqueda en hoja izquierda")
        void testOccurrencesLeftLeaf() {
            BSTBag<Integer> bag = BSTBag.of(10, 5, 15, 3);
            bag.insert(3);
            assertEquals(2, bag.occurrences(3));
        }

        @Test
        @DisplayName("Búsqueda en hoja derecha")
        void testOccurrencesRightLeaf() {
            BSTBag<Integer> bag = BSTBag.of(10, 5, 15, 20);
            bag.insert(20);
            bag.insert(20);
            assertEquals(3, bag.occurrences(20));
        }

        @Test
        @DisplayName("Árbol desbalanceado izquierda")
        void testOccurrencesUnbalancedLeft() {
            BSTBag<Integer> bag = BSTBag.of(10, 8, 6, 4, 2);
            bag.insert(2);
            assertEquals(2, bag.occurrences(2));
            assertEquals(1, bag.occurrences(10));
        }

        @Test
        @DisplayName("Árbol desbalanceado derecha")
        void testOccurrencesUnbalancedRight() {
            BSTBag<Integer> bag = BSTBag.of(2, 4, 6, 8, 10);
            bag.insert(10);
            bag.insert(10);
            assertEquals(3, bag.occurrences(10));
            assertEquals(1, bag.occurrences(2));
        }

        @Test
        @DisplayName("Con comparador personalizado")
        void testOccurrencesCustomComparator() {
            // Comparador inverso (descendente)
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>reverseOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(5);
            bag.insert(5);

            assertEquals(3, bag.occurrences(5));
            assertEquals(1, bag.occurrences(3));
            assertEquals(1, bag.occurrences(7));
        }
    }

    @Nested
    @DisplayName("Tests para InOrderIterator")
    class InOrderIteratorTests {

        @Test
        @DisplayName("Árbol vacío")
        void testIteratorEmpty() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            Iterator<Integer> it = bag.iterator();
            assertFalse(it.hasNext());
        }

        @Test
        @DisplayName("hasNext no consume elementos")
        void testIteratorHasNextIdempotent() {
            BSTBag<Integer> bag = BSTBag.of(5);
            Iterator<Integer> it = bag.iterator();
            assertTrue(it.hasNext());
            assertTrue(it.hasNext());
            assertTrue(it.hasNext());
            assertEquals(5, it.next());
        }

        @Test
        @DisplayName("Un solo elemento")
        void testIteratorOneElement() {
            BSTBag<Integer> bag = BSTBag.of(5);
            Iterator<Integer> it = bag.iterator();

            assertTrue(it.hasNext());
            assertEquals(5, it.next());
            assertFalse(it.hasNext());
        }

        @Test
        @DisplayName("Un elemento con múltiples ocurrencias")
        void testIteratorOneElementMultipleOccurrences() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(5);
            bag.insert(5);

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            assertEquals(Arrays.asList(5, 5, 5), result);
        }

        @Test
        @DisplayName("Orden in-order correcto sin duplicados")
        void testIteratorInOrderNoDuplicates() {
            BSTBag<Integer> bag = BSTBag.of(5, 3, 7, 1, 9);

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            assertEquals(Arrays.asList(1, 3, 5, 7, 9), result);
        }

        @Test
        @DisplayName("Orden in-order con duplicados")
        void testIteratorInOrderWithDuplicates() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(3);
            bag.insert(5);
            bag.insert(3);

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            // Orden esperado: 3, 3, 3, 5, 5, 7
            assertEquals(Arrays.asList(3, 3, 3, 5, 5, 7), result);
        }

        @Test
        @DisplayName("Árbol desbalanceado izquierda")
        void testIteratorUnbalancedLeft() {
            BSTBag<Integer> bag = BSTBag.of(10, 8, 6, 4, 2);

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
        }

        @Test
        @DisplayName("Árbol desbalanceado derecha")
        void testIteratorUnbalancedRight() {
            BSTBag<Integer> bag = BSTBag.of(2, 4, 6, 8, 10);

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
        }

        @Test
        @DisplayName("Árbol balanceado complejo con duplicados")
        void testIteratorComplexWithDuplicates() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(1);
            bag.insert(4);
            bag.insert(6);
            bag.insert(9);
            bag.insert(3); // duplicado
            bag.insert(7); // duplicado
            bag.insert(7); // duplicado

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            assertEquals(Arrays.asList(1, 3, 3, 4, 5, 6, 7, 7, 7, 9), result);
        }

        @Test
        @DisplayName("NoSuchElementException cuando no hay más elementos")
        void testIteratorNoSuchElementException() {
            BSTBag<Integer> bag = BSTBag.of(5);
            Iterator<Integer> it = bag.iterator();

            it.next();
            assertThrows(NoSuchElementException.class, () -> it.next());
        }

        @Test
        @DisplayName("NoSuchElementException en árbol vacío")
        void testIteratorNoSuchElementExceptionEmpty() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            Iterator<Integer> it = bag.iterator();

            assertThrows(NoSuchElementException.class, () -> it.next());
        }

        @Test
        @DisplayName("Múltiples iteradores independientes")
        void testIteratorMultipleIndependent() {
            BSTBag<Integer> bag = BSTBag.of(1, 2, 3);

            Iterator<Integer> it1 = bag.iterator();
            Iterator<Integer> it2 = bag.iterator();

            assertEquals(1, it1.next());
            assertEquals(1, it2.next());
            assertEquals(2, it1.next());
            assertEquals(2, it2.next());
        }

        @Test
        @DisplayName("Con comparador personalizado (reverso)")
        void testIteratorCustomComparator() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>reverseOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(3);

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            // Con orden reverso, el in-order debería dar: 7, 5, 3, 3
            assertEquals(Arrays.asList(7, 5, 3, 3), result);
        }

        @Test
        @DisplayName("Todas las ocurrencias aparecen consecutivas")
        void testIteratorOccurrencesConsecutive() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            for (int i = 1; i <= 5; i++) {
                for (int j = 0; j < i; j++) {
                    bag.insert(i);
                }
            }

            List<Integer> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            // 1(x1), 2(x2), 3(x3), 4(x4), 5(x5)
            assertEquals(Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5), result);
        }

        @Test
        @DisplayName("Con strings usando orden natural")
        void testIteratorStrings() {
            BSTBag<String> bag = BSTBag.of("manzana", "banana", "cereza", "banana");

            List<String> result = new ArrayList<>();
            bag.iterator().forEachRemaining(result::add);

            assertEquals(Arrays.asList("banana", "banana", "cereza", "manzana"), result);
        }
    }

    @Nested
    @DisplayName("Tests de integración")
    class IntegrationTests {

        @Test
        @DisplayName("size() y occurrences() coherentes")
        void testIntegrationSizeAndOccurrences() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(3);
            bag.insert(5);
            bag.insert(3);

            int totalSize = bag.size();
            int sum = bag.occurrences(3) + bag.occurrences(5) + bag.occurrences(7);

            assertEquals(totalSize, sum);
        }

        @Test
        @DisplayName("Iterator recorre size() elementos")
        void testIntegrationIteratorAndSize() {
            BSTBag<Integer> bag = BSTBag.of(5, 3, 7, 3, 5, 3);

            int count = 0;
            Iterator<Integer> it = bag.iterator();
            while (it.hasNext()) {
                it.next();
                count++;
            }

            assertEquals(bag.size(), count);
        }

        @Test
        @DisplayName("Iterator respeta occurrences()")
        void testIntegrationIteratorAndOccurrences() {
            BSTBag<Integer> bag = new BSTBag<>(Comparator.<Integer>naturalOrder());
            bag.insert(5);
            bag.insert(3);
            bag.insert(7);
            bag.insert(3);
            bag.insert(5);
            bag.insert(3);

            Map<Integer, Integer> counts = new HashMap<>();
            bag.iterator().forEachRemaining(x -> counts.merge(x, 1, Integer::sum));

            assertEquals(bag.occurrences(3), counts.get(3));
            assertEquals(bag.occurrences(5), counts.get(5));
            assertEquals(bag.occurrences(7), counts.get(7));
        }
    }
}