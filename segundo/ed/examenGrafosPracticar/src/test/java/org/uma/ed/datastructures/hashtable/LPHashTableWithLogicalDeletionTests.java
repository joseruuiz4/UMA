package org.uma.ed.datastructures.hashtable;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Conjunto completo de pruebas unitarias para LinearProbingHashTableWithLogicalDeletion
 *
 * @author Tests generados para verificar la implementación de LinearProbingHashTableWithLogicalDeletion
 */
@DisplayName("LinearProbingHashTableWithLogicalDeletion Tests")
class LPHashTableWithLogicalDeletionTests {

  @Nested
  @DisplayName("Pruebas de Constructores y Métodos Factory")
  class ConstructoresYFactoryTests {

    @Test
    @DisplayName("Constructor por defecto crea tabla vacía")
    void testConstructorPorDefecto() {
      LPHashTableWithLogicalDeletion<String> tabla = new LPHashTableWithLogicalDeletion<>();

      assertNotNull(tabla);
      assertTrue(tabla.isEmpty());
      assertEquals(0, tabla.size());
    }

    @Test
    @DisplayName("Constructor con parámetros válidos crea tabla correctamente")
    void testConstructorConParametros() {
      LPHashTableWithLogicalDeletion<Integer> tabla = new LPHashTableWithLogicalDeletion<>(17, 0.75);

      assertNotNull(tabla);
      assertTrue(tabla.isEmpty());
      assertEquals(0, tabla.size());
    }

    @Test
    @DisplayName("Constructor lanza excepción con numCells inválido")
    void testConstructorConNumCellsInvalido() {
      assertThrows(IllegalArgumentException.class, () -> new LPHashTableWithLogicalDeletion<String>(0, 0.5));

      assertThrows(IllegalArgumentException.class, () -> new LPHashTableWithLogicalDeletion<String>(-5, 0.5));
    }

    @Test
    @DisplayName("empty() crea tabla vacía")
    void testEmpty() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      assertNotNull(tabla);
      assertTrue(tabla.isEmpty());
      assertEquals(0, tabla.size());
    }

    @Test
    @DisplayName("withCapacity() crea tabla con capacidad especificada")
    void testWithCapacity() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.withCapacity(100);

      assertNotNull(tabla);
      assertTrue(tabla.isEmpty());

      // Insertar 100 elementos no debería causar rehashing
      IntStream.range(0, 100).forEach(tabla::insert);
      assertEquals(100, tabla.size());
    }

    @Test
    @DisplayName("withCapacity() lanza excepción con capacidad inválida")
    void testWithCapacityInvalido() {
      assertThrows(IllegalArgumentException.class, () -> LPHashTableWithLogicalDeletion.withCapacity(0));

      assertThrows(IllegalArgumentException.class, () -> LPHashTableWithLogicalDeletion.withCapacity(-10));
    }

  }

  @Nested
  @DisplayName("Pruebas de isEmpty() y size()")
  class IsEmptyYSizeTests {

    @Test
    @DisplayName("isEmpty() retorna true para tabla vacía")
    void testIsEmptyEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      assertTrue(tabla.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() retorna false para tabla no vacía")
    void testIsEmptyEnTablaNoVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert("elemento");
      assertFalse(tabla.isEmpty());
    }

    @Test
    @DisplayName("size() retorna 0 para tabla vacía")
    void testSizeEnTablaVacia() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      assertEquals(0, tabla.size());
    }

    @Test
    @DisplayName("size() se incrementa correctamente con inserciones")
    void testSizeConInserciones() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();

      IntStream.range(1, 4).forEach(i -> {
        tabla.insert(i);
        assertEquals(i, tabla.size());
      });
    }

    @Test
    @DisplayName("size() no cambia al insertar duplicados")
    void testSizeConDuplicados() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      tabla.insert("hola");
      assertEquals(1, tabla.size());

      tabla.insert("hola"); // duplicado
      assertEquals(1, tabla.size());
    }

    @Test
    @DisplayName("size() se decrementa con delete()")
    void testSizeConDelete() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      Arrays.asList(1, 2, 3).forEach(tabla::insert);
      assertEquals(3, tabla.size());

      tabla.delete(2);
      assertEquals(2, tabla.size());

      tabla.delete(1);
      assertEquals(1, tabla.size());
    }

    @Test
    @DisplayName("size() es 0 después de clear()")
    void testSizeDespuesDeClear() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert("a");
      tabla.insert("b");
      tabla.insert("c");

      tabla.clear();
      assertEquals(0, tabla.size());
      assertTrue(tabla.isEmpty());
    }
  }

  @Nested
  @DisplayName("Pruebas de insert()")
  class InsertTests {

    @Test
    @DisplayName("insert() añade elemento a tabla vacía")
    void testInsertEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      tabla.insert("primero");

      assertEquals(1, tabla.size());
      assertTrue(tabla.contains("primero"));
    }

    @Test
    @DisplayName("insert() añade múltiples elementos diferentes")
    void testInsertMultiplesElementos() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();

      IntStream.range(0, 10).forEach(tabla::insert);

      assertEquals(10, tabla.size());
      IntStream.range(0, 10).forEach(i -> assertTrue(tabla.contains(i)));
    }

    @Test
    @DisplayName("insert() reemplaza elemento duplicado sin aumentar size")
    void testInsertDuplicado() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      tabla.insert("clave");
      assertEquals(1, tabla.size());

      tabla.insert("clave"); // duplicado
      assertEquals(1, tabla.size());
      assertTrue(tabla.contains("clave"));
    }


    @Test
    @DisplayName("insert() maneja colisiones correctamente")
    void testInsertConColisiones() {
      LPHashTableWithLogicalDeletion<ColisionableKey> tabla = LPHashTableWithLogicalDeletion.empty();

      // Crear claves que generan colisiones
      ColisionableKey key1 = new ColisionableKey(1, 0);
      ColisionableKey key2 = new ColisionableKey(2, 0);
      ColisionableKey key3 = new ColisionableKey(3, 0);

      tabla.insert(key1);
      tabla.insert(key2);
      tabla.insert(key3);

      assertEquals(3, tabla.size());
      assertTrue(tabla.contains(key1));
      assertTrue(tabla.contains(key2));
      assertTrue(tabla.contains(key3));
    }
  }

  @Nested
  @DisplayName("Pruebas de search()")
  class SearchTests {

    @Test
    @DisplayName("search() retorna null en tabla vacía")
    void testSearchEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      assertNull(tabla.search("noExiste"));
    }

    @Test
    @DisplayName("search() encuentra elemento existente")
    void testSearchElementoExistente() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert("encontrame");

      String resultado = tabla.search("encontrame");
      assertEquals("encontrame", resultado);
    }

    @Test
    @DisplayName("search() retorna null para elemento no existente")
    void testSearchElementoNoExistente() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      Arrays.asList(1, 2, 3).forEach(tabla::insert);

      assertNull(tabla.search(4));
      assertNull(tabla.search(100));
    }

    @Test
    @DisplayName("search() encuentra elementos después de colisiones")
    void testSearchConColisiones() {
      LPHashTableWithLogicalDeletion<ColisionableKey> tabla = LPHashTableWithLogicalDeletion.empty();

      ColisionableKey key1 = new ColisionableKey(1, 0);
      ColisionableKey key2 = new ColisionableKey(2, 0);
      ColisionableKey key3 = new ColisionableKey(3, 0);

      tabla.insert(key1);
      tabla.insert(key2);
      tabla.insert(key3);

      assertEquals(key1, tabla.search(key1));
      assertEquals(key2, tabla.search(key2));
      assertEquals(key3, tabla.search(key3));
    }

    @Test
    @DisplayName("search() encuentra elementos después de rehashing")
    void testSearchDespuesDeRehashing() {
      LPHashTableWithLogicalDeletion<Integer> tabla = new LPHashTableWithLogicalDeletion<>(7, 0.5);

      // Insertar suficientes elementos para forzar rehashing
      IntStream.range(0, 20).forEach(tabla::insert);

      // Verificar que todos se pueden encontrar
      IntStream.range(0, 20).forEach(i -> assertEquals(i, tabla.search(i)));
    }
  }

  @Nested
  @DisplayName("Pruebas de contains()")
  class ContainsTests {

    @Test
    @DisplayName("contains() retorna false en tabla vacía")
    void testContainsEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      assertFalse(tabla.contains("cualquiera"));
    }

    @Test
    @DisplayName("contains() retorna true para elemento existente")
    void testContainsElementoExistente() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert("estoy");

      assertTrue(tabla.contains("estoy"));
    }

    @Test
    @DisplayName("contains() retorna false para elemento no existente")
    void testContainsElementoNoExistente() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      Arrays.asList(1, 2).forEach(tabla::insert);

      assertFalse(tabla.contains(3));
      assertFalse(tabla.contains(100));
    }

    @Test
    @DisplayName("contains() funciona correctamente con múltiples elementos")
    void testContainsMultiplesElementos() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      List<String> elementos = Arrays.asList("a", "b", "c", "d", "e");

      elementos.forEach(tabla::insert);

      elementos.forEach(elem -> assertTrue(tabla.contains(elem)));

      assertFalse(tabla.contains("f"));
      assertFalse(tabla.contains("z"));
    }
  }

  @Nested
  @DisplayName("Pruebas de delete()")
  class DeleteTests {

    @Test
    @DisplayName("delete() en tabla vacía no causa error")
    void testDeleteEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      assertDoesNotThrow(() -> tabla.delete("noExiste"));
      assertEquals(0, tabla.size());
    }

    @Test
    @DisplayName("delete() elimina elemento existente")
    void testDeleteElementoExistente() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert("eliminar");

      assertEquals(1, tabla.size());
      assertTrue(tabla.contains("eliminar"));

      tabla.delete("eliminar");

      assertEquals(0, tabla.size());
      assertFalse(tabla.contains("eliminar"));
    }

    @Test
    @DisplayName("delete() de elemento no existente no cambia tabla")
    void testDeleteElementoNoExistente() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert(1);
      tabla.insert(2);

      tabla.delete(3); // no existe

      assertEquals(2, tabla.size());
      assertTrue(tabla.contains(1));
      assertTrue(tabla.contains(2));
    }

    @Test
    @DisplayName("delete() múltiples elementos")
    void testDeleteMultiplesElementos() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();

      IntStream.range(0, 10).forEach(tabla::insert);

      assertEquals(10, tabla.size());

      List<Integer> aEliminar = Arrays.asList(5, 3, 7);
      aEliminar.forEach(tabla::delete);

      assertEquals(7, tabla.size());
      aEliminar.forEach(num -> assertFalse(tabla.contains(num)));

      Arrays.asList(0, 1, 2, 4, 6, 8, 9)
              .forEach(num -> assertTrue(tabla.contains(num)));
    }

    @Test
    @DisplayName("delete() con relocación de cluster funciona correctamente")
    void testDeleteConRelocacionDeCluster() {
      LPHashTableWithLogicalDeletion<ColisionableKey> tabla = new LPHashTableWithLogicalDeletion<>(7, 0.9);

      // Crear un cluster de elementos con colisiones
      ColisionableKey key1 = new ColisionableKey(1, 0);
      ColisionableKey key2 = new ColisionableKey(2, 0);
      ColisionableKey key3 = new ColisionableKey(3, 0);

      tabla.insert(key1);
      tabla.insert(key2);
      tabla.insert(key3);

      // Eliminar elemento del medio del cluster
      tabla.delete(key2);

      assertEquals(2, tabla.size());
      assertTrue(tabla.contains(key1));
      assertFalse(tabla.contains(key2));
      assertTrue(tabla.contains(key3));
    }

    @Test
    @DisplayName("delete() y reinserción funciona correctamente")
    void testDeleteYReinsercion() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      tabla.insert("elemento");
      tabla.delete("elemento");
      tabla.insert("elemento");

      assertEquals(1, tabla.size());
      assertTrue(tabla.contains("elemento"));
    }
  }

  @Nested
  @DisplayName("Pruebas de clear()")
  class ClearTests {

    @Test
    @DisplayName("clear() en tabla vacía no causa problemas")
    void testClearEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      assertDoesNotThrow(tabla::clear);
      assertTrue(tabla.isEmpty());
      assertEquals(0, tabla.size());
    }

    @Test
    @DisplayName("clear() elimina todos los elementos")
    void testClearEliminaTodos() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();

      IntStream.range(0, 50).forEach(tabla::insert);

      assertEquals(50, tabla.size());

      tabla.clear();

      assertEquals(0, tabla.size());
      assertTrue(tabla.isEmpty());

      IntStream.range(0, 50).forEach(i -> assertFalse(tabla.contains(i)));
    }

    @Test
    @DisplayName("clear() permite reinsertar elementos")
    void testClearYReinsercion() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      Arrays.asList("a", "b").forEach(tabla::insert);
      tabla.clear();

      assertTrue(tabla.isEmpty());

      Arrays.asList("c", "d").forEach(tabla::insert);

      assertEquals(2, tabla.size());
      assertTrue(tabla.contains("c"));
      assertTrue(tabla.contains("d"));
      assertFalse(tabla.contains("a"));
      assertFalse(tabla.contains("b"));
    }
  }

  @Nested
  @DisplayName("Pruebas de Iterator")
  class IteratorTests {

    @Test
    @DisplayName("iterator() en tabla vacía no tiene elementos")
    void testIteratorEnTablaVacia() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      Iterator<String> it = tabla.iterator();

      assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("iterator() recorre todos los elementos")
    void testIteratorRecorreTodos() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      List<Integer> insertados = Arrays.asList(1, 2, 3, 4, 5);

      insertados.forEach(tabla::insert);

      List<Integer> recorridos = new ArrayList<>();
      tabla.forEach(recorridos::add);

      assertEquals(insertados.size(), recorridos.size());
      assertTrue(recorridos.containsAll(insertados));
    }

    @Test
    @DisplayName("iterator() lanza NoSuchElementException cuando no hay más elementos")
    void testIteratorNoSuchElement() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();
      tabla.insert("unico");

      Iterator<String> it = tabla.iterator();
      assertTrue(it.hasNext());
      assertEquals("unico", it.next());
      assertFalse(it.hasNext());

      assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("múltiples iteradores pueden coexistir")
    void testMultiplesIteradores() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      Arrays.asList(1, 2, 3).forEach(tabla::insert);

      Iterator<Integer> it1 = tabla.iterator();
      Iterator<Integer> it2 = tabla.iterator();

      assertTrue(it1.hasNext());
      assertTrue(it2.hasNext());

      it1.next();
      assertTrue(it2.hasNext()); // it2 no se ve afectado
    }

    @Test
    @DisplayName("iterator() funciona con tabla grande")
    void testIteratorTablaGrande() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      int numElementos = 100;

      for (int i = 0; i < numElementos; i++) {
        tabla.insert(i);
      }

      int contador = 0;
      for (Integer num : tabla) {
        assertNotNull(num);
        contador++;
      }

      assertEquals(numElementos, contador);
    }
  }

  @Nested
  @DisplayName("Pruebas de Casos Especiales y Edge Cases")
  class CasosEspecialesTests {

    @Test
    @DisplayName("tabla maneja correctamente load factor alto")
    void testLoadFactorAlto() {
      LPHashTableWithLogicalDeletion<Integer> tabla = new LPHashTableWithLogicalDeletion<>(7, 0.9);

      // Insertar muchos elementos
      IntStream.range(0, 100).forEach(tabla::insert);

      assertEquals(100, tabla.size());

      // Verificar que todos se pueden buscar
      IntStream.range(0, 100).forEach(i -> assertTrue(tabla.contains(i)));
    }

    @Test
    @DisplayName("tabla maneja elementos con mismo hashCode")
    void testElementosMismoHashCode() {
      LPHashTableWithLogicalDeletion<ColisionableKey> tabla = LPHashTableWithLogicalDeletion.empty();

      // Todas estas claves tienen el mismo hashCode
      IntStream.range(0, 10)
              .mapToObj(i -> new ColisionableKey(i, 42))
              .forEach(tabla::insert);

      assertEquals(10, tabla.size());
    }

    @Test
    @DisplayName("operaciones después de múltiples rehashings")
    void testMultiplesRehashings() {
      LPHashTableWithLogicalDeletion<Integer> tabla = new LPHashTableWithLogicalDeletion<>(5, 0.5);

      // Forzar múltiples rehashings
      IntStream.range(0, 200).forEach(tabla::insert);

      assertEquals(200, tabla.size());

      // Eliminar algunos
      IntStream.range(0, 50).forEach(tabla::delete);

      assertEquals(150, tabla.size());

      // Verificar búsquedas
      IntStream.range(0, 50).forEach(i -> assertFalse(tabla.contains(i)));
      IntStream.range(50, 200).forEach(i -> assertTrue(tabla.contains(i)));
    }

    @Test
    @DisplayName("tabla vacía después de insertar y eliminar todos")
    void testVaciarTablaCompletamente() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      List<String> elementos = Arrays.asList("a", "b", "c", "d", "e");
      elementos.forEach(tabla::insert);

      assertEquals(5, tabla.size());

      elementos.forEach(tabla::delete);

      assertEquals(0, tabla.size());
      assertTrue(tabla.isEmpty());
    }

    @Test
    @DisplayName("toString() no lanza excepción")
    void testToString() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();

      assertDoesNotThrow(() -> {
        String str = tabla.toString();
        assertNotNull(str);
      });

      tabla.insert(1);
      tabla.insert(2);

      assertDoesNotThrow(() -> {
        String str = tabla.toString();
        assertNotNull(str);
      });
    }
  }

  @Nested
  @DisplayName("Pruebas de Integración")
  class IntegracionTests {

    @Test
    @DisplayName("Integración: secuencia completa de operaciones")
    void testSecuenciaCompletaOperaciones() {
      LPHashTableWithLogicalDeletion<String> tabla = LPHashTableWithLogicalDeletion.empty();

      // Insertar
      Arrays.asList("manzana", "banana", "cereza").forEach(tabla::insert);
      assertEquals(3, tabla.size());

      // Buscar
      assertEquals("manzana", tabla.search("manzana"));
      assertTrue(tabla.contains("banana"));

      // Eliminar
      tabla.delete("banana");
      assertEquals(2, tabla.size());
      assertFalse(tabla.contains("banana"));

      // Insertar más
      Arrays.asList("durazno", "fresa").forEach(tabla::insert);
      assertEquals(4, tabla.size());

      // Iterar
      long contador = 0;
      for (String fruta : tabla) {
        assertNotNull(fruta);
        contador++;
      }
      assertEquals(4, contador);

      // Clear
      tabla.clear();
      assertTrue(tabla.isEmpty());
    }

    @Test
    @DisplayName("Integración: stress test con muchas operaciones")
    void testStressTestOperaciones() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.empty();
      int numOperaciones = 1000;

      // Insertar
      IntStream.range(0, numOperaciones).forEach(tabla::insert);
      assertEquals(numOperaciones, tabla.size());

      // Eliminar mitad
      IntStream.range(0, numOperaciones / 2).forEach(tabla::delete);
      assertEquals(numOperaciones / 2, tabla.size());

      // Verificar elementos restantes
      IntStream.range(numOperaciones / 2, numOperaciones)
              .forEach(i -> assertTrue(tabla.contains(i)));

      // Reinsertar
      IntStream.range(0, numOperaciones / 2).forEach(tabla::insert);
      assertEquals(numOperaciones, tabla.size());
    }

    @Test
    @DisplayName("Integración: operaciones con colisiones masivas")
    void testColisionesMasivas() {
      LPHashTableWithLogicalDeletion<ColisionableKey> tabla = new LPHashTableWithLogicalDeletion<>(17, 0.9);

      // Crear muchas claves con colisiones
      List<ColisionableKey> claves = IntStream.range(0, 50)
              .mapToObj(i -> new ColisionableKey(i, i % 5))
              .toList();

      claves.forEach(tabla::insert);

      assertEquals(50, tabla.size());

      // Verificar todas presentes
      claves.forEach(key -> assertTrue(tabla.contains(key)));

      // Eliminar algunas
      claves.stream().limit(25).forEach(tabla::delete);

      assertEquals(25, tabla.size());

      // Verificar estado correcto
      claves.stream().limit(25)
              .forEach(key -> assertFalse(tabla.contains(key)));
      claves.stream().skip(25)
              .forEach(key -> assertTrue(tabla.contains(key)));
    }


    @Test
    @DisplayName("Integración: rendimiento con tabla grande")
    void testRendimientoTablaGrande() {
      LPHashTableWithLogicalDeletion<Integer> tabla = LPHashTableWithLogicalDeletion.withCapacity(10000);

      // Insertar 10000 elementos
      IntStream.range(0, 10000).forEach(tabla::insert);

      assertEquals(10000, tabla.size());

      // Búsquedas aleatorias
      assertTrue(tabla.contains(4567));
      assertTrue(tabla.contains(123));
      assertTrue(tabla.contains(9999));
      assertFalse(tabla.contains(10000));

      // Eliminar 1000 elementos
      IntStream.range(0, 1000).forEach(i -> tabla.delete(i * 10));

      assertEquals(9000, tabla.size());

      // Verificar eliminaciones
      IntStream.range(0, 1000).forEach(i -> assertFalse(tabla.contains(i * 10)));
    }
  }


  @Nested
  @DisplayName("Tests Exhaustivos WhiteBox de searchIndex y clear")
  class LPHashTableSearchIndexWhiteBoxTests {

    // Capacidad prima pequeña pero suficiente para probar clusters y wrap-around
    private static final int CAPACITY = 23;

    private Method searchIndexMethod;
    private Field keysField;
    private Field deletedField;
    private Field sizeField;

    @BeforeEach
    void setUpReflection() throws Exception {
      searchIndexMethod = LPHashTableWithLogicalDeletion.class.getDeclaredMethod("searchIndex", Object.class);
      searchIndexMethod.setAccessible(true);

      keysField = LPHashTableWithLogicalDeletion.class.getDeclaredField("keys");
      keysField.setAccessible(true);

      deletedField = LPHashTableWithLogicalDeletion.class.getDeclaredField("deleted");
      deletedField.setAccessible(true);

      sizeField = LPHashTableWithLogicalDeletion.class.getDeclaredField("size");
      sizeField.setAccessible(true);
    }

    // --- MÉTODOS DE UTILIDAD (HELPER METHODS) ---

    private LPHashTableWithLogicalDeletion<ColisionableKey> createTable() {
      return new LPHashTableWithLogicalDeletion<>(CAPACITY, 1.0);
    }

    // Invoca el método privado searchIndex
    private int callSearchIndex(LPHashTableWithLogicalDeletion<?> table, Object key) {
      try {
        return (int) searchIndexMethod.invoke(table, key);
      } catch (Exception e) {
        throw new RuntimeException("Error invocando searchIndex", e);
      }
    }

    // Inyecta una clave viva (ocupada)
    private void setKeyAt(LPHashTableWithLogicalDeletion<?> table, int index, ColisionableKey key) {
      try {
        Object[] keys = (Object[]) keysField.get(table);
        boolean[] deleted = (boolean[]) deletedField.get(table);
        keys[index] = key;
        deleted[index] = false;
      } catch (Exception e) { throw new RuntimeException(e); }
    }

    // Inyecta un hueco borrado (Tumba).
    // IMPORTANTE: Según especificación, deleted=true IMPLICA key=null.
    private void setDeletedAt(LPHashTableWithLogicalDeletion<?> table, int index) {
      try {
        Object[] keys = (Object[]) keysField.get(table);
        boolean[] deleted = (boolean[]) deletedField.get(table);
        keys[index] = null; // Liberamos referencia
        deleted[index] = true; // Marcamos como borrado lógico
      } catch (Exception e) { throw new RuntimeException(e); }
    }

    // Inyecta un hueco vacío real (fin de cluster)
    private void setNullAt(LPHashTableWithLogicalDeletion<?> table, int index) {
      try {
        Object[] keys = (Object[]) keysField.get(table);
        boolean[] deleted = (boolean[]) deletedField.get(table);
        keys[index] = null;
        deleted[index] = false;
      } catch (Exception e) { throw new RuntimeException(e); }
    }

    private Object[] getInternalKeys(LPHashTableWithLogicalDeletion<?> table) {
      try { return (Object[]) keysField.get(table); } catch (Exception e) { throw new RuntimeException(e); }
    }

    private boolean[] getInternalDeleted(LPHashTableWithLogicalDeletion<?> table) {
      try { return (boolean[]) deletedField.get(table); } catch (Exception e) { throw new RuntimeException(e); }
    }


    // --- TESTS: 1. ESCENARIOS SIMPLES (SIN COLISIONES, SIN BORRADOS) ---

    @Test
    @DisplayName("1. Búsqueda simple: Acierto directo sin colisiones")
    void testDirectHit() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 5;
      ColisionableKey key = new ColisionableKey(100, h);

      setKeyAt(table, h, key); // [5: Key]

      int result = callSearchIndex(table, new ColisionableKey(100, h));
      assertEquals(h, result, "Debe devolver el índice exacto del hash si no hay colisión");
    }

    @Test
    @DisplayName("2. Búsqueda simple: Fallo directo (encuentra null inmediatamente)")
    void testDirectMiss() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 10;
           setNullAt(table, h); // [10: NULL]

      int result = callSearchIndex(table, new ColisionableKey(200, h));
      assertEquals(h, result, "Debe devolver el índice del hash si está vacío (para insertar)");
    }


    // --- TESTS: 2. CLUSTERS LINEALES (SIN WRAP-AROUND, SIN BORRADOS) ---

    @Test
    @DisplayName("3. Cluster Pequeño: Encuentra elemento desplazado 1 posición")
    void testSmallClusterHit() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 5;
      ColisionableKey k1 = new ColisionableKey(1, h); // Ocupa h
      ColisionableKey k2 = new ColisionableKey(2, h); // Buscamos esta

      setKeyAt(table, h, k1);
      setKeyAt(table, h+1, k2);

      int result = callSearchIndex(table, new ColisionableKey(2, h));
      assertEquals(h+1, result);
    }

    @Test
    @DisplayName("4. Cluster Largo: Encuentra elemento al final de un bloque largo")
    void testLongClusterHit() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 10;
      // Cluster de 10 a 14. Buscamos el de 14.
      for(int i=0; i<4; i++) {
        setKeyAt(table, h+i, new ColisionableKey(i, h));
      }
      ColisionableKey target = new ColisionableKey(99, h);
      setKeyAt(table, h+4, target);

      int result = callSearchIndex(table, new ColisionableKey(99, h));
      assertEquals(h+4, result);
    }

    @Test
    @DisplayName("5. Cluster Largo: Fallo al final del cluster (Insertar al final)")
    void testLongClusterMiss() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 10;
      // Cluster 10-13 ocupado. 14 NULL.
      for(int i=0; i<4; i++) {
        setKeyAt(table, h+i, new ColisionableKey(i, h));
      }
      setNullAt(table, h+4);

      ColisionableKey newKey = new ColisionableKey(999, h); // Hash 10

      int result = callSearchIndex(table, newKey);
      assertEquals(h+4, result, "Debe devolver el primer NULL al final del cluster");
    }


    // --- TESTS: 3. WRAP AROUND (SIN BORRADOS) ---

    @Test
    @DisplayName("6. Wrap-Around Simple: Cluster cruza del final al principio")
    void testWrapAroundHit() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int startH = CAPACITY - 1; // 22
      ColisionableKey target = new ColisionableKey(500, startH);

      // [22: Ocupado] -> [0: Ocupado] -> [1: Target]
      setKeyAt(table, CAPACITY - 1, new ColisionableKey(1, startH));
      setKeyAt(table, 0, new ColisionableKey(2, startH));
      setKeyAt(table, 1, target);

      int result = callSearchIndex(table, new ColisionableKey(500, startH));
      assertEquals(1, result);
    }

    @Test
    @DisplayName("7. Wrap-Around Miss: Debe devolver hueco libre tras dar la vuelta")
    void testWrapAroundMiss() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int startH = CAPACITY - 2; // 21

      // [21: Ocupado] -> [22: Ocupado] -> [0: Ocupado] -> [1: NULL]
      setKeyAt(table, 21, new ColisionableKey(1, startH));
      setKeyAt(table, 22, new ColisionableKey(2, startH));
      setKeyAt(table, 0,  new ColisionableKey(3, startH));
      setNullAt(table, 1);

      int result = callSearchIndex(table, new ColisionableKey(600, startH));
      assertEquals(1, result);
    }


    // --- TESTS: 4. CON HUECOS BORRADOS (BUSCAR CLAVES EXISTENTES) ---

    @Test
    @DisplayName("8. Skip Deleted: Encuentra clave saltando un hueco borrado")
    void testFindExistingSkippingDeleted() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 5;
      ColisionableKey target = new ColisionableKey(777, h);

      // [5: DELETED] -> [6: TARGET]
      setDeletedAt(table, 5); // Tumba
      setKeyAt(table, 6, target);

      int result = callSearchIndex(table, new ColisionableKey(777, h));
      assertEquals(6, result, "Debe ignorar el deleted y devolver donde está la clave");
    }

    @Test
    @DisplayName("9. Skip Multiple Deleted: Salta varios huecos alternos")
    void testFindExistingSkippingMultipleDeleted() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 5;
      ColisionableKey target = new ColisionableKey(888, h);

      // [5: DEL] -> [6: Ocupado] -> [7: DEL] -> [8: TARGET]
      setDeletedAt(table, 5);
      setKeyAt(table, 6, new ColisionableKey(1, h)); // Colisión viva
      setDeletedAt(table, 7);
      setKeyAt(table, 8, target);

      int result = callSearchIndex(table, new ColisionableKey(888, h));
      assertEquals(8, result);
    }


    // --- TESTS: 5. CON HUECOS BORRADOS (RECICLAJE / INSERTAR NUEVOS) ---

    @Test
    @DisplayName("10. Recycle: Devuelve el PRIMER hueco borrado para insertar nuevo")
    void testReturnFirstDeletedIndex() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 5;

      // [5: Ocupado] -> [6: DELETED] -> [7: Ocupado] -> [8: NULL]
      setKeyAt(table, 5, new ColisionableKey(1, h));
      setDeletedAt(table, 6); // Queremos este índice
      setKeyAt(table, 7, new ColisionableKey(2, h));
      setNullAt(table, 8);

      int result = callSearchIndex(table, new ColisionableKey(999, h));
      assertEquals(6, result, "Debe devolver el índice 6 (first free index) para reutilizar memoria, no el 8");
    }

    @Test
    @DisplayName("11. Recycle Priority: Elige el deleted más cercano al hash original")
    void testReturnEarliestDeleted() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 5;

      // [5: DEL] -> [6: DEL] -> [7: NULL]
      setDeletedAt(table, 5);
      setDeletedAt(table, 6);
      setNullAt(table, 7);

      int result = callSearchIndex(table, new ColisionableKey(111, h));
      assertEquals(5, result, "Debe devolver el 5, que es el primer deleted encontrado");
    }


    // --- TESTS: 6. COMBINACIONES COMPLEJAS (WRAP + CLUSTER + DELETED) ---

    @Test
    @DisplayName("12. Complejo: Cluster grande con wrap-around y hueco intermedio para insertar")
    void testComplexWrapAroundRecycle() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      // Inicio Hash: 21 (Casi al final)
      int h = 21;

      // Estructura:
      // [21: Ocupado]
      // [22: Ocupado]
      // [0:  DELETED]  <-- Esperamos este (Wrap around funcionó y detectó hueco)
      // [1:  Ocupado]
      // [2:  NULL]

      setKeyAt(table, 21, new ColisionableKey(1, h));
      setKeyAt(table, 22, new ColisionableKey(2, h));
      setDeletedAt(table, 0); // La tumba está tras la vuelta
      setKeyAt(table, 1, new ColisionableKey(3, h));
      setNullAt(table, 2);

      int result = callSearchIndex(table, new ColisionableKey(123, h));
      assertEquals(0, result);
    }

    @Test
    @DisplayName("13. Complejo: Búsqueda exitosa con wrap-around saltando tumbas")
    void testComplexWrapAroundFindExisting() {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();
      int h = 22;
      ColisionableKey target = new ColisionableKey(777, h);

      // Estructura:
      // [22: DELETED]
      // [0:  DELETED]
      // [1:  TARGET]

      setDeletedAt(table, 22);
      setDeletedAt(table, 0);
      setKeyAt(table, 1, target);

      int result = callSearchIndex(table, new ColisionableKey(777, h));
      assertEquals(1, result, "Debe saltar tumbas a través del wrap-around y encontrar la clave");
    }

    // --- TESTS: 7. CLEAR EXHAUSTIVO ---

    @Test
    @DisplayName("14. Clear Exhaustivo: Limpia tabla con estado mixto complejo")
    void testClearThorough() throws Exception {
      LPHashTableWithLogicalDeletion<ColisionableKey> table = createTable();

      Object[] keys = (Object[]) keysField.get(table);
      boolean[] deleted = (boolean[]) deletedField.get(table);

      // Llenamos la tabla con un patrón sucio manualmente
      for(int i=0; i<CAPACITY; i++) {
        if(i % 3 == 0) setKeyAt(table, i, new ColisionableKey(i, i));
        else if(i % 3 == 1) setDeletedAt(table, i); // Key null, deleted true
        else setNullAt(table, i); // Key null, deleted false
      }

      // Forzamos un size > 0
      sizeField.set(table, 10);

      // Ejecutamos clear
      table.clear();

      // Verificamos estado
      assertEquals(0, table.size());
      assertTrue(table.isEmpty());

      Object[] newKeys = getInternalKeys(table);
      boolean[] newDeleted = getInternalDeleted(table);

      assertEquals(keys, newKeys, "Se debe reutilizar el mismo array de claves");
      assertEquals(deleted, newDeleted, "Se debe reutilizar el mismo array de booleanos");

      assertEquals(keys.length, newKeys.length, "La tabla de claves no debe modificar su tamaño");
      assertEquals(deleted.length, newDeleted.length, "La tabla de booleanos no debe modificar su tamaño");

      for(int i=0; i<CAPACITY; i++) {
        assertNull(newKeys[i], "Posición " + i + " debe ser null en keys");
        assertFalse(newDeleted[i], "Posición " + i + " debe ser false en deleted");
      }
    }

    // --- CLASE AUXILIAR COLISIONABLE ---
    record ColisionableKey(int id, int hashBase) {

      @Override
          public int hashCode() {
            // Devuelve siempre hashBase para controlar dónde empieza la búsqueda
            return hashBase;
          }

      @Override
          public String toString() {
            return "K{id=" + id + ", h=" + hashBase + "}";
          }
        }
  }

  // Clases auxiliares para pruebas

  /**
   * Clase auxiliar para probar colisiones
   */
  record ColisionableKey(int id, int hashBase) {

    @Override
      public int hashCode() {
        return hashBase; // Todas las instancias con mismo hashBase colisionan
      }

      @Override
      public String toString() {
        return "ColisionableKey{id=" + id + ", hashBase=" + hashBase + "}";
      }
    }

  /**
   * Clase auxiliar para probar actualizaciones
   */
  record MutableKey(int id, String value) {

    @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableKey that = (MutableKey) o;
        return id == that.id;
      }

      @Override
      public int hashCode() {
        return Integer.hashCode(id);
      }

      @Override
      public String toString() {
        return "MutableKey{id=" + id + ", value='" + value + "'}";
      }
    }
}