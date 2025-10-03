package MorseConfigs;

import MorseTree.MorseTree;

public class MorseConfigs {
    private final char[] SYMBOLS = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0','1','2','3','4','5','6','7','8','9'
    };

    private final String[] CODES = {
            ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
            "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-",
            "..-","...-",".--","-..-","-.--","--..",
            "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
    };

    public void loadDefaultsRecursive(MorseTree tree, int idx) {
        if (idx >= CODES.length) return;
        tree.insert(CODES[idx], SYMBOLS[idx]);
        loadDefaultsRecursive(tree, idx + 1);
    }
}
