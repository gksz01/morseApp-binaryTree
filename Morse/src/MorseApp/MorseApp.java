package MorseApp;

import MorseTree.MorseTree;
import TreePrinter.TreePrinter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MorseApp extends Application {

    private MorseTree tree;
    private TreePrinter printer;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        tree = new MorseTree();
        printer = new TreePrinter(tree);

        BorderPane root = new BorderPane();
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(10));

        TextField insertLetter = new TextField();
        insertLetter.setPromptText("Letra");
        TextField insertCode = new TextField();
        insertCode.setPromptText("Código Morse");

        Button btnInsert = new Button("Inserir");
        btnInsert.setOnAction(e -> {
            char letter = insertLetter.getText().toUpperCase().charAt(0);
            String code = insertCode.getText();
            tree.insert(letter, code);
            printer.draw(canvas);
        });

        TextField txtEncode = new TextField();
        txtEncode.setPromptText("Texto para codificar");
        Button btnEncode = new Button("Codificar");
        btnEncode.setOnAction(e -> {
            String text = txtEncode.getText().toUpperCase();
            StringBuilder sb = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (c != ' ') {
                    String enc = tree.encode(c);
                    if (enc != null) sb.append(enc).append(" ");
                }
            }
            showAlert("Resultado", "Morse: " + sb.toString());
        });

        TextField txtDecode = new TextField();
        txtDecode.setPromptText("Código Morse (separado por espaço)");
        Button btnDecode = new Button("Decodificar");
        btnDecode.setOnAction(e -> {
            String[] codes = txtDecode.getText().split(" ");
            StringBuilder sb = new StringBuilder();
            for (String code : codes) {
                sb.append(tree.decode(code));
            }
            showAlert("Resultado", "Texto: " + sb.toString());
        });

        menu.getChildren().addAll(
                new Label("Inserir letra:"), insertLetter, insertCode, btnInsert,
                new Label("Codificar:"), txtEncode, btnEncode,
                new Label("Decodificar:"), txtDecode, btnDecode
        );

        canvas = new Canvas(600, 400);
        root.setLeft(menu);
        root.setCenter(canvas);

        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Árvore Morse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }
}