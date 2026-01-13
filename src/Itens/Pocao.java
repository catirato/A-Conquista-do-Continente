package Itens;

import Entidades.Heroi;
/**
 * Representa uma poção utilizável por um herói.
 *
 * Uma poção é um consumível que pode restaurar pontos de vida
 * e/ou aumentar temporariamente a força do herói.
 *
 * Esta classe estende {@link Consumivel} e implementa os efeitos
 * específicos de cura e reforço.
 */
public class Pocao extends Consumivel {

    /** Quantidade de vida restaurada pela poção */
    protected int vidaCurar;

    /** Valor de aumento temporário de força */
    protected int aumentoForca;
    /**
     * Cria uma nova poção.
     *
     * @param nome nome da poção
     * @param preco preço da poção
     * @param vidaCurar quantidade de vida restaurada
     * @param aumentoForca valor de aumento de força
     */
    public Pocao(String nome, int preco, int vidaCurar, int aumentoForca) {
        super(nome, preco);
        this.vidaCurar = vidaCurar;
        this.aumentoForca = aumentoForca;
    }

    /**
     * Retorna a quantidade de vida restaurada pela poção.
     *
     * @return vida restaurada
     */
    public int getVidaCurar() {
        return vidaCurar;
    }

    /**
     * Retorna o valor de aumento de força fornecido pela poção.
     *
     * @return aumento de força
     */
    public int getAumentoForca() {
        return aumentoForca;
    }

    /**
     * Aplica os efeitos da poção ao herói.
     *
     * A poção pode restaurar vida, aumentar força
     * ou aplicar ambos os efeitos.
     *
     * @param heroi herói que utiliza a poção
     */
    @Override
    public void usar(Heroi heroi) {
        System.out.println(heroi.getNome() + " bebe a poção " + nome + "!");

        if (vidaCurar > 0) {
            heroi.setHp(heroi.getHp() + vidaCurar);
            System.out.println("❤️ Recupera " + vidaCurar + " pontos de vida!");
        }

        if (aumentoForca > 0) {
            heroi.forca += aumentoForca;
            System.out.println("💪 A força aumenta temporariamente em " + aumentoForca + "!");
        }
    }

    /**
     * Mostra no console os detalhes completos da poção,
     * incluindo efeitos, preço e heróis permitidos.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Tipo: Poção");
        System.out.println(getIcone() + " " + "Nome: " + nome +
                " | ❤️ Cura: " + vidaCurar +
                " | 💪 Força: +" + aumentoForca +
                " | 💰 Preço: " + preco + " moedas de ouro");
        System.out.print("Heróis Permitidos: ");
        if (heroisPermitidos.isEmpty()) {
            System.out.println("Nenhum");
        } else {
            System.out.println(String.join(", ", heroisPermitidos));
        }

        System.out.println("==============================");
    }

    /**
     * Retorna o ícone associado à poção, com base no seu nome.
     *
     * @return ícone representativo da poção
     */
    public String getIcone() {

        String n = nome.toLowerCase();

        if (n.contains("vida")) return "❤️";
        if (n.contains("força")) return "💪";
        if (n.contains("mista")) return "🧪";
        if (n.contains("lend")) return "✨";

        return "🧴"; // ícone genérico
    }

}
