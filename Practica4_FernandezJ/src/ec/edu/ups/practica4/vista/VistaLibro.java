package ec.edu.ups.practica4.vista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import ec.edu.ups.practica4.modelo.Libro;
import ec.edu.ups.practica4.modelo.Prestamo;

public class VistaLibro extends JFrame {

    private JPanel panelIngresar;
    private JPanel panelActualizar;
    private JPanel panelEliminar;
    private JPanel panelBuscar;
    private JPanel panelPrestar;
    private JPanel panelDevolver;

    private JTextField idIngresarField;
    private JTextField tituloIngresarField;
    private JTextField autorIngresarField;
    private JTextField añoIngresarField;
    private JTextField disponibilidadIngresarField;

    private JTextField idActualizarField;
    private JTextField tituloActualizarField;
    private JTextField autorActualizarField;
    private JTextField añoActualizarField;
    private JTextField disponibilidadActualizarField;

    private JTextField idEliminarField;
    private JTextField idBuscarField;
    private JTextField idPrestarField;
    private JTextField idDevolverField;

    public VistaLibro() {
        // Configuración de la ventana
        setTitle("Gestión de Libros");
        setSize(600, 400);

        // Crear paneles
        panelIngresar = new JPanel();
        panelActualizar = new JPanel();
        panelEliminar = new JPanel();
        panelBuscar = new JPanel();
        panelPrestar = new JPanel();
        panelDevolver = new JPanel();

        // Inicializar componentes para el panel de ingreso
        idIngresarField = new JTextField(5);
        tituloIngresarField = new JTextField(15);
        autorIngresarField = new JTextField(15);
        añoIngresarField = new JTextField(5);
        disponibilidadIngresarField = new JTextField(5);

        JButton btnIngresar = new JButton("Ingresar Libro");
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Libro libro = ingresarDatosLibro();
                mostrarAlertas("Libro ingresado: " + libro.toString());
            }
        });

        // Añadir componentes al panel de ingreso
        panelIngresar.add(new JLabel("ID:"));
        panelIngresar.add(idIngresarField);
        panelIngresar.add(new JLabel("Título:"));
        panelIngresar.add(tituloIngresarField);
        panelIngresar.add(new JLabel("Autor:"));
        panelIngresar.add(autorIngresarField);
        panelIngresar.add(new JLabel("Año:"));
        panelIngresar.add(añoIngresarField);
        panelIngresar.add(new JLabel("Disponibilidad:"));
        panelIngresar.add(disponibilidadIngresarField);
        panelIngresar.add(btnIngresar);

        // Inicializar componentes para el panel de actualización
        idActualizarField = new JTextField(5);
        tituloActualizarField = new JTextField(15);
        autorActualizarField = new JTextField(15);
        añoActualizarField = new JTextField(5);
        disponibilidadActualizarField = new JTextField(5);

        JButton btnActualizar = new JButton("Actualizar Libro");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Libro libro = actualizarDatosLibro();
                mostrarAlertas("Libro actualizado: " + libro.toString());
            }
        });

        // Añadir componentes al panel de actualización
        panelActualizar.add(new JLabel("ID:"));
        panelActualizar.add(idActualizarField);
        panelActualizar.add(new JLabel("Título:"));
        panelActualizar.add(tituloActualizarField);
        panelActualizar.add(new JLabel("Autor:"));
        panelActualizar.add(autorActualizarField);
        panelActualizar.add(new JLabel("Año:"));
        panelActualizar.add(añoActualizarField);
        panelActualizar.add(new JLabel("Disponibilidad:"));
        panelActualizar.add(disponibilidadActualizarField);
        panelActualizar.add(btnActualizar);

        // Inicializar componentes para el panel de eliminación
        idEliminarField = new JTextField(5);

        JButton btnEliminar = new JButton("Eliminar Libro");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = eliminarDatosLibro();
                mostrarAlertas("Libro eliminado con ID: " + id);
            }
        });

        // Añadir componentes al panel de eliminación
        panelEliminar.add(new JLabel("ID:"));
        panelEliminar.add(idEliminarField);
        panelEliminar.add(btnEliminar);

        // Inicializar componentes para el panel de búsqueda
        idBuscarField = new JTextField(5);

        JButton btnBuscar = new JButton("Buscar Libro");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = buscarDatosLibro();
                mostrarAlertas("Buscando libro con ID: " + id);
            }
        });

        // Añadir componentes al panel de búsqueda
        panelBuscar.add(new JLabel("ID:"));
        panelBuscar.add(idBuscarField);
        panelBuscar.add(btnBuscar);

        // Inicializar componentes para el panel de préstamo
        idPrestarField = new JTextField(5);

        JButton btnPrestar = new JButton("Prestar Libro");
        btnPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = libroPrestar(null);
                mostrarAlertas("Prestando libro con ID: " + id);
            }
        });

        // Añadir componentes al panel de préstamo
        panelPrestar.add(new JLabel("ID:"));
        panelPrestar.add(idPrestarField);
        panelPrestar.add(btnPrestar);

        // Inicializar componentes para el panel de devolución
        idDevolverField = new JTextField(5);

        JButton btnDevolver = new JButton("Devolver Libro");
        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = libroDevolver();
                mostrarAlertas("Devolviendo libro con ID: " + id);
            }
        });

        // Añadir componentes al panel de devolución
        panelDevolver.add(new JLabel("ID:"));
        panelDevolver.add(idDevolverField);
        panelDevolver.add(btnDevolver);

        // Configurar layout del frame
        setLayout(new GridLayout(6, 1));

        // Añadir paneles al frame
        add(panelIngresar);
        add(panelActualizar);
        add(panelEliminar);
        add(panelBuscar);
        add(panelPrestar);
        add(panelDevolver);
    }
    //Metodos creados en la practica anterior 
    public Libro ingresarDatosLibro() {
        int id = Integer.parseInt(idIngresarField.getText());
        String titulo = tituloIngresarField.getText();
        String autor = autorIngresarField.getText();
        int año = Integer.parseInt(añoIngresarField.getText());
        boolean disponibilidad = Boolean.parseBoolean(disponibilidadIngresarField.getText());

        return new Libro(id, titulo, autor, año, disponibilidad);
    }

    public Libro actualizarDatosLibro() {
        int id = Integer.parseInt(idActualizarField.getText());
        String titulo = tituloActualizarField.getText();
        String autor = autorActualizarField.getText();
        int año = Integer.parseInt(añoActualizarField.getText());
        boolean disponibilidad = Boolean.parseBoolean(disponibilidadActualizarField.getText());

        return new Libro(id, titulo, autor, año, disponibilidad);
    }

    public int eliminarDatosLibro() {
        return Integer.parseInt(idEliminarField.getText());
    }

    public int buscarDatosLibro() {
        return Integer.parseInt(idBuscarField.getText());
    }

    public int libroPrestar(List<Libro> list) {
        return Integer.parseInt(idPrestarField.getText());
    }

    public int libroDevolver() {
        return Integer.parseInt(idDevolverField.getText());
    }

    public static void mostrarAlertas(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

	public void mostrarInformacionLibro(int id, String titulo, String autor, int año, boolean disponible) {
		 System.out.println("Datos del Libro: \n" + id + " - " + titulo + " - \n" + autor +" - " + año+" - " + disponible );
		  
	}
	public void mostrarInformacionLibroPrestado(int id, String titulo, String autor, int año, String nombreUsuario) {
	    System.out.println("Datos del Libro Prestado: \n" + id + " - " + titulo + " - \n" + autor + " - " + año + " - Prestado a: " + nombreUsuario);
	}
	public int libroDevolver(List<Prestamo> prestamos) {
		return Integer.parseInt(idDevolverField.getText());
	}
		
}