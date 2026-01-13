package Jogo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Classe utilitária responsável pela reprodução de sons no jogo.
 *
 * Permite iniciar e parar a reprodução de ficheiros de áudio
 * durante a execução do programa.
 */
public class SoundPlayer {

                      /**
             * Clip de áudio utilizado para reproduzir sons.
             *
             * É mantido como atributo estático para permitir
             * o controlo da reprodução entre diferentes métodos.
             */
            private static Clip clip;

                     /**
             * Reproduz um ficheiro de áudio a partir do caminho indicado.
             *
             * O áudio começa a ser reproduzido imediatamente,
             * caso o ficheiro exista e seja válido.
             *
             * @param caminho caminho para o ficheiro de áudio
             */
            public static void playSound(String caminho) {
                try {
                    File audio = new File(caminho);
                    if (audio.exists()) {
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
                        clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        clip.start();
                    } else {
                        System.out.println("⚠ Ficheiro de som não encontrado");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao reproduzir áudio: " + e.getMessage());
                }
            }

               /**
             * Para a reprodução do áudio atualmente em execução.
             *
             * O clip é interrompido e os recursos associados
             * são libertados.
             */
            public static void stopMusic() {
                try {
                    if (clip != null && clip.isRunning()) {
                        clip.stop();   // pára o áudio
                        clip.close();  // liberta recursos
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao parar áudio: " + e.getMessage());
                }
            }
        }