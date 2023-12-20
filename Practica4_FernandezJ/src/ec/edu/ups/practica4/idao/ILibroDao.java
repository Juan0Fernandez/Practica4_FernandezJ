package ec.edu.ups.practica4.idao;

import java.util.List;

import ec.edu.ups.practica4.modelo.Libro;

public interface ILibroDao extends IPrestableDao {

	//Métodos CRU
    public void create(Libro libro);
    public Libro read(int id);
    public boolean update(int id,Libro libro);
    public boolean delete(int id);   
    public List<Libro> findAll();
}
