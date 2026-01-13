
package Jogo;

import Entidades.*;
import Itens.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Classe responsável pela lógica principal do jogo.
 *
 * Gere a criação do herói, a progressão da história,
 * os combates, as escolhas do jogador, a loja,
 * os eventos aleatórios e os desfechos de vitória ou derrota.
 *
 * Esta classe funciona como o núcleo da experiência de jogo,
 * sendo utilizada pelo menu principal para iniciar e controlar
 * a aventura.
 */
public class Jogo {

      /**
     * Scanner utilizado para leitura de input do jogador
     * durante a execução do jogo.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Gerador de números aleatórios utilizado
     * para eventos imprevisíveis e decisões de sorte.
     */
    private Random random = new Random();


    // ============================================================
    // CRIAÇÃO DE PERSONAGEM
    // ============================================================
       /**
     * Cria um novo herói com base nas escolhas do jogador.
     *
     * O jogador escolhe o tipo de herói, a dificuldade
     * e distribui pontos entre vida e força.
     * O herói é criado com uma arma inicial e ouro base.
     *
     * @return herói criado pelo jogador
     */
   public Heroi criarPersonagem() {

        // -------------------------
        // Escolher Tipo de Herói
        // -------------------------
        String tipo = escolherTipoHeroi();

        // -------------------------
        // Escolher Dificuldade
        // -------------------------
        int pontosCriacao;
        int ouroInicial;

        int dificuldade = escolherDificuldade();

        if (dificuldade == 1) {
            pontosCriacao = 300;
            ouroInicial = 20;
        } else {
            pontosCriacao = 220;
            ouroInicial = 15;
        }

        // -------------------------
        // Distribuir Pontos
        // -------------------------
        int vida = 0, forca = 0;
        boolean distribuidoCorretamente = false;

        while (!distribuidoCorretamente) {

            System.out.println("\n=== DISTRIBUIÇÃO DE PONTOS ===");
            System.out.println("Pontos totais disponíveis: " + pontosCriacao);
            System.out.println("Cada ponto de VIDA custa 1 ❤️");
            System.out.println("Cada ponto de FORÇA custa 5 💪");

            System.out.print("Vida: ");
            vida = sc.nextInt();

            System.out.print("Força: ");
            forca = sc.nextInt();

            int custoTotal = vida + (forca * 5);

            if (custoTotal == pontosCriacao) {
                System.out.println("\n✔ Distribuição correta! O teu Heroi foi criado com sucesso!");
                System.out.println();
                distribuidoCorretamente = true;
            } else {
                System.out.println("\n❌ Cálculo incorreto! Gasta exatamente os pontos disponíveis.");
                System.out.println();
            }
        }

        ArmaPrincipal armaInicialGeralt = new ArmaPrincipal("Espada Velha", 0, 5, 8);
        ArmaPrincipal armaInicialYennefer = new ArmaPrincipal("Espada Mágica", 0, 5, 8);
        ArmaPrincipal armaInicialCiri = new ArmaPrincipal("Espada Élfica", 0, 5, 8);
        ArmaPrincipal armaInicialCahir = new ArmaPrincipal("Espada Nilfgaardiana", 0, 5, 8);


        // -------------------------
        // Instanciar o Herói correto
        // -------------------------
        Heroi heroi = null;

        switch (tipo) {
            case "1" -> heroi = new Geralt("Geralt", vida, forca, 6, ouroInicial, armaInicialGeralt);
            case "2" -> heroi = new Yennefer("Yennefer", vida, forca, 4, ouroInicial, armaInicialYennefer);
            case "3" -> heroi = new Ciri("Ciri", vida, forca, 5, ouroInicial, armaInicialCiri);
            case "4" -> heroi = new Cahir("Cahir", vida, forca, 3, ouroInicial, armaInicialCahir);
        }

        //Mostras os detalhes do Heroi criado
        heroi.mostrarDetalhes();

        return heroi;
    }


    // ============================================================
    // TIPO DE HERÓI
    // ============================================================
        /**
     * Permite ao jogador escolher o tipo de herói.
     *
     * A escolha determina a classe concreta do herói
     * e as suas habilidades especiais.
     *
     * @return opção escolhida pelo jogador
     */
	private String escolherTipoHeroi() {

        while (true) {
            System.out.println("\n====== ESCOLHE O TEU HEROI ======");
            System.out.println("1 - Geralt (Feiticeiro)");
            System.out.println("2 - Yennefer (Maga)");
            System.out.println("3 - Ciri (Filha do Caos)");
            System.out.println("4 - Cahir (Cavaleiro Nilfgaardiano)");
            System.out.print("\nEscolhe uma opção: ");

            String esc = sc.nextLine().trim();

            if (esc.equals("1") || esc.equals("2") || esc.equals("3") || esc.equals("4"))
                return esc;

            System.out.println("❌ Escolha inválida!");
        }
    }

    // ============================================================
    // DIFICULDADE
    // ============================================================
        /**
     * Permite ao jogador escolher o nível de dificuldade.
     *
     * A dificuldade influencia os pontos disponíveis
     * para criação do herói e o ouro inicial.
     *
     * @return nível de dificuldade escolhido
     */
	private int escolherDificuldade() {

        while (true) {
            System.out.println("\n====== Escolhe o nível de dificuldade ======");
            System.out.println("1 - Fácil");
            System.out.println("2 - Difícil");
            System.out.print("\nEscolhe uma opção: ");

            int esc = sc.nextInt();

            if (esc == 1 || esc == 2) return esc;

            System.out.println("❌ Opção inválida!");
        }
    }

    // ============================================================
    //                     CONQUISTA DO CONTINENTE
    // Retorna: 1=jogar novamente com mesmo heroi, 2=criar novo, 3=sair
    // ============================================================
       /**
     * Executa a aventura principal do jogo.
     *
     * Controla a progressão entre salas, eventos narrativos,
     * escolhas do jogador, combates, lojas e o confronto final.
     *
     * Dependendo do resultado, devolve uma opção que indica
     * se o jogador deseja repetir, criar nova personagem
     * ou sair do jogo.
     *
     * @param heroi herói controlado pelo jogador
     * @return código de resultado:
     *         1 = jogar novamente com o mesmo herói
     *         2 = criar nova personagem
     *         3 = sair do jogo
     */
   public int conquistaDoContinente(Heroi heroi) {

        System.out.println("\n==========================================================================");
        System.out.println("       ⫷\uD83D\uDC3A⫸ C O N Q U I S T A  D O  C O N T I N E N T E ⫷\uD83D\uDC3A⫸");
        System.out.println("============================================================================");
        System.out.println(heroi.getNome() + ", o teu destino começa aqui!");
        System.out.println("As terras foram corrompidas por monstros e pelo terrível Imperador Emhyr.\n");
        System.out.println("Pela frente terás de enfrentar diversos inimigos e mas também vais encontrar");
        System.out.println("muitos artefactos misteriosos.");
        System.out.println();
        System.out.println("Felizmente, tens ao teu dispor 10 ítems diferentes para comprar que incluem Poções, ");
        System.out.println("Consumíveis e Armas");
        System.out.println("Escolhe bem!!");

        // ============================================================
        // VENDEDOR INICIAL
        // ============================================================
        Vendedor vendedor = new Vendedor();

        // ================================
        // ARMA PRINCIPAL (10 tipos)
        // ================================
        ArmaPrincipal espadaAco = new ArmaPrincipal("Espada de Aço", 3, 10, 18);
        espadaAco.adicionarHeroiPermitido("Geralt");
        espadaAco.adicionarHeroiPermitido("Ciri");
        espadaAco.adicionarHeroiPermitido("Cahir");

        ArmaPrincipal espadaPrata = new ArmaPrincipal("Espada de Prata", 3, 15, 25);
        espadaPrata.adicionarHeroiPermitido("Geralt");
        espadaPrata.adicionarHeroiPermitido("Ciri");

        ArmaPrincipal espadaWitcher = new ArmaPrincipal("Espada de Lobo Branco", 3, 20, 35);
        espadaWitcher.adicionarHeroiPermitido("Geralt");

        ArmaPrincipal machadoDeGuerra = new ArmaPrincipal("Machado de Guerra", 3, 18, 28);
        machadoDeGuerra.adicionarHeroiPermitido("Geralt");
        machadoDeGuerra.adicionarHeroiPermitido("Ciri");
        machadoDeGuerra.adicionarHeroiPermitido("Cahir");
        machadoDeGuerra.adicionarHeroiPermitido("Yennefer");

        ArmaPrincipal alabarda = new ArmaPrincipal("Alabarda Negra", 3, 22, 40);
        alabarda.adicionarHeroiPermitido("Geralt");
        alabarda.adicionarHeroiPermitido("Yennefer");
        alabarda.adicionarHeroiPermitido("Cahir");

        ArmaPrincipal espadaRúnica = new ArmaPrincipal("Espada Rúnica", 3, 25, 45);
        espadaRúnica.adicionarHeroiPermitido("Cahir");
        espadaRúnica.adicionarHeroiPermitido("Ciri");

        ArmaPrincipal espadaFlamejante = new ArmaPrincipal("Espada Flamejante", 3, 30, 50);
        espadaFlamejante.adicionarHeroiPermitido("Yennefer");
        espadaFlamejante.adicionarHeroiPermitido("Cahir");
        espadaFlamejante.adicionarHeroiPermitido("Ciri");

        ArmaPrincipal espadaVampirica = new ArmaPrincipal("Espada Vampírica", 3, 22, 55);
        espadaVampirica.adicionarHeroiPermitido("Yennefer");
        espadaVampirica.adicionarHeroiPermitido("Ciri");

        ArmaPrincipal espadaNilfgaard = new ArmaPrincipal("Espada Nilfgaardiana", 3, 18, 30);
        espadaNilfgaard.adicionarHeroiPermitido("Cahir");

        ArmaPrincipal katanaÉlfica = new ArmaPrincipal("Katana Élfica", 3, 18, 45);
        katanaÉlfica.adicionarHeroiPermitido("Ciri");
        katanaÉlfica.adicionarHeroiPermitido("Geralt");


        // ===============================================
        // ADICIONAR ARMAS PRINCIPAIS À LOJA DO VENDEDOR
        // ===============================================
        vendedor.adicionarItem(espadaAco);
        vendedor.adicionarItem(espadaPrata);
        vendedor.adicionarItem(espadaWitcher);
        vendedor.adicionarItem(machadoDeGuerra);
        vendedor.adicionarItem(alabarda);
        vendedor.adicionarItem(espadaRúnica);
        vendedor.adicionarItem(espadaFlamejante);
        vendedor.adicionarItem(espadaVampirica);
        vendedor.adicionarItem(espadaNilfgaard);
        vendedor.adicionarItem(katanaÉlfica);

        // ================================
        // POÇÕES (10 tipos)
        // ================================
        Pocao pocaoPequenaVida = new Pocao("Poção de Vida Pequena", 2, 20, 0);
        pocaoPequenaVida.adicionarHeroiPermitido("Geralt");
        pocaoPequenaVida.adicionarHeroiPermitido("Yennefer");
        pocaoPequenaVida.adicionarHeroiPermitido("Ciri");
        pocaoPequenaVida.adicionarHeroiPermitido("Cahir");

        Pocao pocaoMediaVida = new Pocao("Poção de Vida Média", 3, 40, 0);
        pocaoMediaVida.adicionarHeroiPermitido("Geralt");
        pocaoMediaVida.adicionarHeroiPermitido("Yennefer");
        pocaoMediaVida.adicionarHeroiPermitido("Ciri");
        pocaoMediaVida.adicionarHeroiPermitido("Cahir");

        Pocao pocaoGrandeVida = new Pocao("Poção de Vida Grande", 4, 70, 0);
        pocaoGrandeVida.adicionarHeroiPermitido("Geralt");
        pocaoGrandeVida.adicionarHeroiPermitido("Ciri");
        pocaoGrandeVida.adicionarHeroiPermitido("Yennefer");

        Pocao pocaoForcaPequena = new Pocao("Poção de Força Pequena", 3, 0, 2);
        pocaoForcaPequena.adicionarHeroiPermitido("Geralt");
        pocaoForcaPequena.adicionarHeroiPermitido("Ciri");
        pocaoForcaPequena.adicionarHeroiPermitido("Cahir");

        Pocao pocaoForcaGrande = new Pocao("Poção de Força Grande", 5, 0, 5);
        pocaoForcaGrande.adicionarHeroiPermitido("Geralt");
        pocaoForcaGrande.adicionarHeroiPermitido("Ciri");
        pocaoForcaGrande.adicionarHeroiPermitido("Yennefer");
        pocaoForcaGrande.adicionarHeroiPermitido("Cahir");

        Pocao pocaoMista = new Pocao("Poção Mista", 7, 40, 3);
        pocaoMista.adicionarHeroiPermitido("Geralt");
        pocaoMista.adicionarHeroiPermitido("Cahir");

        Pocao pocaoBruxaria = new Pocao("Poção de Bruxaria", 9, 50, 4);
        pocaoBruxaria.adicionarHeroiPermitido("Geralt");
        pocaoBruxaria.adicionarHeroiPermitido("Yennefer");

        Pocao pocaoSuperior = new Pocao("Poção Superior", 9, 80, 5);
        pocaoSuperior.adicionarHeroiPermitido("Geralt");
        pocaoSuperior.adicionarHeroiPermitido("Yennefer");

        Pocao pocaoElite = new Pocao("Poção Elite", 10, 120, 10);
        pocaoElite.adicionarHeroiPermitido("Geralt");
        pocaoElite.adicionarHeroiPermitido("Ciri");
        pocaoElite.adicionarHeroiPermitido("Yennefer");

        Pocao pocaoLendaria = new Pocao("Poção Lendária", 9, 150, 15);
        pocaoLendaria.adicionarHeroiPermitido("Geralt");


        // ====================================
        // ADICIONAR POÇÕES À LOJA DO VENDEDOR
        // ====================================
        vendedor.adicionarItem(pocaoPequenaVida);
        vendedor.adicionarItem(pocaoMediaVida);
        vendedor.adicionarItem(pocaoGrandeVida);
        vendedor.adicionarItem(pocaoForcaPequena);
        vendedor.adicionarItem(pocaoForcaGrande);
        vendedor.adicionarItem(pocaoMista);
        vendedor.adicionarItem(pocaoBruxaria);
        vendedor.adicionarItem(pocaoSuperior);
        vendedor.adicionarItem(pocaoElite);
        vendedor.adicionarItem(pocaoLendaria);


        // ================================
        // CONSUMÍVEIS DE COMBATE (10 tipos)
        // ================================
        ConsumivelCombate bombaExplosiva = new ConsumivelCombate("Bomba Explosiva", 3, 40);
        bombaExplosiva.adicionarHeroiPermitido("Geralt");
        bombaExplosiva.adicionarHeroiPermitido("Ciri");
        bombaExplosiva.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate bombaIncendiaria = new ConsumivelCombate("Bomba Incendiária", 4, 50);
        bombaIncendiaria.adicionarHeroiPermitido("Yennefer");
        bombaIncendiaria.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate bombaDeGelo = new ConsumivelCombate("Bomba de Gelo", 4, 35);
        bombaDeGelo.adicionarHeroiPermitido("Geralt");
        bombaDeGelo.adicionarHeroiPermitido("Ciri");
        bombaDeGelo.adicionarHeroiPermitido("Yennefer");
        bombaDeGelo.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate bombaVeneno = new ConsumivelCombate("Bomba de Veneno", 5, 45);
        bombaVeneno.adicionarHeroiPermitido("Geralt");
        bombaVeneno.adicionarHeroiPermitido("Ciri");
        bombaVeneno.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate adagaArremesso = new ConsumivelCombate("Adaga de Arremesso", 2, 20);
        adagaArremesso.adicionarHeroiPermitido("Geralt");
        adagaArremesso.adicionarHeroiPermitido("Ciri");
        adagaArremesso.adicionarHeroiPermitido("Yennefer");
        adagaArremesso.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate facaEnvenenada = new ConsumivelCombate("Faca Envenenada", 3, 25);
        facaEnvenenada.adicionarHeroiPermitido("Ciri");
        facaEnvenenada.adicionarHeroiPermitido("Yennefer");
        facaEnvenenada.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate bombaLunar = new ConsumivelCombate("Bomba Lunar", 7, 60);
        bombaLunar.adicionarHeroiPermitido("Geralt");
        bombaLunar.adicionarHeroiPermitido("Ciri");
        bombaLunar.adicionarHeroiPermitido("Yennefer");
        bombaLunar.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate sinalIgni = new ConsumivelCombate("Sinal de Igni", 6, 55);
        sinalIgni.adicionarHeroiPermitido("Geralt");
        sinalIgni.adicionarHeroiPermitido("Yennefer");
        sinalIgni.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate sinalAard = new ConsumivelCombate("Sinal de Aard", 4, 35);
        sinalAard.adicionarHeroiPermitido("Geralt");
        sinalAard.adicionarHeroiPermitido("Ciri");
        sinalAard.adicionarHeroiPermitido("Cahir");

        ConsumivelCombate bombaNegra = new ConsumivelCombate("Bomba Negra", 9, 80);
        bombaNegra.adicionarHeroiPermitido("Geralt");
        bombaNegra.adicionarHeroiPermitido("Ciri");
        bombaNegra.adicionarHeroiPermitido("Yennefer");

        // ==========================================
        // ADICIONAR CONSUMÍVEIS À LOJA DO VENDEDOR
        // ==========================================
        vendedor.adicionarItem(bombaExplosiva);
        vendedor.adicionarItem(bombaIncendiaria);
        vendedor.adicionarItem(bombaDeGelo);
        vendedor.adicionarItem(bombaVeneno);
        vendedor.adicionarItem(adagaArremesso);
        vendedor.adicionarItem(facaEnvenenada);
        vendedor.adicionarItem(bombaLunar);
        vendedor.adicionarItem(sinalIgni);
        vendedor.adicionarItem(sinalAard);
        vendedor.adicionarItem(bombaNegra);


        // ===== Loja do vendedor =====
        boolean comprar = true;

        while (comprar) {

            System.out.println("\n================= LOJA DO VENDEDOR =================");
            System.out.println("Ouro que tens disponível: " + heroi.getOuro() + " moedas de ouro");
            System.out.println();

            // GUARDAR quais itens foram mostrados
            ArrayList<Integer> indicesReais = vendedor.imprimirLoja();

            System.out.println("0 - Sair");
            System.out.print("Escolhe o número do item que queres comprar. Ou preferes sair (0)?: ");

            int esc = sc.nextInt();

            if (esc == 0) {
                comprar = false;
            } else if (esc >= 1 && esc <= indicesReais.size()) {

                int indiceReal = indicesReais.get(esc - 1);  // obter índice real

                vendedor.vender(heroi, indiceReal);          // comprar item correto
            } else {
                System.out.println("Opção inválida!");
            }
        }

        System.out.println("\n");
        System.out.println("\n Agora o teu heroi está completo! \uD83D\uDC47 Vê como ficou\n");
        heroi.mostrarDetalhes();

        // ============================================================
        // ESCOLHA INICIAL DE CAMINHO
        // ============================================================
        int salaAtual = 0;

        while (true) {
            System.out.println("Tu e os teus companheiros desta aventura chegaram");
            System.out.println("à primeira encruzilhada.");
            System.out.println("\nEscolhe para onde queres ir primeiro:");
            System.out.println("1 - Pântano dos Lamentos");
            System.out.println("2 - Floresta de Brokilon");
            System.out.print("Escolha uma opção: ");

            int esc = sc.nextInt();
            if (esc == 1) {
                salaAtual = 1;
                break;
            }
            if (esc == 2) {
                salaAtual = 2;
                break;
            }
            System.out.println("❌ Opção inválida!");
        }

        // ============================================================
        // LOOP PRINCIPAL DAS SALAS
        // ============================================================
        boolean aventura = true;

        while (aventura && heroi.getHp() > 0) {

            switch (salaAtual) {

                // -------------------------------------------------------
                // 1 — PÂNTANO DOS LAMENTOS
                // -------------------------------------------------------
                case 1 -> {
                    System.out.println("\n======================= PÂNTANO DOS LAMENTOS =======================");
                    System.out.println("O cheiro pútrido envolve-te e ecos distantes atormentam a tua mente...");

                    System.out.println("\nEncontras um saco encharcado preso a raízes.");
                    System.out.println("Recebes automaticamente +20 moedas de ouro.");
                    System.out.println("Segues para a Floresta de Brokilon");

                    heroi.ganharOuro(20);

                    salaAtual = 2; // segue para Brokilon
                }

                // -------------------------------------------------------
                // 2 — FLORESTA DE BROKILON - 2 COMBATES
                // -------------------------------------------------------
                case 2 -> {
                    System.out.println("\n============== FLORESTA DE BROKILON ==============");
                    System.out.println("""
                            A floresta respira magia antiga. As dríadas observam-te das copas.
                            Elas estão prestes a transformar uma jovem rapariga numa delas!

                            O que pretendes fazer?
                            1 - Seguir o teu caminho silenciosamente sem te chateares
                            2 - Lutar contra as dríadas
                            """);

                    System.out.print("Escolhe uma opção: ");
                    int esc = sc.nextInt();

                    if (esc == 1) {
                        System.out.println("\nAvanças com cautela...");

                        if (random.nextInt(100) < 50) {
                            System.out.println("\n❌ O chão cede! Caíste num buraco profundo e... morres!");
                            heroi.setHp(0);
                            return menuDerrota();
                        } else {
                            System.out.println("Bravo! Conseguiste evitar as armadilhas naturais de Brokilon");
                            System.out.println(" e segues para o desafio seguinte!");
                        }
                    } else {
                        System.out.println("\nAs dríadas surgem das sombras com elegância letal!");

                        NPC d1 = new NPC("Dríada Guardiã", 60, 10, 15);
                        NPC d2 = new NPC("Dríada Arqueira", 50, 12, 12);

                        if (heroi.atacar(d1) != heroi) return menuDerrota();
                        if (heroi.atacar(d2) != heroi) return menuDerrota();

                        System.out.println("\n✔ Derrotaste as dríadas e salvaste as jovens do ritual!");
                    }

                    oferecerUsoPocoes(heroi);
                    salaAtual = 3; // segue para Skellige
                }

                // -------------------------------------------------------
                // 3 — SKELLIGE (PEDRA DO SOL) - TOTEM
                // -------------------------------------------------------
                case 3 -> {
                    System.out.println("\n====== ANTIGA CRIPTA - ILHA DE SKELLIGE ======");
                    System.out.println("""
                            A Pedra do Sol repousa num altar élfico.
                            Dizem que concede poder... ou destruição.

                            Atreves-te a tocar na Pedra?
                            1 - Sim
                            2 - Não
                            """);

                    System.out.print("Escolhe uma opção: ");
                    int esc = sc.nextInt();

                    if (esc == 1) {
                        if (random.nextInt(100) < 50) {
                            System.out.println("\n🌞 A energia aquece-te! +20 Pontos de Vida!");
                            System.out.println("Segues agora para o Deserto de Korath");
                            heroi.setHp(heroi.getHp() + 20);
                        } else {
                            System.out.println("\n⚠️ A energia consome-te! -10 Pontos de Vida!");
                            System.out.println("Segues agora para o Deserto de Korath com menos pontos de Vida");
                            heroi.setHp(heroi.getHp() - 10);
                            if (heroi.getHp() <= 0) return menuDerrota();
                        }
                    }

                    salaAtual = 4; // segue para o Deserto de Korath
                }

                // -------------------------------------------------------
                // 4 — DESERTO DE KORATH - 1 MONSTRO
                // -------------------------------------------------------
                case 4 -> {
                    System.out.println("\n=========== DESERTO DE KORATH ===========");
                    System.out.println("""
                            As temperaturas mortais e dunas traiçoeiras ameaçam-te.
                            Este deserto está repleto de criaturas mortíferas!!

                            1 - Tentar atravessar cuidadosamente
                            2 - Explorar a área
                            """);
                    System.out.print("Escolhe uma opção: ");

                    int esc = sc.nextInt();

                    if (esc == 1) {
                        if (random.nextInt(100) < 50) {
                            System.out.println("\n❌ Uma armadilha de Arachas leva-te à morte! Agora és comida de aranha!");
                            heroi.setHp(0);
                            return menuDerrota();
                        } else {
                            System.out.println("Consegues atravessar a zona perigosa e sobreviver.");
                            System.out.println("Passarás para o desafio seguinte!");
                        }
                    } else {
                        System.out.println("""

                                Uma Aracha salta das dunas!

                                As arachas, ou aranhas-caranguejo, são grandes insetos
                                com veneno mortal e um abdómen volumoso.
                                """);

                        NPC arachas = new NPC("Arachas", 80, 14, 20);
                        if (heroi.atacar(arachas) != heroi) return menuDerrota();
                    }

                    oferecerUsoPocoes(heroi);

                    System.out.println("\nDesejas comprar algo ao vendedor antes de prosseguir?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    System.out.print("Escolhe a opção: ");

                    if (sc.nextInt() == 1) {
                        boolean compra = true;
                        while (compra) {
                            System.out.println("\n================= LOJA DO VENDEDOR =================");
                            System.out.println("Ouro que tens disponível: " + heroi.getOuro() + " moedas de ouro");
                            System.out.println();

                            // GUARDAR quais itens foram mostrados
                            ArrayList<Integer> indicesReais = vendedor.imprimirLoja();

                            System.out.println("0 - Sair");
                            System.out.print("Escolhe o número do item que queres compra. Ou preferes sair (0)?: ");

                            int opt = sc.nextInt();

                            if (opt == 0) {
                                compra = false;
                            } else if (opt >= 1 && opt <= indicesReais.size()) {

                                int indiceReal = indicesReais.get(opt - 1);  // obter índice real

                                vendedor.vender(heroi, indiceReal);          // compra item correto
                            } else {
                                System.out.println("Opção inválida!");
                            }
                        }
                    }

                    salaAtual = 5; // segue para Tor Lara
                }

                // -------------------------------------------------------
                // 5 — PRAIA DE TOR LARA (VILGEFORTZ + possíveis aliados)
                // -------------------------------------------------------
                case 5 -> {
                    System.out.println("\n============== PRAIA DE TOR LARA ==============");
                    System.out.println("""
                            Rumores dizem que Vilgefortz e seus aliados está escondido aqui…
                            Queres investigar?
                            1 - Sim
                            2 - Não
                            """);
                    System.out.print("Escolhe uma opção: ");

                    int esc = sc.nextInt();

                    if (esc == 2) {
                        salaAtual = 50; // vai direto a Nilfgaard
                        break;
                    }

                    int sorte = random.nextInt(100);

                    ArrayList<NPC> inimigos = new ArrayList<>();

                    if (sorte < 50) {
                        inimigos.add(new NPC("Vilgefortz", 120, 18, 25));
                    } else if (sorte < 80) {
                        inimigos.add(new NPC("Vilgefortz", 120, 18, 25));
                        inimigos.add(new NPC("Kikimora", 60, 10, 10));
                    } else {
                        inimigos.add(new NPC("Vilgefortz", 120, 18, 25));
                        inimigos.add(new NPC("Kikimora", 60, 10, 10));
                        inimigos.add(new NPC("Rience", 70, 14, 20));
                    }

                    System.out.println("\n⚔ Inimigos encontrados:");
                    for (NPC npc : inimigos) System.out.println("- " + npc.getNome());

                    for (NPC npc : inimigos) {
                        if (heroi.atacar(npc) != heroi) return menuDerrota();
                    }

                    System.out.println("\n✔ Derrotaste todos os inimigos em Tor Lara!");
                    System.out.println("Segues para Nilfgaard para o conflito final!!");

                    oferecerUsoPocoes(heroi);

                    System.out.println("\nDesejas comprar algo antes da batalha final?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    System.out.print("Escolhe a opção: ");
                    if (sc.nextInt() == 1) {
                        boolean compra = true;
                        while (compra) {
                            System.out.println("\n================= LOJA DO VENDEDOR =================");
                            System.out.println("Ouro que tens disponível: " + heroi.getOuro() + " moedas de ouro");
                            System.out.println();

                            // GUARDAR quais itens foram mostrados
                            ArrayList<Integer> indicesReais = vendedor.imprimirLoja();

                            System.out.println("0 - Sair");
                            System.out.print("Escolhe o número do item que queres compra. Ou preferes sair (0)?: ");

                            int opt = sc.nextInt();

                            if (opt == 0) {
                                compra = false;
                            } else if (opt >= 1 && opt <= indicesReais.size()) {

                                int indiceReal = indicesReais.get(opt - 1);  // obter índice real

                                vendedor.vender(heroi, indiceReal);          // compra item correto
                            } else {
                                System.out.println("Opção inválida!");
                            }
                        }
                    }

                    salaAtual = 6;
                }

                // -------------------------------------------------------
                // 6 — NILFGAARD (FINAL CONTRA EMHYR)
                // -------------------------------------------------------
                case 6 -> {
                    System.out.println("\n========== PALÁCIO IMPERIAL DE NILFGAARD ==========");
                    System.out.println("O Imperador Emhyr var Emreis aguarda a tua chegada...");
                    System.out.println("Este é momento que tanto esperavas e a tua oportunidade ");
                    System.out.println("de Conquistar o Continente!!");

                    NPC emhyr = new NPC("Imperador Emhyr", 160, 20, 35);

                    if (heroi.atacar(emhyr) != heroi) {
                        return menuDerrota();
                    }

                    System.out.println("\n🔥 Vitória! Derrotaste o Imperador e Conquistaste o Continente!");

                    return menuVitoria();
                }

                default -> {
                    aventura = false;
                }
            }
        }

        return menuDerrota();
    }

    // ============================================================
    // USO DE POÇÕES ENTRE SALAS (pode tomar até 3)
    // ============================================================
        /**
     * Permite ao jogador utilizar poções entre fases da aventura.
     *
     * O herói pode usar até um número máximo de poções
     * antes de avançar para a próxima área.
     *
     * @param heroi herói que pode utilizar as poções
     */
	private void oferecerUsoPocoes(Heroi heroi) {

        if (heroi.getHp() <= 0) return;

        int limite = 3;  // Máximo de poções por fase
        int usadas = 0;

        while (usadas < limite) {

            // ---------------------------------------------------
            // Mostrar inventário de poções ANTES da escolha
            // ---------------------------------------------------
            ArrayList<Pocao> pocoesDisponiveis = new ArrayList<>();

            for (Consumivel c : heroi.getInventario()) {
                if (c instanceof Pocao) {
                    pocoesDisponiveis.add((Pocao) c);
                }
            }

            System.out.println("\n=== POÇÕES DISPONÍVEIS ===");

            if (pocoesDisponiveis.isEmpty()) {
                System.out.println("Não tens poções para usar.");
                return;
            }

            for (int i = 0; i < pocoesDisponiveis.size(); i++) {
                Pocao p = pocoesDisponiveis.get(i);
                System.out.println((i + 1) + ". " + p.getNome() +
                        " | ❤️ Cura: " + p.getVidaCurar() +
                        " | 💪 Força: +" + p.getAumentoForca());
            }

            System.out.println("\nPodes tomar até " + (limite - usadas) + " poções antes de avançar.");
            System.out.println("Queres tomar uma poção?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Escolhe uma opção: ");

            int esc = sc.nextInt();

            if (esc == 1) {

                heroi.usarPocao(); // Aqui dentro já seleciona a poção
                usadas++;

            } else if (esc == 2) {

                System.out.println("Decides seguir caminho sem tomar mais poções...");
                break;

            } else {

                System.out.println("❌ Opção inválida.");
            }
        }

        if (usadas == limite) {
            System.out.println("\nJá usaste o máximo de poções permitido nesta fase.");
        }
    }

    /**
     * Mostra o menu de derrota ao jogador.
     *
     * Reproduz o som de derrota e permite escolher
     * entre repetir a aventura, criar nova personagem
     * ou sair do jogo.
     *
     * @return opção escolhida pelo jogador
     */
    public int menuDerrota() {

        SoundPlayer.playSound("src/AudioFiles/derrota.wav");

        System.out.println("\n===========================================");
        System.out.println("              💀  D E R R O T A  💀");
        System.out.println("===========================================");
        System.out.println("Foste derrotado... o Continente permanece nas sombras.");
        System.out.println("O que desejas fazer agora?\n");

        System.out.println("1 - Jogar novamente com a mesma personagem");
        System.out.println("2 - Criar nova personagem");
        System.out.println("3 - Sair do jogo");
        System.out.print("Escolhe uma opção: ");

        int escolha = -1;

        while (escolha < 1 || escolha > 3) {
            try {
                escolha = sc.nextInt();
                if (escolha < 1 || escolha > 3)
                    System.out.print("❌ Escolha inválida! Tenta novamente: ");
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("❌ Escolha inválida! Tenta novamente: ");
            }
        }

        return escolha;
    }

    /**
     * Mostra o menu de vitória ao jogador.
     *
     * Reproduz o som de vitória e permite escolher
     * entre repetir a aventura, criar nova personagem
     * ou sair do jogo.
     *
     * @return opção escolhida pelo jogador
     */
    public int menuVitoria() {

        SoundPlayer.playSound("src/AudioFiles/vitoria.wav");


        System.out.println("\n===========================================");
        System.out.println("           🏆  V I T Ó R I A  🏆");
        System.out.println("===========================================");
        System.out.println("Conquistaste o Continente! Emhyr caiu perante ti.");
        System.out.println("As lendas cantarão o teu nome por gerações.\n");

        System.out.println("O que desejas fazer agora?");
        System.out.println("1 - Jogar novamente com a mesma personagem");
        System.out.println("2 - Criar nova personagem");
        System.out.println("3 - Sair do jogo");
        System.out.print("Escolhe uma opção: ");

        int escolha = -1;

        while (escolha < 1 || escolha > 3) {
            try {
                escolha = sc.nextInt();
                if (escolha < 1 || escolha > 3)
                    System.out.print("❌ Escolha inválida! Tenta novamente: ");
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("❌ Escolha inválida! Tenta novamente: ");
            }
        }

        return escolha;
    }



}
