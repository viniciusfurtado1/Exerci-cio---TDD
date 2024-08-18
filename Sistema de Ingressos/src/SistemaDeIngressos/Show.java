package SistemaDeIngressos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Classe Show
public class Show {
    private LocalDate data;
    private String artista;
    private double cache;
    private double despesasInfraestrutura;
    private boolean dataEspecial;
    private List<Lote> lotes;

    public Show(LocalDate data, String artista, double cache, double despesasInfraestrutura, boolean dataEspecial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.dataEspecial = dataEspecial;
        this.lotes = new ArrayList<>();
    }

    public void adicionarLote(Lote lote) {
        lotes.add(lote);
    }

    public void venderIngresso(int loteId, int ingressoId) {
        for (Lote lote : lotes) {
            if (lote.getId() == loteId) {
                lote.venderIngresso(ingressoId);
                return;
            }
        }
        throw new IllegalArgumentException("Lote com ID " + loteId + " não encontrado.");
    }

    public Relatorio gerarRelatorio(double precoNormal) {
        int ingressosVIPVendidos = 0;
        int ingressosMeiaVendidos = 0;
        int ingressosNormaisVendidos = 0;
        double receita = 0.0;

        for (Lote lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.isVendido()) {
                    double precoIngresso = ingresso.getPreco(precoNormal, lote.getDesconto());
                    receita += precoIngresso;

                    switch (ingresso.getTipo()) {
                        case VIP:
                            ingressosVIPVendidos++;
                            break;
                        case MEIA_ENTRADA:
                            ingressosMeiaVendidos++;
                            break;
                        case NORMAL:
                            ingressosNormaisVendidos++;
                            break;
                    }
                }
            }
        }

        double despesasTotais = despesasInfraestrutura;
        if (dataEspecial) {
            despesasTotais *= 1.15;
        }
        despesasTotais += cache;

        double receitaLiquida = receita - despesasTotais;
        StatusFinanceiro status;

        if (receitaLiquida > 0) {
            status = StatusFinanceiro.LUCRO;
        } else if (receitaLiquida == 0) {
            status = StatusFinanceiro.ESTAVEL;
        } else {
            status = StatusFinanceiro.PREJUIZO;
        }

        return new Relatorio(ingressosVIPVendidos, ingressosMeiaVendidos, ingressosNormaisVendidos, receitaLiquida, status);
    }
    public List<Lote> getLotes() {
        return lotes;
    }
}