package edu.museu.infrastructure;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.ExposicaoListaAlteracaoEntity;
import edu.museu.entity.IntermediarioExposicaoEntity;

public class IntermediarioExposicaoDAO {
//INSERT INTO `asgardprint01`.`Intermediaria_exposicao` (`exposicao_id`, `obra_id`) VALUES (1, 1);
	
	public long insert(List<IntermediarioExposicaoEntity> listaObras) throws SQLException{
		long idGerado = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO `asgardprint01`.`Intermediaria_exposicao`"
					+ " (`exposicao_id`, `obra_id`) "
					+ "VALUES (?, ?)";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			for(IntermediarioExposicaoEntity item:
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
	public long insertByRow(ExposicaoListaAlteracaoEntity item) throws SQLException{
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
	public List<IntermediarioExposicaoEntity> selectById(Long id) {		
		List<IntermediarioExposicaoEntity> lista = 
				new ArrayList<IntermediarioExposicaoEntity>();
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM Intermediaria_exposicao" + 
					" WHERE exposicao_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				IntermediarioExposicaoEntity entity = new IntermediarioExposicaoEntity();
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
