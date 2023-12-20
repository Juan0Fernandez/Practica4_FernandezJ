package ec.edu.ups.practica4.vista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;

import ec.edu.ups.practica4.modelo.Libro;
import ec.edu.ups.practica4.modelo.Prestamo;
import ec.edu.ups.practica4.modelo.Usuario;

public class VistaPrestamo extends JFrame {

    private JPanel panelIngresar;
    private JPanel panelActualizar;
    private JPanel panelEliminar;
    private JPanel panelBuscar;

    private JTextField idIngresarField;
    private JTextField tituloLibroIngresarField;
    private JTextField nombreUsuarioIngresarField;
    private JTextField fechaPrestamoIngresarField;

    private JTextField idActualizarField;
    private JTextField tituloLibroActualizarField;
    private JTextField nombreUsuarioActualizarField;
    private JTextField fechaPrestamoActualizarField;

    private JTextField idEliminarField;
    private JTextField idBuscarField;

    public VistaPrestamo() {
        // Configuración de la ventana
        setTitle("Gestión de Préstamos");
        setSize(600, 400);

        // Crear paneles
        panelIngresar = new JPanel();
        panelActualizar = new JPanel();
        panelEliminar = new JPanel();
        panelBuscar = new JPanel();

        // Inicializar componentes para el panel de ingreso
        idIngresarField = new JTextField(5);
        tituloLibroIngresarField = new JTextField(15);
        nombreUsuarioIngresarField = new JTextField(15);
        fechaPrestamoIngresarField = new JTextField(10);

        JButton btnIngresar = new JButton("Ingresar Préstamo");
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prestamo prestamo = ingresarDatosPrestamo();
                mostrarInformacionPrestamo(prestamo.getId(), prestamo.getLibro(), prestamo.getUsuario(),
                        prestamo.getFechaPrestamo(), prestamo.getFechaDeVolucion());
            }
        });

        // Añadir componentes al panel de ingreso
        panelIngresar.add(new JLabel("ID:"));
        panelIngresar.add(idIngresarField);
        panelIngresar.add(new JLabel("Título del libro:"));
        panelIngresar.add(tituloLibroIngresarField);
        panelIngresar.add(new JLabel("Nombre del usuario:"));
        panelIngresar.add(nombreUsuarioIngresarField);
        panelIngresar.add(new JLabel("Fecha de préstamo (yyyy-MM-dd):"));
        panelIngresar.add(fechaPrestamoIngresarField);
        panelIngresar.add(btnIngresar);

        // Inicializar componentes para el panel de actualización
        idActualizarField = new JTextField(5);
        tituloLibroActualizarField = new JTextField(15);
        nombreUsuarioActualizarField = new JTextField(15);
        fechaPrestamoActualizarField = new JTextField(10);

        JButton btnActualizar = new JButton("Actualizar Préstamo");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prestamo prestamo = actualizarDatosPrestamo();
                mostrarInformacionPrestamo(prestamo.getId(), prestamo.getLibro(), prestamo.getUsuario(),
                        prestamo.getFechaPrestamo(), prestamo.getFechaDeVolucion());
            }
        });

        // Añadir componentes al panel de actualización
        panelActualizar.add(new JLabel("ID:"));
        panelActualizar.add(idActualizarField);
        panelActualizar.add(new JLabel("Nuevo título del libro:"));
        panelActualizar.add(tituloLibroActualizarField);
        panelActualizar.add(new JLabel("Nuevo nombre del usuario:"));
        panelActualizar.add(nombreUsuarioActualizarField);
        panelActualizar.add(new JLabel("Nueva fecha de préstamo (yyyy-MM-dd):"));
        panelActualizar.add(fechaPrestamoActualizarField);
        panelActualizar.add(btnActualizar);

        // Inicializar componentes para el panel de eliminación
        idEliminarField = new JTextField(5);

        JButton btnEliminar = new JButton("Eliminar Préstamo");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = eliminarDatosPrestamo();
                mostrarAlertas("Préstamo eliminado con ID: " + id);
            }
        });

        // Añadir componentes al panel de eliminación
        panelEliminar.add(new JLabel("ID:"));
        panelEliminar.add(idEliminarField);
        panelEliminar.add(btnEliminar);

        // Inicializar componentes para el panel de búsqueda
        idBuscarField = new JTextField(5);

        JButton btnBuscar = new JButton("Buscar Préstamo");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = buscarDatosCliente();
                mostrarAlertas("Buscando préstamo con ID: " + id);
            }
        });

        // Añadir componentes al panel de búsqueda
        panelBuscar.add(new JLabel("ID:"));
        panelBuscar.add(idBuscarField);
        panelBuscar.add(btnBuscar);

        // Configuración del diseño del contenedor principal
        setLayout(new GridLayout(4, 1));
        add(panelIngresar);
        add(panelActualizar);
        add(panelEliminar);
        add(panelBuscar);
    }
    //metodos creados en la practica 3
    public Prestamo ingresarDatosPrestamo() {
        int id = Integer.parseInt(idIngresarField.getText());
        String tituloLibro = tituloLibroIngresarField.getText();
        String nombreUsuario = nombreUsuarioIngresarField.getText();
        String fechaStr = fechaPrestamoIngresarField.getText();
        Date fechaPrestamo = parseFecha(fechaStr);

        Libro libro = new Libro(0, tituloLibro, null, 0, false);
        Usuario usuario = new Usuario(null, nombreUsuario, null);

        return new Prestamo(id, libro, usuario, fechaPrestamo, null);
    }

    public Prestamo actualizarDatosPrestamo() {
        int id = Integer.parseInt(idActualizarField.getText());
        String tituloLibro = tituloLibroActualizarField.getText();
        String nombreUsuario = nombreUsuarioActualizarField.getText();
        String fechaStr = fechaPrestamoActualizarField.getText();
        Date fechaPrestamo = parseFecha(fechaStr);

        Libro libro = new Libro(0, tituloLibro, null, 0, false);
        Usuario usuario = new Usuario(null, nombreUsuario, null);

        return new Prestamo(id, libro, usuario, fechaPrestamo, null);
    }

    public int eliminarDatosPrestamo() {
        return Integer.parseInt(idEliminarField.getText());
    }

    public int buscarDatosCliente() {
        return Integer.parseInt(idBuscarField.getText());
    }

    public void mostrarInformacionPrestamo(int id, Libro libro, Usuario usuario, Date fechaPrestamo,
            Date fechaDeVolucion) {
        JOptionPane.showMessageDialog(null,
                "Datos del Préstamo: \n" + id + " - " + libro + " - " + usuario + " - " + fechaPrestamo + " - "
                        + fechaDeVolucion);
    }

    public void mostrarAlertas(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private Date parseFecha(String fechaStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha. Se utilizará la fecha actual.");
            return new Date();
        }
    }

}
