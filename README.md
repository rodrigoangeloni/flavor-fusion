# 🍽️ FlavorFusion - Descubre el Mundo de las Recetas ✨

<div align="center">

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![API](https://img.shields.io/badge/Min%20API-24-orange.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)

**Una aplicación móvil moderna para explorar y gestionar recetas de comidas y bebidas** 🚀

<p align="center">
  <img src="doc/screenshots/screenshot_1.png" width="250" alt="Pantalla de Carga e Inicio">
  <img src="doc/screenshots/screenshot_2.png" width="250" alt="Explorar Comidas y Bebidas">
  <img src="doc/screenshots/screenshot_3.png" width="250" alt="Detalle de Receta">
</p>
<p align="center">
  <img src="doc/screenshots/screenshot_4.png" width="250" alt="Favoritos y Sincronización">
  <img src="doc/screenshots/screenshot_5.png" width="250" alt="Manejo de Errores">
  <img src="doc/screenshots/screenshot_6.png" width="250" alt="Búsqueda y Sugerencias">
</p>

[📱 Instalación](#-instalación) • [🔍 Características](#-características) • [📖 Manual de Uso](#-manual-de-uso) • [🏗️ Arquitectura](#️-arquitectura)

</div>

---

## 🌟 Características Principales

### 🚀 **Experiencia Visual Personalizada**
- 💫 **Pantalla de carga** con logo personalizado
- 🖼️ **Logo propio** en todas las pantallas principales
- 📱 **Icono adaptativo** personalizado para el launcher
- 🎨 **Diseño coherente** con identidad visual propia

### 🏠 **Inicio Inteligente**
- 🎲 **Sugerencias diarias** de comidas y bebidas aleatorias
- 🎨 **Interfaz moderna** con Material Design 3
- ⚡ **Navegación fluida** entre secciones

### 🔍 **Búsqueda Inteligente** 
- 🍽️ **Explorar comidas** escribiendo "chicken", "pasta", "tacos"
- 🍹 **Descubrir bebidas** con "mojito", "coffee", "beer"
- ⌨️ **Búsqueda con Enter** o botón dedicado
- 💡 **Sugerencias visuales** con chips interactivos
- 🧹 **Limpieza automática** de resultados

### ❤️ **Sistema de Favoritos Completo**
- 💾 **Almacenamiento local** con Room Database SQLite
- 📱 **Acceso offline** a recetas guardadas sin internet
- ♻️ **Sincronización en tiempo real** entre todas las pantallas
- 🎯 **Botón de favorito** disponible en toda la aplicación
- 📋 **Pantalla dedicada** para gestionar favoritos
- 🔄 **Alternado inteligente** - agrega/quita con un toque
- 💡 **Estado visual claro** - corazón rojo/gris

### 🌐 **Datos en Vivo con Traducción Automática**
- 📡 **APIs externas** (TheMealDB + TheCocktailDB)
- 🔄 **Contenido actualizado** constantemente
- 🌍 **Traducción automática** del inglés al español
- 📲 **Traducción offline** con ML Kit (tras descarga inicial)
- 🛡️ **Manejo robusto de errores**
- 🌍 **Interfaz 100% en español**

---

## 📱 Instalación

### Requisitos del Sistema
- 📋 **Android 7.0** (API 24) o superior
- 💾 **2GB RAM** recomendado
- 💿 **150MB** espacio libre (100MB app + 50MB favoritos y modelo de traducción)
- 🌐 **Conexión a internet** para búsquedas (favoritos funcionan offline)

### Pasos de Instalación
1. **Clona el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/FlavorFusion2.git
   ```

2. **Abre en Android Studio**
   ```bash
   cd FlavorFusion2
   ```

3. **Ejecuta la aplicación**
   - Conecta tu dispositivo Android o usa un emulador
   - Presiona Run ▶️ en Android Studio

---

## 📖 Manual de Uso

### 🚀 **Primeros Pasos**

#### 1. 🏠 Pantalla de Inicio
- Al abrir la app, verás la **pantalla de carga con el logo** personalizado
- Después se cargan **sugerencias del día**
- 🍽️ **Comida destacada** - Receta aleatoria de comida
- 🍹 **Bebida destacada** - Cóctel o bebida especial
- ❤️ **Botones de favorito** - Guarda directamente desde inicio
- 📱 Usa los **botones de navegación** para cambiar de sección

#### 2. 🔍 Explorar Recetas

##### 🍽️ **Buscar Comidas**
```
1. Toca la sección "Explorar Comidas"
2. Escribe: "chicken", "pasta", "beef", "pizza"
3. Ejecuta la búsqueda:
   • Presiona Enter ⏎
   • Toca el botón de búsqueda 🔍
4. ❤️ Marca favoritos directamente desde resultados
5. 🌍 Todas las recetas se muestran traducidas al español
```

##### 🍹 **Buscar Bebidas**
```
1. Ve a "Explorar Bebidas"
2. Escribe: "mojito", "coffee", "beer", "tea"
3. Encuentra cócteles y bebidas internacionales
4. ❤️ Guarda tus bebidas favoritas instantáneamente
5. 🌍 Todas las recetas se muestran traducidas al español
```

#### 3. 📋 **Ver Detalles de Recetas**
- 🖼️ **Imagen en alta calidad**
- ❤️ **Botón de favorito prominente** en la parte superior
- 📝 **Información completa**: nombre, categoría, origen
- 🥄 **Lista de ingredientes** con cantidades exactas
- 👨‍🍳 **Instrucciones paso a paso**
- 🌍 **Todo traducido automáticamente** al español

#### 4. ❤️ **Gestionar Favoritos**

##### **Marcar como Favorito**
- **Desde cualquier pantalla**: Toca el corazón ❤️ 
- **Se pone rojo**: Indica que se guardó exitosamente
- **Guardado instantáneo**: No necesitas confirmar

##### **Ver tus Favoritos**
- **Pantalla dedicada**: Toca "Favoritos" en navegación
- **Lista completa**: Todas tus recetas guardadas
- **Información rica**: Imagen, nombre, categoría, tipo (comida/bebida)
- **Acceso offline**: Funciona sin conexión a internet

##### **Quitar de Favoritos**
- **Opción 1**: Toca el ❤️ rojo en cualquier pantalla → se pone gris
- **Opción 2**: Desde pantalla de favoritos, toca ❤️ → se elimina de la lista

##### **Características Especiales**
- ✅ **Sincronización instantánea**: Cambios visibles en toda la app
- ✅ **Persistencia offline**: Datos guardados localmente
- ✅ **Sin límites**: Guarda tantas recetas como quieras
- ✅ **Orden cronológico**: Las más recientes aparecen primero

---

## 🎛️ **Estados de la Aplicación**

| Estado | Icono | Descripción |
|--------|-------|-------------|
| **Cargando** | 🔄 | Spinner mientras se cargan datos |
| **Sin resultados** | 🚫 | Mensaje cuando no hay coincidencias |
| **Error** | ⚠️ | Problema de conexión con botón reintentar |
| **Favorito activo** | ❤️ | Receta guardada en favoritos |
| **Favorito inactivo** | 🤍 | Receta no guardada |
| **Favoritos vacíos** | 💔 | Pantalla sin favoritos con mensaje motivacional |

---

## 💡 **Tips y Trucos**

### 🔍 **Búsquedas Efectivas**
- ✅ **Usa palabras simples**: "chicken" > "pollo con verduras"
- ✅ **Prueba en inglés**: Las APIs funcionan mejor
- ✅ **Sé específico**: "pasta" > "comida italiana"
- ✅ **Experimenta**: Prueba diferentes términos

### ❤️ **Gestión de Favoritos**
- 🗂️ **Organiza mentalmente** por tipo de comida
- 📱 **Aprovecha el acceso offline** para cocinar sin internet
- 🧹 **Limpia periódicamente** favoritos no usados
- 🔄 **Los cambios son instantáneos** en toda la app

### 🚀 **Navegación Rápida**
- 📱 **Botones principales**: Acceso directo a secciones
- ← **Botón atrás**: Consistente en toda la app
- ❤️ **Acceso a favoritos**: Siempre disponible

---

## 🔧 **Solución de Problemas**

<details>
<summary>🚫 La búsqueda no funciona</summary>

- ✅ **Verifica tu conexión** a internet
- ✅ **Revisa la ortografía** del término buscado
- ✅ **Prueba términos diferentes** o más simples
- ✅ **Reinicia la aplicación**
</details>

<details>
<summary>❤️ Los favoritos no se guardan</summary>

- ✅ **Verifica el corazón**: Debe ponerse rojo al tocarlo
- ✅ **Espacio disponible**: Verifica que tengas memoria libre
- ✅ **Reinicia la aplicación** completamente
</details>

<details>
<summary>🌍 La traducción no funciona</summary>

- ✅ **Primera ejecución**: La descarga del modelo puede tardar unos minutos
- ✅ **Verifica tu conexión** para la descarga inicial del modelo
- ✅ **Espacio suficiente**: El modelo ocupa aproximadamente 30MB
- ✅ **Reinicia la aplicación** si la traducción no funciona correctamente
</details>

<details>
<summary>🐌 La aplicación está lenta</summary>

- ✅ **Cierra otras aplicaciones**
- ✅ **Verifica tu conexión** a internet
- ✅ **Reinicia tu dispositivo**
</details>

---

## 🏗️ Arquitectura

### 🏛️ **Patrones Implementados**
- **MVVM** - Separación clara de responsabilidades
- **Repository Pattern** - Abstracción de fuentes de datos
- **Dependency Injection** - Gestión automatizada con Hilt
- **Single Source of Truth** - Estado centralizado

### 🔧 **Stack Tecnológico**
- 🎨 **UI**: Jetpack Compose + Material Design 3
- 🧠 **Architecture**: MVVM + Repository Pattern
- 💉 **DI**: Hilt
- 💾 **Database**: Room SQLite para favoritos
- 🌐 **Network**: Retrofit + Gson
- ⚡ **Async**: Kotlin Coroutines + Flow
- 🧭 **Navigation**: Jetpack Navigation Compose
- 🌍 **Translation**: ML Kit Translate

### 🌐 **APIs Utilizadas**
- 🍽️ **[TheMealDB](https://www.themealdb.com/api.php)** - Recetas de comidas
- 🍹 **[TheCocktailDB](https://www.thecocktaildb.com/api.php)** - Recetas de bebidas

### 💾 **Persistencia de Datos**
```kotlin
// Entidad Room para favoritos
@Entity(tableName = "favoritos")
data class Receta(
    @PrimaryKey val id: String,
    val nombre: String,
    val imagen: String,
    val categoria: String = "",
    val area: String = "",
    val instrucciones: String = "",
    val ingredientes: String = "", // JSON string de los ingredientes
    val tipo: String, // "meal" o "drink"
    val fechaAgregado: Long = System.currentTimeMillis()
)

// DAO con operaciones de favoritos
@Dao interface RecetaDao {
    @Query("SELECT * FROM favoritos ORDER BY fechaAgregado DESC")
    fun obtenerTodosFavoritos(): Flow<List<Receta>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFavorito(receta: Receta)
    
    @Query("DELETE FROM favoritos WHERE id = :id")
    suspend fun eliminarFavoritoPorId(id: String)
    
    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE id = :id)")
    suspend fun esFavorito(id: String): Boolean
}
```

### 🌍 **Servicio de Traducción**
```kotlin
@Singleton
class ServicioTraduccion @Inject constructor() {
    private val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()
    
    private val traductor = Translation.getClient(options)
    
    suspend fun traducir(texto: String): String {
        // Traduce texto de inglés a español
        // Funciona offline tras la descarga inicial
    }
    
    suspend fun traducirNullable(texto: String?): String? {
        // Versión segura para campos opcionales
    }
}
```

---

## 📊 **Estructura del Proyecto**

```
📁 FlavorFusion/
├── 📁 app/src/main/java/com/rodrigoangeloni/flavorfusion/
│   ├── 💾 database/           # Room database y DAOs
│   │   ├── BaseDeDatos.kt     # Configuración Room
│   │   └── RecetaDao.kt       # Operaciones favoritos
│   ├── 💉 di/                 # Módulos de Hilt
│   ├── 📱 model/              # Modelos de datos + DTOs
│   ├── 🌐 network/            # Servicios API 
│   ├── 📊 repository/         # Repository pattern
│   ├── 🖥️ screens/            # Pantallas Compose
│   │   ├── PantallaInicio.kt
│   │   ├── PantallaFavoritos.kt
│   │   ├── PantallaExplorarComidas.kt
│   │   ├── PantallaExplorarBebidas.kt
│   │   ├── PantallaDetalleReceta.kt
│   │   └── PantallaCarga.kt
│   ├── 🎨 ui/theme/           # Tema y estilos
│   ├── 🧰 util/               # Utilidades
│   │   ├── Navegacion.kt      # Configuración de rutas
│   │   └── ServicioTraduccion.kt # Traducción con ML Kit
│   ├── 🧠 viewmodels/         # ViewModels MVVM
│   └── 🚀 MainActivity.kt     # Actividad principal
└── 📁 doc/                   # Documentación
    ├── 📋 objetivo-y-funcionalidades.md
    ├── 🏗️ diagrama-arquitectura.md
    └── 📖 manual-de-uso.md
```

---

## 🚀 **Características Implementadas**

### ❤️ **Sistema de Favoritos Completo**
- ✅ **Base de datos local**: Room SQLite para persistencia
- ✅ **Pantalla dedicada**: Gestión completa de favoritos
- ✅ **Sincronización en tiempo real**: Estado actualizado instantáneamente
- ✅ **Acceso offline**: Favoritos disponibles sin internet
- ✅ **Botones intuitivos**: Corazón rojo/gris en toda la app

### 🌍 **Traducción Automática**
- ✅ **ML Kit de Google**: Traducciones de alta calidad
- ✅ **Funcionamiento offline**: Tras descarga inicial del modelo
- ✅ **Traducción completa**: Todos los textos de la API
- ✅ **Preservación de formato**: Mantiene mayúsculas y estructura

---

## 🤝 Contribuir

¿Quieres contribuir? ¡Genial! 🎉

1. 🍴 **Fork** el proyecto
2. 🌿 **Crea una rama** para tu feature (`git checkout -b feature/AmazingFeature`)
3. 💾 **Commit** tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. 📤 **Push** a la rama (`git push origin feature/AmazingFeature`)
5. 🔄 **Abre un Pull Request**

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

## 👨‍💻 Autor

**Rodrigo Angeloni**
- 📧 Email: [roandev87@gmail.com]
- 🐙 GitHub: [@rodrigoangeloni](https://github.com/rodrigoangeloni)

---

<div align="center">

**¡Disfruta explorando el mundo de las recetas con FlavorFusion! 🍽️✨**

⭐ **¿Te gusta el proyecto? ¡Dale una estrella!** ⭐

</div>
