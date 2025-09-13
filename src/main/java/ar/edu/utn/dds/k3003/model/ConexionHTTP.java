package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.HechoDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Getter
@Setter
public class ConexionHTTP {

    private String url;
    private RestTemplate restTemplate;

    public ConexionHTTP() {
        this.url = "https://dds-app-fuente.onrender.com";
        this.restTemplate = new RestTemplate();
    }

    public Optional<HechoDTO> obtenerHechoID(String id) {
        String url = this.url + "/hecho/" + id;
        try {
            ResponseEntity<HechoDTO> response = restTemplate.getForEntity(url, HechoDTO.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException(" El hecho no existe");
        } catch (HttpClientErrorException e) {
            // Para otros errores 4xx
            throw new IllegalArgumentException("Error al consultar el hecho: " + e.getStatusCode(), e);
        }
    }
}