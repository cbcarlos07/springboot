package com.produto.apirest.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produto.apirest.controller.ProdutoController;
import com.produto.apirest.model.*;
@RestController
@RequestMapping( value="/api" )
public class ProdutoResource {
	ProdutoController pc = new ProdutoController();
	@GetMapping("/produtos")
	public List<Produto> listaProdutos(){
		List<Produto> lista = pc.findAll();
		return lista; 
	}
	
	
}
