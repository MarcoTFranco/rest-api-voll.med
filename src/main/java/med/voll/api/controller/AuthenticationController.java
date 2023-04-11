package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.user.DataAuthenticationLogin;
import med.voll.api.domain.user.DataDetailsUser;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserRepository;
import med.voll.api.infra.security.DataTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataAuthenticationLogin data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);
        userRepository.save(user);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailsUser(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid DataAuthenticationLogin data) {
        var user = userRepository.findByLogin(data.login());
        var AuthenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password(), user.getAuthorities());
        var authentication = manager.authenticate(AuthenticationToken);

        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
