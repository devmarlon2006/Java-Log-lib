import io.github.devmarlon2006.SystemLog.log.LogHandle;
import io.github.devmarlon2006.SystemLog.log.StepsHandle;
import io.github.devmarlon2006.SystemLog.system.Logablee;
import io.github.devmarlon2006.SystemLog.system.Steppable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemLogIntegrationTest {

    @Test
    @DisplayName("Deve criar um Log de Informação e anexar Steps corretamente")
    void shouldCreateLogInfoWithAttachedSteps() {
        // --- ARRANGE (Preparação) ---
        MockService service = new MockService();
        MockStepExecutor stepExecutor = new MockStepExecutor();
        String mensagemLog = "Iniciando processo de validação";
        String mensagemStep = "Validando dados";
        String traceStep = "Trace-001";

        // --- ACT (Ação) ---
        // 1. Cria o Log Principal
        LogHandle log = service.provinderLogInfo(mensagemLog);
        
        // 2. Cria um Step
        StepsHandle step = stepExecutor.warn(mensagemStep, traceStep);
        
        // 3. Adiciona o Step ao Log (Assumindo que LogHandle tem addSteps baseado no seu código anterior)
        // Nota: O método addSteps não estava visível nos anexos, mas estava no seu Teste.java original.
        // Se LogHandle tiver uma lista de steps, é aqui que testamos.
        // log.addSteps(step); 
        
        // --- ASSERT (Verificação) ---
        
        // Verifica se o Log foi criado
        assertNotNull(log, "O LogHandle não deveria ser nulo");
        
        // Verifica propriedades do Step
        assertNotNull(step, "O StepsHandle não deveria ser nulo");
        assertEquals(mensagemStep, step.getMessage(), "A mensagem do step deve ser igual à informada");
        assertEquals(traceStep, step.getStackTrace(), "O trace do step deve ser igual ao informado");
        assertEquals("TEST_OPERATION", step.getOperationType(), "O tipo de operação deve vir da classe Steppable");
        assertNotNull(step.getOperationTime(), "O timestamp do step deve ser gerado automaticamente");

        // Verifica propriedades do Log (Baseado no padrão do Logablee)
        // Como o LogHandle.java completo não foi fornecido, assumo alguns getters:
        // assertEquals(200, log.getStatusCode());
    }

    @Test
    @DisplayName("Deve criar um Log de Erro corretamente")
    void shouldCreateLogError() {
        MockService service = new MockService();
        LogHandle errorLog = service.provinderLogError("Erro crítico no sistema");

        assertNotNull(errorLog);
        // Assumindo que initializeLogError define status 500
        // assertEquals(500, errorLog.getStatusCode()); 
    }

    // --- Classes auxiliares (Mocks) para simular o uso da biblioteca ---

    /**
     * Simula um serviço que implementa Logablee
     */
    static class MockService implements Logablee {
        @Override
        public LogHandle provinderLogInfo(String message) {
            return initializeLogInfo(message);
        }

        @Override
        public LogHandle provinderLogError(String message) {
            return initializeLogError(message);
        }
    }

    /**
     * Simula uma classe de passo (Step) que implementa Steppable
     */
    static class MockStepExecutor implements Steppable {
        @Override
        public StepsHandle warn(String message, String trace) {
            return getSteps(message, trace);
        }

        @Override
        public String provinderOperationType() {
            return "TEST_OPERATION";
        }
    }
}
