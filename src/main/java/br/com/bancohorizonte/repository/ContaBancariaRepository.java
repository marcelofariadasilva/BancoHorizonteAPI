package br.com.bancohorizonte.repository;
import br.com.bancohorizonte.entity.ContaBancaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> { boolean existsByAgenciaAndNumero(String agencia, String numero); List<ContaBancaria> findByTitularId(Long titularId); }
