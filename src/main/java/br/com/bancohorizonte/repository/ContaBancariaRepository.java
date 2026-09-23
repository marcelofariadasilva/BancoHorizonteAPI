package br.com.bancohorizonte.repository;

import br.com.bancohorizonte.entity.ContaBancaria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> { 
    boolean existsByAgenciaAndNumero(String agencia, String numero); 
    
    @EntityGraph(attributePaths = {"titular", "tipoConta"})
    List<ContaBancaria> findByTitularId(Long titularId); 


    @EntityGraph(attributePaths = {"titular", "tipoConta"})
    Optional<ContaBancaria> findWithTitularAndTipoContaById(Long id);
}
