package com.example.cadastro_cliente.controller;

import com.example.cadastro_cliente.exception.ResourceNotFoundException;
import com.example.cadastro_cliente.model.Cidade;
import com.example.cadastro_cliente.model.Cliente;
import com.example.cadastro_cliente.repository.CidadeRepository;
import com.example.cadastro_cliente.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CidadeController {
	
	@Autowired
	CidadeRepository cidadeRepository;
	//Cadastrar cidade
	
	//Consultar cidade pelo nome
	
	//Consultar cidade pelo estado
	
	
    // Get All Cidade
	
	@GetMapping("/cidades")
	public List<Cidade> getAllNotes() {
	    return cidadeRepository.findAll();
	}

    // Create a new Cidade	
	@PostMapping("/cidades")
	public Cidade createCidade(@Valid @RequestBody Cidade cidade) {
	    return cidadeRepository.save(cidade);
	}

    // Get a Single Cidade
	@GetMapping("/cidades/{id}")
	public Cidade getCidadeById(@PathVariable(value = "id") Long cidadeId) {
	    return cidadeRepository.findById(cidadeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cidade", "id", cidadeId));
	}

    // Update a Cidade
	@PutMapping("/cidades/{id}")
	public Cidade updateCidade(@PathVariable(value = "id") Long cidadeId,
	                                        @Valid @RequestBody Cidade cidadeDetails) {

		Cidade cidade = cidadeRepository.findById(cidadeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cidades", "id", cidadeId));

		cidade.setNome(cidadeDetails.getNome());
		cidade.setEstado(cidadeDetails.getEstado());
	    

	    Cidade updatedCidade = cidadeRepository.save(cidade);
	    return updatedCidade;
	}
    // Delete a Cidade
	@DeleteMapping("/cidades/{id}")
	public ResponseEntity<?> deleteCidade(@PathVariable(value = "id") Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cidade", "id", cidadeId));

		cidadeRepository.delete(cidade);

	    return ResponseEntity.ok().build();
	}

}
