import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionalTestTabelaDecisaoTest {

    private ProcessadorDeContas processador;
    private Fatura fatura;

    @BeforeEach
    void setUp() {
        processador = new ProcessadorDeContas();
    }

    @Test
    void testPagamentoBoletoDentroDoPrazoValorValido() {
        // Caso 1: Boleto, valor dentro do limite, data dentro do prazo
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 2, 20), 3000.00, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 2, 20), 3000.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoCartaoCreditoTransferenciaDentroDoPrazo() {
        // Caso 2: Cartão de crédito e transferenia , data dentro do prazo (mais de 15 dias antes da fatura)
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 2, 20), 2000.00, "Cliente 1");
        contas.add(new Conta("CARTAO_CREDITO", LocalDate.of(2023, 2, 5), 1000.00));
        contas.add(new Conta("TRANSFERENCIA_BANCARIA", LocalDate.of(2023, 2, 20), 1000.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoCartaoCreditoTransferenciaForaDoPrazo() {
        // Caso 3: Cartão de crédito e transferencia, data fora do prazo (menos de 15 dias antes da fatura)
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 2, 20), 2000.00, "Cliente 1");
        contas.add(new Conta("CARTAO_CREDITO", LocalDate.of(2023, 2, 10), 1000.00));
        contas.add(new Conta("TRANSFERENCIA_BANCARIA", LocalDate.of(2023, 2, 20), 1000.00));

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoTransferenciaBoletoDentroDoPrazo() {
        // Caso 4: Transferência bancária e boleto, data dentro do prazo (igual ou anterior à data da fatura)
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 2, 20), 2000.00, "Cliente 1");
        contas.add(new Conta("TRANSFERENCIA_BANCARIA", LocalDate.of(2023, 2, 20), 1500.00));
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 2, 5), 500.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoCartaoCreditoBoletoForaDoPrazo() {
        // Caso 5: Cartão de crédito e boleto, data dentro do prazo (mais de 15 dias antes da fatura)
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 2, 20), 2000.00, "Cliente 1");
        contas.add(new Conta("CARTAO_CREDITO", LocalDate.of(2023, 2, 10), 1000.00));
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 2, 5), 1000.00));

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
    }
}