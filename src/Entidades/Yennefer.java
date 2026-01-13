package Entidades;

import Itens.ArmaPrincipal;
/**
 * Representa Yennefer, uma heroína especializada em magia do Caos.
 *
 * Yennefer possui uma habilidade especial que aumenta temporariamente
 * a sua força durante o combate, à custa de sofrer dano residual
 * após o término do confronto.
 *
 * Esta classe estende {@link Heroi} e sobrescreve o método de ataque
 * para implementar essa mecânica de risco e recompensa.
 */
public class Yennefer extends Heroi {
    /**
     * Cria uma instância da heroína Yennefer.
     *
     * @param nome nome da heroína
     * @param vidaMax valor máximo de vida
     * @param forca valor base de força
     * @param nivel nível inicial
     * @param ouro quantidade inicial de ouro
     * @param arma arma principal equipada
     */
    public Yennefer(String nome, int vidaMax, int forca, int nivel, int ouro, ArmaPrincipal arma) {
        super(nome, vidaMax, forca, nivel, ouro, arma);
    }

       /**
     * Executa o ataque especial de Yennefer.
     *
     * Durante o combate, Yennefer aumenta temporariamente a sua força
     * através da magia do Caos, mas sofre dano residual após o combate
     * como penalidade.
     *
     * @param npc NPC envolvido no combate
     * @return a entidade vencedora do combate
     */
    @Override
    public Entidade atacar(NPC npc) {

        System.out.println("\n🖤 Yennefer manipula magia do Caos!");

        this.forca += 1;

        Entidade vencedor = super.atacar(npc);

        // Penalidade temática
        System.out.println("💥 A energia do Caos causa dor residual!");
        this.hp -= 1;

        this.forca -= 1;

        return vencedor;
    }
}
