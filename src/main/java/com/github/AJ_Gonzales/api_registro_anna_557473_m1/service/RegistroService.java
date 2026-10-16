package com.github.AJ_Gonzales.api_registro_anna_557473_m1.service;

import com.github.AJ_Gonzales.api_registro_anna_557473_m1.dto.RegistroRequestDto;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.dto.RegistroResponseDto;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.entities.RegistroMeteorologico;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.exceptions.DatabaseException;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.exceptions.ResourceNotFoundException;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.repositories.RegistroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Transactional(readOnly = true)
    public List<RegistroResponseDto> findAllRegistros(){

        return  registroRepository.findAll()
                .stream().map(RegistroResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public RegistroResponseDto findRegistroById(Long id){

        RegistroMeteorologico registro = registroRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );

        return new RegistroResponseDto(registro);
    }

    @Transactional
    public RegistroResponseDto saveRegistro(RegistroRequestDto inputDto){

        RegistroMeteorologico registro = new RegistroMeteorologico();
        copyDtoToRegistro(inputDto, registro);
        registro = registroRepository.save(registro);
        return new RegistroResponseDto(registro);
    }

    @Transactional
    public RegistroResponseDto updateRegistro(Long id, RegistroRequestDto inputDto){

        try {
            RegistroMeteorologico registro = registroRepository.getReferenceById(id);
            copyDtoToRegistro(inputDto, registro);
            registro = registroRepository.save(registro);
            return new RegistroResponseDto(registro);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteRegistroById(Long id){

        if(!registroRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        try {
            registroRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir registro.");
        }
    }

    private void copyDtoToRegistro(RegistroRequestDto inputDto, RegistroMeteorologico registro) {

        registro.setEstacaoNome(inputDto.getEstacaoNome());
        registro.setDataRegistro(inputDto.getDataRegistro());
        registro.setTemperatura(inputDto.getTemperatura());
        registro.setPercentualUmidade(inputDto.getPercentualUmidade());
        registro.setPrecipitacao(inputDto.getPrecipitacao());
        registro.setCondicaoClimatica(inputDto.getCondicaoClimatica());
    }

}
