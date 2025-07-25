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

### 🔍 **Búsqueda Inteligente en Español** ⭐ **¡NOVEDAD!**
- 🇪🇸 **Búsquedas en español** con traducción automática
- 🍽️ **Explorar comidas** escribiendo "pollo", "pasta", "tacos"
- 🍹 **Descubrir bebidas** con "mojito", "café", "cerveza"
- ⌨️ **Búsqueda con Enter** o botón dedicado
- 💡 **Sugerencias visuales** con chips interactivos
- 🔄 **Traducción transparente** que muestra el proceso
- 🧹 **Limpieza automática** de resultados

### ❤️ **Sistema de Favoritos**
- 💾 **Almacenamiento local** con Room Database
- 📱 **Acceso offline** a recetas guardadas
- ♻️ **Sincronización en tiempo real**

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
- 💿 **100MB** espacio libre
- 🌐 **Conexión a internet** para búsquedas

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
- 📱 Usa la **navegación inferior** para cambiar de sección

#### 2. 🔍 Explorar Recetas

##### 🍽️ **Buscar Comidas en Español** ⭐ **¡NUEVA FUNCIONALIDAD!**
```
1. Toca la sección "Explorar Comidas"
2. Escribe en ESPAÑOL: "pollo", "pasta", "tacos", "hamburguesa"
3. Ve las sugerencias automáticas en chips
4. Ejecuta la búsqueda:
   • Presiona Enter ⏎
   • Toca el botón de búsqueda 🔍
5. ¡La app traduce automáticamente y muestra resultados!
```

**Ejemplos que funcionan perfectamente:**
- "pollo" → encuentra recetas de chicken
- "pasta" → encuentra recetas de pasta  
- "carne" → encuentra recetas de beef
- "pescado" → encuentra recetas de fish
- "pizza" → encuentra recetas de pizza

##### 🍹 **Buscar Bebidas en Español** ⭐ **¡NUEVA FUNCIONALIDAD!**
```
1. Ve a "Explorar Bebidas"
2. Escribe en ESPAÑOL: "mojito", "café", "cerveza", "té"
3. Ve sugerencias específicas para bebidas
4. La traducción es automática y transparente
5. Descubre cócteles y bebidas internacionales
```

**Ejemplos que funcionan perfectamente:**
- "café" → encuentra recetas de coffee
- "mojito" → encuentra recetas de mojito
- "cerveza" → encuentra recetas de beer
- "té" → encuentra recetas de tea
- "batido" → encuentra smoothies

#### 3. 📋 **Ver Detalles de Recetas**
- 🖼️ **Imagen en alta calidad**
- 📝 **Información completa**: nombre, categoría, origen
- 🥄 **Lista de ingredientes** con cantidades exactas
- 👨‍🍳 **Instrucciones paso a paso**
- ❤️ **Botón de favoritos** para guardar

#### 4. ❤️ **Gestionar Favoritos**
- **Marcar favorito**: Toca el corazón ❤️ en cualquier receta
- **Ver favoritos**: Usa la sección dedicada en navegación
- **Acceso offline**: Los favoritos funcionan sin internet
- **Eliminar**: Toca nuevamente el corazón para quitar

---

## 🎛️ **Estados de la Aplicación**

| Estado | Icono | Descripción |
|--------|-------|-------------|
| **Cargando** | 🔄 | Spinner mientras se cargan datos |
| **Sin resultados** | 🚫 | Mensaje cuando no hay coincidencias |
| **Error** | ⚠️ | Problema de conexión con botón reintentar |
| **Favorito activo** | ❤️ | Receta guardada en favoritos |
| **Favorito inactivo** | 🤍 | Receta no guardada |

---

## 💡 **Tips y Trucos**

### 🔍 **Búsquedas Efectivas**
- ✅ **Usa palabras simples**: "chicken" > "pollo con verduras"
- ✅ **Prueba en inglés**: Las APIs funcionan mejor
- ✅ **Sé específico**: "pasta" > "comida italiana"
- ✅ **Experimenta**: Prueba diferentes términos

### ❤️ **Gestión de Favoritos**
- 🗂️ **Organiza mentalmente** por tipo de comida
- 📱 **Aprovecha el acceso offline**
- 🧹 **Limpia periódicamente** favoritos no usados

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

- ✅ **Espera la confirmación** visual (corazón rojo)
- ✅ **Verifica espacio** en tu dispositivo
- ✅ **Reinicia la aplicación**
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
- 💾 **Database**: Room
- 🌐 **Network**: Retrofit + Gson
- ⚡ **Async**: Kotlin Coroutines + Flow
- 🧭 **Navigation**: Jetpack Navigation Compose

### 🌐 **APIs Utilizadas**
- 🍽️ **[TheMealDB](https://www.themealdb.com/api.php)** - Recetas de comidas
- 🍹 **[TheCocktailDB](https://www.thecocktaildb.com/api.php)** - Recetas de bebidas

---

## 📊 **Estructura del Proyecto**

```
📁 FlavorFusion2/
├── 📁 app/src/main/java/com/rodrigoangeloni/flavorfusion/
│   ├── 🎨 components/          # Componentes UI reutilizables
│   ├── 💾 database/           # Room database y DAOs
│   ├── 💉 di/                 # Módulos de Hilt
│   ├── 📱 model/              # Modelos de datos
│   ├── 🌐 network/            # Servicios API y DTOs
│   ├── 📊 repository/         # Repository pattern
│   ├── 🖥️ screens/            # Pantallas Compose
│   ├── 🎨 ui/theme/           # Tema y estilos
│   ├── 🧠 viewmodels/         # ViewModels MVVM
│   └── 🚀 MainActivity.kt     # Actividad principal
└── 📁 doc/                   # Documentación
    ├── 📋 objetivo-y-funcionalidades.md
    ├── 🏗️ diagrama-arquitectura.md
    └── 📖 manual-de-uso.md
```

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
- 📧 Email: [tu-email@ejemplo.com]
- 🐙 GitHub: [@tu-usuario](https://github.com/tu-usuario)
- 💼 LinkedIn: [Tu perfil](https://linkedin.com/in/tu-perfil)

---

<div align="center">

**¡Disfruta explorando el mundo de las recetas! 🍽️✨**

⭐ **¿Te gusta el proyecto? ¡Dale una estrella!** ⭐

</div>
