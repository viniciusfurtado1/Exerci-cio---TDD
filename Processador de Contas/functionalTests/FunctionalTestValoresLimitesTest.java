import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionalTestValoresLimitesTest {

    private ProcessadorDeContas processador;
    private Fatura fatura;

    @BeforeEach
    void setUp() {
        processador = new ProcessadorDeContas();

    }

    @Test
    void testPagamentoBoletoValorLimiteInferior() {
        // Valor do pagamento no limite inferior
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 5), 0.01, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 5), 0.01));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoBoletoValorLimiteSuperior() {
        // Valor do pagamento no limite superior
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 5), 5000.00, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 5), 5000.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoBoletoValorAbaixoLimiteInferior() {
        // Valor do pagamento abaixo do limite inferior
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 5), 0.00, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 5), 0.00));

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoBoletoValorAcimaLimiteSuperior() {
        // Valor do pagamento acima do limite superior
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 5), 5000.01, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 5), 5000.01));

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
        assertEquals(0, fatura.getPagamentos().size());
    }

    @Test
    void testPagamentoBoletoComAtraso() {
        // Pagamento por boleto com data posterior à data da conta
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 5), 1000.00, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 10), 1000.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testPagamentoBoletoNaDataDaConta() {
        // Pagamento por boleto na mesma data da conta
        List<Conta> contas = new ArrayList<>();
        fatura = new Fatura(LocalDate.of(2023, 1, 5), 1000.00, "Cliente 1");
        contas.add(new Conta("BOLETO", LocalDate.of(2023, 1, 5), 1000.00));

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }
}