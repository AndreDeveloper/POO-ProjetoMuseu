package edu.museu.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.Local;

public class LocalDAO {

	public long insert(Local local) {
		long idGerado = 0;
		try {

			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "INSERT INTO `local_emprestimo` (`local_emprestimo_nome`, `local_emprestimo_email`, `local_emprestimo_telefone`, `local_emprestimo_responsavel`, `local_emprestimo_cep`, `local_emprestimo_tipo_logradouro`, `local_emprestimo_logradouro`, `local_emprestimo_numero`, `local_emprestimo_complemento`, `local_emprestimo_bairro`, `local_emprestimo_cidade`, `local_emprestimo_uf`, `local_emprestimo_ativo`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, local.getNomeLocal());
			stmt.setString(2, local.getEmail());
			stmt.setString(3, local.getTelefone());
			stmt.setString(4, local.getResponsavel());
			stmt.setString(5, local.getCep());
			stmt.setString(6, local.getTipoLogradouro());
			stmt.setString(7, local.getLogradouro());
			stmt.setString(8, local.getNumero());
			stmt.setString(9, local.getComplemento());
			stmt.setString(10, local.getBairro());
			stmt.setString(11, local.getCidade());
			stmt.setString(12, local.getUf());
			stmt.setBoolean(13, local.isAtivo());

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

	public int update(Local local) {
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "UPDATE `local_emprestimo` SET `local_emprestimo_nome`=?, `local_emprestimo_email`=?, `local_emprestimo_telefone`=?, `local_emprestimo_responsavel`=?, `local_emprestimo_cep`=?, `local_emprestimo_tipo_logradouro`=?, `local_emprestimo_logradouro`=?, `local_emprestimo_numero`=?, `local_emprestimo_complemento`=?, `local_emprestimo_bairro`=?, `local_emprestimo_cidade`=?, `local_emprestimo_uf`=?, `local_emprestimo_ativo`=? WHERE `local_emprestimo_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, local.getNomeLocal());
			stmt.setString(2, local.getEmail());
			stmt.setString(3, local.getTelefone());
			stmt.setString(4, local.getResponsavel());
			stmt.setString(5, local.getCep());
			stmt.setString(6, local.getTipoLogradouro());
			stmt.setString(7, local.getLogradouro());
			stmt.setString(8, local.getNumero());
			stmt.setString(9, local.getComplemento());
			stmt.setString(10, local.getBairro());
			stmt.setString(11, local.getCidade());
			stmt.setString(12, local.getUf());
			stmt.setBoolean(13, local.isAtivo());

			stmt.setLong(14, local.getId());

			affectedRows = stmt.executeUpdate();

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

			String query = "DELETE FROM `local_emprestimo` WHERE `local_emprestimo_id`=?;";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, id);

			affectedRows = stmt.executeUpdate();

			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	public Local selectById(long id) {
		Local local = new Local();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM local_emprestimo WHERE local_emprestimo_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				local.setId(rs.getLong("local_emprestimo_id"));
				local.setNomeLocal(rs.getString("local_emprestimo_nome"));
				local.setEmail(rs.getString("local_emprestimo_email"));
				local.setTelefone(rs.getString("local_emprestimo_telefone"));
				local.setResponsavel(rs.getString("local_emprestimo_responsavel"));
				local.setCep(rs.getString("local_emprestimo_cep"));
				local.setTipoLogradouro(rs.getString("local_emprestimo_tipo_logradouro"));
				local.setLogradouro(rs.getString("local_emprestimo_logradouro"));
				local.setNumero(rs.getString("local_emprestimo_numero"));
				local.setComplemento(rs.getString("local_emprestimo_complemento"));
				local.setBairro(rs.getString("local_emprestimo_bairro"));
				local.setCidade(rs.getString("local_emprestimo_cidade"));
				local.setUf(rs.getString("local_emprestimo_uf"));
				local.setAtivo(rs.getBoolean("local_emprestimo_ativo"));

			}
			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return local;
	}

	public List<Local> selectByName(String name) {
		List<Local> locais = new ArrayList<Local>();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM local_emprestimo WHERE local_emprestimo_nome LIKE ?;";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, "%" + name + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Local local = new Local();
				local.setId(rs.getLong("local_emprestimo_id"));
				local.setNomeLocal(rs.getString("local_emprestimo_nome"));
				local.setEmail(rs.getString("local_emprestimo_email"));
				local.setTelefone(rs.getString("local_emprestimo_telefone"));
				local.setResponsavel(rs.getString("local_emprestimo_responsavel"));
				local.setCep(rs.getString("local_emprestimo_cep"));
				local.setTipoLogradouro(rs.getString("local_emprestimo_tipo_logradouro"));
				local.setLogradouro(rs.getString("local_emprestimo_logradouro"));
				local.setNumero(rs.getString("local_emprestimo_numero"));
				local.setComplemento(rs.getString("local_emprestimo_complemento"));
				local.setBairro(rs.getString("local_emprestimo_bairro"));
				local.setCidade(rs.getString("local_emprestimo_cidade"));
				local.setUf(rs.getString("local_emprestimo_uf"));
				local.setAtivo(rs.getBoolean("local_emprestimo_ativo"));

				locais.add(local);
			}
			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return locais;
	}

	public List<Local> selectAll() {
		List<Local> locais = new ArrayList<Local>();
		try {
			Connection con = JDBCUtil.getInstancia().getConnection();

			String query = "SELECT * FROM local_emprestimo";
			PreparedStatement stmt = con.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Local local = new Local();
				local.setId(rs.getLong("local_emprestimo_id"));
				local.setNomeLocal(rs.getString("local_emprestimo_nome"));
				local.setEmail(rs.getString("local_emprestimo_email"));
				local.setTelefone(rs.getString("local_emprestimo_telefone"));
				local.setResponsavel(rs.getString("local_emprestimo_responsavel"));
				local.setCep(rs.getString("local_emprestimo_cep"));
				local.setTipoLogradouro(rs.getString("local_emprestimo_tipo_logradouro"));
				local.setLogradouro(rs.getString("local_emprestimo_logradouro"));
				local.setNumero(rs.getString("local_emprestimo_numero"));
				local.setComplemento(rs.getString("local_emprestimo_complemento"));
				local.setBairro(rs.getString("local_emprestimo_bairro"));
				local.setCidade(rs.getString("local_emprestimo_cidade"));
				local.setUf(rs.getString("local_emprestimo_uf"));
				local.setAtivo(rs.getBoolean("local_emprestimo_ativo"));

				locais.add(local);
			}
			JDBCUtil.getInstancia().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return locais;
	}
}
