package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.api.v1.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Suggestion;

@RestController
@RequestMapping("/")
public class FirstRestController {

    @GetMapping
    public ResponseEntity<?> firstAPIGet() {
        System.out.println("GET: FirstRestController!");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> firstAPIPost(@RequestBody Suggestion suggestion) {
        System.out.println("POST: FirstRestController!");
        System.out.println("Body: " + suggestion);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> firstAPIDelete(@PathVariable("id") Integer id) {
        System.out.println("DELETE: FirstRestController!");
        System.out.println("ID: " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> firstAPIPut(@PathVariable("id") Integer id,
            @RequestBody Suggestion suggestion) {
        System.out.println("PUT: FirstRestController!");
        System.out.println("ID: " + id);
        System.out.println("Body: " + suggestion);

        if (suggestion.getId().equals(100)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
