package Entidades;

import Itens.ArmaPrincipal;
/**
 * Representa Geralt, um herói especializado em sinais e alquimia.
 *
 * Geralt possui uma habilidade passiva que aumenta temporariamente
 * a sua força durante o combate, refletindo o uso de sinais mágicos
 * e preparações alquímicas.
 *
 * Esta classe estende {@link Heroi} e sobrescreve o método de ataque
 * para aplicar o bónus temporário de força.
 */

public class Geralt extends Heroi {
	    /**
     * Cria uma instância do herói Geralt.
     *
     * @param nome nome do herói
     * @param vidaMax valor máximo de vida
     * @param forca valor base de força
     * @param nivel nível inicial do herói
     * @param ouro quantidade inicial de ouro
     * @param arma arma principal equipada
     */

    public Geralt(String nome, int vidaMax, int forca, int nivel, int ouro, ArmaPrincipal arma) {
        super(nome, vidaMax, forca, nivel, ouro, arma);
    }
    /**
     * Executa o ataque especial de Geralt.
     *
     * Durante o combate, Geralt recebe um aumento temporário
     * de força, que é aplicado antes do combate e revertido
     * após o seu término.
     *
     * @param npc NPC envolvido no combate
     * @return a entidade vencedora do combate
     */
    @Override
    public Entidade atacar(NPC npc) {

        System.out.println("\n✨ Geralt reforça os seus Sinais!");

        // Aumenta força temporariamente
        this.forca += 2;

        // Usa o combate normal da superclasse
        Entidade vencedor = super.atacar(npc);

        // Reverte o efeito
        this.forca -= 2;

        return vencedor;
    }

}
