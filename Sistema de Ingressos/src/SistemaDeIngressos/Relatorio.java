package SistemaDeIngressos;

public class Relatorio {
    private int ingressosVIPVendidos;
    private int ingressosMeiaVendidos;
    private int ingressosNormaisVendidos;
    private double receitaLiquida;
    private StatusFinanceiro statusFinanceiro;

    public Relatorio(int ingressosVIPVendidos, int ingressosMeiaVendidos, int ingressosNormaisVendidos, double receitaLiquida, StatusFinanceiro statusFinanceiro) {
        this.ingressosVIPVendidos = ingressosVIPVendidos;
        this.ingressosMeiaVendidos = ingressosMeiaVendidos;
        this.ingressosNormaisVendidos = ingressosNormaisVendidos;
        this.receitaLiquida = receitaLiquida;
        this.statusFinanceiro = statusFinanceiro;
    }

    public void exibirRelatorio() {
        System.out.println("Relatório do Show:");
        System.out.println("Ingressos VIP vendidos: " + ingressosVIPVendidos);
        System.out.println("Ingressos Meia Entrada vendidos: " + ingressosMeiaVendidos);
        System.out.println("Ingressos Normais vendidos: " + ingressosNormaisVendidos);
        System.out.println("Receita líquida: R$ " + receitaLiquida);
        System.out.println("Status financeiro: " + statusFinanceiro);
    }
    public int getIngressosVIPVendidos() {
        return ingressosVIPVendidos;
    }

    public int getIngressosMeiaVendidos() {
        return ingressosMeiaVendidos;
    }

    public int getIngressosNormaisVendidos() {
        return ingressosNormaisVendidos;
    }

    public double getReceitaLiquida() {
        return receitaLiquida;
    }

    public StatusFinanceiro getStatusFinanceiro() {
        return statusFinanceiro;
    }
}
