# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de API con Arquitectura Hexagonal**.

| | |
|---|---|
| Tema | uso y aplicacion framework |
| Nivel | advanced-l2 |
| Chapter | Integración — Desarrollo |
| Especialidad | Api |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 2 semanas |

## Receta del stack

Esqueleto obligatorio:

- `openapi.yaml con paths, schemas y responses completos`
- `policies/ con las politicas del gateway (rate limit, auth, transformacion)`
- `environments/ con la configuracion por ambiente`
- `tests/ con la coleccion de contrato`
- `README.md con el contrato y los codigos de error`

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa 3.5.6
- io.projectreactor:reactor-core 3.6.3
- io.github.resilience4j:resilience4j-spring-boot2 2.1.0
- org.apache.camel:camel-spring-boot-starter 4.4.0
- org.apache.camel:camel-jackson 4.4.0
- org.springframework.boot:spring-boot-starter-test 3.5.6
- org.junit.jupiter:junit-jupiter-api 5.10.2
- org.mockito:mockito-core 5.11.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `npx --yes @redocly/cli lint openapi.yaml` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `npx --yes @redocly/cli lint openapi.yaml` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del entorno y canal de entrada**: Canal de entrada operativo persistiendo solicitudes con idempotencia.
- **Fase 2 — Consolidación de canales y manejo de backpressure**: Consolidación de los tres canales sin pérdida ante backpressure de uno.
- **Fase 3 — Recuperación automática ante fallo del servicio externo**: Recuperación automática ante caída del consumidor downstream sin reprocesar emitidas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/empresa/prestamos/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (64)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/empresa/prestamos/PrestamosApplication.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/application/ports/in/LoanRequestUseCase.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/application/ports/out/LoanRequestPersistencePort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/application/ports/out/CreditCheckServicePort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `reactor.util.retry`
      El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/config/IdempotencyConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/config/ReactorConfig.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Scheduler pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/empresa/prestamos/application/service/LoanRequestServiceTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestDTO.customerId`
      Se invoca `customerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestDTO.amount`
      Se invoca `amount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestDTO.termMonths`
      Se invoca `termMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java` — `LoanRequestUseCase.submitRequest`
      Se invoca `submitRequest` sobre `LoanRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.customerId`
      Se invoca `customerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.idempotencyKey`
      Se invoca `idempotencyKey` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.requestId`
      Se invoca `requestId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.amount`
      Se invoca `amount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.termMonths`
      Se invoca `termMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.requestDate`
      Se invoca `requestDate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.status`
      Se invoca `status` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.creditScore`
      Se invoca `creditScore` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.approvedAmount`
      Se invoca `approvedAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestDTO.rejectionReason`
      Se invoca `rejectionReason` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestService.getRequestById`
      Se invoca `getRequestById` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestService.streamPendingRequests`
      Se invoca `streamPendingRequests` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java` — `LoanRequestService.listRequestsByCustomer`
      Se invoca `listRequestsByCustomer` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.idempotencyKey`
      Se invoca `idempotencyKey` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestService.getRequestById`
      Se invoca `getRequestById` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestService.listRequestsByCustomer`
      Se invoca `listRequestsByCustomer` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.requestId`
      Se invoca `requestId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.customerId`
      Se invoca `customerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.amount`
      Se invoca `amount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.termMonths`
      Se invoca `termMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.requestDate`
      Se invoca `requestDate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.status`
      Se invoca `status` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.creditScore`
      Se invoca `creditScore` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.approvedAmount`
      Se invoca `approvedAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java` — `LoanRequestDTO.rejectionReason`
      Se invoca `rejectionReason` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `LoanStatus.name`
      Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `LoanRequestEntity.setLastUpdated`
      Se invoca `setLastUpdated` sobre `LoanRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java` — `LoanRequestEntity.setChannel`
      Se invoca `setChannel` sobre `LoanRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckCacheEntry.isExpired`
      Se invoca `isExpired` sobre `CreditCheckCacheEntry`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckCacheEntry.getResult`
      Se invoca `getResult` sobre `CreditCheckCacheEntry`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckResult.score`
      Se invoca `score` sobre `CreditCheckResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckResponse.score`
      Se invoca `score` sobre `CreditCheckResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java` — `CreditCheckResponse.reference`
      Se invoca `reference` sobre `CreditCheckResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/empresa/prestamos/application/service/LoanRequestServiceTest.java` — `LoanRequestService.processLoanRequest`
      Se invoca `processLoanRequest` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java` — `LoanRequestService.processLoanRequest`
      Se invoca `processLoanRequest` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java` — `LoanRequestService.findById`
      Se invoca `findById` sobre `LoanRequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `org.apache.camel:camel-spring-boot-bom@4.4.0`
      org.apache.camel:camel-spring-boot-bom declara la version 4.4.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (23)

- `pom.xml`
- `src/main/java/com/empresa/prestamos/PrestamosApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/empresa/prestamos/domain/model/LoanRequest.java`
- `src/main/java/com/empresa/prestamos/domain/model/IdempotencyKey.java`
- `src/main/java/com/empresa/prestamos/domain/exception/DuplicateRequestException.java`
- `src/main/java/com/empresa/prestamos/domain/exception/CreditCheckServiceException.java`
- `src/main/java/com/empresa/prestamos/application/ports/in/LoanRequestUseCase.java`
- `src/main/java/com/empresa/prestamos/application/ports/out/LoanRequestPersistencePort.java`
- `src/main/java/com/empresa/prestamos/application/ports/out/CreditCheckServicePort.java`
- `src/main/java/com/empresa/prestamos/application/service/LoanRequestService.java`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebController.java`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile/LoanRequestMobileController.java`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi/LoanRequestExternalApiController.java`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestJpaAdapter.java`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence/LoanRequestEntity.java`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck/CreditCheckServiceAdapter.java`
- `src/main/java/com/empresa/prestamos/infrastructure/config/IdempotencyConfig.java`
- `src/main/java/com/empresa/prestamos/infrastructure/config/ResilienceConfig.java`
- `src/main/java/com/empresa/prestamos/infrastructure/config/ReactorConfig.java`
- `openapi.yaml`
- `src/test/java/com/empresa/prestamos/application/service/LoanRequestServiceTest.java`
- `src/test/java/com/empresa/prestamos/infrastructure/adapters/in/web/LoanRequestWebControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/empresa/prestamos`
- `src/main/java/com/empresa/prestamos/application`
- `src/main/java/com/empresa/prestamos/application/ports/in`
- `src/main/java/com/empresa/prestamos/application/ports/out`
- `src/main/java/com/empresa/prestamos/application/service`
- `src/main/java/com/empresa/prestamos/domain`
- `src/main/java/com/empresa/prestamos/domain/model`
- `src/main/java/com/empresa/prestamos/domain/exception`
- `src/main/java/com/empresa/prestamos/infrastructure`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/web`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/mobile`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/in/externalapi`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/persistence`
- `src/main/java/com/empresa/prestamos/infrastructure/adapters/out/creditcheck`
- `src/main/java/com/empresa/prestamos/infrastructure/config`
- `src/main/resources`
- `src/test/java/com/empresa/prestamos`

## Verificacion

```bash
npx --yes @redocly/cli lint openapi.yaml
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Integración, Especialidad Desarrollador, Tecnología API, Advanced
- Brecha que el reto ataca: Creacion de artefactos con patrones de desarrollo en arquitectura hexagonal o dominio y muestra aplicacion de desarrollo en lenguaje programacion dominante. Uso de framework y aplicacion en entorno práctico. El uso aplicado en desarrollo en cliente usando el lenguaje dando valor de artefactos
- Mision: Candidato con experiencia en soluciones de integración y APIs

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
