package org.manu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
public class HomeController {

  @GetMapping("/")
  public String home(@AuthenticationPrincipal OAuth2User principal) {
    if (principal != null) {
      return "Bienvenue, " + principal.getAttribute("name") + " !";
    }
    return "Bienvenue sur l'application Spring Boot sécurisée avec OAuth2 !";
  }
}
