package br.com.exemplouploadfotos;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadFotosController {

	@Autowired
	private FotoRespoitory fotoRespoitory;
	
	@SuppressWarnings("resource")
	@PostMapping("/fotos")
	@Transactional
	public void gravarFoto(Foto arquivo) throws IllegalStateException, IOException, SerialException, SQLException {
		//arquivo veio atraves da propriedade form-data onde o key é igual ao nome do parâmetro do método e o value é o 
		//binario do arquivo
		byte[] vlr = arquivo.arquivo.getInputStream().readAllBytes();
		arquivo.setValorArquivo( new SerialBlob(vlr));
		arquivo.setTamanho(arquivo.arquivo.getInputStream().readAllBytes().length);
		fotoRespoitory.save(arquivo);
		
		String nomeArquivo = LocalDate.now().toString()+"-"+UUID.randomUUID().toString();
		
		Path camimho = Path.of("c:\\tiago",nomeArquivo+".jpeg");
		
		arquivo.getArquivo().transferTo(camimho);
		
	}
	
	
	@PostMapping("/fotos/base64")
	public void gravaFotoBase64(@RequestBody Foto foto) throws IOException, SQLException {
		//Arquivo veio através da foto convertido à base 64 no atributo valor_arquivo_2
		foto.setTamanho(foto.valorArquivo2.length);
	
		fotoRespoitory.save(foto);
		
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/excluir/{valor}")
	public void excluir(@PathVariable Integer valor) {
		
		System.out.println("Chegou aqui!!");
		
	}
	
	@GetMapping("/teste")
	public ResponseEntity<Foto> teste(){
		
		System.out.println("Chegou aqui...");
		
		Foto f = new Foto();
		f.setId(12);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10,TimeUnit.SECONDS))
				.body(f);
	}
	
}
