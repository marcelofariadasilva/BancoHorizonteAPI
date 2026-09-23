package br.com.bancohorizonte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.bancohorizonte.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
}
