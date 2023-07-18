package sia.tacocloud.tacos.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.dto.Taco;
import sia.tacocloud.tacos.dto.TacoOrder;
import sia.tacocloud.tacos.repos.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos",produces = "application/json")
@CrossOrigin(origins = "http://localhost:8090")
public class TacoController {
    private TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params = "recent")
    @PreAuthorize("hasRole('USER')")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());

        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id")Long id){
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()){
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        System.out.println(taco.toString());
        return tacoRepo.save(taco);
    }



}
