package edu.museu.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.Visitante;

public class VisitanteDAO {

	public long insert(Visitante visitante) {
		int idadeGerado = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO `visitante` (`visitante_cpf`, `visitante_nacionalidade`, `visitante_idade`, `visitante_sexo`, `visitante_grau_instrucao`, `visitante_meio_transporte`) VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, visitante.getCpf());
			stmt.setString(2, visitante.getNacionalidade());
			stmt.setInt(3, visitante.getIdade());
			stmt.setString(4, visitante.getSexo());
			stmt.setString(5, visitante.getGrauInstrucao());
			stmt.setString(6, visitante.getMeioTransporte());

			// stmt.setBoolean(13, local.isAtivo());
			stmt.executeUpdate();

			ResultSet r = stmt.getGeneratedKeys();
			r.next();
			idadeGerado = r.getInt(1);

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return idadeGerado;
	}

	public int update(Visitante visitante) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "UPDATE `visitante` SET `visitante_cpf`=?, `visitante_nacionalidade`=?, `visitante_idade`=?, `visitante_sexo`=?, `visitante_grau_instrucao`=?, `visitante_meio_transporte`=?  WHERE  `visitante_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, visitante.getCpf());
			stmt.setString(2, visitante.getNacionalidade());
			stmt.setInt(3, visitante.getIdade());
			stmt.setString(4, visitante.getSexo());
			stmt.setString(5, visitante.getGrauInstrucao());
			stmt.setString(6, visitante.getMeioTransporte());

			stmt.setLong(7, visitante.getId());

			affectedRows = stmt.executeUpdate();

			JDBCUtil.close(con);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	public int delete(int id) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "DELETE FROM visitante WHERE visitante_id = ?";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, id);

			affectedRows = stmt.executeUpdate();

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	public int deleteByCpf(String cpf) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();

			String query = "DELETE FROM visitante WHERE visitante_cpf = ?";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, cpf);

			affectedRows = stmt.executeUpdate();

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	public Visitante selectById(int id) {
		Visitante visitante = new Visitante();
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM visitante WHERE visitante_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				visitante.setId(rs.getLong("visitante_id"));
				visitante.setCpf(rs.getString("visitante_cpf"));
				visitante.setNacionalidade(rs.getString("visitante_nacionalidade"));
				visitante.setIdade(rs.getInt("visitante_idade"));
				visitante.setSexo(rs.getString("visitante_sexo"));
				visitante.setGrauInstrucao(rs.getString("visitante_grau_instrucao"));
				visitante.setMeioTransporte(rs.getString("visitante_meio_transporte"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return visitante;
	}

	public List<Visitante> selectByCpf(String cpf) {
		List<Visitante> visitantes = new ArrayList<Visitante>();
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM visitante WHERE visitante_cpf LIKE ?;";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, "%" + cpf + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Visitante visitante = new Visitante();
				visitante.setId(rs.getLong("visitante_id"));
				visitante.setCpf(rs.getString("visitante_cpf"));
				visitante.setNacionalidade(rs.getString("visitante_nacionalidade"));
				visitante.setIdade(rs.getInt("visitante_idade"));
				visitante.setSexo(rs.getString("visitante_sexo"));
				visitante.setGrauInstrucao(rs.getString("visitante_grau_instrucao"));
				visitante.setMeioTransporte(rs.getString("visitante_meio_transporte"));

				visitantes.add(visitante);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return visitantes;
	}

	public List<Visitante> selectAll() {
		List<Visitante> visitantes = new ArrayList<Visitante>();
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM visitante";
			PreparedStatement stmt = con.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Visitante visitante = new Visitante();
				visitante.setId(rs.getLong("visitante_id"));
				visitante.setCpf(rs.getString("visitante_cpf"));
				visitante.setNacionalidade(rs.getString("visitante_nacionalidade"));
				visitante.setIdade(rs.getInt("visitante_idade"));
				visitante.setSexo(rs.getString("visitante_sexo"));
				visitante.setGrauInstrucao(rs.getString("visitante_grau_instrucao"));
				visitante.setMeioTransporte(rs.getString("visitante_meio_transporte"));

				visitantes.add(visitante);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return visitantes;
	}

}
