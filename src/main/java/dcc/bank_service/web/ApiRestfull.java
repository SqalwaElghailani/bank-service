package dcc.bank_service.web;

import dcc.bank_service.dto.RequestCompteDto;
import dcc.bank_service.dto.ResponseCompteDto;
import dcc.bank_service.service.CompteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comptes")
public class ApiRestfull {
    private CompteServiceImpl compteService;

    public ApiRestfull(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }
    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto request) {
        ResponseCompteDto response = compteService.Add_Compte(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getall() {
        List<ResponseCompteDto> compteDtos =compteService.GetAllCompte();
        return ResponseEntity.ok(compteDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> getCompteById(@PathVariable Integer id) {
        ResponseCompteDto responseCompteDto = compteService.GetCompteByID(id);
        return ResponseEntity.ok(responseCompteDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> updateCompteById(@PathVariable Integer id, @RequestBody RequestCompteDto request) {
        ResponseCompteDto responseCompteDto = compteService.Update_Compte(id, request);
        return ResponseEntity.ok(responseCompteDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> deleteCompteById(@PathVariable Integer id) {
        compteService.DeleteCompteByID(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/crediter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable Integer id, @PathVariable Double m){
        ResponseCompteDto responseCompteDTo = compteService.Crediter(id, m);
        return ResponseEntity.ok(responseCompteDTo);
    }
    @PatchMapping("/debiter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable Integer id, @PathVariable Double m){
        ResponseCompteDto responseCompteDTo = compteService.Debiter(id, m);
        return ResponseEntity.ok(responseCompteDTo);
    }

}
