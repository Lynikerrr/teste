package imersãoJavaAlura;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {

		String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";

		var http = new ClienteHttp();
		String json = http.buscaDados(url);
	

		var parser = new JsonParser();
		List<Map<String, String>> listaDeConteudo = parser.parse(json);

		var generator = new StickerGenerator();
		for (Map<String, String> conteudo : listaDeConteudo) {
			String urlImagem = conteudo.get("url");
			String titulo = conteudo.get("title");

			InputStream inputStream = new URL(urlImagem).openStream();
			String nomeArquivo = "Saida/" + titulo + ".png";

			generator.criar(inputStream, nomeArquivo);

			System.out.println("\uD83C\uDfAc \u001b[1mTitulo:\u001b[m " + conteudo.get("title") + "\n");
			System.out.println("\u001b[34m" + conteudo.get("image") + "\u001b[m" + "\n");

			System.out.println("\u001b[1mNota:\u001b[m " + conteudo.get("imDbRating") + "\n");
			double classificacao = Double.parseDouble(conteudo.get("imDbRating"));

			int estrelas = (int) classificacao;
			for (int x = 1; x <= estrelas; x++) {
				System.out.print("\u2B50");
			}
			System.out.println("\n");

		}
	}
}
