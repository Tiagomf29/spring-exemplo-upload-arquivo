package br.com.exemplouploadfotos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRespoitory extends JpaRepository<Foto, Integer>{

}
