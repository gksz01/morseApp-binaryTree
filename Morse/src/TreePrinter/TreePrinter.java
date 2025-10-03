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
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Configuração de estilo
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.2);
        gc.setFill(Color.LIGHTYELLOW);

        // Desenha a árvore a partir da raiz no centro superior
        drawTree(gc, tree.getRoot(),
                canvas.getWidth() / 2, 50,         // posição inicial (x, y)
                canvas.getWidth() / 4, 80);       // offset horizontal e vertical
    }

    private void drawTree(GraphicsContext gc, MorseNode node,
                          double x, double y, double xOffset, double yOffset) {
        if (node == null) return;

        double r = 15; // raio do círculo

        // Desenha nó
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillOval(x - r, y - r, r * 2, r * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - r, y - r, r * 2, r * 2);

        if (node.value != '\0') {
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(node.value), x - 5, y + 5);
        }

        // Desenha filho esquerdo
        if (node.leftNode != null) {
            double childX = x - xOffset;
            double childY = y + yOffset;
            gc.strokeLine(x, y, childX, childY);
            drawTree(gc, node.leftNode, childX, childY, xOffset / 2, yOffset);
        }

        // Desenha filho direito
        if (node.rightNode != null) {
            double childX = x + xOffset;
            double childY = y + yOffset;
            gc.strokeLine(x, y, childX, childY);
            drawTree(gc, node.rightNode, childX, childY, xOffset / 2, yOffset);
        }
    }
}