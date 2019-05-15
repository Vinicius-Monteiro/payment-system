import java.util.Scanner;

public class Main {
	public static void add(String name, String address, String contract, String attribute, String id, int index, String [][]employees) {
		employees[index][0] = name;
		employees[index][1] = address;
		employees[index][2] = contract;
		employees[index][3] = attribute;
		employees[index][4] = id;
		return;
	}

	public static int findIndex(int size, String name, String [][]employees) {
		for(int i = 0; i < size; i++) {
			if(employees[i][0] != null && employees[i][0] == name) return i;
		}
		return -1;//employee not found
	}	

	public static void remove(int size, String name, String [][]employees) {
		int index = findIndex(size, name, employees);
		if(index == -1)
			System.out.println("Employee not found");
		else {
			while(employees[index + 1][0] != null) {
				for(int i = 0; i < 5; i++) {
					employees[index][i] = employees[index + 1][i];
					employees[index + 1][i] = null;
				}
				index++;
			}
		}
	}

	public static void getAt(int index, String [][]employees) {
		System.out.println("Name: " + employees[index][0]);
		System.out.println("Address: " + employees[index][1]);
		System.out.println("Contract: " + employees[index][2]);
		System.out.println("Attribute: " + employees[index][3]);
		System.out.println("Id: " + employees[index][4]);
		return;
	}

	public static void printAll(int size, String [][]employees) {
		for(int i = 0; i < size; i++) {
			System.out.println("||||||||||||||");
			getAt(i, employees);
		}
		return;
	}

	public static String[][] growMatrix(String [][]employees) {//returns a matrix 50% larger than the previous, with the same content
		int newSize = (int)(employees.length * 1.5) + 1;
		String [][]newMatrix = new String[newSize][5];
		System.arraycopy(employees, 0, newMatrix, 0, employees.length);
		return newMatrix;
	}

	public static void main(String[] args) {
		String usage = "\t1- Adiçao de um empregado\n"
						+"\t2- Remoçao de um empregado\n"
						+"\t3- Lançar um cartao de ponto\n"
						+"\t4- Lançar um resultado de venda\n"
						+"\t5- Lançar uma taxa de serviço\n"
						+"\t6- Alterar detalhes de um empregado\n"
						+"\t7- Rodar a folha de pagamento para hoje\n"
						+"\t8- Undo/redo\n"
						+"\t9- Agenda de pagamento\n"
						+"\t10- Criação de novas agendas de pagamento\n";

		System.out.print("Açoes do programa:\n"
						+"\tl- Listar comandos do sistema\n"
						+"\t[1,10]- Comandos\n"
						+"\tq- Terminar programa\n");
		
		int size = 20, employeeCount = 0, globalId = 1;
		String [][]employees = new String[size][5];
		while(true) {
			System.out.print(":");
			Scanner in = new Scanner(System.in);
			String command = in.nextLine();

			switch(command.charAt(0)) {
				case 'q':
					return;
				case 'l':
					System.out.print(usage);
					break;
				case '1':
					System.out.println("Forneça as seguintes informações:");
					System.out.print("\tNome: ");
					String name = in.nextLine();
					System.out.print("\tEndereço: ");
					String address = in.nextLine();
					System.out.print("\tContrato: ");
					String contract = in.nextLine();
					System.out.print("\tAtributo associado: ");
					String attribute = in.nextLine();
					
					if(employeeCount == size) {
						employees = growMatrix(employees);
						size = employees.length;
					}
					
					add(name, address, contract, attribute, Integer.toString(globalId), employeeCount, employees);
					employeeCount++;
					globalId++;
					break;
				case '2':
					System.out.print("Forneça o nome do funcionario que deseja remover\n"
									+"\t:");
					String name2 = in.nextLine();
					remove(size, name2, employees);
					employeeCount--;

			}
			//printAll(size, employees);
		}
	}
}