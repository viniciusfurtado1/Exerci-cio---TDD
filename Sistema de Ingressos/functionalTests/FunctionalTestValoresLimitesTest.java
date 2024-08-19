import SistemaDeIngressos.Ingresso;
import SistemaDeIngressos.Lote;
import SistemaDeIngressos.TipoIngresso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionalTestValoresLimitesTest {
    @Test
    public void testNumeroIngressosLimiteInferiorValido() {
        // Testa o limite inferior válido (1 ingresso)
        Lote lote = new Lote(1, 0.10, 0.20, 0.10);
        assertEquals(1, lote.getIngressos().size());
    }

    @Test
    public void testNumeroIngressosLimiteSuperiorValido() {
        // Testa um número muito grande de ingressos (por exemplo, 10000)
        Lote lote = new Lote(10000, 0.10, 0.20, 0.10);
        assertEquals(10000, lote.getIngressos().size());
    }
    @Test
    public void testPrecoIngressoNormalSemDesconto() {
        // Testa preço de ingresso NORMAL no limite inferior (preço base = 10.0)
        Ingresso normal = new Ingresso(TipoIngresso.NORMAL);
        double preco = normal.getPreco(10.0, 0.0);
        assertEquals(10.0, preco, 0.01);
    }

    @Test
    public void testPrecoIngressoNormalComDescontoMaximo() {
        // Testa preço de ingresso NORMAL no limite superior com desconto máximo (25%)
        Ingresso normal = new Ingresso(TipoIngresso.NORMAL);
        double preco = normal.getPreco(10.0, 0.25);
        assertEquals(7.5, preco, 0.01);
    }

    @Test
    public void testPrecoIngressoVIPComDescontoMaximo() {
        // Testa preço de ingresso VIP no limite superior com desconto máximo (25%)
        Ingresso vip = new Ingresso(TipoIngresso.VIP);
        double preco = vip.getPreco(10.0, 0.25);
        assertEquals(15.0, preco,0.01);
    }

    @Test
    public void testPrecoIngressoMeiaEntrada() {
        // Testa preço de ingresso MEIA_ENTRADA (sem desconto aplicável)
        Ingresso meia = new Ingresso(TipoIngresso.MEIA_ENTRADA);
        double preco = meia.getPreco(10.0, 0.0);
        assertEquals(5.0, preco, 0.01);
    }
}