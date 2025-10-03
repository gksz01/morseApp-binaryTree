package TreePrinter;

import MorseNode.MorseNode;
import MorseTree.MorseTree;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TreePrinter {
    private final MorseTree tree;

    public TreePrinter(MorseTree tree) {
        this.tree = tree;
    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawNode(gc, tree.getRoot(), canvas.getWidth() / 2, 30, canvas.getWidth() / 4);
    }

    private void drawNode(GraphicsContext gc, MorseNode node, double x, double y, double offset) {
        if (node == null) return;

        gc.setFill(Color.BLACK);
        gc.fillText(Character.toString(node.value), x, y);

        if (node.leftNode != null) {
            gc.strokeLine(x, y, x - offset, y + 50);
            drawNode(gc, node.leftNode, x - offset, y + 50, offset / 2);
        }
        if (node.rightNode != null) {
            gc.strokeLine(x, y, x + offset, y + 50);
            drawNode(gc, node.rightNode, x + offset, y + 50, offset / 2);
        }
    }
}