///////////////////////////////////////////////////////////////////////////////
// Student's name: [Your Name]
// Identity number:  [Your DNI or Passport Number]
///////////////////////////////////////////////////////////////////////////////

package exam;


import datastructures.tree.binary.BT;
import datastructures.tree.general.GT;
import datastructures.bag.SB;
import datastructures.list.List;

import java.util.Comparator;

public class Exam {
    // Bags

    //// BEGIN (A)
    public static <E> SB.Node<E> insert(E element, SB.Node<E> first, Comparator<E> comparator) {
        SB.Node<E> aux = first;
        SB.Node<E> prev = null;

        while(aux != null && comparator.compare(element, aux.element) > 0){
            prev = aux;
            aux = aux.next;
        }

        if(aux != null && comparator.compare(element, aux.element) == 0){
            aux.occurrences++;
        }else if(prev == null){
            SB.Node<E> newNode = SB.Node.of(element, 1, aux);
            first = newNode;
        }else{
            SB.Node<E> newNode = SB.Node.of(element, 1, aux);
            prev.next = newNode;
        }
        return first;
    }
    //// END (A)


    // Binary Trees

    //// BEGIN (B)
    public static <E> int height(BT.Node<E> root) {
        if(root == null) return 0;

        int izq = height(root.left);
        int der = height(root.right);

        return izq > der ? izq + 1 : der + 1;
    }
    //// END (B)


    //// BEGIN (C)
    public static <E> boolean isBalanced(BT.Node<E> root) {
        if(root == null) return true;

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    //// END (C)


    //// BEGIN (D)
    public static <E> BT.Node<E> zigZagInsert(E element, BT.Node<E> root) {
        return zigZagInsert(element, root, true);

    }

    private static <E> BT.Node<E> zigZagInsert(E element, BT.Node<E> node, boolean left) {
        if(node == null){
            node = BT.Node.of(element);
        }else{
            if(left){
                if(node.left == null){
                    node.left = BT.Node.of(element);
                }else{
                    zigZagInsert(element, node.left, !left);
                }
            }else{
                if(node.right == null){
                    node.right = BT.Node.of(element);
                }else{
                    zigZagInsert(element, node.right, !left);
                }
            }
        }
        return node;
    }
    //// END (D)


    // General Trees

    //// BEGIN (E)
    public static boolean isHeap(GT.Node<Integer> root) {
        if(root == null) return true;
        return isHeap(root.element, root.children);

    }

    private static boolean isHeap(Integer element, List<GT.Node<Integer>> children) {
        for(GT.Node<Integer> child : children){
            if(!isHeap(child.element, child.children) || child.element < element) return false;
        }
        return true;
    }
    //// END (E)



    public static void main(String[] args) {
        // Run this main method to test your solutions.
        Tests.main(args);
    }
}