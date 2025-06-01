package ad.GestionCatering.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String usuario, @RequestParam String pass){
        if ("usuario".equals(usuario) && "123456".equals(pass)) {
            String token = jwtTokenUtil.generateToken(usuario);
            return ResponseEntity.ok().body("Token: " + token);
        }
        return ResponseEntity.status(401).body("Usuario/contraseña incorrecta");
    }
}
