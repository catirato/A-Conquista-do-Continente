package Entidades;

/**
 * Classe abstrata que representa uma entidade do jogo.
 *
 * Serve como base para todas as entidades jogáveis e não jogáveis,
 * como heróis e NPCs, armazenando atributos comuns como nome,
 * pontos de vida e força.
 *
 * Esta classe não pode ser instanciada diretamente.
 */
public abstract class Entidade {
	    /** Nome da entidade */
    protected String nome;

    /** Valor máximo de pontos de vida */
    protected int maxHp;

    /** Pontos de vida atuais */
    protected int hp;

    /** Valor base de força da entidade */
    public int forca;


    /**
     * Cria uma nova entidade com os valores base definidos.
     *
     * A vida atual é inicializada com o valor máximo de vida.
     *
     * @param nome nome da entidade
     * @param maxHp valor máximo de pontos de vida
     * @param forca valor base de força
     */
    public Entidade(String nome, int maxHp, int forca) {
        this.nome = nome;
        this.maxHp = maxHp;
        this.hp = maxHp;   // vidaAtual começa igual a vidaMax
        this.forca = forca;
    }

  /**
     * Retorna o nome da entidade.
     *
     * @return nome da entidade
     */
    public String getNome() {
        return nome;
    }

  /**
     * Retorna o valor máximo de pontos de vida.
     *
     * @return vida máxima
     */
    public int getMaxHp() {
        return maxHp;
    }
	
	/**
     * Retorna os pontos de vida atuais.
     *
     * @return vida atual
     */

    public int getHp() {
        return hp;
    }
	
	  /**
     * Retorna o valor base de força da entidade.
     *
     * @return força base
     */

    public int getForca() {
        return forca;
    }

      /**
     * Mostra no console todos os detalhes atuais da entidade,
     * incluindo nome, vida máxima, vida atual e força.
     */
    public void mostrarDetalhes() {
        System.out.println("=== Detalhes ===");
        System.out.println("Nome: " + nome);
        System.out.println("Vida Máxima: " + maxHp);
        System.out.println("Vida Atual: " + hp);
        System.out.println("Força: " + forca);
        System.out.println("======================\n");
    }
	
	 /**
     * Define os pontos de vida atuais da entidade.
     *
     * O valor atribuído nunca pode ultrapassar a vida máxima.
     *
     * @param hp novo valor de vida
     */

    public void setHp(int hp) {
        this.hp = Math.min(hp, maxHp); // evita que ultrapasse a vida máxima
    }
}
