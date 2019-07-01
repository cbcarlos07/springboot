package com.produto.apirest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.produto.apirest.connectionfactory.ConnectionFactory;
import com.produto.apirest.model.MensagemRetorno;
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
	
	public Produto findById( int id ) {
		conn = ConnectionFactory.getConnection();
		query = "SELECT * FROM produto WHERE id = ?";
		Produto p = null;
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			if( resultSet.next() ) {
				p = new Produto();
				p.setId( resultSet.getInt("id") );
				p.setNome( resultSet.getString("nome") );
				p.setQuantidade( resultSet.getInt("quantidade") );
				p.setValor(resultSet.getDouble("valor"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public Produto save( Produto produto) {
		conn = ConnectionFactory.getConnection();
		Produto p = null;
		query = "INSERT INTO produto VALUES(NULL, ?,?,?)";
		
		try {
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getValor());			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
			    int id =rs.getInt(1);
			    produto.setId(id);
				p = produto;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public MensagemRetorno delete( int id ) {
		MensagemRetorno mR = null;
		query = "DELETE FROM produto WHERE id = ?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
			mR = new MensagemRetorno();
			mR.setMensagem("Item removido com sucesso!");
			mR.setStatus(true);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mR;
	}
	
	public MensagemRetorno update( Produto produto ) {
		MensagemRetorno mR = null;
		
		query = "UPDATE produto SET nome = ?, quantidade = ?, valor= ? WHERE id = ?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getValor());
			stmt.setInt(4, produto.getId());
			stmt.execute();
			mR = new MensagemRetorno();
			mR.setMensagem("Item atualizado com sucesso!");
			mR.setStatus(true);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mR;
	}
}
