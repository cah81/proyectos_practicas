package bosonit.conecta4.reactivefront.modelo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Historico {
    private Integer id;
    private Integer numerojugada;
    private LocalDateTime fechayhora;
    private Integer columna;
    private Integer fila;
    private Integer tablero;
    private Integer jugador;
    private Boolean ganador;
}
