import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessadorDeContasTest {

    private ProcessadorDeContas processador;

    @BeforeEach
    public void setup() {
        processador = new ProcessadorDeContas();
    }

    @Test
    public void testFaturaExemplo1() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        List<Conta> contas = Arrays.asList(
                new Conta("BOLETO", LocalDate.of(2023, 2, 20), 500.00),
                new Conta("BOLETO", LocalDate.of(2023, 2, 20), 400.00),
                new Conta("BOLETO", LocalDate.of(2023, 2, 20), 600.00)
        );

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testFaturaExemplo2() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 2");
        List<Conta> contas = Arrays.asList(
                new Conta("CARTAO_CREDITO", LocalDate.of(2023, 2, 5), 700.00),
                new Conta("TRANSFERENCIA_BANCARIA", LocalDate.of(2023, 2, 17), 800.00)
        );

        processador.processar(contas, fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testFaturaExemplo3() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 3");
        List<Conta> contas = Arrays.asList(
                new Conta("CARTAO_CREDITO", LocalDate.of(2023, 2, 6), 700.00),
                new Conta("TRANSFERENCIA_BANCARIA", LocalDate.of(2023, 2, 17), 800.00)
        );

        processador.processar(contas, fatura);

        assertEquals("PENDENTE", fatura.getStatus());
    }
}