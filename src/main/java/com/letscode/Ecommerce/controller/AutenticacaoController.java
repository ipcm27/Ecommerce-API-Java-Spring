package com.letscode.Ecommerce.controller;

import com.letscode.Ecommerce.domain.TokenUtil;
import com.letscode.Ecommerce.model.exchange.AutenticacaoRequest;
import com.letscode.Ecommerce.model.exchange.AutenticacaoResponse;
import com.letscode.Ecommerce.service.UsuarioDetalheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("login")
@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetalheService usuarioDetalheService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<AutenticacaoResponse> login (
            @RequestBody AutenticacaoRequest request
    ) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsuario(),
                            request.getSenha()
                    )
            );
        } catch (DisabledException e) {
            throw new Exception("Usuário desabilitado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais inválidas", e);
        }
        UserDetails userDetails = usuarioDetalheService
                .loadUserByUsername(request.getUsuario());

        final String token = tokenUtil.gerarToken(userDetails);

        return ResponseEntity.ok(
                AutenticacaoResponse.builder()
                        .token(token)
                        .build()
                );
    }

}
