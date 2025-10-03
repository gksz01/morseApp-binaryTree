package MorseConfigs;

import MorseTree.MorseTree;

public class MorseUtils {
    private final MorseTree tree;

    public MorseUtils(MorseTree tree) {
        this.tree = tree;
    }

    // Recursivo: codifica a string inteira (retorna null se alguma letra não existir)
    public String encodeStringRecursive(String s, int idx) {
        if (s == null) return null;
        if (idx >= s.length()) return "";
        char ch = s.charAt(idx);
        if (ch == ' ') {
            String tail = encodeStringRecursive(s, idx + 1);
            if (tail == null) return null;
            // representamos espaço de palavra como '/'
            if (tail.isEmpty()) return "/";
            return "/" + (tail.isEmpty() ? "" : " " + tail);
        }
        String code = tree.encodeChar(ch);
        // tentar uppercase se necessário
        if (code == null) code = tree.encodeChar(Character.toUpperCase(ch));
        if (code == null) return null;
        String tail = encodeStringRecursive(s, idx + 1);
        if (tail == null) return null;
        if (tail.isEmpty()) return code;
        return code + " " + tail;
    }

    // tokens: array já separado por espaço; '/' token => space
    public String decodeTokensRecursive(String[] tokens, int idx) {
        if (tokens == null) return null;
        if (idx >= tokens.length) return "";
        String t = tokens[idx];
        if (t.equals("/")) {
            String tail = decodeTokensRecursive(tokens, idx + 1);
            if (tail == null) return null;
            return " " + tail;
        }
        Character ch = tree.decode(t);
        if (ch == null) return null;
        String tail = decodeTokensRecursive(tokens, idx + 1);
        if (tail == null) return null;
        return ch + tail;
    }
}
