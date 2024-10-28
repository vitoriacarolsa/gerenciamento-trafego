package br.com.fiap.trafego.repositories;

import br.com.fiap.trafego.entities.Semaforo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemaforoRepository extends JpaRepository<Semaforo, Long> {
}
