import java.io.IOException;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("BEM VINDO AO KALAH");
		int casinha;
		int jogador = 1;
		Tabuleiro jogo = new Tabuleiro();
		Interface i = new Interface(jogo);
		
		while(!jogo.limiteKalah() && jogo.temFeijoes()){
			System.out.println(jogo);
			
			if(jogador == 1){
				System.out.println("Vez do jogador 1");
			
				System.out.println("Em que casinha você deseja começar a jogar?");
			
				casinha = EntradaTeclado.leInt();
				
				if(casinha < 0 || casinha > 5) System.out.println("Posição inválida");
			
				else jogador = jogo.jogada (1, jogo.j1, jogo.k1, jogo.j2, casinha);
			
			}
			else{
				System.out.println("Vez do jogador 2");
			
				System.out.println("Em que casinha você deseja começar a jogar?");
			
				casinha = EntradaTeclado.leInt();
				
				if(casinha < 0 || casinha > 5) System.out.println("Posição inválida");
			
				else jogador = jogo.jogada (2, jogo.j2, jogo.k2, jogo.j1, casinha);
			
			
			}
			
		}

	}

}
