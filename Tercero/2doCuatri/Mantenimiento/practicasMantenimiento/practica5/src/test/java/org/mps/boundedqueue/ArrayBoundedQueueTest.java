/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/

package org.mps.boundedqueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class ArrayBoundedQueueTest {

    @Nested
    @DisplayName("Constructor")
    public class constructor {

        @Test
        @DisplayName("Lanzar excepcion si la capacidad es cero o menor")
        public void ArrayBoundedQueue_capacity0oMenor_IllegalArgumentException() {
            int cap = 0;
            assertThatThrownBy(() -> new ArrayBoundedQueue<Integer>(cap)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Crear correctamente con capacidad mayor a cero")
        public void ArrayBoundedQueue_capacityMayorA0_ArrayNotNull() {
            int cap = 10;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            assertThat(arrayBoundedQueue).isNotNull();
        }
    }

    @Nested
    @DisplayName("Metodo put")
    public class put {

        @Test
        @DisplayName("Lanzar excepcion al insertar en cola llena")
        public void put_AnyadirValorCuandoEstaLleno_FullBoundedQueueException() {
            int cap = 1;
            int val1 = 10;
            int val2 = 20;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(val1);

            assertThatThrownBy(() -> arrayBoundedQueue.put(val2))
                    .isInstanceOf(FullBoundedQueueException.class)
                    .hasMessage("put: full bounded queue");
        }

        @Test
        @DisplayName("Lanzar excepcion al insertar null")
        public void put_AnyadirValorNull_IllegalArgumentException() {
            int cap = 15;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            assertThatThrownBy(() -> arrayBoundedQueue.put(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("put: element cannot be null");
        }

        @Test
        @DisplayName("Insertar valor valido correctamente")
        public void put_AnyadirValorCorrecto_Valid() {
            int cap = 15;
            int val1 = 5;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(val1);

            assertThat(arrayBoundedQueue).isNotNull().containsExactly(val1).isNotEmpty();
        }
    }

    @Nested
    @DisplayName("Metodo get")
    public class get {

        @Test
        @DisplayName("Lanzar excepcion al obtener de cola vacia")
        public void get_ArrayVacio_EmptyBoundedQueueException() {
            int cap = 15;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            assertThatThrownBy(() -> arrayBoundedQueue.get())
                    .isInstanceOf(EmptyBoundedQueueException.class)
                    .hasMessage("get: empty bounded queue");
        }

        @Test
        @DisplayName("Obtener elemento en orden correcto")
        public void get_ArrayCorrecto_Valid() {
            int cap = 15;
            int val1 = 5, val2 = 10, val3 = 20;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(val1);
            arrayBoundedQueue.put(val2);
            arrayBoundedQueue.put(val3);

            int res = arrayBoundedQueue.get();

            assertThat(res).isEqualTo(val1);
            assertThat(arrayBoundedQueue).doesNotContain(val1);
        }
    }

    @Nested
    @DisplayName("Metodo isFull")
    public class isFull {

        @Test
        @DisplayName("Devolver false si la cola no esta llena")
        public void isFull_Test_NoValid() {
            int cap = 15;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);

            assertThat(arrayBoundedQueue.isFull()).isFalse();
        }

        @Test
        @DisplayName("Devolver true si la cola esta llena")
        public void isFull_Test_Valid() {
            int cap = 2;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);

            assertThat(arrayBoundedQueue.isFull()).isTrue();
        }

        @Test
        @DisplayName("Devolver false si la cola fue llenada y luego vaciada")
        public void isFull_Test_FullANDEmptyNoValid() {
            int cap = 2;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();

            assertThat(arrayBoundedQueue.isFull()).isFalse();
        }
    }

    @Nested
    @DisplayName("Metodo isEmpty")
    public class isEmpty {

        @Test
        @DisplayName("Devolver true si la cola esta vacia")
        public void isEmpty_Test_Valid() {
            int cap = 15;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            assertThat(arrayBoundedQueue.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("Devolver false si la cola tiene elementos")
        public void isEmpty_Test_NotValid() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);
            arrayBoundedQueue.put(15);

            assertThat(arrayBoundedQueue.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("Devolver true si se vacia completamente")
        public void isEmpty_Test_FullANDEmptyValid() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);
            arrayBoundedQueue.put(15);

            arrayBoundedQueue.get();
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();

            assertThat(arrayBoundedQueue.isEmpty()).isTrue();
        }
    }

    @Nested
    @DisplayName("Metodo size")
    public class size {

        @Test
        @DisplayName("Devolver el tamanio correcto de la cola")
        public void size_Test_Valid() {
            int cap = 15;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);

            assertThat(arrayBoundedQueue.size()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("Metodo getFirst")
    public class getFirst {

        @Test
        @DisplayName("Obtener indice del primer elemento correctamente")
        public void getFirst_Test_Valid() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);

            assertThat(arrayBoundedQueue.getFirst()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Metodo getLast")
    public class getLast {

        @Test
        @DisplayName("Obtener indice del ultimo elemento correctamente")
        public void getLast_Test_Valid() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);
            arrayBoundedQueue.put(20);

            assertThat(arrayBoundedQueue.getLast()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Iterador")
    public class iterator {

        @Test
        @DisplayName("Crear iterador correctamente")
        public void Constructor_Correcto() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            Iterator<Integer> it = arrayBoundedQueue.iterator();

            assertThat(it).isNotNull();
        }

        @Test
        @DisplayName("hasNext debe devolver false con cola vacia")
        public void hasNext_ArrayVacio_false() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            Iterator<Integer> it = arrayBoundedQueue.iterator();

            assertThat(it.hasNext()).isFalse();
        }

        @Test
        @DisplayName("hasNext debe devolver true con elementos")
        public void hasNext_ArrayConElementos_true() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            Iterator<Integer> it = arrayBoundedQueue.iterator();

            assertThat(it.hasNext()).isTrue();
        }

        @Test
        @DisplayName("Lanzar excepcion si se llama next en cola vacia")
        public void Next_ArrayVacio_NoSuchElementException() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            Iterator<Integer> it = arrayBoundedQueue.iterator();

            assertThatThrownBy(() -> it.next())
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("next: bounded queue iterator exhausted");
        }

        @Test
        @DisplayName("Recorrer correctamente elementos insertados")
        public void Next_ArrayConElementosNoLleno_Correcto() {
            int cap = 3;
            ArrayBoundedQueue<Integer> arrayBoundedQueue = new ArrayBoundedQueue<>(cap);
            arrayBoundedQueue.put(5);
            arrayBoundedQueue.put(10);

            Iterator<Integer> it = arrayBoundedQueue.iterator();

            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(5);
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(10);
            assertThat(it.hasNext()).isFalse();

            assertThatThrownBy(() -> it.next())
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("next: bounded queue iterator exhausted");
        }
    }
}
