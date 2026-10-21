# Desarrollo de API con Arquitectura Hexagonal

La empresa necesita desarrollar una API para gestionar las solicitudes de préstamos de sus clientes. La API debe ser capaz de recibir solicitudes, validar los datos de entrada, consultar el historial crediticio del cliente a través de un servicio externo, y determinar si la solicitud es aprobada o rechazada. La arquitectura debe ser hexagonal para permitir una fácil extensión y mantenimiento. Los clientes enviarán solicitudes a través de tres canales diferentes: web, móvil y API externa. La API debe ser idempotente y capaz de manejar la presión de backpressure de los canales. En caso de fallo del servicio externo de historial crediticio, la API debe ser capaz de recuperarse automáticamente sin reprocesar las solicitudes ya emitidas.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | uso y aplicacion framework |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del entorno y canal de entrada

**Objetivo:** Establecer un canal de entrada operativo que persista las solicitudes con idempotencia.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identificar los tres canales de entrada y sus características.
- Diseñar un mecanismo de idempotencia para las solicitudes entrantes.
- Persistir cada solicitud con una clave de idempotencia.

**Entregable:** Canal de entrada operativo persistiendo solicitudes con idempotencia.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo los diferentes canales pueden afectar la consistencia de los datos.
- Piensa en cómo manejarías la duplicación de solicitudes.

</details>

### Fase 2: Consolidación de canales y manejo de backpressure

**Objetivo:** Consolidar los tres canales de entrada sin pérdida de datos ante backpressure de uno de ellos.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Diseñar un mecanismo para consolidar las solicitudes de los tres canales.
- Implementar un manejo de backpressure que no pierda solicitudes.

**Entregable:** Consolidación de los tres canales sin pérdida ante backpressure de uno.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo los diferentes canales pueden afectar la carga del sistema.
- Piensa en cómo manejarías la presión de backpressure sin perder datos.

</details>

### Fase 3: Recuperación automática ante fallo del servicio externo

**Objetivo:** Implementar una recuperación automática ante caída del servicio externo de historial crediticio sin reprocesar solicitudes emitidas.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Diseñar un mecanismo de recuperación automática para el servicio externo.
- Asegurar que las solicitudes ya emitidas no sean reprocesadas.

**Entregable:** Recuperación automática ante caída del consumidor downstream sin reprocesar emitidas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo los fallos del servicio externo pueden afectar la operación de la API.
- Piensa en cómo manejarías la recuperación sin reprocesar solicitudes ya emitidas.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la arquitectura hexagonal y cómo se aplica en este reto?
- **paraQueSirve**: ¿Para qué sirve la idempotencia en este contexto?
- **comoSeUsa**: ¿Cómo se usa la idempotencia para manejar solicitudes duplicadas?
- **erroresComunes**: ¿Cuáles son los errores comunes al manejar la presión de backpressure y cómo se evitan?
- **queDecisionesImplica**: ¿Qué decisiones implica la recuperación automática ante fallo del servicio externo?

## Criterios de Evaluacion

- Implementación de un canal de entrada idempotente.
- Consolidación de los tres canales sin pérdida ante backpressure.
- Recuperación automática ante caída del servicio externo sin reprocesar solicitudes emitidas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
npx --yes @redocly/cli lint openapi.yaml
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
