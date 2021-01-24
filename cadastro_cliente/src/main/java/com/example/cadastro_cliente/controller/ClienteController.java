package com.example.cadastro_cliente.controller;

import com.example.cadastro_cliente.exception.ResourceNotFoundException;
import com.example.cadastro_cliente.model.Cliente;
import com.example.cadastro_cliente.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {
	@Autowired
    ClienteRepository clienteRepository;
	
    // Get All Cliente
	@GetMapping("/clientes")
	public List<Cliente> getAllCliente() {
	    return clienteRepository.findAll();
	}
	
	
	//Consultar cliente pelo nome
	
	//Consultar cliente pelo Id
	//Remover cliente
	//Alterar o nome do cliente
	
	
	

    // Create a new Cliente	
	@PostMapping("/clientes")
	public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
		    return clienteRepository.save(cliente);
	}												

    // Get a Single Cliente
	@GetMapping("/clientes/{id}")
	public Cliente getClienteById(@PathVariable(value = "id") Long clienteId) {
	    return clienteRepository.findById(clienteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
	}

    // Update a Cliente
	@PutMapping("/clientes/{id}")
	public Cliente updateCliente(@PathVariable(value = "id") Long clienteId,
	                                        @Valid @RequestBody Cliente clienteDetails) {

		Cliente cliente = clienteRepository.findById(clienteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));

	    cliente.setCidade(clienteDetails.getCidade());
	    cliente.setData_nascimento(clienteDetails.getData_nascimento());
	    cliente.setIdade(clienteDetails.getIdade());
	    cliente.setNome_completo(cliente.getNome_completo());
	    cliente.setSexo(cliente.getSexo());

	    Cliente updatedCliente = clienteRepository.save(cliente);
	    return updatedCliente;
	}
    // Delete a Cliente
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));

	    clienteRepository.delete(cliente);

	    return ResponseEntity.ok().build();
	}
}