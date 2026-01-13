package Jogo;

import Entidades.Bestiario;
import Entidades.Enciclopedia;
import Entidades.Heroi;

import java.sql.SQLOutput;
import java.util.Scanner;
/**
 * Classe responsável pelo menu principal do jogo.
 *
 * Esta classe apresenta a introdução narrativa,
 * gere as opções do menu inicial e controla o fluxo
 * entre a criação de personagens, início do jogo
 * e acesso a conteúdos informativos como a enciclopédia
 * e o bestiário.
 *
 * Contém o método {@code main}, sendo o ponto de entrada
 * da aplicação.
 */
public class Menu {
    /**
     * Scanner utilizado para ler a entrada do utilizador
     * ao longo da execução do menu.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Método principal da aplicação.
     *
     * Inicia o menu principal do jogo.
     *
     * @param args argumentos da linha de comandos
     */
    public static void main(String[] args) {
        mostrarMenuInicial();
    }

    /**
     * Mostra o menu inicial do jogo.
     *
     * Apresenta a introdução narrativa, exibe as opções
     * disponíveis ao jogador e responde às escolhas feitas,
     * permitindo iniciar um novo jogo, consultar informação
     * adicional ou sair da aplicação.
     */
    public static void mostrarMenuInicial() {

        // ===============================
        //   INTRODUÇÃO / BACKGROUND
        // letras ascii de https://ascii.co.uk/text
        // ===============================
        System.out.println("=============================================================================================================");
        System.out.println("\t\t\n" +
                "         __   __        __          __  ___          __   __      __   __       ___         ___      ___  ___ \n" +
                " /\\     /  ` /  \\ |\\ | /  \\ |  | | /__`  |   /\\     |  \\ /  \\    /  ` /  \\ |\\ |  |  | |\\ | |__  |\\ |  |  |__  \n" +
                "/~~\\    \\__, \\__/ | \\| \\__X \\__/ | .__/  |  /~~\\    |__/ \\__/    \\__, \\__/ | \\|  |  | | \\| |___ | \\|  |  |___ \n" +
                "                                                                                                              \n");
        System.out.println("                  ⫷\uD83D\uDC3A⫸ Uma aventura RPG inspirada na série THE WITCHER ⫷\uD83D\uDC3A⫸");
        System.out.println();
        System.out.println("=============================================================================================================");
        System.out.println("O Continente foi dominado pelo terrível Imperador Emhyr var Emreis de Nilfgaard.");
        System.out.println("O povo vive na miséria e é maltratado pela guarda nilfgaardiana.O mago Vilgefortz");
        System.out.println(" é seu aliado, numa terra pejada de criaturas mortais, como as Dríadas, Kikimoras");
        System.out.println(" ou as Aranhas-Caraguejo.");
        System.out.println();
        System.out.println("Neste jogo inspirado na série \"The Witcher\", cabe aos heróis Geralt, Yennefer, Cahir");
        System.out.println("e Ciri a \"Conquista do Continente\"!");
        System.out.println();
        System.out.println("Geralt, também conhecido como Lobo Branco ou o Carniceiro de Blaviken, é o protagonista");
        System.out.println("desta aventura. Como feiticeiro, passou por mutações e treinos na infância que lhe");
        System.out.println("conferiram capacidades sobre-humanas, como superforça e velocidade, para caçar monstros.");
        System.out.println("Tem uma relação romântica com Yennefer, que oscila entre altos e baixos, mas ela é o");
        System.out.println("grande amor da sua vida. O destino de Geralt está ligado ao da sua protegida, Ciri de Cintra.");
        System.out.println();
        System.out.println("Yennefer de Vengerberg considera Ciri como uma filha e cuida dela como tal. É famosa");
        System.out.println("tanto pela sua beleza como pela poderosa magia. O seu objetivo é destruir o mago Vilgefortz.");
        System.out.println();
        System.out.println("Ciri é a herdeira do trono de Cintra, recentemente conquistado pelo Império Nilfgaardiano.");
        System.out.println("Agora tornou-se alvo de muitos que desejam usá-la como moeda de troca para ganhos políticos.");
        System.out.println("É também pupila de Geralt e está a ser treinada para se tornar uma feiticeira. Devido à sua");
        System.out.println("ascendência élfica, possui sangue ancestral que lhe permite viajar pelo espaço e pelo tempo.");
        System.out.println();
        System.out.println("Cahir Mawr Dyffryn aep Ceallach era um cavaleiro dos serviços de informação de Nilfgaard e");
        System.out.println("apaixonou-se por Ciri durante o ataque a Cintra. A sua missão era capturá-la e entregá-la a");
        System.out.println("Emhyr var Emreis, mas falhou e acabou preso. Mais tarde, junta-se ao grupo de Geralt e jura");
        System.out.println("proteger Ciri de todo o mal.");
        System.out.println();
        System.out.println("Juntos, a missão destes heróis é destruir o reinado do malvado Imperador Emhyr, mas no");
        System.out.println("caminho terão de enfrentar diversos desafios, monstros, armadilhas e vilões.");
        System.out.println("====================================================\n");


        boolean sair = false;

        while (!sair) {

            System.out.println("\n\uD83D\uDCDC MENU PRINCIPAL");
            System.out.println("1. Novo Jogo");
            System.out.println("2. Carregar Jogo (indisponível)");
            System.out.println("3. Enciclopédia de Personagens");
            System.out.println("4. Bestiário");
            System.out.println("5. Configurações (brevemente)");
            System.out.println("6. Sair");
            System.out.print("\nEscolhe uma opção: ");

            String opcao = sc.nextLine().trim();

            switch (opcao) {

                case "1" -> iniciarNovoJogo();

                case "2" -> System.out.println("⚠ Função ainda não disponível.");

                case "3" -> Enciclopedia.imprimirTudo();

                case "4" -> Bestiario.imprimirTudo();

                case "5" -> System.out.println("⚙ As configurações estarão disponíveis numa atualização futura.");

                case "6" -> {
                    System.out.println("\uD83D\uDC4B A sair do jogo... Até à próxima!");
                    sair = true;
                }

                default -> System.out.println("❌ opção inválida!");
            }
        }
    }

    /**
     * Inicia um novo jogo.
     *
     * Cria uma instância do jogo, permite ao jogador criar
     * um herói e controla o ciclo principal da aventura,
     * incluindo a possibilidade de repetir, criar uma nova
     * personagem ou regressar ao menu inicial.
     */
    private static void iniciarNovoJogo() {

        System.out.println("\n====== NOVO JOGO ======");

        Jogo jogo = new Jogo();

        // Criar Personagem
        Heroi heroi = jogo.criarPersonagem();

        boolean jogar = true;

        while (jogar) {

            int resultado = jogo.conquistaDoContinente(heroi);

            switch (resultado) {

                case 1 -> {
                    // Jogar novamente com a mesma personagem
                    heroi.setHp(heroi.getMaxHp());
                    System.out.println("\n🔄 Preparar nova aventura com a mesma personagem!");
                }

                case 2 -> {
                    // Criar nova personagem
                    System.out.println("\n✨ A criar nova personagem...");
                    heroi = jogo.criarPersonagem();
                }

                case 3 -> {
                    // Sair e voltar ao menu inicial
                    System.out.println("\n👋 A aventura terminou. Regressando ao menu principal...");
                    jogar = false;
                }
            }
        }
    }
}
