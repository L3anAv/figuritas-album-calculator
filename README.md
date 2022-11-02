# 📜 Documentación 📜
<hr>

Simulación de completado del album de figuritas mundial 2022 en JAVA. Proyecto realizado para la materia Programacion III de la Universidad Nacional General Sarmiento.

## Clases 📃
<hr>

1. Album
2. Persona
3. PaqueteDeFiguritas
4. SimulacionUnaPersona

## Descripción de Responsabilidades de Clases ✍🏻
<hr>

###### Clase **_Persona_**:
Encargada de las responsabilidades de una persona en la simulación. Las cuales son:
- Rellenar su propio album.
-  Poder regalar figuritas si asi se lo requieren.
- Poder intercambiar figuritas si asi se lo requieren. (Primeramente sin ninguna condicion particular todas las figuritas valen otra)

###### Clase **_Album_**:
Encargada de representar la entidad album de la simulacion. Su tarea es clara, representar un album de figuritas. Rellenarse, controlar figuritas repetidas, e indicar cuando ya tenga todas las figuritas pegadas.

###### Clase **_PaqueteFiguritasNormal_**:
Encargada de generar los paquetes de figuritas para completar el album, sin ninguna condición especial. Todas las figuritas tienen la misma posibilidad de salir, la elección de figuritas para el paquete es aleatoria.

###### Clase **_SimulacionUnaPersona_**:
Encargada de realizar la simulacion de completado de album de una sola persona. Responsable de dar la cantidad total de gasto de la persona para completar el album.

## Detalle de Implementación de Clases ✍🏻
<hr>

###### - Clase Persona

La clase persona cuenta con los siguientes métodos:

- public Persona(int id) (Constructor)
- public void insertarFiguritaEnAlbum(int numDeFigurita)
- public boolean albumEstaCompleto() 
- public boolean tieneFigurita(int numDeFigurita) 
- public int figuritasParaRegalar(int numDeFiguritaRequerida)
- public int intercambioDeFiguritas(int numDeFiguritaRequerida, int numDeFiguritaNueva)

_Además de sus respectivos setters and getters._
