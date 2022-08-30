package brasileiraoView;

import brasileirao.controll.Campeonato;

public class Main {

	public static void main(String args[]) {

		Campeonato brasileirao = new Campeonato();
		String caminhoElencos = "C:\\Users\\Leo\\git\\brasileirao\\brasileirao\\src\\brasileirao\\model\\arquivos\\cadastro.csv";
		String caminhoPartidas = "C:\\Users\\Leo\\git\\brasileirao\\brasileirao\\src\\brasileirao\\model\\arquivos\\partidas.csv";
		brasileirao.carregarDados(caminhoElencos, caminhoPartidas);

		// new BrasileiraoMenu();

		/*
		 * int opcao; String menu = "\n1 - Classificacao"; menu += "\n2 - Artilharia";
		 * menu += "\n3 - Tabela de partidas"; menu += "\n4 - Inserir resultados"; menu
		 * += "\n5 - Relatorios"; menu += "\n6 - Sair"; menu +=
		 * "\n\nDigite a opção desejada:\n"; while (true) { opcao =
		 * Integer.parseInt(JOptionPane.showInputDialog(menu)); if (opcao == 6) { break;
		 * } switch (opcao) { case 1: // Imprimir classificacao
		 * JOptionPane.showMessageDialog(null,
		 * brasileirao.imprimirCLassificacaoInterface()); break; case 2: // Imprimir
		 * artilharia brasileirao.imprimirArtilharia(); break; case 3: // Imprimir
		 * tabela de partidas brasileirao.apresentarPartidas(); break; case 4: //
		 * Inserir resultados
		 * 
		 * int idPartida =
		 * Integer.parseInt(JOptionPane.showInputDialog("Digite o id da partida:")); int
		 * golsMandante = Integer .parseInt(JOptionPane.
		 * showInputDialog("Digite o número de gols marcados pelo time mandante:")); int
		 * golsVisitante = Integer .parseInt(JOptionPane.
		 * showInputDialog("Digite o número de gols marcados pelo time visitante:"));
		 * int totalGols = golsMandante + golsVisitante; int[] dadosDaPartida = {
		 * idPartida, golsMandante, golsVisitante };
		 * brasileirao.carregarResultadoPeloUsuario(dadosDaPartida);
		 * 
		 * ArrayList<Integer> idMarcadores = new ArrayList<Integer>();
		 * idMarcadores.add(idPartida); for (int a = 0; a < totalGols; a++) { int
		 * idMarcador =
		 * Integer.parseInt(JOptionPane.showInputDialog("Digite o id do marcador:"));
		 * idMarcadores.add(idMarcador); }
		 * 
		 * brasileirao.carregarMarcadores(idMarcadores);
		 * 
		 * break; case 5: // Relatorios System.out.println("Relatórios em construção");
		 * break; default: System.out.println("Opcao invalida"); break; }
		 * 
		 * }
		 * 
		 */

		// brasileirao.apresentarTime(17);

		// brasileirao.apresentarPartidas();

		// brasileirao.imprimirCLassificacao();
		// brasileirao.imprimirArtilharia();

	}

}
