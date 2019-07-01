package com.produto.apirest.resources;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produto.apirest.controller.ProdutoController;
import com.produto.apirest.model.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping( value="/api" )
@Api(value="API REST Produtos")
@CrossOrigin(origins="*")
public class ProdutoResource {
	ProdutoController pc = new ProdutoController();
	
	@GetMapping("/produtos")
	@ApiOperation(value="Retorna uma lista de produtos")
	public List<Produto> listaProdutos(){
		List<Produto> lista = pc.findAll();
		return lista; 
	}
	
	@GetMapping("/produto/{id}")
	@ApiOperation(value="Retorna um produto único")
	public Produto listaProdutoUnico( @PathVariable(value="id") int id ) {
		Produto p = pc.findById(id);
		return p;
	}
	
	@PostMapping("/produto")
	@ApiOperation(value="Este método salva um produto")
	public Produto salvarProduto(@RequestBody Produto produto) {
		Produto p = pc.save(produto);
		return p;
	}
	
	@DeleteMapping("produto/{id}")
	@ApiOperation(value="Este método deleta um produto")
	public MensagemRetorno  delete( @PathVariable(value="id") int id ) {
		MensagemRetorno mr = pc.delete(id);
		return mr;
	}
	
	@PutMapping("produto")
	@ApiOperation(value="Este método atualiza um produto")
	public MensagemRetorno  atualiza(@RequestBody Produto produto ) {
		MensagemRetorno mr = pc.update(produto);
		return mr;
	}
	
}
