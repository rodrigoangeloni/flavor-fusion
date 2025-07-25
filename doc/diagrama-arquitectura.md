# FlavorFusion - Diagrama de Arquitectura

## 🏗️ Arquitectura General de la Aplicación

```
┌─────────────────────────────────────────────────────────────────┐
│                        PRESENTACIÓN (UI)                        │
├─────────────────────────────────────────────────────────────────┤
│  📱 Jetpack Compose UI                                          │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌───────────┐  │
│  │ PantallaIni │ │PantallaExpl │ │PantallaExpl │ │ Pantalla  │  │
│  │    cio      │ │  orComidas  │ │  orBebidas  │ │ Favoritos │  │
│  └─────────────┘ └─────────────┘ └─────────────┘ └───────────┘  │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐               │
│  │PantallaDetl │ │ Componentes │ │ Pantalla    │               │
│  │   leReceta  │ │   Comunes   │ │   Carga     │               │
│  └─────────────┘ └─────────────┘ └─────────────┘               │
└─────────────────────────────────────────────────────────────────┘
                                 ↕
┌─────────────────────────────────────────────────────────────────┐
│                        LÓGICA (ViewModels)                      │
├─────────────────────────────────────────────────────────────────┤
│  🧠 MVVM Pattern + Hilt DI                                      │
│  ┌─────────────────────────────────────────────────────────────┐ │
│  │                InicioViewModel                              │ │
│  │  • cargarSugerenciasAleatorias()                           │ │
│  │  • buscarComidas(consulta)                                 │ │
│  │  • buscarBebidas(consulta)                                 │ │
│  │  • alternarFavorito(receta)                                │ │
│  │  • obtenerFavoritos()                                      │ │
│  │  • limpiarResultadosBusqueda()                             │ │
│  └─────────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
                                 ↕
┌─────────────────────────────────────────────────────────────────┐
│                        DOMINIO (Repository)                     │
├─────────────────────────────────────────────────────────────────┤
│  📊 Gestión de Datos                                            │
│  ┌─────────────────────────────────────────────────────────────┐ │
│  │                RecetasRepositorio                           │ │
│  │  • obtenerComidaAleatoria()                                │ │
│  │  • obtenerBebidaAleatoria()                                │ │
│  │  • buscarComidas(consulta)                                 │ │
│  │  • buscarBebidas(consulta)                                 │ │
│  │  • obtenerDetalleComida(id)                                │ │
│  │  • obtenerDetalleBebida(id)                                │ │
│  │  • obtenerFavoritos()                                      │ │
│  │  • alternarFavorito(receta)                                │ │
│  │  • esFavorito(id)                                          │ │
│  └─────────────────────────────────────────────────────────────┘ │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────────┐ │
│  │            ServicioTraduccion                               │ │
│  │  • traducir(texto)                                          │ │
│  │  • traducirNullable(texto)                                  │ │
│  │  • traducirLista(textos)                                    │ │
│  └─────────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
                                 ↕
┌─────────────────────────────────────────────────────────────────┐
│                        DATOS (Data Layer)                       │
├─────────────────────────────────────────────────────────────────┤
│  🔄 Fuentes de Datos                                            │
│                                                                 │
│  ┌───────────────────┐              ┌─────────────────────────┐ │
│  │   REMOTO (APIs)   │              │    LOCAL (Database)     │ │
│  │                   │              │                         │ │
│  │ 🌐 TheMealDB API  │              │ 💾 Room Database        │ │
│  │ ┌───────────────┐ │              │ ┌─────────────────────┐ │ │
│  │ │  ServicioAPI  │ │              │ │   BaseDeDatos       │ │ │
│  │ │               │ │              │ │                     │ │ │
│  │ └───────────────┘ │              │ │ ┌─────────────────┐ │ │ │
│  │                   │              │ │ │   RecetaDao     │ │ │ │
│  │ 🍹 TheCocktailDB  │              │ │ └─────────────────┘ │ │ │
│  │ ┌───────────────┐ │              │ │                     │ │ │
│  │ │ServicioBebidas│ │              │ │ ┌─────────────────┐ │ │ │
│  │ │               │ │              │ │ │Receta (Entidad) │ │ │ │
│  │ └───────────────┘ │              │ │ └─────────────────┘ │ │ │
│  │                   │              │ │                     │ │ │
│  │ 🔧 Retrofit       │              │ └─────────────────────┘ │ │
│  │    + Gson         │                                         │
│  └───────────────────┘              ┌─────────────────────────┐ │
│                                     │  TRADUCCIÓN (ML Kit)     │ │
│                                     │                         │ │
│                                     │ 🌍 ML Kit Translate     │ │
│                                     │  • Modelos offline      │ │
│                                     │  • Inglés → Español     │ │
│                                     └─────────────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
```

## 🔄 Flujo de Datos

### 1. Búsqueda de Recetas
```
Usuario escribe en barra de búsqueda
        ↓
UI llama a ViewModel.buscarComidas()
        ↓
ViewModel llama a Repository.buscarComidas()
        ↓
Repository llama a ServicioAPI.buscarComidas()
        ↓
API externa (TheMealDB) devuelve resultados en inglés
        ↓
Repository usa ServicioTraduccion para traducir al español
        ↓
Repository procesa y mapea datos
        ↓
ViewModel actualiza StateFlow
        ↓
UI se recompone con nuevos resultados en español
```

### 2. Gestión de Favoritos
```
Usuario marca receta como favorita
        ↓
UI llama a ViewModel.alternarFavorito(receta)
        ↓
ViewModel llama a Repository.alternarFavorito(receta)
        ↓
Repository verifica si ya es favorito con esFavorito(id)
        ↓
Si no es favorito: Repository llama a RecetaDao.insertarFavorito(receta)
Si ya es favorito: Repository llama a RecetaDao.eliminarFavoritoPorId(id)
        ↓
Room Database actualiza la tabla de favoritos
        ↓
Repository.obtenerFavoritos() emite nuevos datos a través de Flow
        ↓
ViewModel actualiza StateFlow con nuevos favoritos
        ↓
Todas las UI observando favoritos se recomponen
```

### 3. Traducción de Contenido
```
Repository recibe datos de API en inglés
        ↓
Repository llama a ServicioTraduccion.traducir() para cada campo
        ↓
ServicioTraduccion verifica si el modelo está descargado
        ↓
Si no está descargado: ML Kit descarga modelo de traducción
        ↓
ML Kit traduce el texto de inglés a español
        ↓
Repository recibe texto traducido
        ↓
Repository devuelve recetas con todos los campos en español
        ↓
UI muestra contenido traducido al usuario
```

## 🧩 Componentes Principales

### 📱 UI (Jetpack Compose)
- **PantallaInicio**: Muestra recetas sugeridas y navegación principal
- **PantallaExplorarComidas**: Búsqueda y resultados de comidas
- **PantallaExplorarBebidas**: Búsqueda y resultados de bebidas
- **PantallaFavoritos**: Gestión de recetas guardadas
- **PantallaDetalleReceta**: Detalles completos de una receta
- **PantallaCarga**: Splash screen con logo de la app

### 🧠 ViewModels
- **InicioViewModel**: Gestiona el estado general de la aplicación
  - Manejo de búsquedas
  - Gestión de favoritos
  - Carga de sugerencias
  - Manejo de errores

### 📊 Repository
- **RecetasRepositorio**: Abstracción de las fuentes de datos
  - Unifica API y Database
  - Realiza traducciones automáticas
  - Gestiona la persistencia de favoritos

### 💾 Modelo de Datos
- **Receta**: Entidad unificada para comidas y bebidas
  - Usada para persistencia en Room
  - Representa cualquier tipo de receta (comida o bebida)

### 🌐 Servicio de API
- **ServicioAPI**: Interfaz para TheMealDB
- **ServicioBebidas**: Interfaz para TheCocktailDB

### 🌍 Servicio de Traducción
- **ServicioTraduccion**: Maneja traducciones con ML Kit
  - Traduce de inglés a español
  - Gestiona descarga de modelos
  - Preserva formato (mayúsculas, etc.)

## 🔒 Persistencia y Acceso Offline

### 💾 Room Database
- **BaseDeDatos**: Configuración general de Room
- **RecetaDao**: Operaciones CRUD para favoritos
- **Receta**: Entidad que representa una receta guardada

### 🔄 Flujos de Datos Reactivos
- **Flow** para datos de favoritos: Actualizaciones automáticas
- **StateFlow** en ViewModel: Estado reactivo para la UI
- **Coroutines** para operaciones asíncronas

## 🔧 Herramientas y Tecnologías Utilizadas

- **Jetpack Compose**: UI declarativa
- **Hilt**: Inyección de dependencias
- **Kotlin Coroutines + Flow**: Programación asíncrona
- **Room**: Persistencia local
- **Retrofit + Gson**: Comunicación con APIs
- **ML Kit Translate**: Traducción offline
- **Material3**: Diseño visual

## 📱 Compatibilidad

- **Android 7.0+** (API 24)
- **Soporte para temas oscuros**
- **Diseño responsive** para diferentes tamaños de pantalla
- **Icono adaptativo personalizado**
