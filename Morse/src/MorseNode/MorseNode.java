package MorseNode;

public class MorseNode {
    public char value;     // '\0' quando vazio
    public MorseNode leftNode; // dot
    public MorseNode rightNode;// dash
    public double x, y;    // coords para desenhar

    public MorseNode() { this.value = '\0'; }
    public MorseNode(char v) { this.value = v; }
}
