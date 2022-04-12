package com.letscode.Ecommerce.infra.filter;

import com.letscode.Ecommerce.domain.TokenUtil;
import com.letscode.Ecommerce.service.UsuarioDetalheService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AutorizacaoFilter extends OncePerRequestFilter { // WebFilter (Spring) <- Filter (javax)

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UsuarioDetalheService usuarioDetalheService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Pegar cabeçalho da requisição para validar o campo 'Authorization'
        final String requestTokenHeader = request.getHeader("Authorization");

        String jwt = null;
        String usuario = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwt = requestTokenHeader.substring(7); // Pegar a partir do 7º caractere;

            try {
                usuario = tokenUtil.getUsernameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                System.out.println("Não foi possível obter o token");
            } catch (ExpiredJwtException e) {
                System.out.println("Token expirado");
            }
        } else {
            logger.warn("Token não é do tipo 'Bearer'");
        }

        // Se o usuário ESTIVER autenticado:
        if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = usuarioDetalheService.loadUserByUsername(usuario);

            if (tokenUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken userToken=
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );

                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }

        // Aplicar filtro
        filterChain.doFilter(request, response);

    }

    /*
    // Com WebFilter
    @Override
    public reactor.core.publisher.Mono<Void> filter(ServerWebExchange exchange,
                                                    WebFilterChain chain) {
        exchange.getRequest().getHeaders();
        return null;
    }
    */
}
