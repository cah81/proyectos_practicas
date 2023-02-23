package bosonit.conecta4.reactivefront.modelo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Movimiento {
    private Integer jugador;
    private Integer tablero;
    private Integer columna;
}
