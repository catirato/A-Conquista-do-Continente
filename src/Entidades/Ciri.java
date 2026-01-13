package Entidades;

import Itens.ArmaPrincipal;
/**
 * Representa Ciri, uma heroína com habilidades ligadas
 * ao controlo do espaço-tempo.
 *
 * Ciri possui uma habilidade especial que lhe permite
 * atacar o inimigo antes do início do combate normal,
 * causando dano rápido proporcional à sua força.
 *
 * Esta classe estende {@link Heroi} e sobrescreve o método
 * de ataque para implementar essa mecânica especial.
 */
public class Ciri extends Heroi {
    /**
     * Cria uma instância da heroína Ciri.
     *
     * @param nome nome da heroína
     * @param vidaMax valor máximo de vida
     * @param forca valor base de força
     * @param nivel nível inicial
     * @param ouro quantidade inicial de ouro
     * @param arma arma principal equipada
     */
    public Ciri(String nome, int vidaMax, int forca, int nivel, int ouro, ArmaPrincipal arma) {
        super(nome, vidaMax, forca, nivel, ouro, arma);
    }

      /**
     * Executa o ataque especial de Ciri.
     *
     * Antes do combate normal, Ciri realiza um ataque rápido,
     * causando dano proporcional à sua força.
     *
     * Caso o NPC seja derrotado neste ataque inicial,
     * o combate termina imediatamente.
     *
     * @param npc NPC envolvido no combate
     * @return a entidade vencedora do combate
     */
    @Override
    public Entidade atacar(NPC npc) {

        System.out.println("\n⚡ Ciri atravessa o espaço-tempo para atacar primeiro!");

        // Ataque rápido proporcional à força
        int danoExtra = this.forca / 2;
        npc.setHp(npc.getHp() - danoExtra);

        System.out.println("⚡ Ataque Rápido: " + danoExtra + " de dano!");

        // Se matar o inimigo antes do combate base
        if (npc.getHp() <= 0) {
            System.out.println("✔ " + npc.getNome() + " foi eliminado num instante!");
            recompensarVitoria(npc);
            return this;
        }

        // Depois usa o combate normal
        return super.atacar(npc);
    }
}
