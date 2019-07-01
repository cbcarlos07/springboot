package com.produto.apirest.controller;

import java.util.List;

import com.produto.apirest.dao.ProdutoDao;
import com.produto.apirest.model.MensagemRetorno;
import com.produto.apirest.model.Produto;
public class ProdutoController {
	ProdutoDao pd = new ProdutoDao();
	public List<Produto> findAll(){
		List<Produto> lista = pd.findAll();
		return lista;
	}
	public Produto findById( int id ) {
		Produto p = pd.findById(id);
		return p;
	}
	
	public Produto save( Produto produto) {
		Produto p = pd.save(produto);
		return p;
	}
	
	public MensagemRetorno delete( int id ) {
		MensagemRetorno mr = pd.delete(id);
		return mr;
	}
	
	public MensagemRetorno update( Produto produto ) {
		MensagemRetorno mr = pd.update(produto);
		return mr;
	}
}
