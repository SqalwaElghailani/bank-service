package dcc.bank_service.service;

import dcc.bank_service.dto.RequestCompteDto;
import dcc.bank_service.dto.ResponseCompteDto;

import java.util.List;

public interface CompteService {
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto);
    public List<ResponseCompteDto> GetAllCompte();
    public ResponseCompteDto GetCompteByID(Integer id);
    public ResponseCompteDto Update_Compte(Integer id,RequestCompteDto requestCompteDto);
    public void DeleteCompteByID(Integer id);
    public ResponseCompteDto Crediter(Integer id,Double m);
    public ResponseCompteDto Debiter(Integer id,Double m);
}
