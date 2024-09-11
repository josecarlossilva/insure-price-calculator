package br.com.insurepricecalculator.enums;

public enum Categoria {
    VIDA(0.01, 0.022, 0.0),
    AUTO(0.055, 0.04, 0.01),
    VIAGEM(0.02, 0.04, 0.01),
    RESIDENCIAL(0.04, 0.0, 0.03),
    PATRIMONIAL(0.05, 0.03, 0.0);

    private final double iof;
    private final double pis;
    private final double cofins;

    Categoria(double iof, double pis, double cofins) {
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }

    public double getIof() {
        return iof;
    }

    public double getPis() {
        return pis;
    }

    public double getCofins() {
        return cofins;
    }
}