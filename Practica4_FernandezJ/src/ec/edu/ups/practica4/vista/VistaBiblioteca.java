package ec.edu.ups.practica4.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ec.edu.ups.practica4.modelo.Biblioteca;
import ec.edu.ups.practica4.modelo.Prestamo;

public class VistaBiblioteca extends JFrame {

    private JPanel panelRegistro;
    private JPanel panelActualizar;
    private JPanel panelEliminar;

    private JTextField nombreRegistroField;
    private JTextField direccionRegistroField;
    private JTextField idRegistroField;

    private JTextField nombreActualizarField;
    private JTextField direccionActualizarField;
    private JTextField idActualizarField;

    private JTextField idEliminarField;

    public VistaBiblioteca() {
        // Configuración de la ventana
        setTitle("Gestión de Bibliotecas");
        setSize(500, 300);

        // Crear paneles
        panelRegistro = new JPanel();
        panelActualizar = new JPanel();
        panelEliminar = new JPanel();

        // Inicializar componentes para el panel de registro
        nombreRegistroField = new JTextField(15);
        direccionRegistroField = new JTextField(15);
        idRegistroField = new JTextField(5);

        JButton btnRegistro = new JButton("Registrar Biblioteca");
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Biblioteca biblioteca = registroDeBiblioteca();
                mostrarAlertas("Biblioteca registrada: " + biblioteca.toString());
            }
        });

        // Añadir componentes al panel de registro
        panelRegistro.add(new JLabel("Nombre:"));
        panelRegistro.add(nombreRegistroField);
        panelRegistro.add(new JLabel("Dirección:"));
        panelRegistro.add(direccionRegistroField);
        panelRegistro.add(new JLabel("ID:"));
        panelRegistro.add(idRegistroField);
        panelRegistro.add(btnRegistro);

        // Inicializar componentes para el panel de actualización
        nombreActualizarField = new JTextField(15);
        direccionActualizarField = new JTextField(15);
        idActualizarField = new JTextField(5);

        JButton btnActualizar = new JButton("Actualizar Biblioteca");
        btnActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Biblioteca biblioteca = actualizarDatosBiblioteca();
	                mostrarAlertas("Biblioteca actualizada: " + biblioteca.toString());
			}
        });

        // Añadir componentes al panel de actualización
        panelActualizar.add(new JLabel("Nombre:"));
        panelActualizar.add(nombreActualizarField);
        panelActualizar.add(new JLabel("Dirección:"));
        panelActualizar.add(direccionActualizarField);
        panelActualizar.add(new JLabel("ID:"));
        panelActualizar.add(idActualizarField);
        panelActualizar.add(btnActualizar);

        // Inicializar componentes para el panel de eliminación
        idEliminarField = new JTextField(5);

        JButton btnEliminar = new JButton("Eliminar Biblioteca");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = eliminarDatosBiblioteca();
                mostrarAlertas("Biblioteca eliminada con ID: " + id);
            }
        });

        // Añadir componentes al panel de eliminación
        panelEliminar.add(new JLabel("ID:"));
        panelEliminar.add(idEliminarField);
        panelEliminar.add(btnEliminar);

        // Configurar layout del frame
        setLayout(new GridLayout(3, 1));

        // Añadir paneles al frame
        add(panelRegistro);
        add(panelActualizar);
        add(panelEliminar);
    }

    public Biblioteca registroDeBiblioteca() {
        String nombre = nombreRegistroField.getText();
        String direccion = direccionRegistroField.getText();
        int id = Integer.parseInt(idRegistroField.getText());

        return new Biblioteca(nombre, direccion, id);
    }

    public Biblioteca actualizarDatosBiblioteca() {
        String nombre = nombreActualizarField.getText();
        String direccion = direccionActualizarField.getText();
        int id = Integer.parseInt(idActualizarField.getText());

        return new Biblioteca(nombre, direccion, id);
    }

    public int eliminarDatosBiblioteca() {
        return Integer.parseInt(idEliminarField.getText());
    }

    public static void mostrarAlertas(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void mostrarInformacionPrestamo(Prestamo prestamo) {
	    System.out.println("Prestamo: " + prestamo.toString());
	}
}