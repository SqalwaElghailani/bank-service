package dcc.bank_service.web;

import dcc.bank_service.dto.RequestCompteDto;
import dcc.bank_service.dto.ResponseCompteDto;
import dcc.bank_service.service.CompteServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des comptes bancaire",
                description = "cette offre tous les méthodes pour gérer les comptes",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8081"
        )
)
@RestController
@RequestMapping("/v1/comptes")
public class ApiRestfull {
    private CompteServiceImpl compteService;

    public ApiRestfull(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }
    @Operation(
            summary = " Ajouter un compte",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto request) {
        ResponseCompteDto response = compteService.Add_Compte(request);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = " récuperer liste des compte",

            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseCompteDto.class ))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getall() {
        List<ResponseCompteDto> compteDtos =compteService.GetAllCompte();
        return ResponseEntity.ok(compteDtos);
    }
    @Operation(
            summary = " récupérer compte par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien récuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> getCompteById(@PathVariable Integer id) {
        ResponseCompteDto responseCompteDto = compteService.GetCompteByID(id);
        return ResponseEntity.ok(responseCompteDto);
    }
    @Operation(
            summary = " Modifier un compte",
            parameters = @Parameter(name = "id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> updateCompteById(@PathVariable Integer id, @RequestBody RequestCompteDto request) {
        ResponseCompteDto responseCompteDto = compteService.Update_Compte(id, request);
        return ResponseEntity.ok(responseCompteDto);
    }
    @Operation(
            summary = " supprimer compte par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien supprimer"),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> deleteCompteById(@PathVariable Integer id) {
        compteService.DeleteCompteByID(id);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = " cette methode pour créditer un compte",
            parameters = {
                    @Parameter(name = "id",required = true),
                    @Parameter(name = "m", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = " Solde bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PatchMapping("/crediter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable Integer id, @PathVariable Double m){
        ResponseCompteDto responseCompteDTo = compteService.Crediter(id, m);
        return ResponseEntity.ok(responseCompteDTo);
    }
    @Operation(
            summary = " cette methode pour debiter un compte",
            parameters = {
                    @Parameter(name = "id",required = true),
                    @Parameter(name = "m", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = " Solde bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PatchMapping("/debiter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable Integer id, @PathVariable Double m){
        ResponseCompteDto responseCompteDTo = compteService.Debiter(id, m);
        return ResponseEntity.ok(responseCompteDTo);
    }

}
