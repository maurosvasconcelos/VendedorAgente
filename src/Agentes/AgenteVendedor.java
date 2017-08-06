package Agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteVendedor extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setup() {
		System.out.println("Olá, sou o agente vendedor. Vou vender a minha TV para quem pagar mais.");
		addBehaviour(new CyclicBehaviour() {
			public void action() {
				Object[] valor = new Object[2];
		 		valor = getArguments();
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					System.out.println(msgRx.getContent());
					ACLMessage msgTx = msgRx.createReply();
					msgTx.setPerformative(ACLMessage.REFUSE);
					msgTx.setContent("A televisão foi vendida.");
					send(msgTx);
				} else {
					block();
				}
			}
		});
	}
}
