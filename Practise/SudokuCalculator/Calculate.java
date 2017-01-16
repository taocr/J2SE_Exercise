package Practise.SudokuCalculator;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Caluate类为算法最重要部分，其许多函数和变量声明为静态方便使用。
 * 作用：
 * 1、数独中空格的填充
 * 2、数据的计算
 * <p>
 * 附加：
 * 实现Runnable接口，从而多线程操作哦，提高效率
 * <p>
 * Created by Taocr on 2016/12/16.
 */
public class Calculate {
    public static boolean[][] boo = new boolean[9][9];//判断数据是否已填
    public static int[][] matrix = new int[9][9];//用于存储九宫格中数据
    public static int upRow = 0;
    public static int upColumn = 0;


    public static void flyBack(boolean[][] judge, int row, int column) {//查找没有填入数值的空格
        int s = column * 9 + row;
        s--;
        int quotient = s / 9;
        int remainder = s % 9;

        if (judge[remainder][quotient])
            flyBack(judge, remainder, quotient);
        else {
            upRow = remainder;
            upColumn = quotient;
            ;
        }
    }


    public static void arrayAdd(ArrayList<Integer> array, TreeSet<Integer> tree) {//添加新的数值至一行中，数据如果已有：跳过，没有：继续赋值
        for (int i = 1; i < 10; i++) {
            boolean flag3 = true;
            Iterator<Integer> it = tree.iterator();
            while (it.hasNext()) {
                int b = it.next().intValue();
                if (i == b) {
                    flag3 = false;
                    break;
                }
            }
            if (flag3) {
                array.add(new Integer(i));
            }
            flag3 = true;
        }
    }

    public static ArrayList<Integer> assume(int row, int column) {//利用候选法，在每个小的九宫格中判断同行同列内哪些数值已经填充，从而添加备选的值
        ArrayList<Integer> array = new ArrayList<Integer>();
        TreeSet<Integer> tree = new TreeSet<Integer>();
        if (0 <= row && row <= 2 && 0 <= column && column <= 2) {
            for (int i = 0; i < 9; i++) {
                if (i != column && matrix[row][i] != 0) {
                    tree.add(new Integer(matrix[row][i]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 0; a2 < 3; a2++) {
                for (int b4 = 0; b4 < 3; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (0 <= row && row <= 2 && 3 <= column && column <= 5) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 0; a2 < 3; a2++) {
                for (int b4 = 3; b4 < 6; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (0 <= row && row <= 2 && 6 <= column && column <= 8) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 0; a2 < 3; a2++) {
                for (int b4 = 6; b4 < 9; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (3 <= row && row <= 5 && 0 <= column && column <= 2) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 3; a2 < 6; a2++) {
                for (int b4 = 0; b4 < 3; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (3 <= row && row <= 5 && 3 <= column && column <= 5) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 3; a2 < 6; a2++) {
                for (int b4 = 3; b4 < 6; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (3 <= row && row <= 5 && 6 <= column && column <= 8) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 3; a2 < 6; a2++) {
                for (int b4 = 6; b4 < 9; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (6 <= row && row <= 8 && 0 <= column && column <= 2) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 6; a2 < 9; a2++) {
                for (int b4 = 0; b4 < 3; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (6 <= row && row <= 8 && 3 <= column && column <= 5) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 6; a2 < 9; a2++) {
                for (int b4 = 3; b4 < 6; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (6 <= row && row <= 8 && 6 <= column && column <= 8) {
            for (int a = 0; a < 9; a++) {
                if (a != column && matrix[row][a] != 0) {
                    tree.add(new Integer(matrix[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && matrix[b1][column] != 0) {
                    tree.add(new Integer(matrix[b1][column]));
                }
            }
            for (int a2 = 6; a2 < 9; a2++) {
                for (int b4 = 6; b4 < 9; b4++) {
                    if ((!(a2 == row && b4 == column)) && matrix[a2][b4] != 0) {
                        tree.add(new Integer(matrix[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        }
        return array;
    }


    public void run() {
        //初始化用来判断的boolean矩阵
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] != 0)
                    boo[i][j] = true;
                else
                    boo[i][j] = false;
            }
        }
        boolean flag = true;

        ArrayList<Integer>[][] utilization = new ArrayList[9][9];//用于存放所有格子中所有可能的值
        int row = 0;//用于确定列号
        int column = 0;//用于确定行号
        while (column < 9) {
            if (flag == true)
                row = 0;
            while (row < 9) {//遍历列中的每个格子
                if (matrix[row][column] == 0) {//对于当前遍历到的格子中数为0即没有填的情况
                    if (flag) {
                        ArrayList<Integer> list = assume(row, column);//添加这个格子的备选数值
                        utilization[row][column] = list;//添加到备选数值的总矩阵中
                    }

                    if (utilization[row][column].isEmpty()) {//为空则表示没有备选数值
                        flyBack(boo, row, column);
                        row = upRow;
                        column = upColumn;
                        ;
                        matrix[row][column] = 0;
                        column--;
                        flag = false;
                    } else {//正常的有备选数值的情况
                        matrix[row][column] = utilization[row][column].get(0);//返回备选数值列表上第一个数
                        utilization[row][column].remove(0);//添加后删掉第一个
                        flag = true;
                        boolean r = true;

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (r == false)
                                    break;
                                if (matrix[i][j] == 0)//判断数独中的所有的数是否都已经填了，没填则r必为flase
                                    r = false;
                            }
                        }
                        if (r) {//当数独中所有数都已经填了时，输出结果
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    System.out.println("matrix[" + i + "][" + j + "]" + matrix[i][j] + ",");
                                }
                            }
                        }
                    }
                } else {//格子中的数已经填了的情况就跳过
                    flag = true;
                }

                row++;
            }
            column++;
        }
    }
}

