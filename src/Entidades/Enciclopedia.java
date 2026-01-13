package Entidades;

import java.util.ArrayList;
/**
 * Representa a enciclopédia de personagens do jogo.
 *
 * Esta classe funciona como um repositório estático de personagens
 * importantes do universo do jogo, permitindo ao jogador consultar
 * informações de lore como afiliação, personalidade e papel na história.
 */
public class Enciclopedia {

    // =====================================================
    // CLASSE INTERNA: PERSONAGEM
    // =====================================================
        /**
     * Representa um personagem da enciclopédia.
     *
     * Contém informações narrativas e descritivas,
     * não estando diretamente ligado à mecânica de combate.
     */
	public static class Personagem {
                /** Nome do personagem */
        private String nome;

        /** Ícone representativo do personagem */
        private String icone;

        /** Afiliação ou origem do personagem */
        private String afiliacao;

        /** Traços principais da personalidade */
        private String personalidade;

        /** Papel do personagem na narrativa do universo */
        private String papelSerie;

        /**
         * Cria um novo personagem da enciclopédia.
         *
         * @param nome nome do personagem
         * @param icone ícone representativo
         * @param afiliacao afiliação ou origem
         * @param personalidade traços de personalidade
         * @param papelSerie papel do personagem na narrativa
         */
        public Personagem(String nome, String icone, String afiliacao, String personalidade, String papelSerie) {
            this.nome = nome;
            this.icone = icone;
            this.afiliacao = afiliacao;
            this.personalidade = personalidade;
            this.papelSerie = papelSerie;
        }

        /**
         * Retorna o nome do personagem.
         *
         * @return nome do personagem
         */
        public String getNome() { return nome; }

        /**
         * Mostra no console os detalhes completos do personagem,
         * incluindo afiliação, personalidade e papel na narrativa.
         */
        public void imprimirDetalhes() {
            System.out.println("\n============== " + icone + " " + nome.toUpperCase() + " ==============");
            System.out.println("🏰 Afilição: " + afiliacao);
            System.out.println("🎭 Personalidade: " + personalidade);
            System.out.println("📖 Papel: " + papelSerie);
            System.out.println("=====================================================\n");
        }
    }

    /**
     * Lista estática que contém todos os personagens da enciclopédia.
     */
    private static ArrayList<Personagem> personagens = new ArrayList<>();

    // =====================================================
    // CARREGAMENTO INICIAL DA ENCICLOPÉDIA
    // =====================================================
    
	    /**
     * Carrega os personagens iniciais da enciclopédia.
     */
	static {

        personagens.add(new Personagem(
                "Geralt de Rívia",
                "🐺",
                "Escola do Lobo • Bruxo",
                "Sarcastico, estoico, moralmente neutro, protetor.",
                "Protagonista da saga; famoso bruxo conhecido como Lobo Branco."
        ));

        personagens.add(new Personagem(
                "Yennefer de Vengerberg",
                "🖤",
                "Lodge of Sorceresses • Feiticeira",
                "Ambiciosa, inteligente, apaixonada, controladora.",
                "Uma das feiticeiras mais poderosas; grande amor de Geralt; figura materna para Ciri."
        ));

        personagens.add(new Personagem(
                "Cirilla Fiona Elen Riannon (Ciri)",
                "⚡",
                "Princesa de Cintra • Aprendiz de Bruxo",
                "Impulsiva, corajosa, determinada.",
                "Personagem central da profecia; portadora do Sangue Antigo; perseguida por múltiplas facções."
        ));

        personagens.add(new Personagem(
                "Triss Merigold",
                "🌹",
                "Feiticeira de Temeria",
                "Amável, leal, sensível.",
                "Aliada próxima de Geralt; ajudou a criar e proteger Ciri."
        ));

        personagens.add(new Personagem(
                "Dandelion (Jaskier)",
                "🎻",
                "Bardo e poeta",
                "Vaidoso, cómico, romântico, leal.",
                "Melhor amigo de Geralt; responsável por popularizar as histórias do bruxo."
        ));

        personagens.add(new Personagem(
                "Zoltan Chivay",
                "⛏️",
                "Guerreiro Anão",
                "Bravo, leal, honrado.",
                "Grande amigo de Geralt; envolvido em batalhas e negócios por todo o continente."
        ));

        personagens.add(new Personagem(
                "Vesemir",
                "🦉",
                "Escola do Lobo • Bruxo Veterano",
                "Paternal, sábio, disciplinado.",
                "Mentor de Geralt; figura paternal para todos os bruxos jovens."
        ));

        personagens.add(new Personagem(
                "Cahir Mawr Dyffryn aep Ceallach",
                "🛡️",
                "Oficial Nilfgaardiano",
                "Determinado, reservado, disciplinado.",
                "Persegue Ciri numa missão imperial, mas a sua jornada muda drasticamente."
        ));

        personagens.add(new Personagem(
                "Emhyr var Emreis",
                "👑",
                "Imperador de Nilfgaard",
                "Implacável, calculista, autoritário.",
                "Governante mais poderoso do sul; o seu destino entrelaça-se com o de Ciri."
        ));

        personagens.add(new Personagem(
                "Avallac'h",
                "✨",
                "Elfo Aen Elle • Sábio",
                "Misterioso, frio, intelectual.",
                "Guia Ciri através dos mundos; importante nas profecias do Sangue Antigo."
        ));
    }

    // =====================================================
    // MÉTODO QUE MOSTRA TODAS AS PERSONAGENS DE UMA VEZ
    // =====================================================
        /**
     * Mostra no console todos os personagens da enciclopédia.
     */
	public static void imprimirTudo() {

        System.out.println("\n========== 📚 ENCICLOPÉDIA DE PERSONAGENS ==========");

        for (Personagem p : personagens) {
            p.imprimirDetalhes();
        }

        System.out.println("====================================================\n");
    }

    /**
     * Retorna a lista completa de personagens da enciclopédia.
     *
     * @return lista de personagens
     */
	 public static ArrayList<Personagem> getLista() {
        return personagens;
    }
}
