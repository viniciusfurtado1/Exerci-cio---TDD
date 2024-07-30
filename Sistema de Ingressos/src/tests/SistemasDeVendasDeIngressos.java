package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;

import SistemaDeIngressos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaVendaIngressosTest {
    private Show show;
    private Lote lote1;

    @BeforeEach
    public void setUp() {
        show = new Show(LocalDate.of(2024, 12, 25), "Bob Marley", 1000.0, 2000.0, true);
        lote1 = new Lote(500, 0.15, 0.20, 0.10);
    }

    @Test
    public void testAdicionarLote() {
        show.adicionarLote(lote1);
        assertEquals(1, show.getLotes().size());
    }

    @Test
    public void testVenderIngresso() {
        show.adicionarLote(lote1);
        show.venderIngresso(lote1.getId(), lote1.getIngressos().get(0).getId());
        assertEquals(true, lote1.getIngressos().get(0).isVendido());
    }

    @Test
    public void testGerarRelatorio() {
        show.adicionarLote(lote1);
        for (Ingresso ingresso : lote1.getIngressos()) {
            ingresso.setVendido(true); // Vendendo todos os ingressos para teste
        }

        Relatorio relatorio = show.gerarRelatorio(10.0);
        assertEquals(100, relatorio.getIngressosVIPVendidos());
        assertEquals(50, relatorio.getIngressosMeiaVendidos());
        assertEquals(350, relatorio.getIngressosNormaisVendidos());
        assertEquals(1625.0, relatorio.getReceitaLiquida(), 0.1);
        assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testVenderIngressoNaoExistente() {
        show.adicionarLote(lote1);
        assertThrows(IllegalArgumentException.class, () -> {
            show.venderIngresso(lote1.getId(), 999); // ID inválido
        });
    }
}