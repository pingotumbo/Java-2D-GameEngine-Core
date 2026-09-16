# Java 2D Game Engine (ECS Architecture Showcase)

A modular 2D game engine core developed in Java, engineered with a focus on Clean Architecture, **Composition over Inheritance**, and a hybrid **MVC / ECS (Entity Component System)** pattern.

## Key Technical Features

* **Asynchronous Game Loop & Thread-Dedicated Flow (`GameFlow`)**: Frame rate stabilization and centralized Delta Time management, ensuring absolute independence of physics and animations from execution framerate.
* **Decoupled Architecture**: Strict separation of concerns leveraging ECS, MVC, Singleton (`TimeManager`, `EventManager`), Factory (`UIFactory`), and State (`GameState`) design patterns.
* **Cascading Visual Rendering Pipeline**: Painter's Algorithm sorting via `RenderLayerComponent`, resolving graphic composition hierarchically:
  `SpriteComponent (primitive) ➔ SequenceComponent (animation) ➔ PoseComponent (visual state machine)`
* **Spatial & Modifier Isolation**: Spatial kinematics ($x, y$, rotation, scale) isolated inside `TransformComponent`, with decoupled transversal visual modifiers (`ColorTintComponent`, `OpacityComponent`).
* **Event-Driven Communication**: Asynchronous event bus (`EventManager`) enabling decoupled communication across isolated game states and logic modules.

---

## Video & Engine Demonstration

Below is a preview of the engine boot sequence, architecture initialization, and scene loading pipeline in action:
