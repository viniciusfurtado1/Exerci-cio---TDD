import SistemaDeIngressos.Ingresso;
import SistemaDeIngressos.TipoIngresso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FunctionalTestParticoesEquivalenciaTest {
    @Test
    public void testPrecoIngressoVIP() {
        Ingresso vip = new Ingresso(TipoIngresso.VIP);
        double preco = vip.getPreco(10.0, 0.15);
        assertEquals(17.0, preco, 0.1);
    }

    @Test
    public void testPrecoIngressoNormal() {
        Ingresso normal = new Ingresso(TipoIngresso.NORMAL);
        double preco = normal.getPreco(10.0, 0.15);
        assertEquals(8.5, preco, 0.1);
    }

    @Test
    public void testPrecoIngressoMeiaEntrada() {
        Ingresso meia = new Ingresso(TipoIngresso.MEIA_ENTRADA);
        double preco = meia.getPreco(10.0, 0.0); // Desconto não aplicado a meia-entrada
        assertEquals(5.0, preco, 0.1);
    }
}
