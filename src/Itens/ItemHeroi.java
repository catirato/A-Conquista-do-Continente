package Itens;

import java.util.ArrayList;
/**
 * Classe abstrata que representa um item utilizável por um herói.
 *
 * Um item pode ser comprado numa loja e utilizado por determinados
 * tipos de heróis, de acordo com as permissões definidas.
 *
 * Esta classe serve como base para armas e consumíveis.
 */
public abstract class ItemHeroi {

    /** Nome do item */
    protected String nome;

    /** Preço do item em moedas de ouro */
    protected int preco;

    /** Lista de tipos de heróis autorizados a usar o item */
    protected ArrayList<String> heroisPermitidos;

    /**
     * Cria um novo item para heróis.
     *
     * @param nome nome do item
     * @param preco preço do item
     */
    public ItemHeroi(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = new ArrayList<String>();
    }

    /**
     * Adiciona um tipo de herói autorizado a utilizar este item.
     *
     * @param tipoHeroi tipo de herói permitido
     */
	 public void adicionarHeroiPermitido(String tipoHeroi) {
        heroisPermitidos.add(tipoHeroi);
    }

       /**
     * Verifica se um determinado tipo de herói pode utilizar o item.
     *
     * @param tipoHeroi tipo de herói a verificar
     * @return true se o herói puder utilizar o item
     */
    public boolean podeUsar(String tipoHeroi) {
        return heroisPermitidos.contains(tipoHeroi);
    }

        /**
     * Mostra no console os detalhes genéricos do item,
     * incluindo nome, preço e heróis permitidos.
     */
    public void mostrarDetalhes() {
        System.out.println("=== Detalhes do Item ===");
        System.out.println("Nome: " + nome + " | Preço: " + preco + " moedas de ouro");
        System.out.print("Heróis Permitidos: ");
        if (heroisPermitidos.isEmpty()) {
            System.out.println("Nenhum");
        } else {
            System.out.println(String.join(", ", heroisPermitidos));
        }

        System.out.println("========================\n");
    }

      /**
     * Retorna o nome do item.
     *
     * @return nome do item
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o preço do item.
     *
     * @return preço do item
     */
    public int getPreco() {
        return preco;
    }

    /**
     * Retorna a lista de tipos de heróis autorizados a usar o item.
     *
     * @return lista de heróis permitidos
     */
    public ArrayList<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }
}
