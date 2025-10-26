package dcc.bank_service.mappers;

import dcc.bank_service.dto.RequestCompteDto;
import dcc.bank_service.dto.ResponseCompteDto;
import dcc.bank_service.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {
    public Compte Dto_To_Entity(RequestCompteDto requestCompteDto) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(requestCompteDto, compte);
        return compte;
    }
    public ResponseCompteDto Entity_To_Dto(Compte compte) {
        ResponseCompteDto  responseCompteDto = new ResponseCompteDto();
        BeanUtils.copyProperties(compte, responseCompteDto);
        return responseCompteDto ;

    }
}
