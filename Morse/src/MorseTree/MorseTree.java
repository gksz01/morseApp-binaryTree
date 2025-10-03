package MorseTree;

import MorseNode.MorseNode;

public class MorseTree {
    private MorseNode root;

    public MorseTree() {
        root = new MorseNode();
    }

    public MorseNode getRoot() { return root; }

    // Insert: morse string then character stored uppercase
    public void insert(String morse, char ch) {
        if (morse == null) throw new IllegalArgumentException("Morse cannot be null");
        ch = Character.toUpperCase(ch);
        root = insertRec(root, morse.trim(), 0, ch);
    }

    private MorseNode insertRec(MorseNode node, String morse, int idx, char ch) {
        if (node == null) node = new MorseNode();
        if (idx == morse.length()) {
            node.value = ch;
            return node;
        }
        char c = morse.charAt(idx);
        if (c == '.') node.leftNode = insertRec(node.leftNode, morse, idx + 1, ch);
        else if (c == '-') node.rightNode = insertRec(node.rightNode, morse, idx + 1, ch);
        else throw new IllegalArgumentException("Invalid morse character: " + c);
        return node;
    }

    // decode a morse token into a Character (or null if not found)
    public Character decode(String morse) {
        if (morse == null) return null;
        MorseNode n = decodeRec(root, morse.trim(), 0);
        if (n == null) return null;
        return n.value == '\0' ? null : n.value;
    }

    private MorseNode decodeRec(MorseNode node, String morse, int idx) {
        if (node == null) return null;
        if (idx == morse.length()) return node;
        char c = morse.charAt(idx);
        if (c == '.') return decodeRec(node.leftNode, morse, idx + 1);
        else if (c == '-') return decodeRec(node.rightNode, morse, idx + 1);
        else return null;
    }

    // encode char -> morse string by searching tree (recursive). returns null if not found
    public String encodeChar(char target) {
        target = Character.toUpperCase(target);
        return encodeRec(root, target, "");
    }

    private String encodeRec(MorseNode node, char target, String path) {
        if (node == null) return null;
        if (node.value == target) return path;
        // search left
        String left = encodeRec(node.leftNode, target, path + ".");
        if (left != null) return left;
        // then right
        return encodeRec(node.rightNode, target, path + "-");
    }

    // compute positions for drawing using inorder to set x, depth for y
    public void computePositions() {
        int[] counter = new int[]{0};
        computePosRec(root, 0, counter);
    }

    private void computePosRec(MorseNode node, int depth, int[] counter) {
        if (node == null) return;
        computePosRec(node.leftNode, depth + 1, counter);
        node.x = counter[0] * 70 + 60;
        node.y = depth * 100 + 60;
        counter[0]++;
        computePosRec(node.rightNode, depth + 1, counter);
    }
}
