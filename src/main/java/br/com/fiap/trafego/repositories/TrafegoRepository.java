package br.com.fiap.trafego.repositories;


import br.com.fiap.trafego.entities.Trafego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafegoRepository extends JpaRepository<Trafego, Long> {
}
