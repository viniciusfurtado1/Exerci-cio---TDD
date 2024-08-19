package SistemaDeIngressos;

import java.util.ArrayList;
import java.util.List;

// Classe Lote
public class Lote {
    private static int idCounter = 0;
    private int id;
    private List<Ingresso> ingressos;
    private double desconto;


    public Lote(int quantidade, double desconto, double percentualVIP, double percentualMeia) {
        this.id = ++idCounter;
        this.ingressos = new ArrayList<>();
        this.desconto = Math.min(desconto, 0.25);

        int numVIP = (int) (quantidade * percentualVIP);
        int numMeia = (int) (quantidade * percentualMeia);
        int numNormal = quantidade - numVIP - numMeia;

        for (int i = 0; i < numVIP; i++) {
            ingressos.add(new Ingresso(TipoIngresso.VIP));
        }
        for (int i = 0; i < numMeia; i++) {
            ingressos.add(new Ingresso(TipoIngresso.MEIA_ENTRADA));
        }
        for (int i = 0; i < numNormal; i++) {
            ingressos.add(new Ingresso(TipoIngresso.NORMAL));
        }
    }

    public int getId() {
        return id;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public double getDesconto() {
        return desconto;
    }

    public void venderIngresso(int ingressoId) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId() == ingressoId) {
                ingresso.setVendido(true);
                return;
            }
        }
        throw new IllegalArgumentException("Ingresso com ID " + ingressoId + " não encontrado.");
    }
}