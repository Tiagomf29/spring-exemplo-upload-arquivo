package br.com.exemplouploadfotos;

import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Foto{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	Integer id;
	
	@Transient
	@JsonIgnore
	MultipartFile arquivo;
	
	@JsonIgnore
	Blob valorArquivo;
	
	@JsonIgnore
	int tamanho;
	
	@Column(name = "valor_arquivo_2")
	Byte[] valorArquivo2;
	
	@Getter
	private LocalDate teste;
	
	public LocalDate teste() {
		
		return this.teste = LocalDate.now();
	}
	
}
