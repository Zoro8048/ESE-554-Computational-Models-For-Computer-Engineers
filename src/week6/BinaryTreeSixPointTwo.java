package week6;

// Q) 6.2
public class BinaryTreeSixPointTwo {
    public static void main(String[] args) {
        int lineWidth = 32;
        char[][] tree = createBinaryTree(lineWidth);
        display(tree);
    }

    public static char[][] createBinaryTree(int lineWidth) {
        int height = (int) Math.ceil(Math.log(lineWidth + 1) / Math.log(2));
        int width = (int) Math.pow(2, height) - 1;

        char[][] tree = new char[height][width];

        makeBranches(tree, 0, width - 1, 0);

        return tree;
    }

    public static void makeBranches(char[][] tree, int left, int right, int row) {
        if (left > right || row >= tree.length) {
            return;
        }

        int mid = (left + right) / 2;
        tree[row][mid] = 'X';

        makeBranches(tree, left, mid - 1, row + 1);
        makeBranches(tree, mid + 1, right, row + 1);
    }

    public static void display(char[][] tree) {
        for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < tree[i].length; j++) {
                System.out.print(tree[i][j] == 'X' ? 'X' : '-');
            }
            System.out.println();
        }
    }
}
