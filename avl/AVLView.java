
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AVLView extends Pane {
    private Avl tree= new Avl<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    AVLView(Avl<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayAVLTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRaiz() != null) {
            // Display tree recursively
            displayAVLTree(tree.getRaiz(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayAVLTree(Nodo<Integer> raiz,
                                double x, double y, double hGap) {
        if (raiz.getIzquierda() != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayAVLTree(raiz.getIzquierda(), x - hGap, y + vGap, hGap / 2);
        }

        if (raiz.getDerecha() != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayAVLTree(raiz.getDerecha(), x + hGap, y + vGap, hGap / 2);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.CYAN);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
                new Text(x - 4, y + 4, raiz.getElemento() + ""));
    }
}