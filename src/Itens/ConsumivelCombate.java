package Itens;

import Entidades.Heroi;
/**
 * Representa um consumível de combate.
 *
 * Um consumível de combate é um item que pode ser utilizado
 * durante o combate para causar dano instantâneo ao inimigo.
 *
 * Esta classe estende {@link Consumivel} e implementa
 * o comportamento específico de ataque imediato.
 */
public class ConsumivelCombate extends Consumivel {

    /**
     * Valor de dano instantâneo causado pelo consumível.
     */
    protected int ataqueInstantaneo;

    /**
     * Cria um novo consumível de combate.
     *
     * @param nome nome do consumível
     * @param preco preço do consumível
     * @param ataqueInstantaneo valor de dano instantâneo
     */
    public ConsumivelCombate(String nome, int preco, int ataqueInstantaneo) {
        super(nome, preco);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    /**
     * Retorna o valor de dano instantâneo do consumível.
     *
     * @return dano instantâneo
     */
    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }

    /**
     * Utiliza o consumível durante o combate.
     *
     * Este método representa apenas a ação narrativa,
     * sendo o dano aplicado externamente pela lógica de combate.
     *
     * @param heroi herói que utiliza o consumível
     */
    @Override
    public void usar(Heroi heroi) {
        System.out.println(heroi.getNome() + " usa " + nome +
                " causando " + ataqueInstantaneo + " de dano instantâneo!");
    }

    /**
     * Mostra no console os detalhes do consumível de combate,
     * incluindo preço, dano instantâneo e heróis permitidos.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Tipo: Consumível de Combate");
        System.out.println(getIcone() + " " + "Nome: " + nome + " | 💰 Preço: " + preco + " moedas de ouro | 💥 Ataque Instantâneo: " + ataqueInstantaneo);
        System.out.print("Heróis Permitidos: ");
        if (heroisPermitidos.isEmpty()) {
            System.out.println("Nenhum");
        } else {
            System.out.println(String.join(", ", heroisPermitidos));
        }

        System.out.println("=======================================");
    }
	
    /**
     * Retorna o ícone associado ao consumível de combate,
     * com base no seu nome.
     *
     * @return ícone representativo do consumível
     */
    public String getIcone() {

        String n = nome.toLowerCase();

        if (n.contains("Explosiva")) return "💣";
        if (n.contains("Icendiária")) return "🔥";
        if (n.contains("Gelo")) return "❄️";
        if (n.contains("Venen")) return "☠️";
        if (n.contains("Negra")) return "⚫";
        if (n.contains("Lunar")) return "🌙";
        if (n.contains("Adaga") || n.contains("Faca")) return "🗡️";
        if (n.contains("Igni") || n.contains("Aard")) return "✨";

        return "🧨"; // ícone padrão
    }

}
