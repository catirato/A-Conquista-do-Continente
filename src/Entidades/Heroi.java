package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.ConsumivelCombate;
import Itens.Pocao;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe abstrata que representa um herói do jogo.
 *
 * Um herói é uma entidade controlada pelo jogador, possuindo
 * atributos adicionais como nível, ouro, arma principal
 * e um inventário de consumíveis.
 *
 * Esta classe implementa a lógica base de combate e gestão
 * de inventário, podendo ser especializada por subclasses
 * através da sobreposição de métodos.
 */
public abstract class Heroi extends Entidade {

    /** Nível atual do herói */
    protected int nivel;

    /** Quantidade de ouro do herói */
    protected int ouro;

    /** Arma principal atualmente equipada */
    protected ArmaPrincipal armaPrincipal;

    /** Inventário de consumíveis do herói */
    protected ArrayList<Consumivel> inventario;


    /**
     * Cria um herói com os atributos iniciais definidos.
     *
     * @param nome nome do herói
     * @param maxHp valor máximo de vida
     * @param forca valor base de força
     * @param nivel nível inicial
     * @param ouro quantidade inicial de ouro
     * @param armaPrincipal arma principal equipada
     */
    public Heroi(String nome, int maxHp, int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, maxHp, forca);
        this.nivel = nivel;
        this.ouro = ouro;
        this.armaPrincipal = armaPrincipal;
        this.inventario = new ArrayList<Consumivel>();
    }

    public int getNivel() { return nivel; }
    public int getOuro() { return ouro; }
    public ArmaPrincipal getArmaPrincipal() { return armaPrincipal; }
    public ArrayList<Consumivel> getInventario() { return inventario; }

    /**
     * Adiciona ou remove ouro ao herói.
     *
     * @param quantidade valor de ouro a adicionar (ou negativo para subtrair)
     */
    public void ganharOuro(int quantidade) { ouro += quantidade; }

    /**
     * Adiciona um consumível ao inventário do herói.
     *
     * @param c consumível a adicionar
     */
    public void adicionarAoInventario(Consumivel c) {
        inventario.add(c);
    }


       /**
     * Indica se o primeiro ataque do NPC deve ser ignorado.
     *
     * Utilizado por subclasses com mecânicas especiais,
     * como Cahir, que altera a ordem do combate.
     */
    protected boolean ignorarPrimeiroAtaqueNPC = false;
      
	  /**
     * Executa o combate entre o herói e um NPC.
     *
     * Implementa a lógica base de combate:
     * <ul>
     *   <li>Escolha da ação pelo jogador</li>
     *   <li>Ataque normal, especial ou uso de consumível</li>
     *   <li>Turno do NPC, caso sobreviva</li>
     * </ul>
     *
     * Subclasses podem alterar este comportamento através
     * da sobreposição deste método.
     *
     * @param npc NPC envolvido no combate
     * @return a entidade vencedora do combate
     */

    public Entidade atacar(NPC npc) {

        Scanner sc = new Scanner(System.in);
        boolean ataqueEspecialDisponivel = true;

        System.out.println("\n" + nome + " entra em combate com " + npc.getNome() + "!");



        // =====================
        // COMBATE REAL (SEM FASE DE POÇÕES ANTES)
        // =====================
        while (this.hp > 0 && npc.getHp() > 0) {

            System.out.println("\n===== Turno de " + nome + " =====");
            System.out.println("1 - Ataque Normal");
            System.out.println("2 - Ataque Especial" + (ataqueEspecialDisponivel ? "" : " (INDISPONÍVEL)"));
            System.out.println("3 - Consumível de Combate");
            System.out.println("4 - Cancelar Turno");
            System.out.println("Escolhe a opção: ");

            int escolha = sc.nextInt();
            boolean ataqueRealizado = false;

            switch (escolha) {

                case 1: { // ATAQUE NORMAL
                    int dano = this.forca + armaPrincipal.getAtaque();
                    npc.setHp(npc.getHp() - dano);
                    System.out.println("➡ Ataque Normal: " + dano + " de dano!");
                    ataqueRealizado = true;
                    break;
                }

                case 2: { // ATAQUE ESPECIAL
                    if (!ataqueEspecialDisponivel) {
                        System.out.println("❌ Ataque Especial já foi usado!");
                        break;
                    }
                    int dano = this.forca + armaPrincipal.getAtaqueEspecial();
                    npc.setHp(npc.getHp() - dano);
                    System.out.println("✨ Ataque Especial: " + dano + " de dano!");
                    ataqueEspecialDisponivel = false;
                    ataqueRealizado = true;
                    break;
                }

                case 3: { // CONSUMÍVEL DE COMBATE
                    ataqueRealizado = usarConsumivelCombate(npc);
                    break;
                }

                case 4:
                    System.out.println("Turno cancelado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

            // Se não houve ataque real, volta ao início do turno
            if (!ataqueRealizado) continue;

            // Se o NPC morreu após o ataque do herói
            if (npc.getHp() <= 0) {
                System.out.println("✔ " + npc.getNome() + " foi derrotado!");
                recompensarVitoria(npc);
                return this;
            }

            // ============================
            // TURNO DO NPC (pode ser ignorado pelo Cahir no primeiro turno)
            // ============================
            if (ignorarPrimeiroAtaqueNPC) {
                ignorarPrimeiroAtaqueNPC = false; // só ignora uma vez
            } else {
                System.out.println("\n===== Turno de " + npc.getNome() + " =====");
                this.hp -= npc.getForca();
                System.out.println("⚔ " + npc.getNome() + " causa " + npc.getForca() + " de dano!");
            }

            // Se o herói morreu após o ataque do NPC
            if (this.hp <= 0) {
                System.out.println("✘ " + nome + " caiu em combate...");
                return npc;
            }
        }

        return null;
    }


    /**
     * Permite ao herói utilizar um consumível de combate
     * durante o seu turno, causando dano instantâneo ao NPC.
     *
     * @param npc NPC que recebe o efeito do consumível
     * @return true se o consumível foi usado com sucesso
     */
    private boolean usarConsumivelCombate(NPC npc) {

        Scanner sc = new Scanner(System.in);
        ArrayList<ConsumivelCombate> consumiveis = new ArrayList<>();

        // Filtrar consumíveis de combate
        for (Consumivel c : inventario) {
            if (c instanceof ConsumivelCombate) {
                consumiveis.add((ConsumivelCombate) c);
            }
        }

        if (consumiveis.isEmpty()) {
            System.out.println("❌ Não tens consumíveis de combate!");
            return false;
        }

        System.out.println("\n=== Consumíveis de Combate Disponíveis ===");
        for (int i = 0; i < consumiveis.size(); i++) {
            ConsumivelCombate cc = consumiveis.get(i);
            System.out.println((i + 1) + " - " + cc.getIcone() + " " + cc.getNome() +
                    " | 💥 Dano: " + cc.getAtaqueInstantaneo());
        }
        System.out.println("0 - Cancelar");

        System.out.print("Escolhe um consumível: ");
        int escolha = sc.nextInt();

        if (escolha == 0) {
            System.out.println("❌ Ataque com consumível cancelado.");
            return false;
        }

        escolha--;

        if (escolha < 0 || escolha >= consumiveis.size()) {
            System.out.println("❌ Escolha inválida.");
            return false;
        }

        ConsumivelCombate usado = consumiveis.get(escolha);

        System.out.println("\nConfirmar uso de: " + usado.getNome() + "? (1 = Sim, 2 = Não)");
        int confirmar = sc.nextInt();

        if (confirmar != 1) {
            System.out.println("❌ Cancelado.");
            return false;
        }

        int dano = usado.getAtaqueInstantaneo();
        npc.setHp(npc.getHp() - dano);

        System.out.println("\n💥 " + usado.getNome() + " usado!");
        System.out.println("➡ Causou " + dano + " de dano instantâneo!");

        inventario.remove(usado);
        return true;
    }

    /**
     * Aplica as recompensas da vitória após derrotar um NPC.
     *
     * O herói sobe de nível, melhora os seus atributos
     * e recebe ouro.
     *
     * @param npc NPC derrotado
     */
    protected void recompensarVitoria(NPC npc) {
        System.out.println("\n===== Recompensas da Vitória =====");

        // Subir nível
        this.nivel++;
        System.out.println("🔼 " + nome + " subiu para o nível " + nivel + "!");

        // Aumentar stats
        this.maxHp += 10;
        this.hp += 10;
        this.forca += 1;

        System.out.println("❤️ +10 Vida Máxima (Total: " + maxHp + ")");
        System.out.println("💪 +1 Força (Total: " + forca + ")");

        // Ganhar ouro
        int ouroGanho = npc.getOuro();
        this.ouro += ouroGanho;

        System.out.println("💰 " + nome + " ganhou " + ouroGanho + " moedas de ouro!");
        System.out.println("====================================\n");
    }

    /**
     * Mostra no console os detalhes completos do herói,
     * incluindo atributos, arma equipada e inventário.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("\n================= DETALHES DO TEU HERÓI =================");

        System.out.println("👤 Nome: " + nome);
        System.out.println("⭐ Nível: " + nivel);
        System.out.println("💰 Ouro: " + ouro);

        // Arma Principal com ícone
        if (armaPrincipal != null) {
            System.out.println("⚔️ Arma Principal: "
                    + armaPrincipal.getIcone() + " "
                    + armaPrincipal.getNome()
                    + " | Dano: " + armaPrincipal.getAtaque()
                    + " | Especial: " + armaPrincipal.getAtaqueEspecial());
        } else {
            System.out.println("⚔️ Arma Principal: Nenhuma");
        }

        System.out.println("\n❤️ Vida Máxima: " + maxHp);
        System.out.println("❤️ Vida Atual: " + hp);
        System.out.println("💪 Força: " + forca);

        // ============================
        // POÇÕES NO INVENTÁRIO
        // ============================
        ArrayList<Pocao> pocoes = new ArrayList<>();
        ArrayList<ConsumivelCombate> consumiveis = new ArrayList<>();

        for (Consumivel c : inventario) {
            if (c instanceof Pocao) pocoes.add((Pocao) c);
            else if (c instanceof ConsumivelCombate) consumiveis.add((ConsumivelCombate) c);
        }

        System.out.println("\n🧪 Poções no Inventário (" + pocoes.size() + "):");
        if (pocoes.isEmpty()) {
            System.out.println("   Nenhuma poção disponível.");
        } else {
            for (Pocao p : pocoes) {
                System.out.println("   • " + p.getNome()
                        + " | ❤️ Cura: " + p.getVidaCurar()
                        + " | 💪 Força: +" + p.getAumentoForca());
            }
        }

        // ============================
        // CONSUMÍVEIS DE COMBATE
        // ============================
        System.out.println("\n💥 Consumíveis de Combate (" + consumiveis.size() + "):");
        if (consumiveis.isEmpty()) {
            System.out.println("   Nenhum consumível de combate.");
        } else {
            for (ConsumivelCombate cc : consumiveis) {
                System.out.println("   • " + cc.getNome()
                        + " | 💥 Dano Instantâneo: " + cc.getAtaqueInstantaneo());
            }
        }

        System.out.println("==========================================================\n");
    }

    /**
     * Permite ao herói utilizar uma poção do inventário.
     *
     * A poção escolhida é aplicada ao herói e removida
     * do inventário após o uso.
     */
    public void usarPocao() {

        Scanner sc = new Scanner(System.in);
        ArrayList<Pocao> pocoes = new ArrayList<>();

        for (Consumivel c : inventario) {
            if (c instanceof Pocao) {
                pocoes.add((Pocao) c);
            }
        }

        if (pocoes.isEmpty()) {
            System.out.println("❌ Não tens poções no inventário!");
            return;
        }

        System.out.println("\n=== Poções Disponíveis ===");
        for (int i = 0; i < pocoes.size(); i++) {
            Pocao p = pocoes.get(i);
            System.out.println((i + 1) + ". " + p.getIcone() + " " + p.getNome() +
                    " | ❤️ " + p.getVidaCurar() +
                    " | 💪 +" + p.getAumentoForca());
        }
        System.out.println("0 - Cancelar");

        System.out.print("Escolhe a poção a usar: ");
        int escolha = sc.nextInt() - 1;

        if (escolha == -1) {
            System.out.println("❌ Uso de poção cancelado.");
            return;
        }

        if (escolha < 0 || escolha >= pocoes.size()) {
            System.out.println("❌ Escolha inválida.");
            return;
        }

        Pocao usada = pocoes.get(escolha);

        System.out.println("\nConfirmar uso da poção \"" + usada.getNome() + "\"? (1 = Sim, 2 = Não)");
        System.out.println("Escolhe uma opção:");
        int confirmar = sc.nextInt();

        if (confirmar != 1) {
            System.out.println("❌ Cancelado.");
            return;
        }

        usada.usar(this);
        inventario.remove(usada);

        System.out.println("🧪 Poção usada com sucesso!");
    }

    /**
     * Mostra no console todas as poções atualmente
     * disponíveis no inventário do herói.
     */
public void imprimirPocoes() {

    ArrayList<Pocao> pocoes = new ArrayList<>();

    for (Consumivel c : inventario) {
        if (c instanceof Pocao) {
            pocoes.add((Pocao) c);
        }
    }

    if (pocoes.isEmpty()) {
        System.out.println("\nNão tens poções no inventário!");
        return;
    }

    System.out.println("\n=== Poções no Inventário ===");
    for (int i = 0; i < pocoes.size(); i++) {
        Pocao p = pocoes.get(i);
        System.out.println((i + 1) + ". " + p.getIcone() + " " + p.getNome() +
                " | ❤️ " + p.getVidaCurar() +
                " | 💪 +" + p.getAumentoForca());
    }
}


}
