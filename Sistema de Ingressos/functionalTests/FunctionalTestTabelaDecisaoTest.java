import static org.junit.jupiter.api.Assertions.assertEquals;

import SistemaDeIngressos.Relatorio;
import SistemaDeIngressos.StatusFinanceiro;
import org.junit.jupiter.api.Test;

public class FunctionalTestTabelaDecisaoTest {

    @Test
    public void testStatusFinanceiroLucro() {
        Relatorio relatorio = new Relatorio(100, 50, 350, 1625.0, StatusFinanceiro.LUCRO);
        assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testStatusFinanceiroEstavel() {
        Relatorio relatorio = new Relatorio(100, 50, 350, 0.0, StatusFinanceiro.ESTAVEL);
        assertEquals(StatusFinanceiro.ESTAVEL, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testStatusFinanceiroPrejuizo() {
        Relatorio relatorio = new Relatorio(100, 50, 350, -500.0, StatusFinanceiro.PREJUIZO);
        assertEquals(StatusFinanceiro.PREJUIZO, relatorio.getStatusFinanceiro());
    }
}
