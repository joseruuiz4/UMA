/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/
package org.mps.tree;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<T> implements BinarySearchTreeStructure<T> {
    private Comparator<T> comparator;
    private T value;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public String render() {
        String render = "";

        if (value != null) {
            render += value.toString();
        }

        if (left != null || right != null) {
            render += "(";
            if (left != null) {
                render += left.render();
            }
            render += ",";
            if (right != null) {
                render += right.render();
            }
            render += ")";
        }

        return render;
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
        value = null;
        left = null;
        right = null;

    }

    @Override
    public void insert(T value) {
        if (value == null) {
            throw new BinarySearchTreeException("Valor nulo");
        }

        if (this.value == null) {
            this.value = value;
        } else if (this.comparator.compare(this.value, value) < 0) { // Si es mayor
            if (this.right == null) {
                this.right = new BinarySearchTree<>(this.comparator);
            }
            this.right.insert(value);
        } else if (this.comparator.compare(this.value, value) > 0) {
            if (this.left == null) {
                this.left = new BinarySearchTree<>(this.comparator);
            }
            this.left.insert(value);
        }
    }

    @Override
    public boolean isLeaf() {
        if (this.value == null) {
            throw new BinarySearchTreeException("Valor nulo");
        }
        return this.left == null && this.right == null;
    }

    @Override
    public boolean contains(T value) {
        boolean loEs = false;

        if (this.value == null) {
            throw new BinarySearchTreeException("Arbol nulo");
        }

        if (value == null) {
            throw new BinarySearchTreeException("Valor a buscar nulo");
        }

        if (this.comparator.compare(this.value, value) == 0) {
            loEs = true;
        } else if (this.comparator.compare(this.value, value) < 0 && this.right != null) {
            loEs = this.right.contains(value);
        } else if (this.comparator.compare(this.value, value) > 0 && this.left != null) {
            loEs = this.left.contains(value);
        }

        return loEs;
    }

    @Override
    public T minimum() {
        T minimo;

        if (this.value == null) {
            throw new BinarySearchTreeException("Arbol vacio");
        }

        if (this.left != null) {
            minimo = this.left.minimum();
        } else {
            minimo = this.value;
        }


        return minimo;
    }

    @Override
    public T maximum() {
        T maximo;

        if (this.value == null) {
            throw new BinarySearchTreeException("Arbol vacio");
        }

        if (this.right != null) {
            maximo = this.right.maximum();
        } else {
            maximo = this.value;
        }
        return maximo;
    }

    @Override
    public void removeBranch(T value) {

        if (value == null) {
            throw new BinarySearchTreeException("Valor a buscar nulo");
        }

        if (this.comparator.compare(this.value, value) == 0) {
            this.left = null;
            this.right = null;
            this.value = null;

        } else if (this.comparator.compare(this.value, value) < 0) {
            this.right.removeBranch(value);
        } else {
            this.left.removeBranch(value);
        }
    }

    @Override
    public int size() {


        if (this.value == null) {
            return 0;
        }
        int i = 1;

        if (this.left != null) {
            i += this.left.size();
        }

        if (this.right != null) {
            i += this.right.size();
        }
        return i;
    }

    @Override
    public int depth() {

        if (this.value == null) {
            return 0;
        }

        int i = 1;
        int j = 1;


        if (this.left != null) {
            i += this.left.depth();
        }

        if (this.right != null) {
            j += this.right.depth();
        }

        return Math.max(i, j);
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)

    @Override
    public void removeValue(T value) {
        if (value == null) {
            throw new BinarySearchTreeException("Valor a eliminar nulo");
        }

        if (this.value == null) {
            throw new BinarySearchTreeException("Árbol vacío");
        }
        //para encontrar donde esta el valor a eliminar
        int cmp = this.comparator.compare(value, this.value);


        if (cmp == 0) {
            // Caso 1: Nodo sin hijos solo lo borra
            if (this.left == null && this.right == null) {
                this.value = null;
            }
            // Caso 2: Nodo con un solo hijo se reemplaza por él
            else if (this.left == null) {
                this.value = this.right.value;
                this.left = this.right.left;
                this.right = this.right.right;
            } else if (this.right == null) {
                this.value = this.left.value;
                this.right = this.left.right;
                this.left = this.left.left;
            }
            // Caso 3: Nodo con dos hijos reemplaza su valor con el menor del subárbol derecho y lo elimina de su posición
            else {
                T minRightSubtree = this.right.minimum();
                this.value = minRightSubtree;
                this.right.removeValue(minRightSubtree);
            }
        }

        if (cmp < 0 && this.left != null) {

            BinarySearchTree<T> i = this.left;
            if (this.left.comparator.compare(this.left.value, value) == 0) {
                this.left = null;
            }

            i.removeValue(value);


        } else if (cmp > 0 && this.right != null) {
            BinarySearchTree<T> i = this.right;

            if (this.right.comparator.compare(this.right.value, value) == 0) {
                this.right = null;
            }

            i.removeValue(value);
        }


    }

    @Override
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        //siempre voy rellenando con la izquierda en el caso que haya
        if (this.left != null) {
            result.addAll(this.left.inOrder());
        }
        //añado el valor de la fila mas alta porque a partir de aqui seran los de su derecha
        if (this.value != null) {
            result.add(this.value);
        }
        //voy añadiendo los de la derecha como ultima opcion
        if (this.right != null) {
            result.addAll(this.right.inOrder());
        }

        return result;
    }

    @Override
    public void balance() {
        List<T> valores_ordenados = inOrder();
        this.value = null;
        this.left = null;
        this.right = null;
        //vacio el arbol para que al volver a ordenarlo pueda ir añadiendolo
        //obtengo la lista de valores ordenados y llamo a un metodo auxiliar para que vaya creando las ramas
        insertarBalanced(valores_ordenados, 0, valores_ordenados.size() - 1);
    }

    private void insertarBalanced(List<T> valores_ordenados, int inicio, int ultimo) {
        if (inicio > ultimo) return;//si el inicio es más alto que el final no puede existir

        int mid = (inicio + ultimo) / 2;            //mid sera la mitad de la lista de los valores ordenados
        insert(valores_ordenados.get(mid));         //añado el valor de enmedio y en "insert()" ya lo ordena

        insertarBalanced(valores_ordenados, inicio, mid - 1);           //lo mismo con la parte de la izquierda de la lista
        insertarBalanced(valores_ordenados, mid + 1, ultimo);           //lo mismo con la parte de la derecha de la lista
    }


}
