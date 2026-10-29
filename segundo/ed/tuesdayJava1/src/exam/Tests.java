package exam;

import datastructures.bag.SB;
import datastructures.list.ArrayList;
import datastructures.list.List;
import datastructures.tree.binary.BT;
import datastructures.tree.general.GT;

import java.util.Comparator;

class Tests {
  public static void main(String[] args) {
    test.unit.TestSuite.run(
        test_SB_remove
        , test_BT_weight
        , test_BT_leftist
        , test_BT_insert
        , test_GT_atLevel
    );
  }

  static SB.Node<Integer> sbEmpty() {return null; }
  static SB.Node<Integer> sb_10_1() { return
      SB.Node.of(10, 1, null);
  }
  static SB.Node<Integer> sb_10_2() { return
      SB.Node.of(10, 2, null);
  }
  static SB.Node<Integer> sb_10_1_12_1_15_1() { return
      SB.Node.of(10, 1, SB.Node.of(12, 1, SB.Node.of(15, 1, null)));
  }
  static SB.Node<Integer> sb_12_1_15_1() { return
      SB.Node.of(12, 1, SB.Node.of(15, 1, null));
  }
  static SB.Node<Integer> sb_10_1_12_1() { return
      SB.Node.of(10, 1, SB.Node.of(12, 1, null));
  }
  static SB.Node<Integer> sb_10_1_15_1() { return
      SB.Node.of(10, 1, SB.Node.of(15, 1, null));
  }
  static SB.Node<Integer> sb_10_2_12_3_15_4() { return
      SB.Node.of(10, 2, SB.Node.of(12, 3, SB.Node.of(15, 4, null)));
  }
  static SB.Node<Integer> sb_10_1_12_3_15_4() { return
      SB.Node.of(10, 1, SB.Node.of(12, 3, SB.Node.of(15, 4, null)));
  }
  static SB.Node<Integer> sb_10_2_12_2_15_4() { return
      SB.Node.of(10, 2, SB.Node.of(12, 2, SB.Node.of(15, 4, null)));
  }
  static SB.Node<Integer> sb_10_2_12_3_15_3() { return
      SB.Node.of(10, 2, SB.Node.of(12, 3, SB.Node.of(15, 3, null)));
  }
  static SB.Node<Integer> sb_10_1_20_2_rec() {
    var node = SB.Node.of(20, 2, null);
    node.next = node;
    return SB.Node.of(10, 1, node);
  }


  static test.unit.TestSuite test_SB_remove = new test.unit.TestSuite("SB remove",
      new test.unit.EqualBy<>("SB remove from empty bag",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(10, sbEmpty(), Comparator.naturalOrder()),
          sbEmpty(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove from unitary bag (free node)",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(10, sb_10_1(), Comparator.naturalOrder()),
          sbEmpty(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove from unitary 2 bag",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(10, sb_10_2(), Comparator.naturalOrder()),
          sb_10_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove front",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(10, sb_10_2_12_3_15_4(), Comparator.naturalOrder()),
          sb_10_1_12_3_15_4(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove back",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(15, sb_10_2_12_3_15_4(), Comparator.naturalOrder()),
          sb_10_2_12_3_15_3(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove middle",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(12, sb_10_2_12_3_15_4(), Comparator.naturalOrder()),
          sb_10_2_12_2_15_4(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove front (free node)",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(10, sb_10_1_12_1_15_1(), Comparator.naturalOrder()),
          sb_12_1_15_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove back (free node)",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(15, sb_10_1_12_1_15_1(), Comparator.naturalOrder()),
          sb_10_1_12_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB remove middle (free node)",
          datastructures.bag.Ops::sameElements,
          () -> Exam.remove(12, sb_10_1_12_1_15_1(), Comparator.naturalOrder()),
          sb_10_1_15_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.Assert("SB remove middle uses order",
          () -> {
            var sb = Exam.remove(20, sb_10_1_20_2_rec(), Comparator.naturalOrder());
            return sb.element == 10 && sb.next.element == 20
                && sb.occurrences == 1 && sb.next.occurrences == 1;
          }
      )
      ,
      new test.unit.Assert("SB remove front uses order",
          () -> {
            var sb = Exam.remove(10, sb_10_1_20_2_rec(), Comparator.naturalOrder());
            return sb.element == 20
                && sb.occurrences == 2;
          }
      )
  );

  static BT.Node<Integer> btEmpty() {
    return
        null;
  }
  static BT.Node<Integer> bt_1() { return
      BT.Node.of(1);
  }
  static BT.Node<Integer> bt_1_2_3() { return
      BT.Node.of(1,
          BT.Node.of(2),
          BT.Node.of(3)
      );
  }
  static BT.Node<Integer> bt_1_2_null() { return
      BT.Node.of(1,
          BT.Node.of(2),
          null
      );
  }
  static BT.Node<Integer> bt_1_null_2() { return
      BT.Node.of(1,
          null,
          BT.Node.of(2)
      );
  }
  static BT.Node<Integer> bt_1_2_4_5_3() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(4),
              BT.Node.of(5)
          ),
          BT.Node.of(3)
      );
  }
  static BT.Node<Integer> bt_1_2_3_4_5() { return
      BT.Node.of(1,
          BT.Node.of(2),
          BT.Node.of(3,
              BT.Node.of(4),
              BT.Node.of(5)
          )
      );
  }
  static BT.Node<Integer> bt_1_2_3_4_5_6_7() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3),
              BT.Node.of(4)
          ),
          BT.Node.of(5,
              BT.Node.of(6),
              BT.Node.of(7)
          )
      );
  }
  static BT.Node<Integer> bt_leftist_1_2_3() { return
      BT.Node.of(1,
          BT.Node.of(2),
          BT.Node.of(3)
      );
  }
  static BT.Node<Integer> bt_leftist_1_2() { return
      BT.Node.of(1,
          BT.Node.of(2),
          null
      );
  }
  static BT.Node<Integer> bt_not_leftist_1_2() { return
      BT.Node.of(1,
          null,
          BT.Node.of(2)
      );
  }
  static BT.Node<Integer> bt_not_leftist_1_2_3_4() { return
      BT.Node.of(1,
          BT.Node.of(2),
          BT.Node.of(3,
              BT.Node.of(4),
              null
          )
      );
  }
  static BT.Node<Integer> bt_leftist_1_2_3_4() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3),
              BT.Node.of(4)
          ),
          null
      );
  }
  static BT.Node<Integer> bt_not_leftist_1_2_3_4_5() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3),
              BT.Node.of(4,
                  BT.Node.of(5),
                  null
              )
          ),
          null
      );
  }
  static BT.Node<Integer> bt_leftist_1_2_3_4_5() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3,
                  BT.Node.of(4),
                  null
              ),
              BT.Node.of(5)
          ),
          null
      );
  }
  static BT.Node<Integer> bt_not_leftist_1_2_3_4_5_6_7_8_9_10() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3,
                  BT.Node.of(4),
                  null
              ),
              BT.Node.of(5)
          ),
          BT.Node.of(6,
              BT.Node.of(7,
                  BT.Node.of(8),
                  null
              ),
              BT.Node.of(9,
                  BT.Node.of(10),
                  null
              )
          )
      );
  }


  static BT.Node<Integer> bt_insert_before1() { return
      null;
  }
  static BT.Node<Integer> bt_insert_after1() { return
      BT.Node.of(1);
  }
  static BT.Node<Integer> bt_insert_before2() { return
      BT.Node.of(20,
          BT.Node.of(4,
              BT.Node.of(2),
              BT.Node.of(5)
          ),
          BT.Node.of(14)
      );
  }
  static BT.Node<Integer> bt_insert_after2() { return
      BT.Node.of(20,
          BT.Node.of(4,
              BT.Node.of(2),
              BT.Node.of(5)
          ),
          BT.Node.of(14,
              BT.Node.of(7),
              null
          )
      );
  }
  static BT.Node<Integer> bt_insert_before3() { return
      BT.Node.of(100,
          BT.Node.of(50,
              BT.Node.of(25,
                  BT.Node.of(5),
                  null
              ),
              BT.Node.of(20)
          ),
          BT.Node.of(33)
      );
  }
  static BT.Node<Integer> bt_insert_after3() { return
      BT.Node.of(100,
          BT.Node.of(50,
              BT.Node.of(25,
                  BT.Node.of(5),
                  BT.Node.of(10)
              ),
              BT.Node.of(20)
          ),
          BT.Node.of(33)
      );
  }
  static BT.Node<Integer> bt_insert_before4() { return
      BT.Node.of(100,
          BT.Node.of(50,
              BT.Node.of(25,
                  BT.Node.of(5),
                  null
              ),
              BT.Node.of(20)
          ),
          BT.Node.of(33)
      );
  }
  static BT.Node<Integer> bt_insert_after4() { return
      BT.Node.of(100,
          BT.Node.of(50,
              BT.Node.of(25,
                  BT.Node.of(5),
                  null
              ),
              BT.Node.of(20)
          ),
          BT.Node.of(33,
              BT.Node.of(11),
              null
          )
      );
  }


  static test.unit.TestSuite test_BT_weight = new test.unit.TestSuite("BT weight"
      ,
      new test.unit.Equal<>("BT weight empty",
          () -> Exam.weight(btEmpty()), 0
      )
      ,
      new test.unit.Equal<>("BT weight 1",
          () -> Exam.weight(bt_1()), 1
      )
      ,
      new test.unit.Equal<>("BT weight 2",
          () -> Exam.weight(bt_1_2_3()), 3
      )
      ,
      new test.unit.Equal<>("BT weight 3",
          () -> Exam.weight(bt_1_2_null()), 2
      )
      ,
      new test.unit.Equal<>("BT weight 4",
          () -> Exam.weight(bt_1_null_2()), 2
      )
      ,
      new test.unit.Equal<>("BT weight 5",
          () -> Exam.weight(bt_1_2_4_5_3()), 5
      )
      ,
      new test.unit.Equal<>("BT weight 6",
          () -> Exam.weight(bt_1_2_3_4_5()), 5
      )
      ,
      new test.unit.Equal<>("BT weight 7",
          () -> Exam.weight(bt_1_2_3_4_5_6_7()), 7
      )
  );


  static test.unit.TestSuite test_BT_leftist = new test.unit.TestSuite("BT leftist",
      new test.unit.Assert("BT leftist empty",
          () -> Exam.isLeftist(btEmpty())
      )
      ,
      new test.unit.Assert("BT leftist unitary",
          () -> Exam.isLeftist(bt_1())
      )
      ,
      new test.unit.Assert("BT leftist 1",
          () -> Exam.isLeftist(bt_leftist_1_2())
      )
      ,
      new test.unit.Refute("BT not leftist 1",
          () -> Exam.isLeftist(bt_not_leftist_1_2())
      )
      ,
      new test.unit.Assert("BT leftist 2",
          () -> Exam.isLeftist(bt_leftist_1_2_3())
      )
      ,
      new test.unit.Refute("BT not leftist 2",
          () -> Exam.isLeftist(bt_not_leftist_1_2_3_4())
      )
      ,
      new test.unit.Assert("BT leftist 3",
          () -> Exam.isLeftist(bt_leftist_1_2_3_4())
      )
      ,
      new test.unit.Refute("BT not leftist 3",
          () -> Exam.isLeftist(bt_not_leftist_1_2_3_4_5())
      )
      ,
      new test.unit.Assert("BT leftist 4",
          () -> Exam.isLeftist(bt_leftist_1_2_3_4_5())
      )
      ,
      new test.unit.Refute("BT not leftist 4",
          () -> Exam.isLeftist(bt_not_leftist_1_2_3_4_5_6_7_8_9_10())
      )
  );



  static test.unit.TestSuite test_BT_insert = new test.unit.TestSuite("BT divisor insert",
      new test.unit.EqualBy<>("BT insert 1",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.divisorInsert(1, bt_insert_before1()),
          bt_insert_after1(),
          datastructures.tree.binary.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("BT insert 2",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.divisorInsert(7, bt_insert_before2()),
          bt_insert_after2(),
          datastructures.tree.binary.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("BT insert 3",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.divisorInsert(10, bt_insert_before3()),
          bt_insert_after3(),
          datastructures.tree.binary.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("BT insert 4",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.divisorInsert(11, bt_insert_before4()),
          bt_insert_after4(),
          datastructures.tree.binary.Ops::toString
      )
  );

  static GT.Node<Integer> gt_levels_Empty() { return
      null;
  }
  static GT.Node<Integer> gt_levels1() { return
      GT.Node.of(1);
  }
  static GT.Node<Integer> gt_levels2() { return
      GT.Node.of(1,
          GT.Node.of(2), GT.Node.of(3), GT.Node.of(1)
      );
  }
  static GT.Node<Integer> gt_levels3() { return
      GT.Node.of(1,
          GT.Node.of(2,
              GT.Node.of(4), GT.Node.of(5)
          ),
          GT.Node.of(3,
              GT.Node.of(6), GT.Node.of(7,
                  GT.Node.of(8)
              )
          ),
          GT.Node.of(1,
              GT.Node.of(8), GT.Node.of(9,
                  GT.Node.of(10)
              )
          )
      );
  }
  static GT.Node<Integer> gt_levels4() { return
      GT.Node.of(1,
          GT.Node.of(2,
              GT.Node.of(4), GT.Node.of(5)
          ),
          GT.Node.of(3,
              GT.Node.of(6), GT.Node.of(7)
          ),
          GT.Node.of(1,
              GT.Node.of(8), GT.Node.of(9,
                  GT.Node.of(10,
                      GT.Node.of(11), GT.Node.of(12)
                  )
              )
          )
      );
  }

  static test.unit.TestSuite test_GT_atLevel = new test.unit.TestSuite("GT at level",
      new test.unit.EqualBy<List<Integer>>("GT leaves empty",
          List::sameElements,
          () -> Exam.atLevel(gt_levels_Empty(), 0),
          ArrayList.of()
      )
      ,
      new test.unit.EqualBy<>("GT leaves 1",
          List::sameElements,
          () -> Exam.atLevel(gt_levels1(), 0),
          ArrayList.of(1)
      )
      ,
      new test.unit.EqualBy<>("GT leaves 2",
          List::sameElements,
          () -> Exam.atLevel(gt_levels2(), 1),
          ArrayList.of(2, 3, 1)
      )
      ,
      new test.unit.EqualBy<>("GT leaves 3",
          List::sameElements,
          () -> Exam.atLevel(gt_levels4(), 1),
          ArrayList.of(2, 3, 1)
      )
      ,
      new test.unit.EqualBy<>("GT leaves 4",
          List::sameElements,
          () -> Exam.atLevel(gt_levels3(), 3),
          ArrayList.of(8, 10)
      )
      ,
      new test.unit.EqualBy<>("GT leaves 5",
          List::sameElements,
          () -> Exam.atLevel(gt_levels4(), 2),
          ArrayList.of(4, 5, 6, 7, 8, 9)
      )
      ,
      new test.unit.EqualBy<>("GT leaves 6",
          List::sameElements,
          () -> Exam.atLevel(gt_levels4(), 4),
          ArrayList.of(11, 12)
      )
      ,
      new test.unit.EqualBy<>("GT leaves 7",
          List::sameElements,
          () -> Exam.atLevel(gt_levels4(), 4),
          ArrayList.of(11, 12)
      )
  );
}

