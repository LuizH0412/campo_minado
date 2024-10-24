package br.com.coder.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.coder.cm.excecao.ExplosaoException;

public class TabuleiroTeste {
	
	private Tabuleiro tabuleiro;
	
	@BeforeEach
	void iniciarTabuleiro() {
		tabuleiro = new Tabuleiro(5, 5, 3);
	}
	
	@Test
    void testeAbrirCampoSemMina() {
        assertDoesNotThrow(() -> tabuleiro.abrir(0, 0));
    }

    @Test
    void testeAbrirCampoComMina() {
        tabuleiro = new Tabuleiro(2, 2, 1);
        tabuleiro.sortearMinas();

        ExplosaoException e = assertThrows(ExplosaoException.class, () -> {
            tabuleiro.abrir(0, 0);
        });
        assertNotNull(e);
    }

    @Test
    void testeAlternarMarcacao() {
        tabuleiro.alternarMarcacao(0, 0);
        assertTrue(tabuleiro.campos.get(0).isMarcado());
        tabuleiro.alternarMarcacao(0, 0);
        assertFalse(tabuleiro.campos.get(0).isMarcado());
    }

    @Test
    void testeReiniciarTabuleiro() {
        tabuleiro.abrir(0, 0);
        tabuleiro.reiniciar();
        assertFalse(tabuleiro.campos.get(0).isAberto());
    }

    @Test
    void testeObjetivoAlcancado() {
        tabuleiro = new Tabuleiro(2, 2, 0);
        tabuleiro.abrir(0, 0);
        tabuleiro.abrir(1, 1);

        assertTrue(tabuleiro.objetivoAlcancado());
    }

    @Test
    void testeToString() {
        String tabuleiroStr = tabuleiro.toString();
        assertNotNull(tabuleiroStr);
        assertTrue(tabuleiroStr.contains("0"));
    }
}
