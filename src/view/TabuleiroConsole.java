package view;

import java.util.Arrays;
import java.util.Scanner;

import cm.models.Tabuleiro;
import excecao.ExplosaoException;
import excecao.SairException;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole( Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Jogo finalizado!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado() ) {
				System.out.println(tabuleiro.toString());
				
				String digitado = capturarValorDigitado("Digite (x, y): ");
				
				System.out.println(Arrays.deepToString(digitado.split(",")));
			}
			
			System.out.println("Você venceu!!!!");
		} catch(ExplosaoException e) {
			System.out.println("Você perdeu!!!");
		}
	}
	
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
