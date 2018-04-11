package com.fafica.web.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fafica.web.api.model.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class HelloFaficaRest {

	@Autowired
	private PessoaRepository pr;

	@GetMapping
	public List<Pessoa> listar() {
		return pr.findAll();
	}

	/*
	 * @PostMapping
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public void novaPessoa(@RequestBody
	 * Pessoa p) { Pessoa pSalva = pessoaRepository.save(p); }
	 */

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> novaPessoa(@RequestBody Pessoa p) {
		Pessoa pSalva = pr.save(p);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pSalva.getId())
				.toUri();

		return ResponseEntity.created(uri).body(pSalva);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		pr.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id, @Valid @RequestBody Pessoa p){
		Optional<Pessoa> pSalva = pr.findById(id);
		pr.save(pSalva.get());
		return ResponseEntity.ok(pSalva.get());
	}
	
}

/*
 * @GetMapping public Pessoa listarPessoa(@PathVariable(id)Long id){ return
 * pr.findById(id).get(); }
 */
