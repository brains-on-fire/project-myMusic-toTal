package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUsuarioService {


    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;


    public Optional<List<UserType>> findAll() {
        return Optional.of(tipoUsuarioRepository.findAll());
    }
}
