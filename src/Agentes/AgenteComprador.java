package Agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * @author mauro
 *
 */
public class AgenteComprador extends Agent {
	
	private static int valor = 0;
	private static String nome;
	private static Boolean isVender = Boolean.FALSE;
	private static int cont = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = -543965660002408101L;

	public void setup() {
		System.out.println("Olá, sou o agente " + getLocalName());
		addBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
				ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
				msgRx.addReceiver(new AID("vendedor", false));
				msgRx.setContent(getLocalName() + " Olá, quero comprar uma TV você pode me vender?");
				send(msgRx);

				if (valor == 0) {
					Object[] obj = new Object[2];
					obj = getArguments();
					valor = Integer.valueOf(obj[1].toString());
					nome = getLocalName();
				} else {
					Object[] obj = new Object[2];
					obj = getArguments();
					if (Integer.valueOf(obj[1].toString()) > valor) {
						valor = Integer.valueOf(obj[1].toString());
						nome = getLocalName();
						isVender = Boolean.TRUE;
					}
				}
			}
		});
		addBehaviour(new CyclicBehaviour() {
			public void action() {

				// cont ++;
				// System.out.println(cont);

				ACLMessage msgRx = receive();
				if (msgRx != null && isVender) {
					System.out.println(msgRx.getContent() + nome);
				} else {
					block();
				}
			}
		});
	}
}
