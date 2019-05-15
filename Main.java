import java.util.Scanner;

public class Main {
	public static boolean add(String name, String address, String contract, String attribute, String id, int index, String [][]employees) {
		if(employees[index][0] != null) return false;
		employees[index][0] = name;
		employees[index][1] = address;
		employees[index][2] = contract;
		employees[index][3] = attribute;
		employees[index][4] = id;
		return true;
	}

	public static void getAt(int index, String [][]employees) {
		System.out.println("Name: " + employees[index][0]);
		System.out.println("Address: " + employees[index][1]);
		System.out.println("Contract: " + employees[index][2]);
		System.out.println("Attribute: " + employees[index][3]);
		System.out.println("Id: " + employees[index][4]);
		return;
	}

	public static String[][] growMatrix(int size, String [][]employees) {//returns a matrix 20% larger than the previous, copying its content
		int newSize = (int)(size * 1.2), i = 0;
		String [][]newString = new String[newSize][5];
		while(employees[i][0] != null && i < size) {
			newString[i][0] = employees[i][0];
			newString[i][1] = employees[i][1];
			newString[i][2] = employees[i][2];
			newString[i][3] = employees[i][3];
			newString[i][4] = employees[i][4];
		}
		return newString;
	}
	public static void main(String[] args) {
		String usage = "Forneça um comando (1-10):\n"
						+"\t1- Adiçao de um empregado\n"
						+"\t2- Remoçao de um empregado\n"
						+"\t3- Lançar um cartao de ponto\n"
						+"\t4- Lançar um resultado de venda\n"
						+"\t5- Lançar uma taxa de serviço\n"
						+"\t6- Alterar detalhes de um empregado\n"
						+"\t7- Rodar a folha de pagamento para hoje\n"
						+"\t8- Undo/redo\n"
						+"\t9- Agenda de pagamento\n"
						+"\t10- Criação de novas agendas de pagamento\n";

		int size = 50, employeeCount = 0, globalId = 0;
		String [][]employees = new String[size][5];
		
		System.out.print(usage + ":");
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();

		switch(command.charAt(0)) {
			case '1':
				System.out.println("Forneça as seguintes informações separadas em linhas separadas:\n"
								+"\tNome, endereço, tipo de contrato (hourly, salaried, commissioned)\n"
								+"\te os atributos associados (salario horario, salario mensal, comissao)");
				String name = in.nextLine();
				String address = in.nextLine();
				String contract = in.nextLine();
				String attribute = in.nextLine();
				
				if(employeeCount == size) {
					employees = growMatrix(size, employees);
					size = (int)(size * 1.2);
				}
				
				add(name, address, contract, attribute, Integer.toString(globalId), employeeCount, employees);
				employeeCount++;
				globalId++;
				break;
		}
	}
}