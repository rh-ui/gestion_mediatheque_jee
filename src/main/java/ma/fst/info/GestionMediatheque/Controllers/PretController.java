package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.fst.info.GestionMediatheque.Models.Prets;
import ma.fst.info.GestionMediatheque.Service.PretService;

@RestController
@RequestMapping("/prets")
public class PretController {

    @Autowired
    PretService pretsService;

    

}
