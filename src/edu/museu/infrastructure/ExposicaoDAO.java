package edu.museu.infrastructure;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.Emprestimo;
import edu.museu.entity.Exposicao;

public class ExposicaoDAO {
	public long insert(Exposicao exposicao) throws SQLException {
		long idGerado = 0;
		try {

			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "INSERT INTO `asgardprint01`.`exposicao` " + "(`exposicao_nome`," + " `exposicao_valor`, "
					+ "`exposicao_dataInicio`," + " `exposicao_dataFim`) " + "VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, exposicao.getNome());
			stmt.setDouble(2, exposicao.getValor());
			stmt.setDate(3, new Date(exposicao.getDataInicio().getTime()));
			stmt.setDate(4, new Date(exposicao.getDataFim().getTime()));

			stmt.executeUpdate();

			ResultSet r = stmt.getGeneratedKeys();
			r.next();
			idGerado = r.getLong(1);

			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return idGerado;
	}

	public int update(Exposicao exposicao) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "UPDATE `asgardprint01`.`exposicao` " + "SET `exposicao_nome`=?," + " `exposicao_valor`=?,"
					+ " `exposicao_dataInicio`=?," + " `exposicao_dataFim`=?" + " WHERE  `exposicao_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, exposicao.getNome());
			stmt.setDouble(2, exposicao.getValor());
			stmt.setDate(3, new Date(exposicao.getDataInicio().getTime()));
			stmt.setDate(4, new Date(exposicao.getDataFim().getTime()));
			stmt.setLong(4, exposicao.getId());

			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	public int delete(long id) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "DELETE FROM `exposicao` WHERE `exposicao_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, id);

			affectedRows = stmt.executeUpdate();

			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	public Exposicao selectById(long id) {
		Exposicao exposicao = new Exposicao();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM exposicao WHERE exposicao_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				exposicao.setId(rs.getInt("exposicao_id"));
				exposicao.setNome(rs.getString("exposicao_nome"));
				exposicao.setValor(rs.getDouble("exposicao_valor"));
				exposicao.setDataInicio(rs.getDate("exposicao_dataInicio"));
				exposicao.setDataFim(rs.getDate("exposicao_dataFim"));

			}
			JDBCUtil.getInstancia().getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exposicao;
	}

	public List<Exposicao> selectByName(String name) {
		List<Exposicao> exposicoes = new ArrayList<Exposicao>();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM exposicao WHERE exposicao_nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, "%" + name + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Exposicao exposicao = new Exposicao();
				exposicao.setId(rs.getInt("exposicao_id"));
				exposicao.setNome(rs.getString("exposicao_nome"));
				exposicao.setValor(rs.getDouble("exposicao_valor"));
				exposicao.setDataInicio(rs.getDate("exposicao_dataInicio"));
				exposicao.setDataFim(rs.getDate("exposicao_dataFim"));

				exposicoes.add(exposicao);
			}
			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exposicoes;
	}

	public List<Exposicao> selectAll() {
		List<Exposicao> exposicoes = new ArrayList<Exposicao>();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM exposicao";
			PreparedStatement stmt = con.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Exposicao exposicao = new Exposicao();
				exposicao.setId(rs.getInt("exposicao_id"));
				exposicao.setNome(rs.getString("exposicao_nome"));
				exposicao.setValor(rs.getDouble("exposicao_valor"));
				exposicao.setDataInicio(rs.getDate("exposicao_dataInicio"));
				exposicao.setDataFim(rs.getDate("exposicao_dataFim"));

				exposicoes.add(exposicao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCUtil.getInstancia().close();
		return exposicoes;
	}

	public List<Exposicao> selectAllToBuy() {
		List<Exposicao> exposicoes = new ArrayList<Exposicao>();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM exposicao WHERE `exposicao_dataFim` > ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setDate(1, new Date(new java.util.Date().getTime()));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Exposicao exposicao = new Exposicao();
				exposicao.setId(rs.getInt("exposicao_id"));
				exposicao.setNome(rs.getString("exposicao_nome"));
				exposicao.setValor(rs.getDouble("exposicao_valor"));
				exposicao.setDataInicio(rs.getDate("exposicao_dataInicio"));
				exposicao.setDataFim(rs.getDate("exposicao_dataFim"));

				exposicoes.add(exposicao);
			}
			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exposicoes;
	}

}
