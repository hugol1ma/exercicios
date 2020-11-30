package br.com.tinnova.veiculo.entidade;

/**
 * Enum com as principais marcas de veiculos que atuam no brasil
 *
 */
public enum MarcaVeiculoEnum {


    CHEVROLET("Chevrolet"),
    VOLKSWAGEN("Volkswagen"),
    FIAT("Fiat"),
    RENAULT("Renault"),
    FORD("Ford"),
    TOYOTA("Toyota"),
    HONDA("Honda"),
    HYUNDAI("Hyundai")
    ;

    private String nome;

    MarcaVeiculoEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
