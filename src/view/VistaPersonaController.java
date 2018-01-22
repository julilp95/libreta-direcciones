/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.LibretaDirecciones;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;
import util.UtilidadDeFechas;

/**
 *
 * @author dam
 */
public class VistaPersonaController {
    
    @FXML
    private TableView tablaPersonas;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private TableColumn apellidosColumn;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label codigoPostalLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label fechaDeNacimientoLabel;

    // Referencia a la clase principal
    private LibretaDirecciones libretaDirecciones;
    
    //El constructor es llamado ANTES del método initialize

    public VistaPersonaController() {

    }

    //Inicializa la clase controller y es llamado justo después de cargar el archivo FXML
    @FXML
    private void initialize() {
        
        //Inicializo la tabla con las dos primera columnas
        String nombre = "nombre";
        String apellidos = "apellidos";
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>(nombre));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>(apellidos));
        
        mostrarDetallesPersona(null);
        
        //Escucho cambios en la selección de la tabla y muestro los detalles en caso de cambio
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> mostrarDetallesPersona((Persona) newValue));
        
    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setLibretaDirecciones(LibretaDirecciones libretaDirecciones) {
        
        this.libretaDirecciones = libretaDirecciones;

        //Añado la lista obervable a la tabla
        tablaPersonas.setItems(libretaDirecciones.getDatosPersona());
    }
    
    //Muestra los detalles de la persona seleccionada
    private void mostrarDetallesPersona(Persona persona) {
        
        if (persona != null) {
            //Relleno los labels desde el objeto persona
            nombreLabel.setText(persona.getNombre());
            apellidosLabel.setText(persona.getApellidos());
            direccionLabel.setText(persona.getDireccion());
            codigoPostalLabel.setText(Integer.toString(persona.getCodigoPostal()));
            ciudadLabel.setText(persona.getCiudad());
            fechaDeNacimientoLabel.setText(UtilidadDeFechas.formato(persona.getFechaDeNacimiento()));
        } else {
            //Persona es null, vacío todos los labels.
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            codigoPostalLabel.setText("");
            ciudadLabel.setText("");
            fechaDeNacimientoLabel.setText("");
        }
    }
    
    @FXML
    private void borrarPersona(){
        int indiceSeleccionado = tablaPersonas.getSelectionModel().getSelectedIndex();
        if(indiceSeleccionado >= 0){
        tablaPersonas.getItems().remove(indiceSeleccionado);
        }else{
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Atención");
            alerta.setHeaderText("Persona no seleccionada");
            alerta.setContentText("Por favor, selecciona persona de la tabla");
            alerta.showAndWait();
        }
    }
    
    //Muestro el diálogo editar persona cuando el usuario hace clic en el botón de Crear
    @FXML
    private void crearPersona() {
        Persona temporal = new Persona();
        boolean guardarClicked = libretaDirecciones.muestraEditarPersona(temporal);
        if (guardarClicked) {
            libretaDirecciones.getDatosPersona().add(temporal);
        }
    }
    
    //Muestro el diálogo editar persona cuando el usuario hace clic en el botón de Editar
    @FXML
    private void editarPersona() {
        Persona seleccionada = (Persona) tablaPersonas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            boolean guardarClicked = libretaDirecciones.muestraEditarPersona(seleccionada);
            if (guardarClicked) {
                mostrarDetallesPersona(seleccionada);
            }

        } else {
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Persona no seleccionada");
            alerta.setContentText("Por favor, selecciona una persona");
            alerta.showAndWait();
        }
    }
    
}
