# KindaLab-Elevator-Challenge
**Funcionamiento básico**
- 
****

La aplicación inicia, se le pide al usuario su peso y el peso de lo que transporte.
Inicia un menú de opciones numéricas que nos van a permitir elegir varias opciones. Podemos llamar, subir y dejar dos ascensores (uno público y otro de carga),
también podemos buscar y dejar la llave de acceso, cambiar el peso de lo que transportamos, nuestro propio peso y salir del edificio (finalizando la aplicación).

Los ascensores solo pueden ser accedidos si su piso coincide con el piso del usuario y si no se excede su límite.

**Modelos**
- 
****

**Elevator**

Es la representación abstracta de un ascensor. No se puede instanciar.
Tiene los atributos:
- Long id
- Float maxWeight
- Float actualWeight
- Integer Position

Tiene constructor vacío y completo, getters de todos sus atributos y setter solo de position y actualWeight.
***
**Freight Elevator**

Representación del ascensor de carga. Hereda sus atributos y métodos de **Elevator**. Permite hasta 3000kg de carga máxima y tiene acceso a todos los pisos.
- Long id
- Float maxWeight
- Float actualWeight
- Integer Position

Tiene constructor vacío y completo. Es igual al Elevator en cuanto a funcionalidad, solo que este sí se puede instanciar.


***
**Public Elevator**

Representación del ascensor público. Hereda sus atributos y métodos de **Elevator**. Permite hasta 1000kg de carga máxima y requiere de una llave de acceso para ir a los pisos -1 y 50.
Tiene los atributos:
- Long id
- Float maxWeight
- Float actualWeight
- Integer Position
- Boolean AccessKey

Tiene constructor completo y vacío, getters de todos sus atributos y setters solo de position, actualWeight y accessKey.
***
**Building**

Es la representación del edificio. Sirve para instanciar la cantidad de pisos máximos. Pese a que no se pida que el usuario lo seleccione, en caso de necesitarlo, con solo cambiar este atributo los demás métodos se adaptan. Empieza con 50 pisos. Tiene un ascensor público y otro de carga.
Tiene los atributos:
- Long id
- Integer storiesHigh
- PublicElevator publicElevator
- FreightElevator freightElevator

Tiene constructor vacío y completo, getters de todos sus elementos y ningún setter.
***
**User**

Es la representación del usuario que va a usar los ascensores. El usuario selecciona su peso y el peso de lo que esté transportando, puede hacer uso de los ascensores y tiene acceso a una llave de acceso.
Tiene los atributos:
- Integer actualFloor,
- Float weight;
- float carryingWeight

Tiene constructor vacío, completo y solo con actualFloor iniciado. Tiene getters y setters de todos sus elementos.
***
# Funcionalidad avanzada
Los ascensores deben ser llamados y al ser usados no se debe exceder su límite de carga, no se deben seleccionar pisos inexistentes ni se puede acceder a pisos no autorizados sin la llave de acceso.

Cuando el usuario usa un ascensor, este ascensor carga el peso del usuario sumado al peso de lo que esté transformando, si su peso total es mayor al límite, el ascensor no cambiará su posición y no llevará al usuario.

Cuando el usuario usa un ascensor y cumple todas las condiciones, el ascensor cambia su peso a la suma del peso del usuario más el peso de lo que transporte el usuario y tanto el ascensor como el usuario cambian su posición al piso seleccionado.









