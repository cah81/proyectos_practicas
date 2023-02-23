package bosonit.conecta4.reactivefront.modelo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Tablero {
    private Integer id;
    private int num_filas;
    private int num_columnas;
    private int[][] matriz;
}
