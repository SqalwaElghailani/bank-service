package dcc.bank_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResponseCompteDto {
    private Integer Id;
    private String nom;
    private String tel;
    private Double sold;
}
