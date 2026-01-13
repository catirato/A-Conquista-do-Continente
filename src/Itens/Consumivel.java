package Itens;

import Entidades.Heroi;

import java.util.ArrayList;
/**
 * Classe abstrata que representa um consumível do jogo.
 *
 * Um consumível é um item que pode ser utilizado por um herói
 * para produzir um efeito específico, como causar dano
 * ou restaurar atributos.
 *
 * Esta classe estende {@link ItemHeroi} e define o comportamento
 * comum a todos os consumíveis.
 */
public abstract class Consumivel extends ItemHeroi {
    /**
     * Cria um novo consumível.
     *
     * @param nome nome do consumível
     * @param preco preço do consumível
     */
    public Consumivel(String nome, int preco) {
        super(nome, preco);
    }
    /**
     * Aplica o efeito do consumível ao herói.
     *
     * O efeito concreto é definido pelas subclasses.
     *
     * @param heroi herói que utiliza o consumível
     */
    public abstract void usar(Heroi heroi);

    /**
     * Mostra no console os detalhes genéricos do consumível,
     * incluindo nome, preço e heróis permitidos.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Tipo: Consumível");
        System.out.println("Nome: " + nome + " | Preço: " + preco + " moedas de ouro");
        System.out.print("Herois Permitidos: ");
        if (heroisPermitidos.isEmpty()) {
            System.out.println("Nenhum");
        } else {
            System.out.println(String.join(", ", heroisPermitidos));
        }

        System.out.println("====================================================");
    }
}


