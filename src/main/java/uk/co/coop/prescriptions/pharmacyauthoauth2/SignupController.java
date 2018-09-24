package uk.co.coop.prescriptions.pharmacyauthoauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @GetMapping("/")
    public String signup() {
        return "{\"message\":\"OK\"}";
    }
}
