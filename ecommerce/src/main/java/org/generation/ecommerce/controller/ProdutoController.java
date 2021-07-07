package org.generation.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.generation.ecommerce.model.Produto;
import org.generation.ecommerce.repository.ProdutoRepository;
import org.generation.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produto;

	@Autowired
	private ProdutoService serviceProduto;

	@GetMapping("/todos")
	public ResponseEntity<List<Produto>> buscarTodos() {
		return ResponseEntity.ok(produto.findAll());
	}

	@GetMapping("/buscar/id/{id_produto}")
	public ResponseEntity<ResponseEntity<Produto>> getID(@Valid @PathVariable Long id_produto) {
		return ResponseEntity.ok(serviceProduto.buscarPorId(id_produto));
	}

	@GetMapping("/buscar/status/{status}")
	public ResponseEntity<List<Produto>> buscarStatus(@Valid @PathVariable String status) {
		return ResponseEntity.ok(produto.findAllByStatusContainingIgnoreCase(status));
	}

	@GetMapping("/buscar/endereco/{endereco}")
	public ResponseEntity<List<Produto>> buscarEndereco(@Valid @PathVariable String endereco) {
		return ResponseEntity.ok(produto.findAllByEnderecoContainingIgnoreCase(endereco));
	}

	@GetMapping("/buscar/descricao/{descricao}")
	public ResponseEntity<List<Produto>> buscarDescricao(@Valid @PathVariable String descricao) {
		return ResponseEntity.ok(produto.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> salvarproduto(@Valid @RequestBody Produto produto1) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produto.save(produto1));
	}

	@PutMapping
	public ResponseEntity<Produto> alterar(@Valid @RequestBody Produto produto1) {
		return ResponseEntity.ok(produto.save(produto1));
	}

	@DeleteMapping("/deletar/{id}")
	public void delete(@Valid @PathVariable Long id) {
		produto.deleteById(id);
	}

	/*
	 * @GetMapping("/data_retirada/{data_retirada}") public
	 * ResponseEntity<List<Produto>> buscarData_retirada(@PathVariable String
	 * data_retirada) { return
	 * ResponseEntity.ok(produto.findAllByData_RetiradaContainingIgnoreCase(
	 * data_retirada)); }
	 */

	/*
	 * @GetMapping("/quantidade/{quantidade}") public ResponseEntity<List<Produto>>
	 * buscarQuantidade(@PathVariable Long quantidade) { return
	 * ResponseEntity.ok(produto.findAllByQuantidadeContainingIgnoreCase(quantidade)
	 * ); }
	 */

}