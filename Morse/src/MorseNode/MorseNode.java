package MorseNode;

public class MorseNode {
    public char value;
    public MorseNode leftNode;   // dot
    public MorseNode rightNode;  // dash

    public MorseNode(char value) {
        this.value = value;
        this.leftNode = null;
        this.rightNode = null;
    }
}
