package edu.museu.infrastructure;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.ObraExposicaoAlteracao;
import edu.museu.entity.ObraExposicao;

public class ObraExposicaoDAO {
//INSERT INTO `asgardprint01`.`Intermediaria_exposicao` (`exposicao_id`, `obra_id`) VALUES (1, 1);
	
	public long insert(List<ObraExposicao> listaObras) throws SQLException{
		long idGerado = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO `asgardprint01`.`Intermediaria_exposicao`"
					+ " (`exposicao_id`, `obra_id`) "
					+ "VALUES (?, ?)";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			for(ObraExposicao item:
				listaObras){
			
				stmt.setLong(1, item.getExposicao_id());
				stmt.setLong(2, item.getObra_id());		
			
				stmt.executeUpdate();

				ResultSet r = stmt.getGeneratedKeys();
				r.next();
				//idGerado = r.getLong(1);
			}

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return idGerado;
	}
	public long insertByRow(ObraExposicaoAlteracao item) throws SQLException{
		long idGerado = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO `asgardprint01`.`Intermediaria_exposicao`"
					+ " (`exposicao_id`, `obra_id`) "
					+ "VALUES (?, ?)";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);			
			
				stmt.setLong(1, item.getExposicao_id());
				stmt.setLong(2, item.getObra_id());		
			
				stmt.executeUpdate();

				ResultSet r = stmt.getGeneratedKeys();
				r.next();
				//idGerado = r.getLong(1);			

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return idGerado;
	}
	public List<ObraExposicao> selectById(Long id) {		
		List<ObraExposicao> lista = 
				new ArrayList<ObraExposicao>();
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM Intermediaria_exposicao" + 
					" WHERE exposicao_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ObraExposicao entity = new ObraExposicao();
				entity.setExposicao_id(rs.getLong("exposicao_id"));
				entity.setObra_id(rs.getLong("obra_id"));				
				
				lista.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public int delete(long id) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "DELETE FROM `Intermediaria_exposicao` WHERE `exposicao_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, id);

			affectedRows = stmt.executeUpdate();

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}
	
	public int deleteByRow(long exposicao_id, long obra_id) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "DELETE FROM `Intermediaria_exposicao` WHERE `exposicao_id`=? AND `obra_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, exposicao_id);
			stmt.setLong(2, obra_id);

			affectedRows = stmt.executeUpdate();

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}
	public int deleteByObra(long obra_id) {
		int affectedRows = 0;
		try {
			
			Connection con = JDBCUtil.getConnection();
			
			String query = "DELETE FROM `Intermediaria_exposicao` WHERE `obra_id`=?;";
			
			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setLong(1, obra_id);
			
			affectedRows = stmt.executeUpdate();
			
			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}
	
	
	
}
