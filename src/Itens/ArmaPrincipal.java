package Itens;
/**
 * Representa uma arma principal utilizável por um herói.
 *
 * Uma arma principal fornece valores de ataque normal
 * e ataque especial, que são utilizados durante o combate.
 *
 * Esta classe estende {@link ItemHeroi}, podendo ser equipada
 * pelo herói e substituindo a arma atualmente em uso.
 */
public class ArmaPrincipal extends ItemHeroi {

       /** Valor de ataque normal da arma */
    protected int ataque;

    /** Valor de ataque especial da arma */
    protected int ataqueEspecial;
	
    /**
     * Cria uma nova arma principal.
     *
     * @param nome nome da arma
     * @param preco preço da arma
     * @param ataque valor de ataque normal
     * @param ataqueEspecial valor de ataque especial
     */
    public ArmaPrincipal(String nome, int preco, int ataque, int ataqueEspecial) {
        super(nome, preco);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }
    /**
     * Retorna o valor de ataque normal da arma.
     *
     * @return ataque normal
     */
    public int getAtaque() {
        return ataque;
    }
    /**
     * Retorna o valor de ataque especial da arma.
     *
     * @return ataque especial
     */
    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }
	
    /**
     * Mostra no console os detalhes completos da arma,
     * incluindo preço, valores de ataque e heróis permitidos.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Tipo: Arma Principal");
        System.out.println(getIcone() + " " + " Nome: " + nome +
                            " | 💰 Preço: " + preco +
                            " | ⚔️ Ataque: " + ataque +
                            " | ✨ Ataque Especial: " + ataqueEspecial);
        System.out.print("Herois Permitidos: ");
        if (heroisPermitidos.isEmpty()) {
            System.out.println("Nenhum");
        } else {
            System.out.println(String.join(", ", heroisPermitidos));
        }

        System.out.println("============================================");
    }

    /**
     * Retorna o ícone associado à arma, com base no seu nome.
     *
     * O ícone é determinado através de palavras-chave,
     * permitindo uma representação visual simples no console.
     *
     * @return ícone representativo da arma
     */
    public String getIcone() {

        String n = nome.toLowerCase();

        if (n.contains("prata")) return "🐺✨";
        if (n.contains("aço")) return "🐺";
        if (n.contains("rúnic")) return "🔷";
        if (n.contains("katana")) return "🗡️";
        if (n.contains("vamp")) return "🩸";
        if (n.contains("flame")) return "🔥";
        if (n.contains("nilfgaard")) return "🛡️";
        if (n.contains("alabarda")) return "🔱";
        if (n.contains("machado")) return "🪓";
        if (n.contains("espada")) return "⚔️";

        return "🗡️"; // ícone genérico
    }

}
