package Entidades;
/**
 * Representa uma entidade não jogável (NPC) do jogo.
 *
 * Um NPC é um inimigo ou personagem controlado pelo sistema,
 * que pode entrar em combate com o herói e conceder recompensas
 * em ouro quando derrotado.
 *
 * Esta classe estende {@link Entidade}, adicionando a gestão
 * de ouro associada ao NPC.
 */
public class NPC extends Entidade {
    /**
     * Quantidade de ouro que o NPC possui.
     */
    protected int ouro;
    /**
     * Cria um NPC com os atributos base definidos.
     *
     * @param nome nome do NPC
     * @param maxHp valor máximo de vida
     * @param forca valor base de força
     * @param ouro quantidade de ouro inicial
     */
    public NPC(String nome, int maxHp, int forca, int ouro) {
        super(nome, maxHp, forca);
        this.ouro = ouro;
    }
    /**
     * Retorna a quantidade de ouro do NPC.
     *
     * @return ouro do NPC
     */
    public int getOuro() {
        return ouro;
    }
    /**
     * Adiciona ouro ao NPC.
     *
     * @param quantidade valor de ouro a adicionar
     */
    public void ganharOuro(int quantidade) {
        this.ouro += quantidade;
    }
    /**
     * Remove ouro do NPC.
     *
     * O valor de ouro nunca pode ser inferior a zero.
     *
     * @param quantidade valor de ouro a remover
     */
    public void perderOuro(int quantidade) {
        this.ouro -= quantidade;
        if (ouro < 0) {
            ouro = 0; // evita valores negativos
        }
    }
    /**
     * Mostra no console os detalhes completos do NPC,
     * incluindo atributos de combate e ouro disponível.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("=== Detalhes do NPC ===");
        System.out.println("Nome: " + nome);
        System.out.println("Vida Máxima: " + maxHp);
        System.out.println("Vida Atual: " + hp);
        System.out.println("Força: " + forca);
        System.out.println("Ouro: " + ouro);
        System.out.println("===================\n");
    }
}
