package africa.semicolon.uberdeluxe.config.security.filters;

import africa.semicolon.uberdeluxe.config.security.util.JwtUtil;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.exception.BusinessLogicException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UberDeluxeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException{

        ObjectMapper mapper = new ObjectMapper();

        AppUser user;
        try{
            user= mapper.readValue(request.getInputStream(), AppUser.class);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

            Authentication authenticationResult =
                    authenticationManager.authenticate(authentication);

            if(authenticationResult != null) return getAuthentication(authenticationResult);
        } catch (IOException e) {
            throw new BusinessLogicException(e.getMessage());
        }
        throw new BusinessLogicException("oops!");
    }

    private static Authentication getAuthentication(Authentication authenticationResult){
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)throws IOException, ServletException{
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> claims = authResult.getAuthorities().stream()
                .collect(Collectors.toMap(k->"claim",v->v));

        String accessToken = Jwts.builder()
                .setIssuer("uber_deluxe")
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(Date.from(Instant.now()
                        .plusSeconds(BigInteger.valueOf(60).longValue()*
                                BigInteger.valueOf(60).intValue())))
                .signWith(SignatureAlgorithm.HS512, jwtUtil.getJwtSecret())
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuer("uber_deluxe")
                .setExpiration(Date.from(Instant.now()
                        .plusSeconds(BigInteger.valueOf(24).intValue())))
                .signWith(SignatureAlgorithm.HS512, jwtUtil.getJwtSecret())
                .compact();

        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token",accessToken);
        tokens.put("refresh_token",refreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(),tokens);

    }


}
