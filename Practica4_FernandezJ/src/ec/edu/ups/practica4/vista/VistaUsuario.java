package ec.edu.ups.practica4.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import ec.edu.ups.practica4.modelo.Usuario;

public class VistaUsuario extends JFrame {

    private JPanel panelRegistro;
    private JPanel panelActualizar;
    private JPanel panelEliminar;
    private JPanel panelBuscar;

    private JTextField nombreRegistroField;
    private JTextField identificacionRegistroField;
    private JTextField correoRegistroField;

    private JTextField nombreActualizarField;
    private JTextField identificacionActualizarField;
    private JTextField correoActualizarField;

    private JTextField idEliminarField;
    private JTextField idBuscarField;

    public Scanner entrada;

    public VistaUsuario() {
        // Configuración de la ventana
        setTitle("Gestión de Usuarios");
        setSize(600, 400);

        // Crear paneles
        panelRegistro = new JPanel();
        panelActualizar = new JPanel();
        panelEliminar = new JPanel();
        panelBuscar = new JPanel();

        // Inicializar componentes para el panel de registro
        nombreRegistroField = new JTextField(15);
        identificacionRegistroField = new JTextField(15);
        correoRegistroField = new JTextField(15);

        JButton btnRegistro = new JButton("Registrar Usuario");
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = registroDeUsuario();
                mostrarInformacionUsuario(usuario.getNombre(), usuario.getIdentificacion(), usuario.getCorreo());
            }
        });

        // Añadir componentes al panel de registro
        panelRegistro.add(new JLabel("Nombre:"));
        panelRegistro.add(nombreRegistroField);
        panelRegistro.add(new JLabel("Identificación:"));
        panelRegistro.add(identificacionRegistroField);
        panelRegistro.add(new JLabel("Correo:"));
        panelRegistro.add(correoRegistroField);
        panelRegistro.add(btnRegistro);

        // Inicializar componentes para el panel de actualización
        nombreActualizarField = new JTextField(15);
        identificacionActualizarField = new JTextField(15);
        correoActualizarField = new JTextField(15);

        JButton btnActualizar = new JButton("Actualizar Usuario");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = actualizarDatosUsuario();
                mostrarInformacionUsuario(usuario.getNombre(), usuario.getIdentificacion(), usuario.getCorreo());
            }
        });

        // Añadir componentes al panel de actualización
        panelActualizar.add(new JLabel("Nombre a actualizar:"));
        panelActualizar.add(nombreActualizarField);
        panelActualizar.add(new JLabel("Identificación a actualizar:"));
        panelActualizar.add(identificacionActualizarField);
        panelActualizar.add(new JLabel("Correo a actualizar:"));
        panelActualizar.add(correoActualizarField);
        panelActualizar.add(btnActualizar);

        // Inicializar componentes para el panel de eliminación
        idEliminarField = new JTextField(15);

        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = eliminarDatosUsuario();
                mostrarAlertas("Usuario eliminado con identificación: " + id);
            }
        });

        // Añadir componentes al panel de eliminación
        panelEliminar.add(new JLabel("Identificación a eliminar:"));
        panelEliminar.add(idEliminarField);
        panelEliminar.add(btnEliminar);
        // Inicializar componentes para el panel de búsqueda
        idBuscarField = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar Usuario");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = buscarDatosUsuario();
                mostrarAlertas("Buscando usuario con identificación: " + id);
            }
        });
        // Añadir componentes al panel de búsqueda
        panelBuscar.add(new JLabel("Identificación a buscar:"));
        panelBuscar.add(idBuscarField);
        panelBuscar.add(btnBuscar);
        // Configuración del diseño del contenedor principal
        setLayout(new GridLayout(4, 1));
        add(panelRegistro);
        add(panelActualizar);
        add(panelEliminar);
        add(panelBuscar);
    }
    //metodos creados en la practica 3
    public Usuario registroDeUsuario() {
        String nombre = nombreRegistroField.getText();
        String identificacion = identificacionRegistroField.getText();
        String correo = correoRegistroField.getText();

        return new Usuario(nombre, identificacion, correo);
    }
    public Usuario actualizarDatosUsuario() {
        String nombre = nombreActualizarField.getText();
        String identificacion = identificacionActualizarField.getText();
        String correo = correoActualizarField.getText();

        return new Usuario(nombre, identificacion, correo);
    }
    public String eliminarDatosUsuario() {
        return idEliminarField.getText();
    }
    public String buscarDatosUsuario() {
        return idBuscarField.getText();
    }
    public void mostrarInformacionUsuario(String nombre, String identificacion, String correo) {
        JOptionPane.showMessageDialog(null, "Datos del Usuario: \n" + nombre + " - " + identificacion + " - " + correo);
    }
    public void mostrarAlertas(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

	public void mostrarListaUsuarios(List<Usuario> listaUsuarios) {
		System.out.println("------ Lista de Usuarios ------");
	    for (Usuario usuario : listaUsuarios) {
	        mostrarInformacionUsuario(usuario);
	    }
	}

	public void mostrarInformacionUsuario(Usuario usuario) {
	    System.out.println("Datos del Usuario: ");
	    System.out.println(usuario.getNombre() + " - " + usuario.getIdentificacion() + " - " + usuario.getCorreo());
	}
	public String solicitarUsuarioPrestamo(List<Usuario> listaUsuarios) {
		// Mostrar la lista de usuarios
	    System.out.println("------ Lista de Usuarios ------");
	    for (Usuario usuario : listaUsuarios) {
	        System.out.println("Identificación: " + usuario.getIdentificacion() + " - Nombre: " + usuario.getNombre());
	    }

	    // Solicitar la identificación del usuario al que se prestará el libro
	    System.out.print("Ingrese la identificación del usuario al que se prestará el libro: ");
	    Scanner scanner = new Scanner(System.in);
	    String identificacionUsuario = scanner.nextLine();

	    return identificacionUsuario;
	}
	
	
}
