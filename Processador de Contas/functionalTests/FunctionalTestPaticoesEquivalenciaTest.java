import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionalTestPaticoesEquivalenciaTest {

    private ProcessadorDeContas processador;
    private Fatura fatura;

    @BeforeEach
    void setUp() {
        processador = new ProcessadorDeContas();

    }

    @Test
    void testPagamentoPorBoletoComDataValida() {
        // Pagamento por boleto com data da conta igual ou anterior à data da fatura
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 20), 100.00, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 20), 100.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoPorCartaoCreditoComDataValida() {
        // Pagamento por cartão de crédito com data da conta pelo menos 15 dias antes da fatura
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 20), 100.00, "Cliente 1");
        contas.add(new Conta("CARTAO_CREDITO", LocalDate.of(2023, 1, 5), 100.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoPorCartaoCreditoComDataInvalida() {
        // Pagamento por cartão de crédito com data da conta menos de 15 dias antes da fatura
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 20), 100.00, "Cliente 1");
        contas.add(new Conta("CARTAO_CREDITO", LocalDate.of(2023, 1, 10), 100.00));

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoPorTransferenciaComDataValida() {
        // Pagamento por transferência com data da conta igual ou anterior à data da fatura
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 20), 100.00, "Cliente 1");
        contas.add(new Conta("TRANSFERENCIA_BANCARIA", LocalDate.of(2023, 1, 20), 100.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoPorTipoNaoEspecificado() {
        // Pagamento com tipo de pagamento não especificado (não suportado)
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 20), 100.00, "Cliente 1");
        contas.add(new Conta("VALE", LocalDate.of(2023, 1, 20), 100.00));

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
    }
}