package com.produto.apirest.controller;

import java.util.List;

import com.produto.apirest.dao.ProdutoDao;
import com.produto.apirest.model.Produto;

public class ProdutoController {
	ProdutoDao pd = new ProdutoDao();
	public List<Produto> findAll(){
		List<Produto> lista = pd.findAll();
		return lista;
	}
}
