package org.example.bst;

import java.util.*;

public class BSTProbs {

    public static void insert(int value, BST root) {
        BST tmp = new BST();
        tmp.value = value;
        tmp.left = null;
        tmp.right = null;

        if (root == null) {
            root = tmp;
            return;
        }

        while (root != null) {
            if (value <= root.value) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (value <= root.value) {
            root.left = tmp;
        } else {
            root.right = tmp;
        }
    }

    public static boolean search(BST root, int value) {
        if (root.value == value) {
            return true;
        }

        while (root != null) {
            if (value == root.value) {
                return true;
            } else if (value < root.value) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return false;
    }

    public static int searchClosesValue(BST root, int value) {
        if (root.value == value) {
            return root.value;
        }

        BST current = root;
        int closest = current.value;

        while (current != null) {
            int diffClosest = Math.abs(value - closest);
            int diffCCurrent = Math.abs(value - current.value);

            if (diffClosest > diffCCurrent) {
                closest = current.value;
            }

            if (value == current.value) {
                return current.value;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return closest;
    }

    public static void delete(BST root, int value) {
        BST parent = null;
        BST current = root;

        while (current != null) {
            parent = current;

            if (value <= current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null) {
            if (current == parent.left) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (current == parent.left) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {
            BST sParent = current;
            BST sCurrent = current.right;

            while (sCurrent.left != null) {
                sParent = sCurrent;
                sCurrent = sCurrent.left;
            }

            if (sParent == current) {
                sParent.right = sCurrent.right;
            } else {
                sParent.left = sCurrent.right;
            }

            current.value = sCurrent.value;
        }
    }

    public static List<Integer> preorder(BST root) {
        List<Integer> list = new ArrayList<>();
        preOrderHelper(root, list);

        return list;
    }

    public static void preOrderHelper(BST root, List<Integer> list) {
        if (root != null) {
            list.add(root.value);
            preOrderHelper(root.left, list);
            preOrderHelper(root.right, list);
        }
    }

    public static void inorderHelper(BST root, List<Integer> list) {
        if (root != null) {
            inorderHelper(root.left, list);
            list.add(root.value);
            inorderHelper(root.right, list);
        }
    }

    public static void postorderHelper(BST root, List<Integer> list) {
        if (root != null) {
            postorderHelper(root.left, list);
            postorderHelper(root.right, list);
            list.add(root.value);
        }
    }

    public static int minElement(BST root) {
        BST current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    public static int secondMaxElement(BST root) {
        BST current = root;
        BST parent = current;

        while (current.right != null) {
            parent = current;
            current = current.right;
        }

        return parent.right.value;
    }

    public static int getDistance(BST root, int value) {
        BST current = root;
        int count = 0;

        while (current != null) {
            if (value == current.value) {
                return count; // root level = 0
            } else if (value < current.value) {
                current = current.left;
                count++;
            } else {
                current = current.right;
                count++;
            }
        }

        return count;
    }

    public static BST getLCA(BST root, int x, int y) {
        BST current = root;

        while (current != null) {
            if (x > current.value && y > current.value) {
                current = current.right;
            } else if (x < current.value && y < current.value) {
                current = current.left;
            } else {
                break;
            }
        }

        return current;
    }

    public static int getXYDistance(BST root, BST x, BST y) {
        int xCount = getDistance(root, x.value);
        int yCount = getDistance(root, y.value);
        BST lca = getLCA(root, x.value, y.value);
        int lcaCount = getDistance(root, lca.value);

        return xCount + yCount - 2 * lcaCount;
    }

    public static int getMaxHeight(BST root) {
        Queue<BST> queue = new LinkedList<>();
        Map<Integer, Integer> height = new HashMap<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BST current = queue.poll();

            if (current.left == null && current.right == null) {
                continue;
            }

            if (current.left != null) {
                height.put(current.left.value, height.getOrDefault(current.value, 0) + 1);
                queue.add(current.left);
            }

            if (current.right != null) {
                height.put(current.right.value, height.getOrDefault(current.value, 0) + 1);
                queue.add(current.right);
            }
        }

        return height.values().stream().mapToInt(entry -> entry).max().orElse(0);
        // node depth: return height.values().stream().mapToInt(entry -> entry).sum();
    }

    public int maxDepth2(BST root) {
        if (root == null) {
            return 0;
        }

        Queue<BST> q = new LinkedList<>();
        q.add(root);

        int depth = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                BST node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            depth++;
        }
        return depth;
    }

    public List<List<Integer>> levelOrder(BST root) {
        Queue<BST> q = new LinkedList<>();

        if (root != null) {
            q.add(root);
        }

        List<List<Integer>> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> nodeList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                BST node = q.poll();
                nodeList.add(node.value);

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }

            res.add(nodeList);
        }

        return res;
    }

    public static boolean isSymmetric(BST root) {
        Queue<BST> queue = new LinkedList<>();

        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            BST p = queue.poll();
            BST q = queue.poll();

            if (p == null && q == null) {
                continue;
            }

            if (p == null || q == null || p.value != q.value) {
                return false;
            }

            queue.add(p.left);
            queue.add(q.right);
            queue.add(p.right);
            queue.add(q.left);
        }

        return true;
    }

    public static BST sortedArrayToBST(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BST root = new BST();
        root.value = array[mid];
        root.left = sortedArrayToBST(array, start, mid - 1);
        root.right = sortedArrayToBST(array, mid + 1, end);

        return root;
    }

    public static void main(String[] args) {
    }
}
