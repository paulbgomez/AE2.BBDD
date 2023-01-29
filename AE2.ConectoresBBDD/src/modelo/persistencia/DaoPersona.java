package modelo.persistencia;

import java.util.List;

import modelo.entidad.Persona;

public interface DaoPersona {
	public boolean alta(Persona p);
	public boolean baja(int id);
	public boolean modificar(Persona p);
	public Persona obtener(int id);
	public List<Persona> listar();
	public boolean addToCar(int idPersona, int idCoche);
	public boolean deleteFromCar(int idPersona, int idCoche);
	public List<Persona> listarPersonasCoche(int id);
}
