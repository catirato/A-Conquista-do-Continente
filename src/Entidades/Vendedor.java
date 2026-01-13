package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.ItemHeroi;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa um vendedor do jogo.
 *
 * O vendedor mantém um stock de itens e permite que o herói
 * visualize itens disponíveis e compre aqueles que pode usar,
 * desde que tenha ouro suficiente.
 */

public class Vendedor {
	
	    /**
     * Lista de itens disponíveis para venda.
     */

    private ArrayList<ItemHeroi> loja;

    public Vendedor() {
        this.loja = new ArrayList<>();
    }

      /**
     * Adiciona um item ao stock da loja.
     *
     * @param item item a adicionar
     */
    public void adicionarItem(ItemHeroi item) {
        loja.add(item);
    }

        /**
     * Mostra até 10 itens aleatórios disponíveis na loja.
     *
     * Os itens são apresentados ao jogador, mas o método devolve
     * os índices reais desses itens na lista interna da loja,
     * permitindo que a compra seja feita corretamente.
     *
     * @return lista de índices reais dos itens apresentados
     */
    public ArrayList<Integer> imprimirLoja() {

        ArrayList<Integer> indicesReais = new ArrayList<>();

        if (loja.isEmpty()) {
            System.out.println("A loja não tem itens disponíveis!");
            return indicesReais;
        }

        // Criar lista de índices reais
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < loja.size(); i++) {
            indices.add(i);
        }

        // Baralhar índices
        Collections.shuffle(indices);

        int quantidade = Math.min(10, indices.size());

        for (int i = 0; i < quantidade; i++) {

            int indiceReal = indices.get(i);
            indicesReais.add(indiceReal);

            System.out.println("Item #" + (i + 1));
            loja.get(indiceReal).mostrarDetalhes();
            System.out.println();
        }

        System.out.println();

        return indicesReais;  // devolvemos a lista dos índices reais mostrados
    }


       /**
     * Vende um item ao herói, caso este possa utilizá-lo
     * e tenha ouro suficiente.
     *
     * Dependendo do tipo do item, este é equipado como arma
     * principal ou adicionado ao inventário.
     *
     * @param heroi herói que pretende comprar o item
     * @param indice índice do item na loja
     */
    public void vender(Heroi heroi, int indice) {

        if (indice < 0 || indice >= loja.size()) {
            System.out.println("Seleção de item inválida.");
            return;
        }

        ItemHeroi item = loja.get(indice);

        // Verifica se o herói pode usar este item
        String tipoHeroi = heroi.getClass().getSimpleName();
        if (!item.podeUsar(tipoHeroi)) {
            System.out.println("Não podes usar este item! (" + tipoHeroi + " não tem permissão)");
            return;
        }

        // Verifica se o herói tem ouro suficiente
        if (heroi.getOuro() < item.getPreco()) {
            System.out.println("Não tens ouro suficiente para comprar este item!");
            return;
        }

        // Compra possível → processar compra
        System.out.println(heroi.getNome() + " comprou " + item.getNome() + " por " + item.getPreco() + " moedas de ouro.");

        // Se for arma principal → substituir arma atual
        if (item instanceof ArmaPrincipal) {

            heroi.armaPrincipal = (ArmaPrincipal) item;
            System.out.println("A tua arma principal foi substituída!");

        } else {

            // Consumível → adicionar ao inventário
            heroi.getInventario().add((Consumivel) item);
            System.out.println("O item foi adicionado ao teu inventário.");
        }

        // Subtrair ouro
        heroi.ganharOuro(-item.getPreco());

        // Remover item da loja
        loja.remove(indice);

        System.out.println("Compra concluída!\n");
    }
	
	    /**
     * Retorna a lista de itens atualmente disponíveis na loja.
     *
     * @return lista de itens da loja
     */
    public ArrayList<ItemHeroi> getLoja() {
        return loja;
    }
}
