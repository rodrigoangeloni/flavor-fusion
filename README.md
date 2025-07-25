# 🍽️ FlavorFusion2 - Descubre el Mundo de las Recetas ✨

<div align="center">

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![API](https://img.shields.io/badge/Min%20API-24-orange.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)

**Una aplicación móvil moderna para explorar y gestionar recetas de comidas y bebidas** 🚀

[📱 Instalación](#-instalación) • [🔍 Características](#-características) • [📖 Manual de Uso](#-manual-de-uso) • [🏗️ Arquitectura](#️-arquitectura)

</div>

---

## 🌟 Características Principales

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

### ❤️ **Sistema de Favoritos Completo** ⭐ **¡IMPLEMENTADO!**
- 💾 **Almacenamiento local** con Room Database SQLite
- 📱 **Acceso offline** a recetas guardadas sin internet
- ♻️ **Sincronización en tiempo real** entre todas las pantallas
- 🎯 **Botón de favorito** disponible en toda la aplicación
- 📋 **Pantalla dedicada** para gestionar favoritos
- 🔄 **Alternado inteligente** - agrega/quita con un toque
- 💡 **Estado visual claro** - corazón rojo/gris

### 🌐 **Datos en Vivo**
- 📡 **APIs externas** (TheMealDB + TheCocktailDB)
- 🔄 **Contenido actualizado** constantemente
- 🛡️ **Manejo robusto de errores**
- 🌍 **Interfaz 100% en español**

---

## 📱 Instalación

### Requisitos del Sistema
- 📋 **Android 7.0** (API 24) o superior
- 💾 **2GB RAM** recomendado
- 💿 **150MB** espacio libre (100MB app + 50MB favoritos)
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
- Al abrir la app, verás **sugerencias del día**
- 🍽️ **Comida destacada** - Receta aleatoria de comida
- 🍹 **Bebida destacada** - Cóctel o bebida especial
- ❤️ **Botones de favorito** - Guarda directamente desde inicio
- 📱 Usa la **navegación inferior** para cambiar de sección

#### 2. 🔍 Explorar Recetas

##### 🍽️ **Buscar Comidas**
```
1. Toca la sección "Explorar Comidas"
2. Escribe: "chicken", "pasta", "beef", "pizza"
3. Ejecuta la búsqueda:
   • Presiona Enter ⏎
   • Toca el botón de búsqueda 🔍
4. ❤️ Marca favoritos directamente desde resultados
```

##### 🍹 **Buscar Bebidas**
```
1. Ve a "Explorar Bebidas"
2. Escribe: "mojito", "coffee", "beer", "tea"
3. Encuentra cócteles y bebidas internacionales
4. ❤️ Guarda tus bebidas favoritas instantáneamente
```

#### 3. 📋 **Ver Detalles de Recetas**
- 🖼️ **Imagen en alta calidad**
- ❤️ **Botón de favorito prominente** en la parte superior
- 📝 **Información completa**: nombre, categoría, origen
- 🥄 **Lista de ingredientes** con cantidades exactas
- 👨‍🍳 **Instrucciones paso a paso**

#### 4. ❤️ **Gestionar Favoritos** ⭐ **¡NUEVA FUNCIONALIDAD!**

##### **Marcar como Favorito**
- **Desde cualquier pantalla**: Toca el corazón ❤️ 
- **Se pone rojo**: Indica que se guardó exitosamente
- **Guardado instantáneo**: No necesitas confirmar

##### **Ver tus Favoritos**
- **Pantalla dedicada**: Toca "Favoritos" en navegación inferior
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
- 📱 **Barra inferior**: Siempre accesible
- ← **Botón atrás**: Consistente en toda la app
- 👆 **Gestos**: Deslizar para actualizar

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
- ✅ **El problema está resuelto**: Nueva implementación con Room Database
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
- 💾 **Database**: Room SQLite ⭐ **Para favoritos**
- 🌐 **Network**: Retrofit + Gson
- ⚡ **Async**: Kotlin Coroutines + Flow
- 🧭 **Navigation**: Jetpack Navigation Compose

### 🌐 **APIs Utilizadas**
- 🍽️ **[TheMealDB](https://www.themealdb.com/api.php)** - Recetas de comidas
- 🍹 **[TheCocktailDB](https://www.thecocktaildb.com/api.php)** - Recetas de bebidas

### 💾 **Persistencia de Datos** ⭐ **NUEVO**
```kotlin
// Entidad Room para favoritos
@Entity(tableName = "favoritos")
data class Receta(
    @PrimaryKey val id: String,
    val nombre: String,
    val imagen: String,
    val categoria: String,
    val area: String,
    val instrucciones: String,
    val ingredientes: String,
    val tipo: String, // "meal" o "drink"
    val fechaAgregado: Long
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

---

## 📊 **Estructura del Proyecto**

```
📁 FlavorFusion2/
├── 📁 app/src/main/java/com/rodrigoangeloni/flavorfusion/
│   ├── 💾 database/           # Room database y DAOs ⭐ NUEVO
│   │   ├── BaseDeDatos.kt     # Configuración Room
│   │   └── RecetaDao.kt       # Operaciones favoritos
│   ├── 💉 di/                 # Módulos de Hilt
│   ├── 📱 model/              # Modelos de datos + DTOs
│   ├── 🌐 network/            # Servicios API 
│   ├── 📊 repository/         # Repository pattern ⭐ ACTUALIZADO
│   ├── 🖥️ screens/            # Pantallas Compose
│   │   ├── PantallaInicio.kt
│   │   ├── PantallaFavoritos.kt ⭐ NUEVO
│   │   ├── PantallaExplorarComidas.kt
│   │   ├── PantallaExplorarBebidas.kt
│   │   └── PantallaDetalleReceta.kt
│   ├── 🎨 ui/theme/           # Tema y estilos
│   ├── 🧠 viewmodels/         # ViewModels MVVM ⭐ ACTUALIZADO
│   └── 🚀 MainActivity.kt     # Actividad principal
└── 📁 doc/                   # Documentación ⭐ ACTUALIZADA
    ├── 📋 objetivo-y-funcionalidades.md
    ├── 🏗️ diagrama-arquitectura.md
    └── 📖 manual-de-uso.md
```

---

## 🚀 **Lo Nuevo en esta Versión** ⭐

### ❤️ **Sistema de Favoritos Completo**
- ✅ **Base de datos local**: Room SQLite para persistencia
- ✅ **Pantalla dedicada**: Gestión completa de favoritos
- ✅ **Sincronización en tiempo real**: Estado actualizado instantáneamente
- ✅ **Acceso offline**: Favoritos disponibles sin internet
- ✅ **Botones intuitivos**: Corazón rojo/gris en toda la app

### 🏗️ **Arquitectura Mejorada**
- ✅ **Repository actualizado**: Maneja API + Database
- ✅ **ViewModel renovado**: Estado unificado con Flow reactivo  
- ✅ **Modelo de datos unificado**: Una entidad para comidas y bebidas
- ✅ **Inyección de dependencias**: Configuración completa con Hilt

### 📖 **Documentación Completa**
- ✅ **Manual de uso actualizado**: Instrucciones detalladas de favoritos
- ✅ **Diagrama de arquitectura**: Refleja nueva implementación
- ✅ **Objetivos y funcionalidades**: Lista completa de características

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

**¡Disfruta explorando el mundo de las recetas con favoritos que nunca se pierden! 🍽️✨**

⭐ **¿Te gusta el proyecto? ¡Dale una estrella!** ⭐

**Nueva funcionalidad de favoritos implementada y completamente funcional** ❤️

</div>
