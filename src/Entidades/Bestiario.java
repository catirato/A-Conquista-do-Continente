package Entidades;

import java.util.ArrayList;

public class Bestiario {

    // =====================================================
    // CLASSE INTERNA: MONSTRO
    // =====================================================
    public static class Monstro {
        private String nome;
        private String tipo;
        private String descricao;
        private String fraquezas;
        private String dificuldade;
        private String icone;

        public Monstro(String nome, String tipo, String descricao, String fraquezas, String dificuldade, String icone) {
            this.nome = nome;
            this.tipo = tipo;
            this.descricao = descricao;
            this.fraquezas = fraquezas;
            this.dificuldade = dificuldade;
            this.icone = icone;
        }

        public String getNome() {
            return nome;
        }

        public void imprimirDetalhes() {
            System.out.println("\n============== " + icone + " " + nome.toUpperCase() + " ==============");
            System.out.println("📌 Tipo: " + tipo);
            System.out.println("📖 Descrição: " + descricao);
            System.out.println("🔥 Fraquezas: " + fraquezas);
            System.out.println("⚔️ Dificuldade: " + dificuldade);
            System.out.println("=====================================================\n");
        }
    }

    // =====================================================
    // LISTA DE MONSTROS
    // =====================================================
    private static ArrayList<Monstro> monstros = new ArrayList<>();

    // =====================================================
    // CARREGAMENTO INICIAL DO BESTIÁRIO
    // =====================================================
    static {

        monstros.add(new Monstro(
                "Kikimora",
                "Insectoide",
                "Criatura agressiva dos pântanos que ataca com múltiplas patas.",
                "Óleo Insectoide, Igni",
                "Fácil–Médio",
                "🕷"
        ));

        monstros.add(new Monstro(
                "Arachas",
                "Insectoide",
                "Monstro aracnídeo blindado que usa teias para imobilizar presas.",
                "Insectoid Oil, Yrden, Bombas",
                "Médio",
                "🕸"
        ));

        monstros.add(new Monstro(
                "Ghoul",
                "Necrofágico",
                "Devoradores de cadáveres que atacam em grupo.",
                "Necrophage Oil, Igni",
                "Médio",
                "🧟"
        ));

        monstros.add(new Monstro(
                "Alghoul",
                "Necrofágico",
                "Ghoul evoluído com espinhos defensivos.",
                "Aard, Igni",
                "Difícil",
                "💀"
        ));

        monstros.add(new Monstro(
                "Aparição",
                "Espectral",
                "Fantasma preso ao mundo físico, vulnerável a sinais mágicos.",
                "Yrden, Specter Oil, Moondust",
                "Médio–Difícil",
                "👻"
        ));

        monstros.add(new Monstro(
                "Nekker",
                "Ogroide",
                "Pequenos monstros que atacam sempre em bando.",
                "Ogroid Oil, Aard",
                "Médio",
                "👺"
        ));

        monstros.add(new Monstro(
                "Wyvern",
                "Dracónideo",
                "Criatura alada venenosa com cauda perigosa.",
                "Draconid Oil, Aard",
                "Difícil",
                "🐉"
        ));

        monstros.add(new Monstro(
                "Basilisco",
                "Dracónideo",
                "Réptil voador que combina voo e veneno.",
                "Draconid Oil, Quen",
                "Difícil",
                "🐍"
        ));

        monstros.add(new Monstro(
                "Lobisomem",
                "Maldito",
                "Criatura amaldiçoada com regeneração extrema.",
                "Cursed Oil, Moondust",
                "Muito Difícil",
                "🐺"
        ));

        monstros.add(new Monstro(
                "Leshen",
                "Relíquia",
                "Guardião antigo da floresta que controla raízes e animais.",
                "Dimeritium, Igni, Bombas",
                "Boss",
                "🌲"
        ));
    }

    // =====================================================
    // MOSTRAR TODOS OS MONSTROS DE UMA VEZ
    // =====================================================
    public static void imprimirTudo() {

        System.out.println("\n========== 📘 BESTIÁRIO COMPLETO ==========");

        for (Monstro m : monstros) {
            m.imprimirDetalhes();
        }

        System.out.println("=============================================\n");
    }

    public static ArrayList<Monstro> getLista() {
        return monstros;
    }
}
