# Spring AI - Chat con Mistral AI

> API REST desarrollada con Spring Boot y Spring AI para interactuar con el modelo de lenguaje de Mistral AI.

## Tabla de Contenidos

- [Descripción](#descripción-del-proyecto)
- [Tecnologías](#tecnologías-usadas)
- [Estructura](#estructura-principal)
- [Flujo Funcional](#flujo-funcional-implementado)
- [Endpoints](#endpoints)
- [Configuración](#configuración)
- [Ejecución](#cómo-ejecutar)

---

## Descripción del Proyecto

Una API REST que actúa como proxy hacia el modelo de lenguaje de Mistral AI, permitiendo:

- Enviar preguntas al modelo LLM a través de un endpoint HTTP
- Recibir respuestas generadas por la IA de Mistral
- Utilizar un system prompt personalizado para contextualizar las respuestas del modelo

El sistema utiliza Spring AI para la integración con el proveedor de IA y expone un endpoint sencillo para consultas vía POSTMAN o cualquier cliente HTTP.

## Tecnologías Usadas

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| Java | 26 | Lenguaje base |
| Spring Boot | 4.1.1 | Framework web y REST |
| Spring AI | 2.0.1 | Integración con modelos de IA |
| Mistral AI | open-mistral-7b | Modelo de lenguaje |
| Spring Web (MVC) | Incluido | Controladores HTTP |
| Maven | Wrapper | Gestor de dependencias |

## Estructura Principal

```
SpringAI/
├── src/
│   ├── main/
│   │   ├── java/com/example/SpringAI/
│   │   │   ├── SpringAiApplication.java
│   │   │   ├── controller/
│   │   │   │   └── AIController.java
│   │   │   └── service/
│   │   │       └── AIService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/SpringAI/
│           └── SpringAiApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Flujo Funcional Implementado

```
┌─────────────────────────────────────────────────────────────┐
│                    SPRING AI - MISTRAL AI                   │
├─────────────────────────────────────────────────────────────┤
│  1. GET /api/ai/ask?ask={pregunta}                          │
│     ↓ El usuario envía una pregunta por HTTP                │
│                                                             │
│  2. AIController recibe la petición                         │
│     ↓ Delega al AIService                                   │
│                                                             │
│  3. AIService construye el prompt                           │
│     ↓ System prompt + mensaje del usuario                   │
│                                                             │
│  4. ChatClient invoca la API de Mistral AI                  │
│     ↓ Envía el prompt al modelo open-mistral-7b             │
│                                                             │
│  5. Retorna la respuesta como String                        │
│     ↓ El controller devuelve la respuesta HTTP 200          │
└─────────────────────────────────────────────────────────────┘
```

## Endpoints

| Endpoint | Método | Parámetros | Descripción |
|----------|--------|------------|-------------|
| `/api/ai/ask` | GET | `ask` (query param) | Envía una pregunta al modelo Mistral AI y retorna la respuesta |

### Ejemplo con POSTMAN

**Request:**

```
GET http://localhost:8080/api/ai/ask?ask=¿Cuáles son los mejores animes de la última temporada?
```

**Response (200 OK):**

```
(Respuesta generada por Mistral AI)
```

## Configuración

### Variables de Entorno

| Variable | Descripción | Requerido |
|----------|-------------|-----------|
| `MISTRALAI_API_KEY` | API Key de Mistral AI para autenticación | Sí |

### application.properties

```properties
spring.application.name=SpringAI

spring.ai.mistralai.api-key=${MISTRALAI_API_KEY}
spring.ai.mistralai.base-url=https://api.mistral.ai/
spring.ai.mistralai.chat.model=open-mistral-7b
```

> **Nota:** El system prompt está configurado para responder únicamente preguntas sobre animes de MyAnimeList ordenados por la última temporada. Para modificar el comportamiento, editar el prompt en `AIService.java`.

## Funcionalidades Implementadas

- [x] Controlador REST con endpoint GET para consultas
- [x] Servicio de integración con Spring AI y Mistral AI
- [x] System prompt personalizado para restringir el dominio de respuestas
- [x] Configuración externa de API Key por variable de entorno
- [x] Modelo open-mistral-7b como backend de IA

## Cómo Ejecutar

### Requisitos Previos

- Java 26+
- Maven 3.6+ (incluido como wrapper)
- API Key de Mistral AI (obtener en [https://console.mistral.ai/](https://console.mistral.ai/))

### Configurar Variable de Entorno

**En Windows (PowerShell):**

```powershell
$env:MISTRALAI_API_KEY="tu-api-key-aqui"
```

**En Linux/macOS:**

```bash
export MISTRALAI_API_KEY="tu-api-key-aqui"
```

### Ejecutar la Aplicación

**En Windows (PowerShell):**

```powershell
.\mvnw.cmd spring-boot:run
```

**En Linux/macOS:**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

### Probar el Endpoint

Una vez iniciada la aplicación, realizar una petición con POSTMAN:

```
GET http://localhost:8080/api/ai/ask?ask=¿Cuáles son los mejores animes de la última temporada?
```

**Puerto por defecto:** 8080

---

## Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring AI Reference](https://docs.spring.io/spring-ai/reference/)
- [Mistral AI Documentation](https://docs.mistral.ai/)
- [Spring AI - Mistral AI Integration](https://docs.spring.io/spring-ai/reference/api/chat/mistralai-chat.html)

---

## Licencia

Este proyecto es de uso académico y educativo.
