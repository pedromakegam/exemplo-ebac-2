package br.com.pedro.ebac;

public final class PessoaJuridica extends Pessoa {
    private final String cnpj;
    private final String razaoSocial;
    public PessoaJuridica(String nome, String endereco, String cnpj, String razaoSocial) {
        super(nome, endereco);
        this.cnpj = obrigatorio(cnpj, "CNPJ");
        this.razaoSocial = obrigatorio(razaoSocial, "razão social");
    }
    @Override public String getDocumento() { return cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
}
