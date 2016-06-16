package edu.museu.infrastructure;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.Ingresso;

public class IngressoDAO {
	
	public List<Ingresso> selectAll;
	
	
	
	public long insert (Ingresso ingresso)
	{
		long idGerado= 0;
		try
		{

			Connection con = JDBCUtil.getConnection();

			String query = 
					"INSERT INTO `ingresso` "
					+ "(`ingresso_exposicao_nome`,"
					+ " `ingresso_exposicao_valor`,"
					+ " `ingresso_pagar_valor`,"
					+ " `ingresso_desconto`,"
					+ " `ingresso_meiaentrada`,"
					+ " `ingresso_gratuito`, "
					+ " `quantidade`) "
					+ "VALUES (?, ?, ?, ?,?,?,?);";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, ingresso.getNome());
			stmt.setDouble(2, ingresso.getValor());
			stmt.setDouble(3, ingresso.getValorPagar());
			stmt.setDouble(4, ingresso.getDesconto());
			stmt.setBoolean(5, ingresso.isMeiaEntrada());
			stmt.setBoolean(6, ingresso.isGratuito());
			stmt.setInt(7, ingresso.getQtdade());

			
			stmt.executeUpdate();

			ResultSet r = stmt.getGeneratedKeys();
			r.next();
			idGerado = r.getLong(1);

			JDBCUtil.close(con);
		}catch (Exception e) {
			e.printStackTrace();
		}

		
		return idGerado;
		
	}
	public int update(Ingresso ingresso) {
		
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();
			
			String query = "UPDATE `ingresso` SET `ingresso_obra_nome`=?, `ingresso_obra_valor`=?, `ingresso_pagar_valor`=?, `ingresso_meiaentrada`=?; `ingresso_gratuito`, WHERE `ingresso_id`=?";
			
			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, ingresso.getNome());
			stmt.setDouble(2, ingresso.getValor());
			stmt.setDouble(3, ingresso.getValorPagar());
			stmt.setDouble(4, ingresso.getDesconto());
			stmt.setBoolean(5, ingresso.isMeiaEntrada());
			stmt.setBoolean(6, ingresso.isGratuito());

			stmt.setLong(7, ingresso.getIngressoId());
			affectedRows = stmt.executeUpdate();

			JDBCUtil.close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

}
