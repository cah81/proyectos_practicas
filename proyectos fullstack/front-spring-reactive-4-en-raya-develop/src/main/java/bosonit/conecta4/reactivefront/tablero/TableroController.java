package bosonit.conecta4.reactivefront.tablero;

import bosonit.conecta4.reactivefront.modelo.Historico;
import bosonit.conecta4.reactivefront.modelo.Jugador;
import bosonit.conecta4.reactivefront.modelo.Movimiento;
import bosonit.conecta4.reactivefront.modelo.Tablero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class TableroController {
    @Value("${backend.url}")
    private String urlServer;

    final byte[] columnas = new byte[]{0, 1, 2, 3, 4, 5, 6};

    /**
     * SE ENVÍA CONSTANTE INFORMACIÓN SOBRE EL
     * ESTADO DEL TABLERO JUNTO CON TODOS SUS
     * COMPONENTES
     *
     * @param id
     * @return
     */
    @GetMapping("/tablero/{id}")
    public Mono<Rendering> partida(@PathVariable int id) {
        WebClient client = WebClient.create(urlServer);
        Mono<Tablero> monoTablero = client
                .get()
                .uri("/tablero/" + id)
                .retrieve()
                .bodyToMono(Tablero.class);

        Mono<Jugador> monoJugador1 = client
                .get()
                .uri("/jugador/" + id + "/1")
                .retrieve()
                .bodyToMono(Jugador.class);

        Mono<Jugador> monoJugador2 = client
                .get()
                .uri("/jugador/" + id + "/2")
                .retrieve()
                .bodyToMono(Jugador.class);

        Mono<Integer> turno = client
                .get()
                .uri("/historico/" + id)
                .retrieve()
                .bodyToMono(Integer.class);

        Mono<Integer> empate = client
                .get()
                .uri("/empate/" + id)
                .retrieve()
                .bodyToMono(Integer.class);

        return monoTablero.flatMap(tablero -> client
                .get()
                .uri("/winner/" + id)
                .retrieve()
                .bodyToMono(Boolean.class).flatMap(aBoolean -> Mono
                        .just(Rendering
                                .view("tablero")
                                .modelAttribute("columnas", columnas)
                                .modelAttribute("tablero", monoTablero)
                                .modelAttribute("jugador1", monoJugador1)
                                .modelAttribute("jugador2", monoJugador2)
                                .modelAttribute("winner", aBoolean)
                                .modelAttribute("turno", turno)
                                .modelAttribute("empate", empate)
                                .build())));
    }

    /**
     * FUNCIÓN QUE GENERA EL FORMULARIO
     * DEL JUGADOR 1
     *
     * @return
     */
    @GetMapping("jugador1")
    public Mono<Rendering> viewHomePage() {
        return Mono.just(Rendering
                .view("principal")
                .modelAttribute("jugador", new Jugador())
                .build());
    }

    @GetMapping("partidasEmpezadas")
    public Mono<Rendering> partidasEmpezadas() {
        WebClient client = WebClient.create(urlServer);
        Flux<Tablero> monoTableros = client
                .get()
                .uri("/partidasEmpezadas/")
                .retrieve()
                .bodyToFlux(Tablero.class);
        return Mono.just(Rendering
                .view("partidasEmpezadas")
                .modelAttribute("tablero", monoTableros)
                .build());
    }

    /**
     * FUNCIÓN QUE MUESTRA LA PANTALLA
     * DE INICIO
     *
     * @return
     */
    @GetMapping("inicio")
    public Mono<Rendering> inicio() {

        WebClient client = WebClient.create(urlServer);
        Flux<Tablero> tablero = client
                .get()
                .uri("/inicio/")
                .retrieve()
                .bodyToFlux(Tablero.class);

        return Mono.just(Rendering
                .view("index")
                .modelAttribute("tablero", tablero)
                .build());
    }

    /**
     * FUNCIÓN QUE MUESTRA EL GANADOR
     *
     * @return
     */
    @GetMapping("ganador")
    public Mono<Rendering> ganador() {
        return Mono.just(Rendering
                .view("ganador")
                .build());
    }

    @GetMapping("empate")
    public Mono<Rendering> empate() {
        return Mono.just(Rendering
                .view("empate")
                .build());
    }

    /**
     * SE RELLENA EL FORMULARIO DEL JUGADOR UNO Y SE
     * LLEVA TODA ESA INFORMACIÓN A PARTIDAS, QUE ES
     * DONDE NOS UNIREMOS A OTRO JUEGO
     *
     * @param jugador
     * @return
     */
    @PostMapping(path = "jugador1")
    public Mono<Rendering> jugador1(@ModelAttribute Jugador jugador) {
        jugador.setId(null);
        WebClient client = WebClient.create(urlServer);

        Mono<Tablero> jugadorMono = client
                .post()
                .uri("/game")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(jugador), Jugador.class)
                .retrieve()
                .bodyToMono(Tablero.class);

        return jugadorMono.flatMap(t -> Mono.just(Rendering
                .view("redirect:/partidas")
                .build()));
    }

    /**
     * FORMULARIO DEL SEGUNDO JUGADOR QUE SE UNE
     * A UNA PARTIDA YA EXISTENTE, LUEGO REDIRECCIONA
     * HACIA EL TABLERO CON SU ID
     *
     * @param id
     * @param jugador
     * @return
     */
    @PostMapping(path = "jugador2/{id}")
    public Mono<Rendering> jugador2(@PathVariable int id, @ModelAttribute Jugador jugador) {
        jugador.setId(null);
        WebClient client = WebClient.create(urlServer);

        return client
                .post()
                .uri("/game/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(jugador), Jugador.class)
                .retrieve()
                .bodyToMono(Tablero.class)
                .flatMap((tablero) -> Mono.just(Rendering
                        .view("redirect:/tablero/" + tablero.getId())
                        .modelAttribute("winner", false)
                        .build()));
    }

    /**
     * LÓGICA DE CADA MOVIMIENTO QUE SE ENVÍA
     * AL BACK MIENTRAS SE VA JUGANDO
     *
     * @param id
     * @param col
     * @param movimiento
     * @return
     */
    @PostMapping("mov/{id}/{col}")
    public Mono<Rendering> movimiento(
            @PathVariable int id, @PathVariable int col, @ModelAttribute Movimiento movimiento) {
        movimiento.setTablero(id);
        movimiento.setColumna(col);

        /**
         * Aquí se envía el histórico de la
         * última partida
         */
        WebClient client = WebClient.create(urlServer);
        Mono<Integer> jugador = client
                .get()
                .uri("/historico/" + id)
                .retrieve()
                .bodyToMono(Integer.class);

        return jugador.flatMap(integer -> {

            // Se van cambiando turnos de quién pone ficha
            switch (integer) {
                case 1 -> movimiento.setJugador(2);
                case 2 -> movimiento.setJugador(1);
            }

            // Se envía con cada movimiento los datos del mismo al back
            // como dónde poner ficha, el jugador que lo ha hecho ...
            return client
                    .post()
                    .uri("/mov")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(movimiento), Movimiento.class)
                    .retrieve()
                    // Aquí se tendría que enviar un mensaje de que la columna está llena
                    .onStatus(status -> status == HttpStatus.BAD_REQUEST,
                            response -> Mono.error(new ResponseStatusException(response.statusCode(), "Columna llena")))
                    .bodyToMono(Tablero.class)
                    .flatMap((tablero) ->
                            Mono.just(Rendering
                                    .view("redirect:/tablero/" + tablero.getId())
                                    .build()));
        });
    }

    /**
     * FUNCIÓN QUE RECOGE LA INFORMACIÓN
     * DE UNA PARTIDA Y LA DEVUELVE
     *
     * @return info sobre la jugada
     * @id id de la jugada
     * @nombre nombre del jugador
     */
    @GetMapping("historial")
    public Mono<Rendering> historicoJugada(@RequestParam("id") int id) {
        WebClient client = WebClient.create(urlServer);
        Flux<Historico> listaHistoricos = client
                .get()
                .uri("/historial/" + id)
                .retrieve()
                .bodyToFlux(Historico.class);

        return Mono.just(Rendering
                .view("historial")
                .modelAttribute("historico", listaHistoricos)
                .build());
    }

    /**
     * MUESTRA TODAS LAS PARTIDAS A LAS
     * QUE NOS PODEMOS CONECTAR COMO
     * SEGUNDO JUGADOR
     *
     * @return
     */
    @GetMapping("partidas")
    public Mono<Rendering> partidas() {
        WebClient client = WebClient.create(urlServer);
        Flux<Tablero> tablero = client
                .get()
                .uri("/partidas/")
                .retrieve()
                .bodyToFlux(Tablero.class);

        return Mono.just(Rendering
                .view("partidas")
                .modelAttribute("tablero", tablero)
                .build());
    }

    /**
     * FUNCIÓN DE VÍCTOR PARA RECOGER EL
     * SEGUNDO JUGADOR Y UNIRSE A UNA PARTID
     *
     * @param jugador
     * @return
     */
    @PostMapping(path = "recogerNombre")
    public Mono<Rendering> recogerJugador2(@ModelAttribute Jugador jugador) {
        jugador.setId(null);
        WebClient client = WebClient.create(urlServer);

        Mono<Tablero> tableroMono = client
                .post()
                .uri("/jugador/" + jugador.getTablero())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(jugador), Jugador.class)
                .retrieve()
                .bodyToMono(Tablero.class);

        return tableroMono.flatMap(t -> Mono.just(Rendering
                .view("redirect:/tablero/" + t.getId())
                .build()));
    }
}
