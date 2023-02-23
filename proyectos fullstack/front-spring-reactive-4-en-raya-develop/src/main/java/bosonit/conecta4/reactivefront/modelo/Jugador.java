package bosonit.conecta4.reactivefront.modelo;

import lombok.*;

@Data
@NoArgsConstructor
public class Jugador {
    private Integer id;
    private String nombre;
    private String direccion_ip;
    private Integer tablero;
}
