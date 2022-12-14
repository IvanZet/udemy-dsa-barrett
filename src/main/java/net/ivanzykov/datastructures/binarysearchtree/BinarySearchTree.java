package net.ivanzykov.datastructures.binarysearchtree;

import java.util.*;

public class BinarySearchTree {

    private Node root;

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        Node temp = root;
        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Breadth First Search. Uses ArrayDeque instead of LinkedList because Java
     * documentation favours the first one. Uses ArrayDeque.offer() and ArrayDeque.poll()
     * instead of add() and remove() because the first two are defined in Queue interface.
     *
     * @return  list of integers representing the values of nodes in the tree
     */
    public List<Integer> BFS() {
        Node currentNode = root;
        Queue<Node> queue = new ArrayDeque<>();
        List<Integer> results = new ArrayList<>();
        queue.offer(currentNode);

        while (queue.size() > 0) {
            currentNode = queue.poll();
            results.add(currentNode.value);
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
        return results;
    }

    /**
     * Depth First Search Pre Order without recursion.
     */
    public List<Integer> DFSPreOrderNoRecursion() {
        List<Integer> results = new ArrayList<>();
        Node currentNode = root;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(currentNode);
        while (stack.size() > 0) {
            currentNode = stack.peek();
            if (! results.contains(currentNode.value)) {
                results.add(currentNode.value);
            }
            if (currentNode.left != null && ! results.contains(currentNode.left.value)) {
                stack.push(currentNode.left);
            } else if (currentNode.right != null && ! results.contains(currentNode.right.value)) {
                stack.push(currentNode.right);
            } else {
                stack.pop();
            }
        }
        return results;
    }

    /**
     * Depth First Search Pre Order with recursion.
     */
    public List<Integer> DFSPreOrder() {
        List<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                results.add(currentNode.value);
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }

        new Traverse(root);
        return results;
    }

    /**
     * Depth First Search Pre Order with recursion via private method.
     */
    public List<Integer> DFSPreOrderRecursionWithMethod() {
        List<Integer> results = new ArrayList<>();
        traverse(root, results);
        return results;
    }

    private void traverse(Node currentNode, List<Integer> results) {
        results.add(currentNode.value);
        if (currentNode.left != null) {
            traverse(currentNode.left, results);
        }
        if (currentNode.right != null) {
            traverse(currentNode.right, results);
        }
    }

    /**
     * Depth First Search Post Order.
     */
    public List<Integer> DFSPostOrder() {
        List<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
                results.add(currentNode.value);
            }
        }

        new Traverse(root);
        return results;
    }

    /**
     * Depth First Search In Order.
     */
    public List<Integer> DFSInOrder() {
        List<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                results.add(currentNode.value);
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }

        new Traverse(root);
        return results;
    }
}
