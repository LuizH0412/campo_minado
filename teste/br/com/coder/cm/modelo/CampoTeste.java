package br.com.coder.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.coder.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		 campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAltenarMarcacao() {
		campo.altenarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAltenarMarcacaoDuasChamadas() {
		campo.altenarMarcacao();
		campo.altenarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.altenarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.altenarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeObjetivoAlcancado() {
		// campo.abrir();
		campo.minar();
		campo.altenarMarcacao();
		
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeMinasNaVizinhanca() {
		Campo vizinhoMinado = new Campo(3, 2);
		vizinhoMinado.minar();
		campo.adicionarVizinho(vizinhoMinado);
		assertEquals(1, campo.minasNaVizinhanca());
		
	}
	
	@Test
	void testeReiniciar() {
		campo.abrir();
		campo.minar();
		campo.altenarMarcacao();
		campo.reiniciar();
		assertFalse(campo.isAberto());
		assertFalse(campo.isMinado());
		assertFalse(campo.isMarcado());
	}
	
	@Test
    public void testToString_Marcado() {
        campo.altenarMarcacao();
        assertEquals("x", campo.toString());
    }

    @Test
    public void testToString_AbertoComMina() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir(); // A exceção deve ser lançada aqui, no método abrir()
        });
        
        assertEquals("*", campo.toString());
    }

    @Test
    public void testToString_AbertoSemMinaComMinasNaVizinhanca() {
        Campo vizinhoMinado = new Campo(3, 2);
        vizinhoMinado.minar();
        campo.adicionarVizinho(vizinhoMinado);
        campo.abrir();
        assertEquals("1", campo.toString());
    }

    @Test
    public void testToString_AbertoSemMinas() {
        campo.abrir();
        assertEquals(" ", campo.toString());
    }

    @Test
    public void testToString_Fechado() {
        assertEquals("?", campo.toString());
    }
}
