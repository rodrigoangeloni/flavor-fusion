# FlavorFusion2 - Diagrama de Arquitectura

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
│  ┌─────────────┐ ┌─────────────┐                                │
│  │PantallaDetl │ │ Componentes │                                │
│  │   leReceta  │ │   Comunes   │                                │
│  └─────────────┘ └─────────────┘                                │
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
│  │  • marcarComidaComoFavorita()                              │ │
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
│  │  • toggleComidaFavorita()                                  │ │
│  │  • toggleBebidaFavorita()                                  │ │
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
│  │ │ServicioAPICom │ │              │ │   BaseDeDatos       │ │ │
│  │ │    idas       │ │              │ │                     │ │ │
│  │ └───────────────┘ │              │ │ ┌─────────────────┐ │ │ │
│  │                   │              │ │ │  FavoritosDao   │ │ │ │
│  │ 🍹 TheCocktailDB  │              │ │ └─────────────────┘ │ │ │
│  │ ┌───────────────┐ │              │ │                     │ │ │
│  │ │ServicioAPIBeb │ │              │ │ ┌─────────────────┐ │ │ │
│  │ │    idas       │ │              │ │ │ComidaFavoritaEnt│ │ │ │
│  │ └───────────────┘ │              │ │ └─────────────────┘ │ │ │
│  │                   │              │ │                     │ │ │
│  │ 🔧 Retrofit       │              │ │ ┌─────────────────┐ │ │ │
│  │    + Gson         │              │ │ │BebidaFavoritaEnt│ │ │ │
│  └───────────────────┘              │ │ └─────────────────┘ │ │ │
│                                     │ └─────────────────────┘ │ │
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
Repository llama a ServicioAPIComidas.buscarComidas()
        ↓
API externa (TheMealDB) devuelve resultados
        ↓
Repository procesa y mapea datos
        ↓
ViewModel actualiza StateFlow
        ↓
UI se recompone con nuevos resultados
```

### 2. Gestión de Favoritos
```
Usuario marca receta como favorita
        ↓
UI llama a ViewModel.marcarComidaComoFavorita()
        ↓
ViewModel llama a Repository.toggleComidaFavorita()
        ↓
Repository verifica estado actual en Room DB
        ↓
Repository inserta/elimina de base de datos local
        ↓
ViewModel actualiza estado en memoria
        ↓
UI refleja cambio inmediatamente
```

## 🌐 APIs Externas Utilizadas

### TheMealDB API
- **Base URL**: `https://www.themealdb.com/api/json/v1/1/`
- **Endpoints**:
  - `random.php` - Comida aleatoria
  - `search.php?s={query}` - Búsqueda por nombre
  - `lookup.php?i={id}` - Detalles por ID

### TheCocktailDB API
- **Base URL**: `https://www.thecocktaildb.com/api/json/v1/1/`
- **Endpoints**:
  - `random.php` - Bebida aleatoria
  - `search.php?s={query}` - Búsqueda por nombre
  - `lookup.php?i={id}` - Detalles por ID

## 🏛️ Patrones de Arquitectura Implementados

### MVVM (Model-View-ViewModel)
- **View**: Composables de Jetpack Compose
- **ViewModel**: Gestión de estado y lógica de presentación
- **Model**: Repository + Data Sources

### Repository Pattern
- Abstrae las fuentes de datos
- Centraliza la lógica de acceso a datos
- Facilita testing y mantenimiento

### Dependency Injection (Hilt)
- Gestión automática de dependencias
- Scope management para lifecycle
- Testing simplificado

### Single Source of Truth
- StateFlow como única fuente de verdad
- Datos centralizados en ViewModel
- Consistencia en toda la aplicación

## 🔧 Tecnologías y Librerías

- **UI**: Jetpack Compose + Material Design 3
- **Architecture**: MVVM + Repository Pattern
- **DI**: Hilt
- **Database**: Room
- **Network**: Retrofit + Gson
- **Async**: Kotlin Coroutines + Flow
- **Navigation**: Jetpack Navigation Compose
- **Image Loading**: Coil
