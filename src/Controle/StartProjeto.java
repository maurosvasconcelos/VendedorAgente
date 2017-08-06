package Controle;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;

import jade.wrapper.*;

/**
 * endereco do exemplo
 * https://intra.serpro.gov.br/tema/tematec/jade-framework-java-para-sistemas-
 * baseados-em-agentes
 * 
 * @author mauro
 * 
 * novo modelo
 * D:\PROJETO IVO\JADE\JADE-examples-4.5.0\jade\src\examples\inprocess
 *
 */
public class StartProjeto {

	public static void main(String[] args) {

		// String[] sniffer = { "-gui", "-local-host", "127.0.0.1",
		// "sniffer:jade.tools.sniffer.Sniffer", };
		// jade.Boot.main(sniffer);

		// String[] parametros = { "-gui", "-local-host", "127.0.0.1",
		// "comprador:Agentes.AgenteComprador()", };
		// jade.Boot.main(parametros);
		// String[] novoContainer = { "-local-host", "127.0.0.1", "-container",
		// "-container-name", "Meu-Container",
		// "vendedor:Agentes.AgenteVendedor()" };
		// jade.Boot.main(novoContainer);

		// String[] parametros = { "-gui", "-local-host", "127.0.0.1",
		// "comprador:Agentes.AgenteComprador(),comprador:Agentes.AgenteComprador()",};
		// jade.Boot.main(parametros);

		Runtime rt = Runtime.instance();
		rt.setCloseVM(true);
		Profile pMain = new ProfileImpl(null, 8888, null);
		AgentContainer mc = rt.createMainContainer(pMain);

		try {
			AgentController rma = mc.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
			rma.start();

			AgentController dummy = mc.createNewAgent("vendedor", "Agentes.AgenteVendedor", new Object[0]);
			dummy.start();
			
			AgentController mauro = mc.createNewAgent("mauro", "Agentes.AgenteComprador", new Object[]{"valor",  new Integer(5)});
			mauro.start();
			AgentController ivo = mc.createNewAgent("ivo", "Agentes.AgenteComprador", new Object[]{"valor", new Integer(2)});
			ivo.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
