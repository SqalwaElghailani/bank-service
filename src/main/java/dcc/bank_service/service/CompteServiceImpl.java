package dcc.bank_service.service;

import dcc.bank_service.dto.RequestCompteDto;
import dcc.bank_service.dto.ResponseCompteDto;
import dcc.bank_service.entities.Compte;
import dcc.bank_service.mappers.CompteMapper;
import dcc.bank_service.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {
    private CompteRepository compteRepository;
    private CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto) {
        Compte compte = compteMapper.Dto_To_Entity(requestCompteDto);
        Compte saved_compte = compteRepository.save(compte);

        return compteMapper.Entity_To_Dto(saved_compte);
    }

    @Override
    public List<ResponseCompteDto> GetAllCompte() {
        List<Compte> comptes = compteRepository.findAll();
        List<ResponseCompteDto> compteDtos = new ArrayList<>();
        for (Compte compte : comptes) {
            compteDtos.add(compteMapper.Entity_To_Dto(compte));
        }
        return compteDtos;
    }

    @Override
    public ResponseCompteDto GetCompteByID(Integer id) {
        Compte compte =compteRepository.findById(id).orElseThrow();
        return compteMapper.Entity_To_Dto(compte);
    }

    @Override
    public ResponseCompteDto Update_Compte(Integer id, RequestCompteDto requestCompteDto) {
        Compte new_compte = compteMapper.Dto_To_Entity(requestCompteDto);
        Compte compte = compteRepository.findById(id).orElseThrow();
        if(new_compte.getNom()!=null){
            compte.setNom(new_compte.getNom());
        }
        if (new_compte.getTel()!=null){
            compte.setTel(new_compte.getTel());
        }
        if (new_compte.getSold()!=null){
            compte.setSold(new_compte.getSold());
        }

        Compte saved_compte = compteRepository.save(compte);
        return compteMapper.Entity_To_Dto(saved_compte);
    }

    @Override
    public void DeleteCompteByID(Integer id) {
        compteRepository.deleteById(id);
    }

    @Override
    public ResponseCompteDto Crediter(Integer id, Double m) {
       Compte compte = compteRepository.findById(id).orElseThrow();
       compte.setSold(compte.getSold()+m);
       Compte saved_compte = compteRepository.save(compte);
        return compteMapper.Entity_To_Dto(saved_compte);
    }

    @Override
    public ResponseCompteDto Debiter(Integer id, Double m) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        if (compte.getSold() >= m ){
            compte.setSold(compte.getSold()-m);
        }
        Compte saved_compte = compteRepository.save(compte);

        return compteMapper.Entity_To_Dto(saved_compte);
    }
}
