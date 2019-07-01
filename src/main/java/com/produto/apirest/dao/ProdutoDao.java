package com.produto.apirest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.produto.apirest.connectionfactory.ConnectionFactory;
import com.produto.apirest.model.Produto;

public class ProdutoDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet resultSet;
	private String query;
	public List<Produto> findAll(){
		List<Produto> lista = new ArrayList<>();
		conn = ConnectionFactory.getConnection();
		query = "SELECT * FROM produto";
		try {
			stmt = conn.prepareStatement(query);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Produto p = new Produto();
				p.setId( resultSet.getInt("id") );
				p.setNome( resultSet.getString("nome") );
				p.setQuantidade( resultSet.getInt("quantidade") );
				p.setValor(resultSet.getDouble("valor"));
				lista.add(p);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}
}
