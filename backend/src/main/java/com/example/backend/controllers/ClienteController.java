package com.example.backend.controllers;

import com.example.backend.dto.LoginRequest;
import com.example.backend.models.Cliente;
import com.example.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.backend.dto.EmailRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(login.getEmail());

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();

            // Verifica a senha com o encoder
            if (passwordEncoder.matches(login.getSenha(), cliente.getSenha())) {
                return ResponseEntity.ok(cliente);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
    }

    @PostMapping(value = "/redefinir-senha", produces = "text/plain")
    public ResponseEntity<String> redefinirSenha(@RequestBody EmailRequest request) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(request.getEmail());

        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok("Acesse seu e-mail para redefinir sua senha.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("E-mail não cadastrado.");
        }
    }

    // GET /api/clientes - listar todos
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // GET /api/clientes/{id} - buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/clientes - criar novo cliente
    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            if (cliente.getSenha() == null || cliente.getSenha().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode estar vazia.");
            }

            cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
            Cliente savedCliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);

        } catch (Exception ex) {
            String message = ex.getMessage();

            if (message.contains("cpf")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("cpf já cadastrado, vá para a página de login.");
            } else if (message.contains("email")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("email já cadastrado, vá para a página de login.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao cadastrar cliente.");
            }
        }
    }

    // PUT /api/clientes/{id} - atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNome(clienteDetails.getNome());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setCpf(clienteDetails.getCpf());

            return ResponseEntity.ok(clienteRepository.save(cliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/clientes/{id} - deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}