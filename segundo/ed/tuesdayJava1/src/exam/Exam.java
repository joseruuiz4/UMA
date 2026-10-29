///////////////////////////////////////////////////////////////////////////////
// Student's name: [Your Name]
// Identity number:  [Your DNI or Passport Number] 
///////////////////////////////////////////////////////////////////////////////

package exam;


import datastructures.list.ArrayList;
import datastructures.tree.binary.BT;
import datastructures.tree.general.GT;
import datastructures.bag.SB;
import datastructures.list.List;

import java.util.Comparator;

public class Exam {
    // Bags

    //// BEGIN (A)
    public static <E> SB.Node<E> remove(E element, SB.Node<E> first, Comparator<E> comparator) {
        SB.Node<E> aux = first;
        SB.Node<E> prev = null;

        while(aux != null && comparator.compare(element, aux.element) > 0){
            prev = aux;
            aux = aux.next;
        }

        if(aux == null){
            return first;
        }

        if(comparator.compare(element, aux.element) == 0){
            aux.occurrences--;

            if(aux.occurrences == 0){
                if(prev == null){
                    first = aux.next;
                }else{
                    prev.next = aux.next;
                }
            }

        }

        return first;
    }
    //// END (A)


    // Binary Trees

    //// BEGIN (B)
    public static <E> int weight(BT.Node<E> root) {
        if(root == null) return 0;

        return 1 + weight(root.left) + weight(root.right);

    }
    //// END (B)


    //// BEGIN (C)
    public static <E> boolean isLeftist(BT.Node<E> root) {
        if(root == null) return true;

        return weight(root.left) >= weight(root.right) && isLeftist(root.left) && isLeftist(root.right);

    }
    //// END (C)


    //// BEGIN (D)
    public static BT.Node<Integer> divisorInsert(int element, BT.Node<Integer> node) {
        if(node == null){
            node = BT.Node.of(element);
            return node;
        }

        if(node.element % element == 0){
            if(node.left == null){
                node.left = BT.Node.of(element);
                return node;

            }
             divisorInsert(element, node.right);
        }else{
            divisorInsert(element, node.left);
        }


        return node;
    }
    //// END (D)


    // General Trees

    //// BEGIN (E)
    public static <E> List<E> atLevel(GT.Node<E> root, int level) {
        List<E> list = new ArrayList<>();
        atLevel(root, level, 0, list);
        return list;
    }

    private static <E> void atLevel(GT.Node<E> node, int targetLevel, int currentLevel, List<E> elements) {
        if(node == null) return;

        if(targetLevel == currentLevel){
            elements.append(node.element);
            return;
        }

        for(GT.Node<E> child : node.children){
            atLevel(child, targetLevel, currentLevel + 1, elements);
        }




    }
    //// END (E)


    public static void main(String[] args) {
        // Run this main method to test your solutions.
        Tests.main(args);
    }
}