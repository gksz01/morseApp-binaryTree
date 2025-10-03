package MorseApp;

import MorseConfigs.MorseConfigs;
import MorseConfigs.MorseUtils;
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

    private final MorseTree tree = new MorseTree();
    private final MorseUtils utils = new MorseUtils(tree);
    private final MorseConfigs defaults = new MorseConfigs();
    private TreePrinter printer;
    private Canvas canvas;
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        printer = new TreePrinter(tree);

        BorderPane root = new BorderPane();
        VBox left = new VBox(8);
        left.setPadding(new Insets(10));

        TextField tfChar = new TextField();
        tfChar.setPromptText("Letra (A-Z ou 0-9)");
        TextField tfMorse = new TextField();
        tfMorse.setPromptText("Código Morse (. e -)");
        Button btnInsert = new Button("Inserir");

        TextField tfEncode = new TextField();
        tfEncode.setPromptText("Texto para codificar");
        Button btnEncode = new Button("Codificar");

        TextField tfDecode = new TextField();
        tfDecode.setPromptText("Código Morse (separado por espaços, use / para espaço)");
        Button btnDecode = new Button("Decodificar");

        Button btnLoadDefaults = new Button("Carregar tabela padrão");

        left.getChildren().addAll(new Label("Inserir letra:"), tfChar, tfMorse, btnInsert,
                new Label("Codificar:"), tfEncode, btnEncode,
                new Label("Decodificar:"), tfDecode, btnDecode,
                btnLoadDefaults);

        canvas = new Canvas(900, 500);
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefRowCount(6);

        root.setLeft(left);
        root.setCenter(canvas);
        root.setBottom(logArea);

        // Handlers
        btnLoadDefaults.setOnAction(e -> {
            defaults.loadDefaultsRecursive(tree, 0);
            log("Defaults loaded.");
            printer.draw(canvas);
        });

        btnInsert.setOnAction(e -> {
            String c = tfChar.getText();
            String m = tfMorse.getText();
            if (c == null || c.isBlank()) { alert("Informe uma letra."); return; }
            if (m == null || m.isBlank()) { alert("Informe o código morse."); return; }
            char ch = c.trim().toUpperCase().charAt(0);
            try {
                tree.insert(m.trim(), ch);
                log("Inserted: " + ch + " -> " + m.trim());

                // 🔄 redesenha a árvore com a raiz atual
                printer.draw(canvas);

            } catch (Exception ex) {
                alert("Erro ao inserir: " + ex.getMessage());
            }
        });


        btnEncode.setOnAction(e -> {
            String txt = tfEncode.getText();
            if (txt == null || txt.isBlank()) { alert("Informe um texto para codificar."); return; }
            String res = utils.encodeStringRecursive(txt, 0);
            if (res == null) { alert("Falha: algum caractere não está na árvore."); return; }
            log("Encode: " + txt + " -> " + res);
            Alert a = new Alert(Alert.AlertType.INFORMATION, res, ButtonType.OK);
            a.setHeaderText("Resultado da codificação");
            a.show();
        });

        btnDecode.setOnAction(e -> {
            String morse = tfDecode.getText();
            if (morse == null || morse.isBlank()) { alert("Informe uma string morse."); return; }
            String[] tokens = morse.trim().split("\\s+");
            String res = utils.decodeTokensRecursive(tokens, 0);
            if (res == null) { alert("Falha na decodificação: token inválido/mapeamento ausente."); return; }
            log("Decode: " + morse + " -> " + res);
            Alert a = new Alert(Alert.AlertType.INFORMATION, res, ButtonType.OK);
            a.setHeaderText("Resultado da decodificação");
            a.show();
        });

        // opcional: carregar defaults automaticamente ao iniciar:
        defaults.loadDefaultsRecursive(tree, 0);
        printer.draw(canvas);
        log("Aplicação iniciada. Tabela padrão carregada.");

        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setTitle("Árvore Morse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void log(String msg) { logArea.appendText(msg + "\n"); }
    private void alert(String msg) { new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK).showAndWait(); }
}