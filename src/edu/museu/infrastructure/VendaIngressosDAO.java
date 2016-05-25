package edu.museu.infrastructure;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.museu.entity.VendaIngressos;

public class VendaIngressosDAO {
	
	public List<VendaIngressos> selectAll;
	
	
	
	public long insert (VendaIngressos ingresso)
	{
		long idGerado= 0;
		try
		{

			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO `ingresso` (`ingresso_obra_nome`, `ingresso_obra_valor`, `ingresso_pagar_valor`, `ingresso_meiaentrada`, `ingresso_gratuito`) VALUES (?, ?, ?, ?,?);";

			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, ingresso.getNomeObra());
			stmt.setDouble(2, ingresso.getValorObra());
			stmt.setDouble(3, ingresso.getValorPagar());
			stmt.setDouble(4, ingresso.getDesconto());
			stmt.setBoolean(5, ingresso.isMeiaEntrada());
			stmt.setBoolean(6, ingresso.isGratuito());

			
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
	public int update(VendaIngressos ingresso) {
		
		int affectedRows = 0;
		try {

			Connection con = JDBCUtil.getConnection();
			
			String query = "UPDATE `ingresso` SET `ingresso_obra_nome`=?, `ingresso_obra_valor`=?, `ingresso_pagar_valor`=?, `ingresso_meiaentrada`=?; `ingresso_gratuito`, WHERE `ingresso_id`=?";
			
			PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, ingresso.getNomeObra());
			stmt.setDouble(2, ingresso.getValorObra());
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
