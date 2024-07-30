import java.util.List;

class ProcessadorDeContas {

    public void processar(List<Conta> contas, Fatura fatura) {
        double somaPagamentos = 0.0;

        for (Conta conta : contas) {
            Pagamento pagamento = criarPagamento(conta, fatura);
            if (pagamento != null) {
                fatura.adicionarPagamento(pagamento);
                if (isPagamentoValido(pagamento, fatura)) {
                    somaPagamentos += pagamento.getValorPago();
                }
            }
        }

        if (somaPagamentos >= fatura.getValorTotal()) {
            fatura.setStatus("PAGA");
        } else {
            fatura.setStatus("PENDENTE");
        }
    }

    private Pagamento criarPagamento(Conta conta, Fatura fatura) {
        double valorPago = conta.getValorPago();
        String tipoPagamento = conta.getCodigo();

        if ("BOLETO".equals(conta.getCodigo())) {
            if (valorPago < 0.01 || valorPago > 5000.00) {
                return null;
            }
            if (conta.getData().isAfter(fatura.getData())) {
                valorPago *= 1.10; // Acrescentar 10% de juros
            }
        }

        return new Pagamento(valorPago, conta.getData(), tipoPagamento);
    }

    private boolean isPagamentoValido(Pagamento pagamento, Fatura fatura) {
        if ("CARTAO_CREDITO".equals(pagamento.getTipoPagamento())) {
            return pagamento.getData().isBefore((fatura.getData().minusDays(14)));
        }
        return !pagamento.getData().isAfter(fatura.getData());
    }
}