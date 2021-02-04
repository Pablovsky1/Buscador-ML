
# Buscador ML

![](https://raw.githubusercontent.com/Pablovsky1/mercadolibre.products/develop/app/src/main/res/mipmap-xhdpi/ic_launcher_foreground.png)


Utilizar APIs de Mercadolibre para crear una app que le permita ver al usuario los detalles de un producto.
### Dependencias de terceros:

[Retrofit](https://square.github.io/retrofit/ "Retrofit"): Se trata de un cliente REST bastante fácil de usar. Lo utilice en el proyecto ya que es una dependencia bastante conocida y permite agregar convertidores personalizados para mapear los datos obtenidos desde una API REST en formato XML o JSON en un objeto de una clase personalizada mediante un desearilizador.

[Picasso](https://square.github.io/picasso/ "Picasso"): Se trata de una librería open source la cual me facilita cargar imágenes ya utiliza OkHttp lo cual me permite cargar imágenes en internet.


### Manejo de casos de error desde el punto de vista del developer:

- Estoy utilizando la arquitectura de diseño MVVM para conservar los datos luego de una rotación de pantalla gracias a ViewModel.

- Para el manejo de estados de error en LiveData estoy utilizando una clase Resource con tres métodos estáticos success, error, loading.

- Estoy utilizando una clase MyLog para el manejo de logs de esta forma puedo activarlos o desactivarlo con un constante.

### Manejo de casos de error desde el punto de vista del usuario:

- Avisar al usuario si se encuentra sin conexión a internet tanto al obtener los productos como al seleccionar uno.

- Avisar al usuario en caso de que no se hayan obtenido resultados tanto al obtener los productos como al seleccionar uno.

- Mostrar imagen por defecto en caso de que no se pueda obtener.

- Mostrar imágenes por defecto cuando se obtiene las imágenes.

- Mostrarle una opción al usuario para que pueda reintentar la búsqueda o en caso de que seleccionar un producto para que pueda obtener la información nuevamente en caso de error. 
