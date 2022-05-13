import java.util.*;

public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree(String[] values) {
        if (values != null && values.length != 0) {
            int centreIndex = values.length / 2;
            if (values[centreIndex] != null) {
                root = new TreeNode(Integer.parseInt(values[centreIndex]));
                root.setLeft(addChildesToTree(Arrays.copyOfRange(values, 0, centreIndex)));
                root.setRight(addChildesToTree(Arrays.copyOfRange(values, centreIndex + 1, values.length)));
            }
        }
    }

    private TreeNode addChildesToTree(String[] values) {
        if (values.length != 0) {
            int centreIndex = values.length / 2;
            if (values[centreIndex] == null) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(values[centreIndex]));
                node.setLeft(addChildesToTree(Arrays.copyOfRange(values, 0, centreIndex)));
                node.setRight(addChildesToTree(Arrays.copyOfRange(values, centreIndex + 1, values.length)));
                return node;
            }
        }

        return null;
    }

    public List<String> findSubtrees() {
        if (root != null) {
            if (root.getRight() != null || root.getLeft() != null) {
                List<String> ways = new ArrayList<>();
                HashMap<String, Integer> allSums = findAllSums();
                Object[] keys = allSums.keySet().toArray();
                int maxSum = allSums.get(keys[0]);

                for (Object key : keys) {
                    if (allSums.get(key) > maxSum) {
                        maxSum = allSums.get(key);
                        ways.clear();
                        ways.add((String) key);
                    } else if (allSums.get(key) == maxSum) {
                        ways.add((String) key);
                    }
                }
                return ways;
            }
        }

        return null;
    }

    private HashMap<String, Integer> findAllSums() {
        HashMap<String, Integer> sums = new HashMap<>();
        StringBuilder way = new StringBuilder();

        findChildesSum(root, sums, way);
        return sums;
    }

    private void findChildesSum(TreeNode node, HashMap<String, Integer> sums, StringBuilder way) {
        if (node != null) {
            way.append("L");
            if (node.getLeft() != null) {
                sums.put(way.toString(), findSubtreeSum(node.getLeft()));
                findChildesSum(node.getLeft(), sums, way);
            }

            way.setLength(way.length() - 1);
            way.append("R");
            if (node.getRight() != null) {
                sums.put(way.toString(), findSubtreeSum(node.getRight()));
                findChildesSum(node.getRight(), sums, way);
            }
            way.setLength(way.length() - 1);
        }
    }

    private int findSubtreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.getValue() + findSubtreeSum(node.getRight()) + findSubtreeSum(node.getLeft());
        }
    }

    private static class TreeNode {
        private int value;
        private TreeNode leftChild;
        private TreeNode rightChild;

        TreeNode(int value, TreeNode leftChild, TreeNode rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        TreeNode(int value) {
            this(value, null, null);
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }

        TreeNode getLeft() {
            return leftChild;
        }

        void setLeft(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        void setRight(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        TreeNode getRight() {
            return rightChild;
        }
    }

    public String toString() {
        List<TreeNode> childes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int height = 0;

        if (root != null) {
            sb.append(root.getValue());
        } else {
            return null;
        }

        childes.add(root.getRight());
        childes.add(root.getLeft());
        addChildes(sb, childes, height + 1);

        return sb.toString();
    }

    private void addChildes(StringBuilder sb, List<TreeNode> roots, int height) {
        List<TreeNode> childes = new ArrayList<>();
        for (TreeNode root : roots) {
            if (root != null) {
                sb.append("\n");
                sb.append("\t".repeat(height));
                sb.append(root.getValue());

                childes.add(root.getRight());
                childes.add(root.getLeft());
                addChildes(sb, childes, height + 1);

                childes.clear();
            }
        }
    }
}
