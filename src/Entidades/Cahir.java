package Entidades;

import Itens.ArmaPrincipal;

/**
 * Representa Cahir, um herói especial do jogo.
 *
 * Cahir é um comandante Nilfgaardiano que possui uma habilidade única:
 * toma sempre a iniciativa no combate, permitindo um ataque inicial
 * do NPC antes do fluxo normal da batalha.
 *
 * Esta classe estende {@link Heroi} e sobrescreve o método de ataque
 * para implementar a sua mecânica especial.
 *
 */
public class Cahir extends Heroi {
   /**
     * Cria uma instância do herói Cahir.
     *
     * @param nome nome do herói
     * @param vidaMax valor máximo de vida
     * @param forca valor base de força
     * @param nivel nível atual do herói
     * @param ouro quantidade de ouro inicial
     * @param arma arma principal equipada
     */
    public Cahir(String nome, int vidaMax, int forca, int nivel, int ouro, ArmaPrincipal arma) {
        super(nome, vidaMax, forca, nivel, ouro, arma);
    }

    /**
     * Executa o ataque especial de Cahir.
     *
     * Como comandante Nilfgaardiano, Cahir permite que o NPC ataque
     * imediatamente antes do combate normal.
     *
     * Para evitar duplicação de ataques, o primeiro ataque do NPC
     * é ignorado na lógica da superclasse.
     *
     * @param npc entidade não jogável envolvida no combate
     * @return a entidade vencedora do combate
     */
    @Override
    public Entidade atacar(NPC npc) {

        System.out.println("\n🛡 Cahir toma a iniciativa militar!");

        // NPC ataca imediatamente
        System.out.println("\n===== Turno de " + npc.getNome() + " (Ataque Inicial) =====");
        this.hp -= npc.getForca();
        System.out.println("⚔ " + npc.getNome() + " causa " + npc.getForca() + " de dano!");

        // Se morrer antes do combate
        if (this.hp <= 0) {
            System.out.println("✘ Cahir caiu antes de contra-atacar…");
            return npc;
        }

        // Diz ao sistema base para ignorar o primeiro ataque do NPC lá dentro
        this.ignorarPrimeiroAtaqueNPC = true;

        // Agora segue o combate normal
        return super.atacar(npc);
    }

}
