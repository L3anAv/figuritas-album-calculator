# 📜 Documentación 📜

Simulación de completado del album de figuritas mundial 2022 en JAVA. Proyecto realizado para la materia Programacion III de la Universidad Nacional General Sarmiento.

## Paquetes 📦

- Modelo
- Interfaces
- Vista
- Test
- Utilidades

## Clases 📃

- ### Paquete Modelo

1. Album
2. Persona
3. FabricaDeSimulaciones
4. PaqueteFiguritasNormal
5. SimulacionUnaPersona
6. SimulacionVariasPersonasRegalo
7. SimulacionVariasPersonasIntercambio

- ### Paquete Interfaces

1. Generador
2. Observador
3. Simulacion

- ### Paquete Vista

1. InterfazSimuladorAlbum

- ### Paquete Test

1. PersonaTest
2. SimulacionVariasPersonasRegaloTest

- ### Paquete Utilidades

1. GeneradorRandom
2. ObservadorPorConsola

## Descripción de Responsabilidades de Clases (Modelo) ✍🏻

###### <u>Clase <i>Persona</i>:</u>

>Encargada de las responsabilidades de una persona en la simulación. Las cuales son:
>
  >> 1. Rellenar su propio album.
  >> 2. Poder regalar figuritas si asi se lo requieren.
  >> 3. Poder intercambiar figuritas si asi se lo requieren. (Primeramente sin ninguna condicion particular todas las figuritas valen otra)

###### <u>Clase <i>Album</i>:</u>

>	Encargada de representar la entidad album de la simulacion. Su tarea es clara, representar un album de figuritas. Rellenarse, controlar figuritas repetidas, e indicar cuando ya tenga todas las figuritas pegadas.

###### <u>Clase <i>FabricaDeSimulaciones</i>:</u>

>	Encargada de fabricar cualquier tipo de simulacion solicitado.

###### <u>Clase <i>PaqueteFiguritasNormal</i>:</u>

>	Encargada de generar los paquetes de figuritas para completar el album, sin ninguna condición especial. Todas las figuritas tienen la misma posibilidad de salir, la elección de figuritas para el paquete es aleatoria.

###### <u>Clase <i>SimulacionUnaPersona</i>:</u>

>	Encargada de realizar la simulacion de completado de album de una sola persona. Responsable de dar la cantidad total de gasto de la persona para completar el album.

###### <u>Clase <i>SimulacionVariasPersonasRegalo</i>:</u>

>	Encargada de realizar la simulación de completado de album entre n personas donde si alguna tiene una figurita que otro necesita, se la regala sin pedir nada a cambio. 
>	Responsable tambien de dar la cantidad total de gasto de las personas para completar los n participantes cada uno su album.

###### <u>Clase <i>SimulacionVariasPersonasIntercambio</i>:</u>

>	Encargada de realizar la simulación de completado de album entre n personas donde se realizan intercambios de 1 a 1 figurita solo con la condición que las figuritas intercambiadas no las posean las personas que realizan el intercambio. 
>	Responsable además de dar la cantidad total de gasto de las personas para completar los n participantes cada uno su album.

