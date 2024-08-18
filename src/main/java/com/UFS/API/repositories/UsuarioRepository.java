package com.UFS.API.repositories;


import com.UFS.API.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByCpf(Long cpf);

}
