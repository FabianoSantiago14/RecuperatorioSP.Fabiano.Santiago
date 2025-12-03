package recuperatoriosp.fabiano.santiago;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class MainController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtConcentracion;
    @FXML private DatePicker dpVencimiento;
    @FXML private ChoiceBox<String> cbTipo;
    @FXML private TextField txtExtra;
    @FXML private ListView<Producto> listaProductos;

    private Inventario inventario = new Inventario();

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll("Químico", "Ecológico");
        cbTipo.getSelectionModel().selectFirst();
    }

    private Producto crearProductoDesdeCampos() throws Exception {
        String nombre = txtNombre.getText();
        String conc = txtConcentracion.getText();
        LocalDate venc = dpVencimiento.getValue();

        if (nombre.isEmpty() || conc.isEmpty() || venc == null) {
            throw new Exception("Completar todos los campos.");
        }

        String tipo = cbTipo.getValue();
        String extra = txtExtra.getText();

        if (tipo.equals("Químico")) {
            return new ProductoQuimico(nombre, conc, venc, extra);
        } else {
            return new ProductoEcologico(nombre, conc, venc, extra);
        }
    }

    @FXML
    private void onAgregar() {
        try {
            Producto nuevo = crearProductoDesdeCampos();
            inventario.agregarProducto(nuevo);
            refrescarLista();
        } catch (Exception e) {
            mostrarAlerta(e.getMessage());
        }
    }

    @FXML
    private void onEliminar() {
        Producto sel = listaProductos.getSelectionModel().getSelectedItem();

        if (sel != null) {
            inventario.eliminarProducto(sel);
            refrescarLista();
        } else {
            mostrarAlerta("Seleccione un producto para eliminar.");
        }
    }

    @FXML
    private void onModificar() {
        Producto original = listaProductos.getSelectionModel().getSelectedItem();

        if (original == null) {
            mostrarAlerta("Seleccione un producto.");
            return;
        }

        try {
            Producto modificado = crearProductoDesdeCampos();
            inventario.modificarProducto(original, modificado);
            refrescarLista();
        } catch (Exception e) {
            mostrarAlerta(e.getMessage());
        }
    }

    private void refrescarLista() {
        listaProductos.getItems().setAll(inventario.getProductos());
    }

    private void mostrarAlerta(String mensaje) {
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}
