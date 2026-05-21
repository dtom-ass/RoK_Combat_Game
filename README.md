# RoK Combat Game

Juego de combate por turnos desarrollado en Java y JavaFX.

---

## Descripción

Proyecto académico enfocado en:

- Programación Orientada a Objetos
- Arquitectura MVC
- Sistema de combate por turnos
- Generación aleatoria de equipos
- Interfaz gráfica con JavaFX
- Organización modular del proyecto

Actualmente el juego permite:

- Seleccionar una cultura
- Generar equipos aleatorios
- Realizar combate entre aliados y enemigos
- Cambiar guerreros durante la batalla
- Mostrar estadísticas en tiempo real
- Detectar victoria o derrota

---

## Tecnologías

- Java
- JavaFX
- CSS
- VSCode

---

## Arquitectura

El proyecto está organizado utilizando MVC:

```text
MODEL
→ Cultura
→ Guerreros
→ Estadísticas

VIEW
→ Escenas JavaFX
→ CSS
→ Componentes visuales

CONTROLLER
→ Combate
→ Selección
→ Gestión de jugador y enemigo
```

---

## Estructura del proyecto

```text
ROK_COMBAT_GAME/
│
├── app/
│   └── Main.java
│
├── controller/
│   ├── BattleController.java
│   ├── EnemyBot.java
│   ├── PlayerController.java
│   ├── PlayerPanel.java
│   ├── SelectionController.java
│   └── WarriorCard.java
│
├── model/
│   ├── Culture.java
│   ├── Warrior.java
│   │
│   ├── Cultures/
│   │   ├── AztecaCulture.java
│   │   ├── IncaCulture.java
│   │   ├── MayaCulture.java
│   │   └── MuiscaCulture.java
│   │
│   └── Warriors/
│       ├── Archer.java
│       ├── Fighter.java
│       ├── Healer.java
│       ├── Lancer.java
│       └── Tank.java
│
├── view/
│   ├── console/
│   │   └── ConsoleLog.java
│   │
│   └── scenes/
│       ├── WelcomeScene.java
│       ├── SelectionScene.java
│       ├── BattleScene.java
│       ├── EndScene.java
│       │
│       └── styles/
│           ├── Welcome.css
│           ├── Selection.css
│           ├── Battle.css
│           ├── End.css
│           └── imgs/
│                └── welcome_background.jpg
│
├── lib/
│   └── JavaFX libraries
│
└── .vscode/
    └── launch.json
```

---

## Escenas del juego

### WelcomeScene

Pantalla principal del juego.

Opciones:

- Iniciar juego
- Salir

Incluye:

- Fondo personalizado
- Estilo CSS independiente

---

### SelectionScene

Permite seleccionar la cultura del jugador.

Culturas disponibles:

- Azteca
- Maya
- Inca
- Muisca

Al seleccionar una cultura:

- Se genera un equipo aleatorio
- Inicia automáticamente la batalla

---

### BattleScene

Escenario principal de combate.

Características:

- 3 guerreros aliados
- 3 guerreros enemigos
- Barras de vida dinámicas
- Cambio visual al morir
- Armas visibles
- Estadísticas en tiempo real
- Cambio de guerrero activo

Acciones disponibles:

- Ataque básico
- Ataque especial
- Cambiar guerrero

---

### EndScene

Pantalla final del juego.

Muestra:

- Victoria
- Derrota

Opciones:

- Jugar nuevamente
- Salir

---

## Sistema de combate

### Ataque básico

Daño aproximado:

```text
2 - 5
```

---

### Ataque especial

Tiene probabilidad crítica.

Daño aproximado:

```text
5 - 10
```

---

### Defensa

La defensa reduce el daño recibido.

---

### Condición de victoria

La batalla termina cuando:

```text
Todos los guerreros de un equipo son eliminados.
```

---

## JavaFX

La carpeta:

```text
/lib
```

contiene las librerías necesarias para ejecutar JavaFX localmente.

---

## launch.json

Configuración utilizada:

```json
"vmArgs": "--module-path \"${workspaceFolder}/lib\" --add-modules javafx.controls,javafx.fxml"
```

---

## Ejecutar el proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/dtom-ass/RoK_Combat_Game.git
```

---

### 2. Abrir en VSCode

---

### 3. Ejecutar

```text
Main.java
```

---

## Estado actual

El proyecto continúa en desarrollo.

Actualmente se trabaja en:

- Mejora visual
- Balance de combate
- Optimización de controladores
- Refactorización MVC
- Mejoras de interfaz JavaFX

---

## Objetivos futuros

- Animaciones
- Sistema de habilidades
- IA enemiga más compleja
- Sonidos y música
- Inventario
- Persistencia de partidas
- Selección manual de guerreros
- Efectos visuales de combate

---

## Autor

Proyecto desarrollado con fines académicos y práctica de arquitectura MVC en Java.