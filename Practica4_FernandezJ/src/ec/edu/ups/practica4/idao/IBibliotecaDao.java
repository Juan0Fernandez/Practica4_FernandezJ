package ec.edu.ups.practica4.idao;

import java.util.List;

import ec.edu.ups.practica4.modelo.Biblioteca;

public interface IBibliotecaDao {

	public Biblioteca obtenerBiblioteca(int id);
	public void crearBiblioteca(Biblioteca biblioteca);
    public boolean actualizarBiblioteca(int id, Biblioteca biblioteca);
    public boolean eliminarBiblioteca(int id);
    
}
