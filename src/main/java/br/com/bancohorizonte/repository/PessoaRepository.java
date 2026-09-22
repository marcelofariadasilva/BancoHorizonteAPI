package br.com.bancohorizonte.repository;
import br.com.bancohorizonte.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PessoaRepository extends JpaRepository<Pessoa, Long> { boolean existsByCpf(String cpf); }
