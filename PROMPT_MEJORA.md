# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/empresa/prestamos/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/empresa/prestamos/PrestamosApplication.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/application/ports/in/LoanRequestUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/application/ports/out/LoanRequestPersistencePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/application/ports/out/CreditCheckServicePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/config/IdempotencyConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/config/ReactorConfig.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Scheduler pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/empresa/prestamos/application/service/LoanRequestServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestDTO.customerId`: Se invoca `customerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestDTO.amount`: Se invoca `amount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestDTO.termMonths`: Se invoca `termMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestUseCase.submitRequest`: Se invoca `submitRequest` sobre `LoanRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.customerId`: Se invoca `customerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.idempotencyKey`: Se invoca `idempotencyKey` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.requestId`: Se invoca `requestId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.amount`: Se invoca `amount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.termMonths`: Se invoca `termMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.requestDate`: Se invoca `requestDate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.status`: Se invoca `status` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.creditScore`: Se invoca `creditScore` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.approvedAmount`: Se invoca `approvedAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.rejectionReason`: Se invoca `rejectionReason` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestService.getRequestById`: Se invoca `getRequestById` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestService.streamPendingRequests`: Se invoca `streamPendingRequests` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestService.listRequestsByCustomer`: Se invoca `listRequestsByCustomer` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.idempotencyKey`: Se invoca `idempotencyKey` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestService.getRequestById`: Se invoca `getRequestById` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestService.listRequestsByCustomer`: Se invoca `listRequestsByCustomer` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.requestId`: Se invoca `requestId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.customerId`: Se invoca `customerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.amount`: Se invoca `amount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.termMonths`: Se invoca `termMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.requestDate`: Se invoca `requestDate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.status`: Se invoca `status` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.creditScore`: Se invoca `creditScore` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.approvedAmount`: Se invoca `approvedAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.rejectionReason`: Se invoca `rejectionReason` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `LoanStatus.name`: Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `LoanRequestEntity.setLastUpdated`: Se invoca `setLastUpdated` sobre `LoanRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `LoanRequestEntity.setChannel`: Se invoca `setChannel` sobre `LoanRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckCacheEntry.isExpired`: Se invoca `isExpired` sobre `CreditCheckCacheEntry`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckCacheEntry.getResult`: Se invoca `getResult` sobre `CreditCheckCacheEntry`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckResult.score`: Se invoca `score` sobre `CreditCheckResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckResponse.score`: Se invoca `score` sobre `CreditCheckResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckResponse.reference`: Se invoca `reference` sobre `CreditCheckResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/empresa/prestamos/application/service/LoanRequestServiceTest.java` — `LoanRequestService.processLoanRequest`: Se invoca `processLoanRequest` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java` — `LoanRequestService.processLoanRequest`: Se invoca `processLoanRequest` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java` — `LoanRequestService.findById`: Se invoca `findById` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `org.apache.camel:camel-spring-boot-bom@4.4.0`: org.apache.camel:camel-spring-boot-bom declara la version 4.4.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
npx --yes @redocly/cli lint openapi.yaml
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Integración, Especialidad Desarrollador, Tecnología API, Advanced

### Brecha de conocimiento
Creacion de artefactos con patrones de desarrollo en arquitectura hexagonal o dominio y muestra aplicacion de desarrollo en lenguaje programacion dominante. Uso de framework y aplicacion en entorno práctico. El uso aplicado en desarrollo en cliente usando el lenguaje dando valor de artefactos

### Misión / candidato
Candidato con experiencia en soluciones de integración y APIs

### Reto
- Tema: uso y aplicacion framework
- Seniority: advanced-l2
- Tipo: practical
- Título: Desarrollo de API con Arquitectura Hexagonal
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del entorno y canal de entrada — objetivo: Establecer un canal de entrada operativo que persista las solicitudes con idempotencia. — entregable (NO resolver): Canal de entrada operativo persistiendo solicitudes con idempotencia.
- Fase 2: Consolidación de canales y manejo de backpressure — objetivo: Consolidar los tres canales de entrada sin pérdida de datos ante backpressure de uno de ellos. — entregable (NO resolver): Consolidación de los tres canales sin pérdida ante backpressure de uno.
- Fase 3: Recuperación automática ante fallo del servicio externo — objetivo: Implementar una recuperación automática ante caída del servicio externo de historial crediticio sin reprocesar solicitudes emitidas. — entregable (NO resolver): Recuperación automática ante caída del consumidor downstream sin reprocesar emitidas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.empresa</groupId>
    <artifactId>prestamos</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>prestamos</name>
    <description>API para gestion de solicitudes de prestamos con arquitectura hexagonal</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.apache.camel</groupId>
                <artifactId>camel-spring-boot-bom</artifactId>
                <version>4.4.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>io.github.resilience4j</groupId>
                <artifactId>resilience4j-bom</artifactId>
                <version>2.1.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>io.projectreactor</groupId>
                <artifactId>reactor-bom</artifactId>
                <version>2023.0.5</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Apache Camel -->
        <dependency>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-jackson</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-reactive-streams-starter</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot2</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
        </dependency>

        <!-- Reactor -->
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
        </dependency>
        <dependency>
            <groupId>io.projectreactor.netty</groupId>
            <artifactId>reactor-netty</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/empresa/prestamos/PrestamosApplication.java ===
package com.empresa.prestamos;


import com.empresa.prestamos.infrastructure.adapters.out.creditcheck.CreditCheckServiceAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.scheduler.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableWebFlux
@EnableAsync
public class PrestamosApplication {
    
    private final IdempotencyManager idempotencyManager;
    private final LoanRequestProcessor loanRequestProcessor;
    private final CreditCheckServiceAdapter creditCheckServiceAdapter;
    
    public PrestamosApplication(IdempotencyManager idempotencyManager,
                                LoanRequestProcessor loanRequestProcessor,
                                CreditCheckServiceAdapter creditCheckServiceAdapter) {
        this.idempotencyManager = idempotencyManager;
        this.loanRequestProcessor = loanRequestProcessor;
        this.creditCheckServiceAdapter = creditCheckServiceAdapter;
        
        validateDependencies();
    }
    
    private void validateDependencies() {
        if (idempotencyManager == null) {
            throw new IllegalStateException("IdempotencyManager no puede ser null");
        }
        if (loanRequestProcessor == null) {
            throw new IllegalStateException("LoanRequestProcessor no puede ser null");
        }
        if (creditCheckServiceAdapter == null) {
            throw new IllegalStateException("CreditCheckServiceAdapter no puede ser null");
        }
    }
    
    @Bean
    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }
    
    @Bean
    public reactor.core.scheduler.Scheduler reactorScheduler() {
        return Schedulers.newBoundedElastic(
            Runtime.getRuntime().availableProcessors() * 2,
            Integer.MAX_VALUE,
            "reactor-pool"
        );
    }
    
    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }
    
    // Clase interna para manejar la idempotencia
    public static class IdempotencyManager {
        private final java.util.Map<String, Boolean> idempotencyCache;
        
        public IdempotencyManager() {
            this.idempotencyCache = new java.util.concurrent.ConcurrentHashMap<>();
        }
        
        public boolean isIdempotent(String requestId) {
            if (requestId == null || requestId.trim().isEmpty()) {
                throw new IllegalArgumentException("requestId no puede ser null o vacio");
            }
            return idempotencyCache.putIfAbsent(requestId, Boolean.TRUE) != null;
        }
        
        public void clear(String requestId) {
            idempotencyCache.remove(requestId);
        }
    }
    
    // Clase interna para procesar solicitudes de préstamo
    public static class LoanRequestProcessor {
        private final reactor.core.publisher.Sinks.Many<LoanRequestDTO> requestSink;
        private final reactor.core.publisher.Flux<LoanRequestDTO> requestFlux;
        
        public LoanRequestProcessor() {
            this.requestSink = reactor.core.publisher.Sinks.many().multicast().onBackpressureBuffer();
            this.requestFlux = requestSink.asFlux();
        }
        
        public void processRequest(LoanRequestDTO request) {
            if (request == null) {
                throw new IllegalArgumentException("La solicitud no puede ser null");
            }
            requestSink.tryEmitNext(request);
        }
        
        public reactor.core.publisher.Flux<LoanRequestDTO> getRequestFlux() {
            return requestFlux;
        }
    }
    
    // DTO básico para la solicitud de préstamo
    public record LoanRequestDTO(
        String requestId,
        String customerId,
        double amount,
        int termMonths,
        String channel
    ) {
        public LoanRequestDTO {
            if (requestId == null || requestId.trim().isEmpty()) {
                throw new IllegalArgumentException("requestId es obligatorio");
            }
            if (customerId == null || customerId.trim().isEmpty()) {
                throw new IllegalArgumentException("customerId es obligatorio");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("amount debe ser positivo");
            }
            if (termMonths <= 0) {
                throw new IllegalArgumentException("termMonths debe ser positivo");
            }
            if (channel == null || channel.trim().isEmpty()) {
                throw new IllegalArgumentException("channel es obligatorio");
            }
        }
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: prestamos
  
  webflux:
    base-path: /api
    
  datasource:
    url: jdbc:h2:mem:prestamosdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ""
    
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true
  
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080
  error:
    include-message: always
    include-binding-errors: always
    include-stacktrace: on_param

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,env,configprops
  endpoint:
    health:
      show-details: always

camel:
  springboot:
    main-run-controller: true
    name: PrestamosCamel
    
  component:
    servlet:
      mapping:
        context-path: /camel/*

integration:
  idempotency:
    enabled: true
    cache:
      ttl: 3600
      max-size: 10000
    key-header: X-Request-ID
  
  backpressure:
    buffer-size: 1000
    overflow-strategy: DROP_OLDEST
  
  credit-check-service:
    base-url: https://api.external-credit-service.com/v1
    timeout: 5000
    retry:
      max-attempts: 3
      delay: 1000
      multiplier: 2.0
    circuit-breaker:
      failure-rate-threshold: 50
      wait-duration-in-open-state: 30000
      permitted-number-of-calls-in-half-open-state: 10
      sliding-window-size: 20
      sliding-window-type: COUNT_BASED

logging:
  level:
    root: INFO
    com.empresa.prestamos: DEBUG
    org.apache.camel: INFO
    org.springframework.web: INFO
    reactor.netty: INFO
"

// === ARCHIVO: src/main/java/com/empresa/prestamos/domain/model/LoanRequest.java ===
package com.empresa.prestamos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio que representa una solicitud de préstamo.
 * Contiene los atributos esenciales para la evaluación crediticia y
 * la lógica de negocio asociada.
 */
public final class LoanRequest {
    private final UUID requestId;
    private final String customerId;
    private final BigDecimal amount;
    private final Integer termMonths;
    private final LocalDate requestDate;
    private final LoanStatus status;
    private final String idempotencyKey;

    public LoanRequest(UUID requestId, String customerId, BigDecimal amount,
                      Integer termMonths, LocalDate requestDate, LoanStatus status,
                      String idempotencyKey) {
        this.requestId = Objects.requireNonNull(requestId, "requestId no puede ser nulo");
        this.customerId = Objects.requireNonNull(customerId, "customerId no puede ser nulo");
        this.amount = Objects.requireNonNull(amount, "amount no puede ser nulo");
        this.termMonths = Objects.requireNonNull(termMonths, "termMonths no puede ser nulo");
        this.requestDate = Objects.requireNonNull(requestDate, "requestDate no puede ser nulo");
        this.status = Objects.requireNonNull(status, "status no puede ser nulo");
        this.idempotencyKey = Objects.requireNonNull(idempotencyKey, "idempotencyKey no puede ser nulo");
        
        validateAmount();
        validateTerm();
    }

    private void validateAmount() {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor que cero");
        }
    }

    private void validateTerm() {
        if (termMonths <= 0 || termMonths > 84) {
            throw new IllegalArgumentException("El plazo en meses debe estar entre 1 y 84");
        }
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public LoanRequest withStatus(LoanStatus newStatus) {
        return new LoanRequest(this.requestId, this.customerId, this.amount,
                              this.termMonths, this.requestDate, newStatus, this.idempotencyKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return requestId.equals(that.requestId) && 
               customerId.equals(that.customerId) && 
               amount.equals(that.amount) && 
               termMonths.equals(that.termMonths) && 
               requestDate.equals(that.requestDate) && 
               status == that.status && 
               idempotencyKey.equals(that.idempotencyKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, customerId, amount, termMonths, requestDate, status, idempotencyKey);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "requestId=" + requestId +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", termMonths=" + termMonths +
                ", requestDate=" + requestDate +
                ", status=" + status +
                ", idempotencyKey='" + idempotencyKey + '\'' +
                '}';
    }

    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED,
        FAILED
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/domain/model/IdempotencyKey.java ===
package com.empresa.prestamos.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para manejar claves de idempotencia.
 * Garantiza que las solicitudes duplicadas sean detectadas 
 * y manejadas adecuadamente.
 */
public final class IdempotencyKey {
    private final String key;
    private final String channel;

    public IdempotencyKey(String key, String channel) {
        this.key = Objects.requireNonNull(key, "La clave de idempotencia no puede ser nula");
        this.channel = Objects.requireNonNull(channel, "El canal no puede ser nulo");
        
        if (key.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede estar vacía");
        }
        
        if (channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede estar vacío");
        }
    }

    public String getKey() {
        return key;
    }

    public String getChannel() {
        return channel;
    }

    public String getCompositeKey() {
        return channel + "::" + key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdempotencyKey that = (IdempotencyKey) o;
        return key.equals(that.key) && channel.equals(that.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, channel);
    }

    @Override
    public String toString() {
        return "IdempotencyKey{" +
                "key='" + key + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/domain/exception/DuplicateRequestException.java ===
package com.empresa.prestamos.domain.exception;

/**
 * Excepción lanzada cuando se detecta una solicitud duplicada
 * utilizando la clave de idempotencia.
 */
public final class DuplicateRequestException extends RuntimeException {
    private final String idempotencyKey;
    private final String channel;

    public DuplicateRequestException(String idempotencyKey, String channel) {
        super(String.format("Solicitud duplicada detectada con clave '%s' del canal '%s'", 
                idempotencyKey, channel));
        this.idempotencyKey = idempotencyKey;
        this.channel = channel;
    }

    public DuplicateRequestException(String idempotencyKey, String channel, Throwable cause) {
        super(String.format("Solicitud duplicada detectada con clave '%s' del canal '%s'", 
                idempotencyKey, channel), cause);
        this.idempotencyKey = idempotencyKey;
        this.channel = channel;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getChannel() {
        return channel;
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/domain/exception/CreditCheckServiceException.java ===
package com.empresa.prestamos.domain.exception;

import java.time.Instant;
import java.util.UUID;

public final class CreditCheckServiceException extends RuntimeException {

    private final String serviceName;
    private final String customerId;
    private final String errorCode;
    private final Instant timestamp;
    private final boolean retryable;
    private final UUID correlationId;

    public CreditCheckServiceException(String serviceName, String customerId, String errorCode) {
        this(serviceName, customerId, errorCode, null, true, UUID.randomUUID());
    }

    public CreditCheckServiceException(String serviceName, String customerId, String errorCode, Throwable cause) {
        this(serviceName, customerId, errorCode, cause, true, UUID.randomUUID());
    }

    public CreditCheckServiceException(String serviceName, String customerId, String errorCode, 
                                        Throwable cause, boolean retryable, UUID correlationId) {
        super(buildMessage(serviceName, customerId, errorCode), cause);
        this.serviceName = serviceName;
        this.customerId = customerId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.retryable = retryable;
        this.correlationId = correlationId;
    }

    private static String buildMessage(String serviceName, String customerId, String errorCode) {
        return String.format("Credit check failed for customer %s. Service: %s, Error: %s", 
                             customerId, serviceName, errorCode);
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public boolean isRetryable() {
        return retryable;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public static CreditCheckServiceException serviceUnavailable(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "SERVICE_UNAVAILABLE", 
                                                new RuntimeException("External service timeout"), 
                                                true, UUID.randomUUID());
    }

    public static CreditCheckServiceException invalidResponse(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "INVALID_RESPONSE", 
                                                new RuntimeException("Malformed response from credit service"), 
                                                false, UUID.randomUUID());
    }

    public static CreditCheckServiceException authenticationFailure(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "AUTH_FAILURE", 
                                                new RuntimeException("Authentication failed with credit service"), 
                                                false, UUID.randomUUID());
    }

    public static CreditCheckServiceException rateLimitExceeded(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "RATE_LIMIT_EXCEEDED", 
                                                new RuntimeException("Too many requests to credit service"), 
                                                true, UUID.randomUUID());
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/application/ports/in/LoanRequestUseCase.java ===
package com.empresa.prestamos.application.ports.in;

import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface LoanRequestUseCase {

    Mono<LoanRequest> submitLoanRequest(String customerId, BigDecimal amount, 
                                         Integer termMonths, String idempotencyKey);

    Mono<LoanRequest> getLoanRequestById(UUID requestId);

    Mono<LoanRequest> getLoanRequestByIdempotencyKey(String idempotencyKey);

    Mono<LoanRequest> approveLoanRequest(UUID requestId);

    Mono<LoanRequest> rejectLoanRequest(UUID requestId, String rejectionReason);

    Mono<LoanRequest> updateLoanRequestStatus(UUID requestId, LoanStatus newStatus);

    Mono<Boolean> validateCustomerEligibility(String customerId);

    Mono<LoanRequest> reprocessLoanRequest(UUID requestId);
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/application/ports/out/LoanRequestPersistencePort.java ===
package com.empresa.prestamos.application.ports.out;

import com.empresa.prestamos.domain.model.IdempotencyKey;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LoanRequestPersistencePort {

    Mono<LoanRequest> save(LoanRequest loanRequest);

    Mono<LoanRequest> findById(UUID requestId);

    Mono<LoanRequest> findByIdempotencyKey(String idempotencyKey);

    Mono<LoanRequest> findByCustomerId(String customerId);

    Mono<List<LoanRequest>> findByCustomerIdAndStatus(String customerId, LoanStatus status);

    Mono<List<LoanRequest>> findByStatus(LoanStatus status);

    Mono<List<LoanRequest>> findByDateRange(LocalDate startDate, LocalDate endDate);

    Mono<LoanRequest> update(LoanRequest loanRequest);

    Mono<Boolean> existsByIdempotencyKey(String idempotencyKey);

    Mono<Boolean> existsById(UUID requestId);

    Mono<Void> deleteById(UUID requestId);

    Flux<LoanRequest> findAll();

    Mono<Long> count();

    Mono<Long> countByStatus(LoanStatus status);

    Mono<LoanRequest> findByIdempotencyKeyAndChannel(String idempotencyKey, String channel);

    Mono<LoanRequest> saveWithIdempotencyCheck(LoanRequest loanRequest, IdempotencyKey idempotencyKey);
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/application/ports/out/CreditCheckServicePort.java ===
package com.empresa.prestamos.application.ports.out;

import java.math.BigDecimal;
import reactor.core.publisher.Mono;

public interface CreditCheckServicePort {
    
    Mono<CreditCheckResult> checkCreditHistory(String customerId, BigDecimal requestedAmount);
    
    record CreditCheckResult(
        String customerId,
        boolean approved,
        BigDecimal approvedAmount,
        int creditScore,
        String riskLevel,
        String rejectionReason
    ) {
        public static CreditCheckResult approved(String customerId, BigDecimal amount, int score, String risk) {
            return new CreditCheckResult(customerId, true, amount, score, risk, null);
        }
        
        public static CreditCheckResult rejected(String customerId, String reason) {
            return new CreditCheckResult(customerId, false, BigDecimal.ZERO, 0, "HIGH", reason);
        }
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java ===
package com.empresa.prestamos.application.service;

import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.application.ports.out.CreditCheckServicePort;
import com.empresa.prestamos.application.ports.out.LoanRequestPersistencePort;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.IdempotencyKey;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

public class LoanRequestService implements LoanRequestUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(LoanRequestService.class);
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("50000");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 48;
    private static final int MAX_CONCURRENT_REQUESTS = 100;
    
    private final LoanRequestPersistencePort persistencePort;
    private final CreditCheckServicePort creditCheckServicePort;
    private final Sinks.Many<LoanRequest> requestProcessingSink;
    private volatile int currentBackpressureRequests = 0;
    private final Object backpressureLock = new Object();
    
    public LoanRequestService(
            LoanRequestPersistencePort persistencePort,
            CreditCheckServicePort creditCheckServicePort) {
        this.persistencePort = persistencePort;
        this.creditCheckServicePort = creditCheckServicePort;
        this.requestProcessingSink = Sinks.many().multicast().directBestEffort();
    }
    
    @Override
    public Mono<LoanRequest> submitRequest(LoanRequest request, String channel) {
        log.info("Recibida solicitud de prestamo para cliente: {}, canal: {}", 
                request.getCustomerId(), channel);
        
        validateRequest(request);
        
        IdempotencyKey idempotencyKey = new IdempotencyKey(
            request.getIdempotencyKey() != null ? request.getIdempotencyKey() : UUID.randomUUID().toString(),
            channel
        );
        
        return checkBackpressure()
            .then(checkIdempotency(idempotencyKey))
            .then(persistInitialRequest(request, idempotencyKey))
            .flatMap(this::processCreditCheck)
            .doOnSuccess(result -> log.info("Solicitud procesada exitosamente: {}", result.getRequestId()))
            .doOnError(error -> log.error("Error procesar solicitud: {}", error.getMessage()));
    }
    
    private Mono<Void> checkBackpressure() {
        return Mono.fromRunnable(() -> {
            synchronized (backpressureLock) {
                if (currentBackpressureRequests >= MAX_CONCURRENT_REQUESTS) {
                    log.warn("Backpressure detectado. Solicitudes en proceso: {}", currentBackpressureRequests);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                currentBackpressureRequests++;
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
    
    private Mono<Void> checkIdempotency(IdempotencyKey idempotencyKey) {
        return persistencePort.findByIdempotencyKey(idempotencyKey.getCompositeKey())
            .flatMap(existing -> {
                log.warn("Solicitud duplicada detectada para clave: {}", idempotencyKey.getCompositeKey());
                return Mono.error(new DuplicateRequestException(
                    idempotencyKey.getKey(), 
                    idempotencyKey.getChannel()
                ));
            })
            .then();
    }
    
    private Mono<LoanRequest> persistInitialRequest(LoanRequest request, IdempotencyKey idempotencyKey) {
        LoanRequest requestWithKey = new LoanRequest(
            request.getRequestId() != null ? request.getRequestId() : UUID.randomUUID(),
            request.getCustomerId(),
            request.getAmount(),
            request.getTermMonths(),
            LocalDate.now(),
            LoanStatus.PENDING,
            idempotencyKey.getCompositeKey()
        );
        
        return persistencePort.save(requestWithKey)
            .doOnSuccess(saved -> {
                log.info("Solicitud persistida con ID: {}, clave idempotencia: {}", 
                    saved.getRequestId(), saved.getIdempotencyKey());
                requestProcessingSink.emitNext(saved, Sinks.EmitFailureHandler.FAIL_FAST);
            });
    }
    
    private Mono<LoanRequest> processCreditCheck(LoanRequest request) {
        return creditCheckServicePort.checkCreditHistory(
            request.getCustomerId(),
            request.getAmount()
        )
        .flatMap(creditResult -> {
            if (creditResult.approved()) {
                LoanRequest approvedRequest = request.withStatus(LoanStatus.APPROVED);
                return persistencePort.save(approvedRequest)
                    .doOnSuccess(saved -> log.info("Prestamo aprobado para cliente: {}, monto: {}", 
                        saved.getCustomerId(), saved.getAmount()));
            } else {
                LoanRequest rejectedRequest = request.withStatus(LoanStatus.REJECTED);
                return persistencePort.save(rejectedRequest)
                    .doOnSuccess(saved -> log.info("Prestamo rechazado para cliente: {}, razon: {}", 
                        saved.getCustomerId(), creditResult.rejectionReason()));
            }
        })
        .doFinally(signalType -> {
            synchronized (backpressureLock) {
                currentBackpressureRequests--;
            }
            log.debug("Procesamiento de solicitud completado, solicitudes en proceso: {}", 
                currentBackpressureRequests);
        });
    }
    
    private void validateRequest(LoanRequest request) {
        if (request.getCustomerId() == null || request.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("El ID del cliente es requerido");
        }
        if (request.getAmount() == null) {
            throw new IllegalArgumentException("El monto del prestamo es requerido");
        }
        if (request.getAmount().compareTo(MIN_AMOUNT) < 0) {
            throw new IllegalArgumentException(
                "El monto minimo del prestamo es: " + MIN_AMOUNT);
        }
        if (request.getAmount().compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException(
                "El monto maximo del prestamo es: " + MAX_AMOUNT);
        }
        if (request.getTermMonths() == null) {
            throw new IllegalArgumentException("El plazo en meses es requerido");
        }
        if (request.getTermMonths() < MIN_TERM_MONTHS || request.getTermMonths() > MAX_TERM_MONTHS) {
            throw new IllegalArgumentException(
                "El plazo debe estar entre " + MIN_TERM_MONTHS + " y " + MAX_TERM_MONTHS + " meses");
        }
    }
    
    public Sinks.Many<LoanRequest> getRequestProcessingSink() {
        return requestProcessingSink;
    }
    
    public int getCurrentBackpressureRequests() {
        return currentBackpressureRequests;
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java ===
package com.empresa.prestamos.infrastructure.adapters.in.web;


import com.empresa.prestamos.LoanRequestDTO;
import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/loans/web")
public class LoanRequestWebController {
    
    private static final Logger log = LoggerFactory.getLogger(LoanRequestWebController.class);
    private static final String CHANNEL_WEB = "WEB";
    
    private final LoanRequestUseCase loanRequestUseCase;
    
    public LoanRequestWebController(LoanRequestUseCase loanRequestUseCase) {
        this.loanRequestUseCase = loanRequestUseCase;
    }
    
    @PostMapping
    public Mono<ResponseEntity<LoanRequestDTO>> submitLoanRequest(
            @RequestBody LoanRequestDTO requestDTO,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId) {
        
        log.info("Solicitud recibida via WEB para cliente: {}", requestDTO.customerId());
        
        LoanRequest loanRequest = new LoanRequest(
            requestId != null ? UUID.fromString(requestId) : UUID.randomUUID(),
            requestDTO.customerId(),
            requestDTO.amount(),
            requestDTO.termMonths(),
            LocalDate.now(),
            LoanStatus.PENDING,
            idempotencyKey
        );
        
        return loanRequestUseCase.submitRequest(loanRequest, CHANNEL_WEB)
            .map(this::toResponseDTO)
            .map(ResponseEntity::ok)
            .onErrorResume(DuplicateRequestException.class, error -> {
                log.warn("Solicitud duplicada via WEB - clave: {}, canal: {}", 
                    error.getIdempotencyKey(), error.getChannel());
                return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                    .<LoanRequestDTO>body(null));
            })
            .onErrorResume(IllegalArgumentException.class, error -> {
                log.error("Validacion fallida via WEB: {}", error.getMessage());
                return Mono.just(ResponseEntity.badRequest()
                    .<LoanRequestDTO>body(null));
            })
            .onErrorResume(Exception.class, error -> {
                log.error("Error interno procesando solicitud WEB: {}", error.getMessage(), error);
                return Mono.just(ResponseEntity.internalServerError()
                    .<LoanRequestDTO>body(null));
            });
    }
    
    private LoanRequestDTO toResponseDTO(LoanRequest request) {
        return new LoanRequestDTO(
            request.getRequestId().toString(),
            request.getCustomerId(),
            request.getAmount(),
            request.getTermMonths(),
            request.getRequestDate().toString(),
            request.getStatus().name(),
            request.getIdempotencyKey()
        );
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java ===
package com.empresa.prestamos.infrastructure.adapters.in.mobile;

import com.empresa.prestamos.PrestamosApplication.LoanRequestDTO;
import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.application.service.LoanRequestService;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/mobile/v1/loans")
public class LoanRequestMobileController {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestMobileController.class);
    private static final String CHANNEL_MOBILE = "MOBILE";

    private final LoanRequestUseCase loanRequestUseCase;
    private final Map<String, AtomicLong> requestMetrics = new ConcurrentHashMap<>();

    public LoanRequestMobileController(LoanRequestService loanRequestUseCase) {
        this.loanRequestUseCase = loanRequestUseCase;
        requestMetrics.put("submitted", new AtomicLong(0));
        requestMetrics.put("approved", new AtomicLong(0));
        requestMetrics.put("rejected", new AtomicLong(0));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LoanRequestDTO> submitLoanRequest(@RequestBody LoanRequestDTO requestDTO) {
        log.info("Recibida solicitud de prestamo desde canal MOBILE para cliente: {}", 
                requestDTO.customerId());
        
        requestMetrics.get("submitted").incrementAndGet();
        
        String idempotencyKey = requestDTO.idempotencyKey() != null ? 
                requestDTO.idempotencyKey() : 
                generateMobileIdempotencyKey(requestDTO.customerId());
        
        LoanRequestDTO enrichedDTO = new LoanRequestDTO(
                requestDTO.requestId(),
                requestDTO.customerId(),
                requestDTO.amount(),
                requestDTO.termMonths(),
                requestDTO.requestDate(),
                requestDTO.status(),
                idempotencyKey,
                CHANNEL_MOBILE,
                requestDTO.creditScore(),
                requestDTO.approvedAmount(),
                requestDTO.rejectionReason()
        );

        return loanRequestUseCase.submitRequest(enrichedDTO, CHANNEL_MOBILE)
                .doOnSuccess(result -> {
                    if (result.status() == LoanStatus.APPROVED) {
                        requestMetrics.get("approved").incrementAndGet();
                    } else if (result.status() == LoanStatus.REJECTED) {
                        requestMetrics.get("rejected").incrementAndGet();
                    }
                    log.info("Solicitud procesada desde MOBILE - Estado: {}", result.status());
                })
                .doOnError(error -> log.error("Error procesando solicitud MOBILE: {}", error.getMessage()));
    }

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LoanRequestDTO> getLoanRequest(@PathVariable String requestId) {
        log.info("Consultando solicitud {} desde canal MOBILE", requestId);
        return loanRequestUseCase.getRequestById(requestId)
                .doOnSuccess(dto -> log.info("Solicitud {} encontrada para canal MOBILE", requestId))
                .doOnError(error -> log.error("Error consultando solicitud MOBILE: {}", error.getMessage()));
    }

    @GetMapping(value = "/stream/status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<LoanRequestDTO>> streamLoanStatus() {
        log.info("Iniciando stream de estado de solicitudes para canal MOBILE");
        return loanRequestUseCase.streamPendingRequests()
                .map(dto -> ServerSentEvent.builder(dto)
                        .event("loan-status-update")
                        .build())
                .doOnSubscribe(s -> log.info("Suscriptor conectado al stream MOBILE"))
                .doOnCancel(() -> log.info("Suscriptor desconectado del stream MOBILE"));
    }

    @GetMapping(value = "/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Long>> getChannelMetrics() {
        log.debug("Consultando metricas del canal MOBILE");
        return Mono.just(Map.of(
                "channel", CHANNEL_MOBILE,
                "submitted", requestMetrics.get("submitted").get(),
                "approved", requestMetrics.get("approved").get(),
                "rejected", requestMetrics.get("rejected").get(),
                "timestamp", Instant.now().toEpochMilli()
        ));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LoanRequestDTO> listLoanRequests(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("Listando solicitudes para cliente {} con estado {} - pagina {} tamaño {}", 
                customerId, status, page, size);
        return loanRequestUseCase.listRequestsByCustomer(customerId, status, page, size)
                .take(Duration.ofSeconds(30))
                .doOnComplete(() -> log.info("Listado de solicitudes MOBILE completado"));
    }

    private String generateMobileIdempotencyKey(String customerId) {
        return "MOBILE-" + customerId + "-" + System.currentTimeMillis();
    }

    @ExceptionHandler(Exception.class)
    public Mono<Map<String, Object>> handleMobileException(Exception e) {
        log.error("Excepcion en controller MOBILE: {}", e.getMessage(), e);
        return Mono.just(Map.of(
                "error", "Error procesando solicitud mobile",
                "message", e.getMessage(),
                "channel", CHANNEL_MOBILE,
                "timestamp", Instant.now().toString()
        ));
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java ===
package com.empresa.prestamos.infrastructure.adapters.in.externalapi;

import com.empresa.prestamos.PrestamosApplication.LoanRequestDTO;
import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.application.service.LoanRequestService;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/external/v1/loans")
public class LoanRequestExternalApiController {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestExternalApiController.class);
    private static final String CHANNEL_EXTERNAL = "EXTERNAL_API";

    private final LoanRequestUseCase loanRequestUseCase;
    private final ConcurrentHashMap<String, Long> requestTimestamps = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> requestTraceIds = new ConcurrentHashMap<>();

    public LoanRequestExternalApiController(LoanRequestService loanRequestUseCase) {
        this.loanRequestUseCase = loanRequestUseCase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Object>> submitExternalLoanRequest(
            @RequestHeader(value = "X-Client-Id", required = false) String clientId,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId,
            @RequestBody LoanRequestDTO requestDTO) {
        
        String effectiveTraceId = traceId != null ? traceId : generateTraceId();
        log.info("Solicitud EXTERNAL_API recibida - Client: {}, TraceId: {}", clientId, effectiveTraceId);
        
        requestTraceIds.put(effectiveTraceId, Instant.now().toString());
        
        if (requestDTO.idempotencyKey() == null || requestDTO.idempotencyKey().isBlank()) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of(
                    "error", "Idempotency key es requerido para el canal externo",
                    "code", "MISSING_IDEMPOTENCY_KEY",
                    "traceId", effectiveTraceId
            )));
        }

        LoanRequestDTO enrichedDTO = enrichRequestDTO(requestDTO, clientId, effectiveTraceId);

        return loanRequestUseCase.submitRequest(enrichedDTO, CHANNEL_EXTERNAL)
                .map(result -> {
                    log.info("Solicitud externa procesada exitosamente - TraceId: {}, Status: {}", 
                            effectiveTraceId, result.status());
                    return ResponseEntity.ok(buildSuccessResponse(result, effectiveTraceId));
                })
                .onErrorResume(DuplicateRequestException.class, ex -> {
                    log.warn("Solicitud duplicada detectada - TraceId: {}, Key: {}", 
                            effectiveTraceId, ex.getIdempotencyKey());
                    return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                            "error", "Solicitud duplicada",
                            "idempotencyKey", ex.getIdempotencyKey(),
                            "channel", CHANNEL_EXTERNAL,
                            "traceId", effectiveTraceId,
                            "code", "DUPLICATE_REQUEST"
                    )));
                })
                .onErrorResume(Exception.class, ex -> {
                    log.error("Error procesando solicitud externa - TraceId: {}, Error: {}", 
                            effectiveTraceId, ex.getMessage(), ex);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                            "error", "Error interno procesando solicitud",
                            "message", ex.getMessage(),
                            "traceId", effectiveTraceId,
                            "code", "INTERNAL_ERROR"
                    )));
                });
    }

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Object>> getExternalLoanRequest(
            @PathVariable String requestId,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        
        String effectiveTraceId = traceId != null ? traceId : generateTraceId();
        log.info("Consultando solicitud externa - RequestId: {}, TraceId: {}", requestId, effectiveTraceId);
        
        return loanRequestUseCase.getRequestById(requestId)
                .map(dto -> ResponseEntity.ok(buildSuccessResponse(dto, effectiveTraceId)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LoanRequestDTO> listExternalRequests(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestHeader(value = "X-Client-Id", required = false) String clientId) {
        
        log.info("Listando solicitudes externas - Client: {}, Customer: {}, Status: {}", 
                clientId, customerId, status);
        
        return loanRequestUseCase.listRequestsByCustomer(customerId, status, page, size)
                .delayElements(Duration.ofMillis(100))
                .doOnComplete(() -> log.info("Listado de solicitudes externas completado"));
    }

    @PostMapping(value = "/batch", consumes = MediaType.APPLICATION_JSON_VALUE, 
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ResponseEntity<Object>> submitBatchRequests(
            @RequestBody Flux<LoanRequestDTO> requests,
            @RequestHeader(value = "X-Client-Id", required = false) String clientId) {
        
        String batchTraceId = generateTraceId();
        log.info("Procesando lote de solicitudes externas - BatchId: {}, Client: {}", 
                batchTraceId, clientId);
        
        return requests
                .flatMap(dto -> loanRequestUseCase.submitRequest(
                        enrichRequestDTO(dto, clientId, batchTraceId), 
                        CHANNEL_EXTERNAL
                ).map(result -> ResponseEntity.ok(buildSuccessResponse(result, batchTraceId))))
                .doOnComplete(() -> log.info("Procesamiento de lote completado - BatchId: {}", batchTraceId));
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> healthCheck() {
        return Mono.just(Map.of(
                "status", "UP",
                "channel", CHANNEL_EXTERNAL,
                "timestamp", Instant.now().toString(),
                "activeTraces", requestTraceIds.size()
        ));
    }

    private LoanRequestDTO enrichRequestDTO(LoanRequestDTO dto, String clientId, String traceId) {
        String idempotencyKey = dto.idempotencyKey();
        if (idempotencyKey != null && !idempotencyKey.startsWith("EXT-")) {
            idempotencyKey = "EXT-" + clientId + "-" + idempotencyKey;
        }
        return new LoanRequestDTO(
                dto.requestId(),
                dto.customerId(),
                dto.amount(),
                dto.termMonths(),
                dto.requestDate(),
                dto.status(),
                idempotencyKey,
                CHANNEL_EXTERNAL,
                dto.creditScore(),
                dto.approvedAmount(),
                dto.rejectionReason()
        );
    }

    private Map<String, Object> buildSuccessResponse(LoanRequestDTO dto, String traceId) {
        return Map.of(
                "requestId", dto.requestId() != null ? dto.requestId() : "",
                "customerId", dto.customerId(),
                "status", dto.status() != null ? dto.status().name() : "",
                "amount", dto.amount() != null ? dto.amount().toString() : "0",
                "termMonths", dto.termMonths() != null ? dto.termMonths() : 0,
                "channel", CHANNEL_EXTERNAL,
                "traceId", traceId,
                "timestamp", Instant.now().toString()
        );
    }

    private String generateTraceId() {
        return "EXT-" + System.currentTimeMillis() + "-" + 
               java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleExternalException(Exception e) {
        String traceId = generateTraceId();
        log.error("Excepcion en controller EXTERNAL_API - TraceId: {}, Error: {}", 
                traceId, e.getMessage(), e);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "Error en el servicio externo",
                "message", e.getMessage(),
                "traceId", traceId,
                "timestamp", Instant.now().toString()
        )));
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java ===
package com.empresa.prestamos.infrastructure.adapters.out.persistence;

import com.empresa.prestamos.application.ports.out.LoanRequestPersistencePort;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanRequestJpaAdapter implements LoanRequestPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestJpaAdapter.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Mono<LoanRequest> save(LoanRequest loanRequest) {
        log.debug("Persistiendo solicitud de prestamo: {}", loanRequest.getRequestId());
        return Mono.fromCallable(() -> {
            LoanRequestEntity entity = toEntity(loanRequest);
            entityManager.persist(entity);
            entityManager.flush();
            log.info("Solicitud {} persistida exitosamente en canal {}", 
                    loanRequest.getRequestId(), loanRequest.getIdempotencyKey());
            return toDomain(entity);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<LoanRequest> findById(UUID requestId) {
        log.debug("Buscando solicitud por ID: {}", requestId);
        return Mono.fromCallable(() -> {
            LoanRequestEntity entity = entityManager.find(LoanRequestEntity.class, requestId);
            return entity != null ? toDomain(entity) : null;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<LoanRequest> findByIdempotencyKey(String idempotencyKey, String channel) {
        log.debug("Buscando solicitud por clave de idempotencia: {} en canal {}", idempotencyKey, channel);
        return Mono.fromCallable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.idempotencyKey = :key AND e.channel = :channel",
                    LoanRequestEntity.class);
            query.setParameter("key", idempotencyKey);
            query.setParameter("channel", channel);
            query.setMaxResults(1);
            
            List<LoanRequestEntity> results = query.getResultList();
            return results.isEmpty() ? null : toDomain(results.get(0));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findByCustomerId(String customerId) {
        log.debug("Buscando solicitudes para cliente: {}", customerId);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.customerId = :customerId ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setParameter("customerId", customerId);
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findByStatus(LoanStatus status) {
        log.debug("Buscando solicitudes con estado: {}", status);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.status = :status ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setParameter("status", status.name());
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findByCustomerIdAndStatus(String customerId, LoanStatus status) {
        log.debug("Buscando solicitudes para cliente {} con estado {}", customerId, status);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.customerId = :customerId AND e.status = :status " +
                    "ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setParameter("customerId", customerId);
            query.setParameter("status", status.name());
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<LoanRequest> updateStatus(UUID requestId, LoanStatus newStatus) {
        log.info("Actualizando estado de solicitud {} a {}", requestId, newStatus);
        return Mono.fromCallable(() -> {
            LoanRequestEntity entity = entityManager.find(LoanRequestEntity.class, requestId);
            if (entity == null) {
                throw new IllegalArgumentException("Solicitud no encontrada: " + requestId);
            }
            entity.setStatus(newStatus.name());
            entity.setLastUpdated(java.time.Instant.now());
            entityManager.flush();
            log.info("Estado de solicitud {} actualizado a {}", requestId, newStatus);
            return toDomain(entity);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Long> countByCustomerId(String customerId) {
        log.debug("Contando solicitudes para cliente: {}", customerId);
        return Mono.fromCallable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT COUNT(e) FROM LoanRequestEntity e WHERE e.customerId = :customerId");
            query.setParameter("customerId", customerId);
            return (Long) query.getSingleResult();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Boolean> existsByIdempotencyKey(String idempotencyKey, String channel) {
        log.debug("Verificando existencia de clave de idempotencia: {} en canal {}", idempotencyKey, channel);
        return Mono.fromCallable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT COUNT(e) FROM LoanRequestEntity e WHERE e.idempotencyKey = :key AND e.channel = :channel");
            query.setParameter("key", idempotencyKey);
            query.setParameter("channel", channel);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(UUID requestId) {
        log.info("Eliminando solicitud: {}", requestId);
        return Mono.fromRunnable(() -> {
            LoanRequestEntity entity = entityManager.find(LoanRequestEntity.class, requestId);
            if (entity != null) {
                entityManager.remove(entity);
                entityManager.flush();
                log.info("Solicitud {} eliminada", requestId);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    public Flux<LoanRequest> findPendingRequests() {
        log.debug("Buscando solicitudes pendientes");
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.status = 'PENDING' ORDER BY e.requestDate ASC",
                    LoanRequestEntity.class);
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findAllPaginated(int page, int size) {
        log.debug("Buscando todas las solicitudes - pagina: {}, tamano: {}", page, size);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    private LoanRequestEntity toEntity(LoanRequest loanRequest) {
        LoanRequestEntity entity = new LoanRequestEntity();
        entity.setRequestId(loanRequest.getRequestId());
        entity.setCustomerId(loanRequest.getCustomerId());
        entity.setAmount(loanRequest.getAmount());
        entity.setTermMonths(loanRequest.getTermMonths());
        entity.setRequestDate(loanRequest.getRequestDate());
        entity.setStatus(loanRequest.getStatus().name());
        entity.setIdempotencyKey(loanRequest.getIdempotencyKey());
        entity.setChannel(determineChannel(loanRequest.getIdempotencyKey()));
        entity.setCreatedAt(java.time.Instant.now());
        entity.setLastUpdated(java.time.Instant.now());
        return entity;
    }

    private LoanRequest toDomain(LoanRequestEntity entity) {
        return new LoanRequest(
                entity.getRequestId(),
                entity.getCustomerId(),
                entity.getAmount(),
                entity.getTermMonths(),
                entity.getRequestDate(),
                LoanStatus.valueOf(entity.getStatus()),
                entity.getIdempotencyKey()
        );
    }

    private String determineChannel(String idempotencyKey) {
        if (idempotencyKey == null) return "UNKNOWN";
        if (idempotencyKey.startsWith("MOBILE-")) return "MOBILE";
        if (idempotencyKey.startsWith("EXT-")) return "EXTERNAL_API";
        return "WEB";
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestEntity.java ===
package com.empresa.prestamos.infrastructure.adapters.out.persistence;

import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loan_requests", indexes = {
    @Index(name = "idx_customer_id", columnList = "customerId"),
    @Index(name = "idx_idempotency_key", columnList = "idempotencyKey", unique = true),
    @Index(name = "idx_status", columnList = "status")
})
public class LoanRequestEntity {

    @Id
    @Column(name = "request_id", nullable = false)
    private UUID requestId;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 100)
    private String idempotencyKey;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "credit_check_score")
    private Integer creditCheckScore;

    @Column(name = "credit_check_reference", length = 100)
    private String creditCheckReference;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;

    public LoanRequestEntity() {
    }

    public static LoanRequestEntity fromDomain(LoanRequest loanRequest) {
        LoanRequestEntity entity = new LoanRequestEntity();
        entity.requestId = loanRequest.getRequestId();
        entity.customerId = loanRequest.getCustomerId();
        entity.amount = loanRequest.getAmount();
        entity.termMonths = loanRequest.getTermMonths();
        entity.requestDate = loanRequest.getRequestDate();
        entity.status = loanRequest.getStatus();
        entity.idempotencyKey = loanRequest.getIdempotencyKey();
        entity.createdAt = LocalDate.now();
        entity.updatedAt = LocalDate.now();
        return entity;
    }

    public LoanRequest toDomain() {
        return new LoanRequest(
            this.requestId,
            this.customerId,
            this.amount,
            this.termMonths,
            this.requestDate,
            this.status,
            this.idempotencyKey
        );
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreditCheckScore() {
        return creditCheckScore;
    }

    public void setCreditCheckScore(Integer creditCheckScore) {
        this.creditCheckScore = creditCheckScore;
    }

    public String getCreditCheckReference() {
        return creditCheckReference;
    }

    public void setCreditCheckReference(String creditCheckReference) {
        this.creditCheckReference = creditCheckReference;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java ===
package com.empresa.prestamos.infrastructure.adapters.out.creditcheck;

import com.empresa.prestamos.application.ports.out.CreditCheckServicePort;
import com.empresa.prestamos.domain.exception.CreditCheckServiceException;
import com.empresa.prestamos.domain.model.LoanRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreditCheckServiceAdapter implements CreditCheckServicePort {

    private static final Logger log = LoggerFactory.getLogger(CreditCheckServiceAdapter.class);
    private static final int MINIMUM_SCORE_FOR_APPROVAL = 650;

    private final WebClient webClient;
    private final String creditCheckBaseUrl;
    private final int timeoutSeconds;
    private final int maxRetries;
    private final long retryDelayMillis;

    private final Map<String, CreditCheckCacheEntry> cache = new ConcurrentHashMap<>();

    public CreditCheckServiceAdapter(
            @Value("${external.credit-check.base-url:https://api.credit bureau.com/v1}") String creditCheckBaseUrl,
            @Value("${external.credit-check.timeout-seconds:5}") int timeoutSeconds,
            @Value("${external.credit-check.max-retries:3}") int maxRetries,
            @Value("${external.credit-check.retry-delay-millis:1000}") long retryDelayMillis) {
        this.creditCheckBaseUrl = creditCheckBaseUrl;
        this.timeoutSeconds = timeoutSeconds;
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.webClient = WebClient.builder()
                .baseUrl(creditCheckBaseUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Override
    @CircuitBreaker(name = "creditCheckCircuitBreaker", fallbackMethod = "fallbackCreditCheck")
    public Mono<CreditCheckResult> checkCredit(LoanRequest request) {
        String cacheKey = buildCacheKey(request.getCustomerId());

        CreditCheckCacheEntry cached = cache.get(cacheKey);
        if (cached != null && !cached.isExpired()) {
            log.info("Credit check cache hit for customer: {}", request.getCustomerId());
            return Mono.just(cached.getResult());
        }

        log.info("Executing credit check for customer: {}, requestId: {}", 
                request.getCustomerId(), request.getRequestId());

        return webClient.post()
                .uri("/credit-assessment")
                .bodyValue(buildCreditCheckRequest(request))
                .retrieve()
                .bodyToMono(CreditCheckResponse.class)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .retryWhen(Retry.backoff(maxRetries, Duration.ofMillis(retryDelayMillis))
                        .filter(throwable -> isRetryable(throwable))
                        .doBeforeRetry(signal -> {
                            log.warn("Retrying credit check for customer: {}, attempt: {}",
                                    request.getCustomerId(), signal.totalRetries() + 1);
                        }))
                .map(this::mapToResult)
                .doOnSuccess(result -> {
                    cache.put(cacheKey, new CreditCheckCacheEntry(result, Duration.ofMinutes(15)));
                    log.info("Credit check completed for customer: {}, score: {}", 
                            request.getCustomerId(), result.score());
                })
                .doOnError(error -> log.error("Credit check failed for customer: {}, error: {}",
                        request.getCustomerId(), error.getMessage()));
    }

    private boolean isRetryable(Throwable throwable) {
        return throwable instanceof org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable
                || throwable instanceof java.util.concurrent.TimeoutException
                || throwable instanceof java.io.IOException;
    }

    private CreditCheckRequest buildCreditCheckRequest(LoanRequest request) {
        return new CreditCheckRequest(
                request.getCustomerId(),
                request.getAmount(),
                request.getTermMonths()
        );
    }

    private CreditCheckResult mapToResult(CreditCheckResponse response) {
        boolean approved = response.score() >= MINIMUM_SCORE_FOR_APPROVAL;
        String reason = approved ? "Credit score meets minimum requirement" 
                : "Credit score below minimum requirement of " + MINIMUM_SCORE_FOR_APPROVAL;
        return new CreditCheckResult(response.score(), approved, response.reference(), reason);
    }

    private String buildCacheKey(String customerId) {
        return "credit-check:" + customerId;
    }

    @SuppressWarnings("unused")
    private Mono<CreditCheckResult> fallbackCreditCheck(LoanRequest request, Throwable throwable) {
        log.error("Circuit breaker triggered for credit check. Customer: {}, Error: {}",
                request.getCustomerId(), throwable.getMessage());
        throw new CreditCheckServiceException(
                "Credit check service unavailable. Please try again later.",
                request.getCustomerId(),
                throwable
        );
    }

    public record CreditCheckRequest(String customerId, java.math.BigDecimal amount, Integer termMonths) {}

    public record CreditCheckResponse(Integer score, String reference, String details) {}

    public record CreditCheckResult(Integer score, boolean approved, String reference, String reason) {}

    private static class CreditCheckCacheEntry {
        private final CreditCheckResult result;
        private final long expiresAt;

        CreditCheckCacheEntry(CreditCheckResult result, Duration ttl) {
            this.result = result;
            this.expiresAt = System.currentTimeMillis() + ttl.toMillis();
        }

        CreditCheckResult getResult() {
            return result;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt;
        }
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/config/IdempotencyConfig.java ===
package com.empresa.prestamos.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class IdempotencyConfig {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyConfig.class);
    private static final String IDEMPOTENCY_CACHE_NAME = "idempotency-cache";
    private static final String PROCESSING_CACHE_NAME = "idempotency-processing";

    @Value("${idempotency.cache.ttl-minutes:60}")
    private int cacheTtlMinutes;

    @Value("${idempotency.max-entries:10000}")
    private int maxCacheEntries;

    @Value("${idempotency.cleanup-interval-minutes:10}")
    private int cleanupIntervalMinutes;

    @Bean
    @Primary
    public CacheManager idempotencyCacheManager() {
        log.info("Configuring idempotency cache manager with TTL: {} minutes, max entries: {}",
                cacheTtlMinutes, maxCacheEntries);

        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(
                IDEMPOTENCY_CACHE_NAME,
                PROCESSING_CACHE_NAME
        );

        cacheManager.setStoreByValue(false);
        return cacheManager;
    }

    @Bean
    public IdempotencyCacheCleaner idempotencyCacheCleaner(CacheManager cacheManager) {
        return new IdempotencyCacheCleaner(cacheManager, cleanupIntervalMinutes);
    }

    @Bean
    public IdempotencyKeyValidator idempotencyKeyValidator() {
        return new IdempotencyKeyValidator();
    }

    public static class IdempotencyCacheCleaner implements Runnable {
        private final CacheManager cacheManager;
        private final int intervalMinutes;
        private volatile boolean running = true;

        public IdempotencyCacheCleaner(CacheManager cacheManager, int intervalMinutes) {
            this.cacheManager = cacheManager;
            this.intervalMinutes = intervalMinutes;
        }

        @Override
        public void run() {
            log.debug("Idempotency cache cleanup task started");
            while (running) {
                try {
                    Thread.sleep(TimeUnit.MINUTES.toMillis(intervalMinutes));
                    performCleanup();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.warn("Idempotency cache cleaner interrupted");
                    break;
                }
            }
        }

        private void performCleanup() {
            log.debug("Performing idempotency cache cleanup");
        }

        public void stop() {
            this.running = false;
        }
    }

    public static class IdempotencyKeyValidator {
        private static final int MIN_KEY_LENGTH = 8;
        private static final int MAX_KEY_LENGTH = 128;
        private static final java.util.regex.Pattern KEY_PATTERN = 
                java.util.regex.Pattern.compile("^[a-zA-Z0-9_-]+$");

        public void validate(String key, String channel) {
            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Idempotency key cannot be null or blank");
            }

            if (key.length() < MIN_KEY_LENGTH) {
                throw new IllegalArgumentException(
                        String.format("Idempotency key must be at least %d characters", MIN_KEY_LENGTH));
            }

            if (key.length() > MAX_KEY_LENGTH) {
                throw new IllegalArgumentException(
                        String.format("Idempotency key cannot exceed %d characters", MAX_KEY_LENGTH));
            }

            if (!KEY_PATTERN.matcher(key).matches()) {
                throw new IllegalArgumentException(
                        "Idempotency key must contain only alphanumeric characters, hyphens and underscores");
            }

            if (channel == null || channel.isBlank()) {
                throw new IllegalArgumentException("Idempotency channel cannot be null or blank");
            }
        }

        public String buildCompositeKey(String key, String channel) {
            return channel + ":" + key;
        }
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/config/ResilienceConfig.java ===
package com.empresa.prestamos.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .build();
        return RetryRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/empresa/prestamos/infrastructure/config/ReactorConfig.java ===
package com.empresa.prestamos.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Executor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class ReactorConfig {

    @Bean
    public Scheduler reactorScheduler() {
        return Schedulers.newBoundedElastic(
                100,
                1000,
                "reactor-bounded",
                60,
                true
        );
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public reactor.core.scheduler.Scheduler creditCheckScheduler() {
        return Schedulers.boundedElastic();
    }

    @Bean
    public reactor.core.scheduler.Scheduler persistenceScheduler() {
        return Schedulers.parallel();
    }
}

// === ARCHIVO: openapi.yaml ===
openapi: 3.1.0
info:
  title: API de Gestión de Préstamos
  description: >
    API para gestionar solicitudes de préstamos con arquitectura hexagonal.
    Soporta múltiples canales de entrada (web, móvil, API externa) con
    manejo de idempotencia y recuperación automática ante fallos.
  version: 1.0.0
  contact:
    name: Equipo de Desarrollo
    email: desarrollo@empresa.com

servers:
  - url: https://api.prestamos.empresa.com/v1
    description: Servidor de producción
  - url: https://staging.prestamos.empresa.com/v1
    description: Servidor de staging
  - url: http://localhost:8080/v1
    description: Servidor local

tags:
  - name: Solicitudes
    description: Operaciones sobre solicitudes de préstamos
  - name: Idempotencia
    description: Control de solicitudes duplicadas
  - name: Crediticio
    description: Consulta de historial crediticio

paths:
  /solicitudes:
    post:
      tags:
        - Solicitudes
      summary: Crear una nueva solicitud de préstamo
      operationId: createLoanRequest
      description: >
        Crea una nueva solicitud de préstamo. El sistema verifica la
        idempotencia usando la clave proporcionada en el header.
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/LoanRequestDTO'
            examples:
              solicitud-valida:
                summary: Solicitud válida
                value:
                  customerId: "CUST-001"
                  amount: 50000.00
                  termMonths: 24
              solicitud-minima:
                summary: Solicitud con monto mínimo
                value:
                  customerId: "CUST-002"
                  amount: 1000.00
                  termMonths: 6
      parameters:
        - name: X-Idempotency-Key
          in: header
          required: true
          description: Clave única para control de idempotencia
          schema:
            type: string
            format: uuid
          example: "550e8400-e29b-41d4-a716-446655440000"
        - name: X-Channel
          in: header
          required: false
          description: Canal de origen de la solicitud
          schema:
            type: string
            enum: [web, mobile, external_api]
            default: web
      responses:
        '201':
          description: Solicitud creada exitosamente
          headers:
            X-Request-Id:
              schema:
                type: string
                format: uuid
              description: Identificador único de la solicitud
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LoanResponseDTO'
              examples:
                aprobada:
                  summary: Solicitud aprobada
                  value:
                    requestId: "550e8400-e29b-41d4-a716-446655440000"
                    status: APPROVED
                    message: "Solicitud aprobada"
                    approvedAmount: 50000.00
                    interestRate: 12.5
                rechazada:
                  summary: Solicitud rechazada
                  value:
                    requestId: "550e8400-e29b-41d4-a716-446655440001"
                    status: REJECTED
                    message: "Historial crediticio no aprobado"
        '400':
          description: Datos de entrada inválidos
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
              examples:
                monto-invalido:
                  summary: Monto fuera de rango
                  value:
                    code: "INVALID_AMOUNT"
                    message: "El monto solicitado debe estar entre 1000 y 500000"
                    field: "amount"
                plazo-invalido:
                  summary: Plazo fuera de rango
                  value:
                    code: "INVALID_TERM"
                    message: "El plazo debe estar entre 6 y 60 meses"
                    field: "termMonths"
        '409':
          description: Solicitud duplicada
          headers:
            X-Idempotency-Key:
              schema:
                type: string
              description: Clave de idempotencia duplicada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
              examples:
                duplicada:
                  summary: Solicitud duplicada
                  value:
                    code: "DUPLICATE_REQUEST"
                    message: "Ya existe una solicitud con esta clave de idempotencia"
        '422':
          description: Error de validación de negocio
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
              examples:
                cliente-invalido:
                  summary: Cliente no encontrado
                  value:
                    code: "INVALID_CUSTOMER"
                    message: "El cliente no existe en el sistema"
                    field: "customerId"
        '503':
          description: Servicio externo no disponible
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
              examples:
                servicio-caido:
                  summary: Fallo en servicio de crédito
                  value:
                    code: "SERVICE_UNAVAILABLE"
                    message: "El servicio de verificación crediticia no está disponible"
                    retryAfter: 30

  /solicitudes/{requestId}:
    get:
      tags:
        - Solicitudes
      summary: Obtener detalles de una solicitud
      operationId: getLoanRequest
      parameters:
        - name: requestId
          in: path
          required: true
          description: Identificador único de la solicitud
          schema:
            type: string
            format: uuid
          example: "550e8400-e29b-41d4-a716-446655440000"
      responses:
        '200':
          description: Solicitud encontrada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LoanResponseDTO'
        '404':
          description: Solicitud no encontrada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'

  /solicitudes/{requestId}/estado:
    patch:
      tags:
        - Solicitudes
      summary: Actualizar estado de una solicitud
      operationId: updateLoanRequestStatus
      parameters:
        - name: requestId
          in: path
          required: true
          description: Identificador único de la solicitud
          schema:
            type: string
            format: uuid
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/StatusUpdateDTO'
      responses:
        '200':
          description: Estado actualizado
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LoanResponseDTO'
        '400':
          description: Estado inválido para la transición
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'

  /crediticio/verificar:
    post:
      tags:
        - Crediticio
      summary: Verificar historial crediticio del cliente
      operationId: checkCreditHistory
      description: >
        Endpoint interno para verificar el historial crediticio.
        Requiere autenticación mediante API Key.
      security:
        - ApiKeyAuth: []
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CreditCheckRequestDTO'
      responses:
        '200':
          description: Verificación completada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/CreditCheckResponseDTO'
        '500':
          description: Error interno del servicio
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'

components:
  schemas:
    LoanRequestDTO:
      type: object
      required:
        - customerId
        - amount
        - termMonths
      properties:
        customerId:
          type: string
          description: Identificador único del cliente
          pattern: "^CUST-\\d{3,10}$"
          example: "CUST-001"
        amount:
          type: number
          description: Monto solicitado en la moneda base
          minimum: 1000
          maximum: 500000
          multipleOf: 0.01
          example: 50000.00
        termMonths:
          type: integer
          description: Plazo del préstamo en meses
          minimum: 6
          maximum: 60
          example: 24
        requestDate:
          type: string
          format: date
          description: Fecha de la solicitud (opcional, usa fecha actual si no se provee)
          example: "2024-01-15"

    LoanResponseDTO:
      type: object
      properties:
        requestId:
          type: string
          format: uuid
          description: Identificador único de la solicitud
          example: "550e8400-e29b-41d4-a716-446655440000"
        status:
          type: string
          enum:
            - PENDING
            - APPROVED
            - REJECTED
            - CANCELLED
          description: Estado actual de la solicitud
          example: "APPROVED"
        message:
          type: string
          description: Mensaje descriptivo del resultado
          example: "Solicitud aprobada"
        approvedAmount:
          type: number
          description: Monto aprobado (puede diferir del solicitado)
          nullable: true
          example: 50000.00
        interestRate:
          type: number
          format: double
          description: Tasa de interés anual aplicada
          nullable: true
          example: 12.5
        customerId:
          type: string
          description: Identificador del cliente
          example: "CUST-001"
        requestDate:
          type: string
          format: date
          description: Fecha de la solicitud
          example: "2024-01-15"

    StatusUpdateDTO:
      type: object
      required:
        - status
      properties:
        status:
          type: string
          enum:
            - PENDING
            - APPROVED
            - REJECTED
            - CANCELLED
          description: Nuevo estado de la solicitud
        reason:
          type: string
          description: Razón del cambio de estado
          maxLength: 500

    CreditCheckRequestDTO:
      type: object
      required:
        - customerId
      properties:
        customerId:
          type: string
          description: Identificador del cliente a verificar
          example: "CUST-001"
        requestedAmount:
          type: number
          description: Monto solicitado
          example: 50000.00

    CreditCheckResponseDTO:
      type: object
      properties:
        customerId:
          type: string
          description: Identificador del cliente
          example: "CUST-001"
        approved:
          type: boolean
          description: Indicador de aprobación crediticia
          example: true
        creditScore:
          type: integer
          description: Puntuación crediticia (300-850)
          minimum: 300
          maximum: 850
          example: 720
        maxApprovedAmount:
          type: number
          description: Monto máximo aprobado
          example: 100000.00
        riskLevel:
          type: string
          enum:
            - LOW
            - MEDIUM
            - HIGH
          description: Nivel de riesgo asociado
          example: "LOW"

    ErrorResponse:
      type: object
      properties:
        code:
          type: string
          description: Código de error técnico
          example: "INVALID_AMOUNT"
        message:
          type: string
          description: Mensaje legible para el usuario
          example: "El monto solicitado debe estar entre 1000 y 500000"
        field:
          type: string
          description: Campo que causó el error
          nullable: true
          example: "amount"
        retryAfter:
          type: integer
          description: Segundos hasta reintentar (para errores 503)
          nullable: true
          example: 30
        timestamp:
          type: string
          format: date-time
          description: Timestamp del error
          example: "2024-01-15T10:30:00Z"

  securitySchemes:
    ApiKeyAuth:
      type: apiKey
      in: header
      name: X-API-Key
      description: >
        API Key para autenticación de servicios internos.
        Debe ser proporcionada por el administrador del sistema.

  responses:
    BadRequestError:
      description: Solicitud incorrecta
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/ErrorResponse'

    UnauthorizedError:
      description: No autorizado
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/ErrorResponse'

    NotFoundError:
      description: Recurso no encontrado
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/ErrorResponse'

    InternalServerError:
      description: Error interno del servidor
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/ErrorResponse'

// === ARCHIVO: src/test/java/com/empresa/prestamos/application/service/LoanRequestServiceTest.java ===
package com.empresa.prestamos.application.service;

import com.empresa.prestamos.application.ports.out.CreditCheckServicePort;
import com.empresa.prestamos.application.ports.out.LoanRequestPersistencePort;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.IdempotencyKey;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanRequestService")
class LoanRequestServiceTest {

    @Mock
    private LoanRequestPersistencePort persistencePort;

    @Mock
    private CreditCheckServicePort creditCheckServicePort;

    @InjectMocks
    private LoanRequestService loanRequestService;

    private LoanRequest loanRequest;
    private IdempotencyKey idempotencyKey;

    @BeforeEach
    void setUp() {
        UUID requestId = UUID.randomUUID();
        String customerId = "CUST-001";
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;

        idempotencyKey = new IdempotencyKey(customerId + "|" + amount.toPlainString(), "WEB");

        loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            LocalDate.now(),
            LoanStatus.PENDING,
            idempotencyKey.getCompositeKey()
        );
    }

    @Test
    @DisplayName("Debe aprobar solicitud cuando el servicio de crédito responde positivamente")
    void shouldApproveRequestWhenCreditCheckPasses() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString())).thenReturn(Mono.just(true));
        when(persistencePort.save(any(LoanRequest.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .assertNext(savedRequest -> {
                assertEquals(LoanStatus.APPROVED, savedRequest.getStatus());
                assertNotNull(savedRequest.getRequestId());
            })
            .verifyComplete();

        verify(persistencePort).existsByIdempotencyKey(idempotencyKey.getCompositeKey());
        verify(creditCheckServicePort).checkCreditHistory(loanRequest.getCustomerId());
        verify(persistencePort).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar solicitud cuando el servicio de crédito responde negativamente")
    void shouldRejectRequestWhenCreditCheckFails() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString())).thenReturn(Mono.just(false));
        when(persistencePort.save(any(LoanRequest.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .assertNext(savedRequest -> {
                assertEquals(LoanStatus.REJECTED, savedRequest.getStatus());
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar DuplicateRequestException cuando la clave de idempotencia ya existe")
    void shouldThrowDuplicateRequestExceptionWhenIdempotencyKeyExists() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(true));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .expectError(DuplicateRequestException.class)
            .verify();

        verify(persistencePort, never()).save(any(LoanRequest.class));
        verify(creditCheckServicePort, never()).checkCreditHistory(anyString());
    }

    @Test
    @DisplayName("Debe manejar backpressure cuando el buffer está lleno")
    void shouldHandleBackpressureWhenBufferIsFull() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString()))
            .thenReturn(Mono.delay(java.time.Duration.ofMillis(100)).flatMap(l -> Mono.just(true)));
        when(persistencePort.save(any(LoanRequest.class)))
            .thenReturn(Mono.delay(java.time.Duration.ofMillis(50)).flatMap(l -> Mono.just(loanRequest)));

        int concurrentRequests = 100;
        var requests = java.util.stream.IntStream.range(0, concurrentRequests)
            .mapToObj(i -> loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .toList();

        StepVerifier.create(Mono.zip(requests, objects -> objects))
            .expectComplete()
            .verify(java.time.Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Debe recuperar automáticamente cuando el servicio de crédito falla temporalmente")
    void shouldRecoverAutomaticallyWhenCreditServiceFailsTemporarily() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString()))
            .thenReturn(Mono.error(new RuntimeException("Servicio no disponible")))
            .thenReturn(Mono.just(true));
        when(persistencePort.save(any(LoanRequest.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .assertNext(savedRequest -> {
                assertEquals(LoanStatus.APPROVED, savedRequest.getStatus());
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe rechazar solicitud con monto inválido")
    void shouldRejectRequestWithInvalidAmount() {
        LoanRequest invalidRequest = new LoanRequest(
            UUID.randomUUID(),
            "CUST-001",
            new BigDecimal("-1000.00"),
            24,
            LocalDate.now(),
            LoanStatus.PENDING,
            "invalid-key"
        );

        StepVerifier.create(loanRequestService.processLoanRequest(invalidRequest, idempotencyKey))
            .expectError(IllegalArgumentException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe rechazar solicitud con plazo inválido")
    void shouldRejectRequestWithInvalidTerm() {
        LoanRequest invalidRequest = new LoanRequest(
            UUID.randomUUID(),
            "CUST-001",
            new BigDecimal("50000.00"),
            0,
            LocalDate.now(),
            LoanStatus.PENDING,
            "invalid-key"
        );

        StepVerifier.create(loanRequestService.processLoanRequest(invalidRequest, idempotencyKey))
            .expectError(IllegalArgumentException.class)
            .verify();
    }
}

// === ARCHIVO: src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java ===
package com.empresa.prestamos.infrastructure.adapters.in.web;


import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.application.service.LoanRequestService;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.*;

@WebFluxTest(LoanRequestWebController.class)
@DisplayName("Pruebas de integración para LoanRequestWebController")
class LoanRequestWebControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LoanRequestService loanRequestService;

    private LoanRequest loanRequest;
    private String requestBody;

    @BeforeEach
    void setUp() {
        UUID requestId = UUID.randomUUID();
        loanRequest = new LoanRequest(
            requestId,
            "CUST-001",
            new BigDecimal("50000.00"),
            24,
            LocalDate.now(),
            LoanStatus.APPROVED,
            "CUST-001|50000.00"
        );

        requestBody = """
            {
                "customerId": "CUST-001",
                "amount": 50000.00,
                "termMonths": 24,
                "idempotencyKey": "CUST-001|50000.00"
            }
            """;
    }

    @Test
    @DisplayName("Debe crear solicitud de préstamo exitosamente")
    void shouldCreateLoanRequestSuccessfully() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.just(loanRequest));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.requestId").isEqualTo(loanRequest.getRequestId().toString())
            .jsonPath("$.customerId").isEqualTo("CUST-001")
            .jsonPath("$.status").isEqualTo("APPROVED");
    }

    @Test
    @DisplayName("Debe devolver 400 cuando el monto es inválido")
    void shouldReturn400WhenAmountIsInvalid() {
        String invalidBody = """
            {
                "customerId": "CUST-001",
                "amount": -1000.00,
                "termMonths": 24,
                "idempotencyKey": "CUST-001|50000.00"
            }
            """;

        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.error(new IllegalArgumentException("Monto inválido")));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(invalidBody)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Debe devolver 409 cuando hay solicitud duplicada")
    void shouldReturn409WhenDuplicateRequest() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.error(new com.empresa.prestamos.domain.exception.DuplicateRequestException(
                "CUST-001|50000.00", "WEB")));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(409);
    }

    @Test
    @DisplayName("Debe devolver 500 cuando el servicio externo falla")
    void shouldReturn500WhenExternalServiceFails() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.error(new RuntimeError("Error de conexión con servicio de crédito")));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().is5xxServerError();
    }

    @Test
    @DisplayName("Debe obtener solicitud por ID exitosamente")
    void shouldGetRequestByIdSuccessfully() {
        when(loanRequestService.findById(any(UUID.class)))
            .thenReturn(Mono.just(loanRequest));

        webTestClient.get()
            .uri("/api/v1/loan-requests/{id}", loanRequest.getRequestId())
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.requestId").isEqualTo(loanRequest.getRequestId().toString());
    }

    @Test
    @DisplayName("Debe devolver 404 cuando la solicitud no existe")
    void shouldReturn404WhenRequestNotFound() {
        when(loanRequestService.findById(any(UUID.class)))
            .thenReturn(Mono.empty());

        webTestClient.get()
            .uri("/api/v1/loan-requests/{id}", UUID.randomUUID())
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Debe validar que el Content-Type sea application/json")
    void shouldValidateContentTypeIsJson() {
        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.TEXT_PLAIN)
            .bodyValue("invalid payload")
            .exchange()
            .expectStatus().isUnsupportedMediaType();
    }

    @Test
    @DisplayName("Debe manejar múltiples solicitudes concurrentes correctamente")
    void shouldHandleConcurrentRequestsCorrectly() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.just(loanRequest));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated();

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated();
    }
}
```
