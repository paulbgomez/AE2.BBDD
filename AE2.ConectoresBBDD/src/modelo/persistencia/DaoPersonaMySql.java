package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Persona;
import modelo.persistencia.DaoPersona;

public class DaoPersonaMySql implements DaoPersona{

	private Connection conexion;
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:8889/AE2BBDD";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean alta(Persona p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into personas (NOMBRE,EDAD,PESO) values(?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean baja(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from personas where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Persona p) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update personas set NOMBRE=?, EDAD=?, "
				+ "PESO=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			ps.setInt(4, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar la "
					+ " persona " + p);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Persona obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Persona persona = null;
		
		String query = "select ID,NOMBRE,EDAD,PESO from personas "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				persona = new Persona();
				persona.setId(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setEdad(rs.getInt(3));
				persona.setPeso(rs.getDouble(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener la "
					+ "persona con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return persona;
	}

	@Override
	public List<Persona> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Persona> listaPersonas = new ArrayList<>();
		
		String query = "select ID,NOMBRE,EDAD,PESO from personas";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Persona persona = new Persona();
				persona.setId(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setEdad(rs.getInt(3));
				persona.setPeso(rs.getDouble(4));
				
				listaPersonas.add(persona);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las "
					+ "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPersonas;
	}

	@Override
	public boolean addToCar(int idPersona, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		
		Persona persona = this.obtener(idPersona);
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFromCar(int idPersona, int idCoche) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Persona> listarPersonasCoche(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
