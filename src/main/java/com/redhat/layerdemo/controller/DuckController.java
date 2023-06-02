package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.layerdemo.model.Duck;
import com.redhat.layerdemo.repository.DuckRepository;

@RestController
@RequestMapping("/")
public class DuckController {
    
	@Autowired
	DuckRepository duckRepository;

    @GetMapping("/ducks")
	public ResponseEntity<List<Duck>> getAllTutorials(@RequestParam(required = false) String name) {
		try {
			List<Duck> ducks = new ArrayList<Duck>();

			if (name == null)
                duckRepository.findAll().forEach(ducks::add);
			else
            duckRepository.findByNameContaining(name).forEach(ducks::add);

			if (ducks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(ducks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/ducks/{id}")
    public ResponseEntity<Duck> getDuckById(@PathVariable("id") long id) {
        Optional<Duck> duckData = duckRepository.findById(id);

        if (duckData.isPresent()) {
            return new ResponseEntity<>(duckData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
