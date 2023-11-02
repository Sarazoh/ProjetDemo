package com.example.demo.controleur;


import com.example.demo.presentation.vo.DepartementVO;
import com.example.demo.presentation.vo.PersonneVO;
import com.example.demo.service.DepartementService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("ws/departements")
public class DepartementControlleur {
    private final DepartementService departementService;

    public DepartementControlleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping()
    public List<DepartementVO> getDepartements(){
        return departementService.getDepartements();
    }

    @GetMapping("/code/{code}")
    public DepartementVO getDepartementByCode(@PathVariable String code){
        return departementService.getDepartementByCode(code);
    }

    @GetMapping("/{id}")
    public DepartementVO getDepartement(@PathVariable long id){
        return departementService.getDepartement(id);
    }

    @PostMapping
    public DepartementVO createDepartement(@RequestBody DepartementVO departement){
        return  departementService.createDepartement(departement);
    }

    @GetMapping("/auto-compete/{filtre}")
    public List<DepartementVO> rechercherParCodeOuDesignation(@PathVariable String filtre){
        return departementService.rechercherParCodeOuDesignation(filtre);
    }

    @PutMapping("/{id}")
    public DepartementVO updateDepartement(@PathVariable Long id, @RequestBody DepartementVO departement){
        return departementService.updateDepartement(id,departement);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable Long id){
        departementService.deleteDepartement(id);
    }



}


