package MorseTree;

import MorseNode.MorseNode;

public class MorseTree {
    private final MorseNode root;

    public MorseTree() {
        root = new MorseNode('*'); // root doesn't represent a letter
    }

    public MorseNode getRoot() {
        return root;
    }


    public void insert(char letter, String code) {
        insertRecursive(root, letter, code, 0);
    }

    private void insertRecursive(MorseNode node, char letter, String code, int index) {
        if (index == code.length()) {
            node.value = letter;
            return;
        }
        char c = code.charAt(index);
        if (c == '.') {
            if (node.leftNode == null) node.leftNode = new MorseNode('*');
            insertRecursive(node.leftNode, letter, code, index + 1);
        } else if (c == '-') {
            if (node.rightNode == null) node.rightNode = new MorseNode('*');
            insertRecursive(node.rightNode, letter, code, index + 1);
        }
    }

    public char decode(String code) {
        return decodeRecursive(root, code, 0);
    }

    private char decodeRecursive(MorseNode node, String code, int index) {
        if (node == null) return '?';
        if (index == code.length()) return node.value;

        char c = code.charAt(index);
        if (c == '.') return decodeRecursive(node.leftNode, code, index + 1);
        else return decodeRecursive(node.rightNode, code, index + 1);
    }

    // Recursive search for encoding
    public String encode(char letter) {
        return encodeRecursive(root, letter, "");
    }

    private String encodeRecursive(MorseNode node, char letter, String path) {
        if (node == null) return null;
        if (node.value == letter) return path;

        String left = encodeRecursive(node.leftNode, letter, path + ".");
        if (left != null) return left;

        return encodeRecursive(node.rightNode, letter, path + "-");
    }
}
