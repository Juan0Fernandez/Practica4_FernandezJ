package ec.edu.ups.practica4.controlador;

import java.util.List;

import ec.edu.ups.practica4.dao.LibroDao;
import ec.edu.ups.practica4.dao.UsuarioDao;
import ec.edu.ups.practica4.idao.ILibroDao;
import ec.edu.ups.practica4.idao.IUsuarioDao;
import ec.edu.ups.practica4.modelo.Libro;
import ec.edu.ups.practica4.modelo.Prestamo;
import ec.edu.ups.practica4.modelo.Usuario;
import ec.edu.ups.practica4.vista.VistaLibro;
import ec.edu.ups.practica4.vista.VistaUsuario;

public class LibroControlador {

	private ILibroDao libroDao;
	private VistaLibro vistaLibro;
	private Libro libro;

    // Constructor
    public LibroControlador(ILibroDao libroDao, VistaLibro vistaLibro) {
        super();
        this.libroDao = libroDao;
        this.vistaLibro = vistaLibro;
    }

  /*//Agrego una lista de libros a la biblioteca
  	LibroDao.create(new ec.edu.ups.practica3.modelo.Libro(1,"El señor de los anillos", "J.R.R. Tolkien", 1954, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(2,"Cien años de soledad", "Gabriel García Márquez", 1967, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(3,"To Kill a Mockingbird", "Harper Lee", 1960, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(4,"The Great Gatsby", "JF. Scott Fitzgerald", 1925, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(5,"One Hundred Years of Solitude", "Gabriel García Márquez", 1967, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(6,"Brave New World", "Aldous Huxley", 1932, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(7,"The Catcher in the Rye", "J.D. Salinger", 1951, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(8,"The Lord of the Rings", "J.R.R. Tolkien", 1955, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(9,"Pride and Prejudice", "Jane Austen", 1954, true));
  	libroDao.create(new ec.edu.ups.practica3.modelo.Libro(10,"The Hobbit", "J.R.R. Tolkien", 1937, true));
       */  
    
	public void crearLibro() {
		libro = vistaLibro.ingresarDatosLibro();
		libroDao.create(libro);
	}

	public void actualizarLibro() {
		libro = vistaLibro.actualizarDatosLibro();
		if (libroDao.update(libro.getId(), libro) == true) {
			vistaLibro.mostrarAlertas("Libro actualizado correctamente!");
		} else {
			vistaLibro.mostrarAlertas("Ha ocurrido un error!");
		}
	}

	public void eliminarLibro() {
		int id = vistaLibro.eliminarDatosLibro();
		if (libroDao.delete(id) == true) {
			vistaLibro.mostrarAlertas("Libro eliminado correctamente!");
		} else {
			vistaLibro.mostrarAlertas("Ha ocurrido un error!");
		}
	}

	public void buscarLibroPorId() {
		int id = vistaLibro.buscarDatosLibro();
		libro = libroDao.read(id);
		if (libro == null) {
			vistaLibro.mostrarAlertas("Libro no encontrado");
		} else {
			vistaLibro.mostrarInformacionLibro(libro.getId(), libro.getTitulo(), libro.getAutor(), libro.getAño(),
					libro.isDisponible());
		}
	}

	public void listarLibros() {
		List<Libro> listaLibro = libroDao.findAll();
	    for (Libro libro : listaLibro) {
	        vistaLibro.mostrarInformacionLibro(libro.getId(), libro.getTitulo(), libro.getAutor(), libro.getAño(), libro.isDisponible());
	    }
	}

	public void Prestar(VistaUsuario vistaUsuario, IUsuarioDao usuarioDao, BibliotecaControlador bibliotecaControlador) {
	    // Mostrar la lista de libros antes de realizar el préstamo
	    System.out.println("Lista de Libros Disponibles:");
	    listarLibros();

	    // Solicitar el ID del libro para el préstamo
	    int idLibro = vistaLibro.libroPrestar(libroDao.findAll());
	    Libro libroPrestado = libroDao.prestar(idLibro);

	    if (libroPrestado != null) {
	        // Mostrar la lista de usuarios
	        vistaUsuario.mostrarListaUsuarios(usuarioDao.findAll());

	        // Solicitar al usuario que elija uno
	        String identificacionUsuario = vistaUsuario.solicitarUsuarioPrestamo(usuarioDao.findAll());
	        Usuario usuario = usuarioDao.read(identificacionUsuario);

	        if (usuario != null) {
	            // Obtener la lista de préstamos actual de la biblioteca
	            List<Prestamo> prestamos = bibliotecaControlador.obtenerPrestamos();

	            // B:
	            // Verificar si el libro ya está prestado
	            boolean libroYaPrestado = prestamos.stream().anyMatch(p -> p.getLibro().getId() == idLibro);

	            if (!libroYaPrestado) {
	                // C:
	                // Registrar el préstamo en la biblioteca
	                bibliotecaControlador.registrarPrestamo(idLibro, libroPrestado, usuario);
	                
	                // Mostrar la información del préstamo
	                vistaLibro.mostrarAlertas("Libro prestado correctamente a " + usuario.getNombre() + ": " + libroPrestado.getTitulo());
	            } else {
	                vistaLibro.mostrarAlertas("Error: El libro ya está prestado.");
	            }
	        } else {
	            vistaLibro.mostrarAlertas("Usuario no encontrado. No se pudo realizar el préstamo.");
	        }
	    } else {
	        vistaLibro.mostrarAlertas("No se pudo prestar el libro. Verifica el ID o la disponibilidad.");
	    }
	}
	public void mostrarLibrosPrestados(IUsuarioDao usuarioDao) {
		List<Libro> librosPrestados = libroDao.findAll();
	    if (librosPrestados.isEmpty()) {
	        vistaLibro.mostrarAlertas("No hay libros prestados actualmente.");
	    } else {
	        vistaLibro.mostrarAlertas("Lista de Libros Prestados:");
	        for (Libro libro : librosPrestados) {
	            // Verificar que el libro tenga un usuario de préstamo
	            if (libro.getIdUsuarioPrestamo() != null) {
	                // Obtener el usuario de préstamo
	                Usuario usuario = usuarioDao.read(libro.getIdUsuarioPrestamo());
	                if (usuario != null) {// Verificar que el usuario sea diferente de null
	                    // Mostrar la información del libro prestado
	                    vistaLibro.mostrarInformacionLibroPrestado(libro.getId(), libro.getTitulo(), libro.getAutor(), libro.getAño(), usuario.getNombre());
	                } else {
	                    // Manejar el caso en que el usuario no pueda ser encontrado
	                    vistaLibro.mostrarAlertas("No se encontró el usuario del libro prestado.");
	                }
	            } else {
	                // Manejar el caso en que el libro no tenga un usuario de préstamo
	                vistaLibro.mostrarAlertas("El libro no tiene un usuario de préstamo.");
	            }
	        }
	    }
	}
	public void Devolver(IUsuarioDao usuarioDao, BibliotecaControlador bibliotecaControlador) {
	    // Mostrar la lista de libros prestados antes de realizar la devolución
	    bibliotecaControlador.mostrarLibrosPrestados();
	    
	    // Solicitar el ID del libro para la devolución
	    int idLibro = vistaLibro.libroDevolver(bibliotecaControlador.obtenerPrestamos());
	    Libro libroPrestado = libroDao.devolver(idLibro);

	    if (libroPrestado != null) {
	        // Mostrar la información del libro devuelto
	        vistaLibro.mostrarAlertas("Libro devuelto correctamente: " + libroPrestado.getTitulo());
	    } else {
	        vistaLibro.mostrarAlertas("No se pudo devolver el libro. Verifica el ID o la disponibilidad.");
	    }
	}

}
