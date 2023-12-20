package ec.edu.ups.practica4.test;

import javax.swing.*;

import ec.edu.ups.practica4.vista.VistaBiblioteca;
import ec.edu.ups.practica4.vista.VistaLibro;
import ec.edu.ups.practica4.vista.VistaPrestamo;
import ec.edu.ups.practica4.vista.VistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        // Crear un objeto JFrame (ventana principal)
        JFrame miVentana = new JFrame("Sistema de Gestión de Biblioteca");

        // Configurar la operación predeterminada al cerrar la ventana
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de VistaBiblioteca y hacerla visible
        VistaBiblioteca vistaBiblioteca = new VistaBiblioteca();
        vistaBiblioteca.setVisible(true);

        // Configurar el tamaño de la ventana
        miVentana.setSize(600, 400);

        // Crear un panel para contener los botones
        JPanel panel = new JPanel();

        // Crear botones para cada opción del menú
        JButton registrarUsuarioButton = new JButton("Registro de Usuario");
        JButton registroLibroButton = new JButton("Registro de LIbro");
        JButton buscarLibroButton = new JButton("Buscar Libro por ID");
        JButton prestarLibroButton = new JButton("Prestar Libro");
        JButton devolverLibroButton = new JButton("Devolver Libro");
        JButton salirButton = new JButton("Salir");

        // Agregar los botones al panel
        panel.add(registrarUsuarioButton);
        panel.add(buscarLibroButton);
        panel.add(prestarLibroButton);
        panel.add(devolverLibroButton);
        panel.add(salirButton);

        // Agregar el panel a la ventana
        miVentana.getContentPane().add(panel);

        // Variable booleana para verificar si la biblioteca está registrada
        boolean bibliotecaRegistrada = true;

        // ActionListener para el botón de registro de libro
        registrarUsuarioButton.addActionListener(e -> {

        	if (bibliotecaRegistrada== true) {
               VistaUsuario vista = new VistaUsuario();
               vista.setVisible(true);
               
            } else {
                JOptionPane.showMessageDialog(null, "Debe registrar la biblioteca primero");
            }
        });
        
        // ActionListener para el botón de registro de Usuario
        registroLibroButton.addActionListener(e -> {
        	if (bibliotecaRegistrada == true) {
            	VistaLibro vista = new VistaLibro();
                vista.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Debe registrar la biblioteca primero");
            }
        	
        });
        
        // ActionListener para el botón de búsqueda de libro
        buscarLibroButton.addActionListener(e -> {
        	if (bibliotecaRegistrada == true) {
            	VistaLibro vista = new VistaLibro();
                vista.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Debe registrar la biblioteca primero");
            }
        });

        // ActionListener para el botón de préstamo de libro
        prestarLibroButton.addActionListener(e -> {
            if (bibliotecaRegistrada== true) {
                    VistaPrestamo vista = new VistaPrestamo();
                    vista.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debe registrar la biblioteca primero");
            }
        });

        // ActionListener para el botón de devolución de libro
        devolverLibroButton.addActionListener(e -> {
        	if (bibliotecaRegistrada== true ) {
                SwingUtilities.invokeLater(() -> {
                    VistaPrestamo vista = new VistaPrestamo();
                    vista.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(null, "Debe registrar la biblioteca primero");
            }
        });

        // ActionListener para el botón de salir
        salirButton.addActionListener(e -> System.exit(0));

      

        // Hace que la ventana principal visible
        miVentana.setVisible(true);
    }
}