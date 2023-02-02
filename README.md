# LABECOIN

Proyecto de la asignatura Inteligencia Artificial y Sistemas Inteligentes. Escuela Politécnica de Cáceres

Se pide diseñar e implementar un sistema relacionado con los movimientos y acciones de un robot que
busque un camino para salir de un laberinto. Se proporciona un laberinto en un tablero de 10 x 10, que se
da como parámetro de entrada, y un mínimo valor de monedas (precio de salida) que se deben conseguir
en el camino. Como salida de la aplicación, deben mostrarse los movimientos del robot hasta la salida. El
tablero de entrada podrá ser diferente en cada caso, aunque el tamaño es siempre de 10 x 10. Las celdas
tienen un valor asignado que se corresponden con:

0 – CELDA VACÍA   9 – MURO   8 – ROBOT   7 – SALIDA   1-6 – MONEDAS

El movimiento del robot puede ser Arriba, aBajo, Derecha, Izquierda, y las diagonales (AI, AD, BD, BI).
No se podrá mover a una casilla que haya un muro. Cuando se mueva a una casilla con monedas, debe
sumarse esa cantidad al acumulado de monedas (cartera) del camino. Sólo se suma una vez la moneda al
pasar por esa casilla. Es decir, que cuando se pasa por una casilla con la moneda, se “retira” y se pasa a la
cartera.

Se deben utilizar técnicas de búsquedas de forma que proporcione una solución, consistente en indicar la
secuencia de acciones (movimientos) del robot. Esta solución debe cumplir que, al llegar a la salida, el
acumulado de monedas (cartera) debe ser igual o superior al precio indicado. Se parte desde el supuesto
de que se dispone de muy poco tiempo para encontrar la solución, por lo que interesa utilizar búsquedas
con heurísticas, y se quiere encontrar una solución que no tenga muchos movimientos. Siempre debe
buscarse una solución para que supere la condición de que cartera ≥ precio.
