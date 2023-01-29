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

public class DaoCocheMySql implements DaoCoche{
	
	private Connection con;
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:8889/AE2BBDD";
		String usuario = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alta(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
        String query = "INSERT INTO COCHES (MATRICULA, MODELO, COLOR, MARCA) VALUES (?, ?, ?, ?)";
        
        try {
			PreparedStatement pStatement = con.prepareStatement(query);
			pStatement.setString(1, c.getMatricula());
			pStatement.setString(2, c.getModelo());
			pStatement.setString(3, c.getColor());
			pStatement.setString(4, c.getMarca());
			
			// ejecutamos la query
			int numeroFilasAfectadas = pStatement.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally {
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
		
        String query = "DELETE FROM COCHES WHERE ID = ?";
        
        try {
			PreparedStatement pStatement = con.prepareStatement(query);
			pStatement.setInt(1, id);
			
			// ejecutamos la query
			int numeroFilasAfectadas = pStatement.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al borrar coche con id: " + id);
			borrado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
        
		return borrado;
	}

	@Override
	public boolean modificar(Coche c) {
		if (!abrirConexion())
			return false;
		
		boolean modif = true;
		
		String query = "UPDATE COCHES SET MATRICULA=? MODELO=? COLOR=? MARCA=? WHERE ID=?";
		
		try {
			PreparedStatement pStatement = con.prepareStatement(query);
			pStatement.setString(1, c.getMatricula());
			pStatement.setString(2, c.getModelo());
			pStatement.setString(3, c.getColor());
			pStatement.setString(4, c.getMarca());
			pStatement.setInt(5, c.getId());
			
			int numeroFilasAfectadas = pStatement.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modif = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error al modificar");
			modif = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return false;
	}

	@Override
	public Coche obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		
		String query = "SELECT * FROM COCHES WHERE ID = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setColor(rs.getString(4));
				coche.setMarca(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("error al obtener coche");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return coche;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> coches = new ArrayList<>();
		
		String query = "select * from coches";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setColor(rs.getString(4));
				coche.setMarca(rs.getString(4));
				
				coches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("error al obtener coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return coches;
	}

}
