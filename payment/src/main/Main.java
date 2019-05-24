package main;
import java.util.Scanner;

public class Main {
	
	public static boolean buildCalendar(int [][]calendar, String firstDay) {
		int day = 0;
		switch(firstDay) {
			case "domingo":
				day = 1;
				break;
			case "segunda":
				day = 2;
				break;
			case "terca":
				day = 3;
				break;	
			case "quarta":
				day = 4;
				break;
			case "quinta":
				day = 5;
				break;
			case "sexta":
				day = 6;
				break;
			case "sabado":
				day = 7;
				break;
		}
		if(day == 0) return false;
		for(int i = 0; i < 13; i++) {//meses
			for(int j = 0; j < 32; j++) {//dias
				calendar[i][j] = day;
				//estabelecendo a quantidade de dias dos meses
				//e colocando flags na parte descartada da matriz
				if  (i == 0 || j == 0 || (i == 2 && j > 28)
					|| j > 30 && ((i < 8 && (i % 2) == 0)  
					|| (i > 8 && (i % 2) != 0))) calendar[i][j] = -1;
				else if(++day == 8) day = 1;
			}
		}
		return true;
	}

	public static void add(String []attributes, int index, String [][]employees) {
		
		for(int i = 0; i < 9; i++) {
			employees[index][i] = attributes[i];
		}
		System.out.println("Added employee's ID: " + employees[index][4]);
		return;
	}

	public static void nextPayment(String []employee, String [][]calendar) {
		
	}

	public static int findIndex(int size, String rmId, String [][]employees) {
		for(int i = 0; i < size; i++)
			if(employees[i][0] != null && 
			Integer.parseInt(employees[i][4]) == Integer.parseInt(rmId)) return i;
		System.out.println("employee not found");
		return -1;//employee not found
	}	
	
	public static int findName(int size, String name, String [][]employees) {
		for(int i = 0; i < size; i++)
			if(employees[i][0] != null && employees[i][0] == name) return i;
		System.out.println("employee not found");
		return -1;//employee not found
	}	

	public static void remove(int size, String rmId, String [][]employees) {
		int index = findIndex(size, rmId, employees);
		if(index == -1)
			System.out.println("Employee not found");
		else {
			while(employees[index + 1][0] != null) {
				for(int i = 0; i < 9; i++) {
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
		System.out.println("To be payed: " + employees[index][5]);
		System.out.println("Payment day: " + employees[index][6]);
		System.out.println("Payment method: " + employees[index][7]);
		System.out.println("Union's information: " + employees[index][8]);
		return;
	}

	public static void printAll(int size, String [][]employees) {
		for(int i = 0; i < size; i++) {
			System.out.println("||||||||||||||");
			getAt(i, employees);
		}
		return;
	}

	public static int timeToMinutes(int arrivalH, int arrivalM, int exitH, int exitM) {
		int hours = (exitH - arrivalH), mins, total;
		total = hours * 60;
		if(exitM < arrivalM) {
			mins = arrivalM - exitM;
			total -= mins;
		} else {
			mins = exitM - arrivalM;
			total += mins;
		}
		return total;
	}

	public static String[][] growMatrix(String [][]employees) {
		//returns a matrix 50% larger than the previous, with the same content
		int newSize = (int)(employees.length * 1.5) + 1;
		String [][]newMatrix = new String[newSize][6];
		System.arraycopy(employees, 0, newMatrix, 0, employees.length);
		return newMatrix;
	}

	public static double relativePay(double ratio, int minutes) {
		//receives the payment ratio per hour, and the minutes worked
		//returns the payment for the time worked
		double perMinute = ratio / 60.0;
		if(minutes <= (60 * 8)) return perMinute * minutes;
		else return ((minutes - (60 * 8)) * (1.5 * perMinute)) + ((60 * 8) * perMinute);
	}

	public static double commissionPayment(double commission, double price) {
		return (commission * price)/100;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Especifique o primeiro dia do ano (e.g. segunda, terca...)\n:");
		//calendario 12x31 com uma linha/coluna a mais para indexar mais facil
		int [][]calendar = new int[13][32];
		String firstDay = in.nextLine();
		if(!buildCalendar(calendar, firstDay)){
			System.out.println("Dia invalido.");
			return;
		}
		else System.out.println("Calendario criado.\n");

		String usage = "\t1- Adicao de um empregado\n"
						+"\t2- Remocao de um empregado\n"
						+"\t3- Lancar um cartao de ponto\n"
						+"\t4- Lancar um resultado de venda\n"
						+"\t5- Lancar uma taxa de servico\n"
						+"\t6- Alterar detalhes de um empregado\n"
						+"\t7- Rodar a folha de pagamento para hoje\n"
						+"\t8- Undo/redo\n"
						+"\t9- Agenda de pagamento\n"
						+"\t10- Criacao de novas agendas de pagamento\n";

		System.out.print("Acoes do programa:\n"
						+"\tl- Listar comandos do sistema\n"
						+"\t[1,10]- Comandos\n"
						+"\tq- Terminar programa\n");
		//employees[0] = nome
		//employees[1] = endereco
		//employees[2] = contrato
		//employees[3] = atributos associados ao contrato:
						//salario mensal; comissao por venda; salario por hora
						//comissionados tem salario mensal e comissao (separados por espaco)
		//employees[4] = ID
		//employees[5] = valor do proximo contracheque
		//employees[6] = data do prox pagamento
		//employees[7] = metodo de pagamento:
						//1: cheque correios, cheque maos, deposito conta
		//employees[8] = pertence ao sindicato (1 ou 0), identificacao no sindicato, taxa sindical
		
		int size = 4, employeeCount = 0, globalId = 1, index;
		String [][]employees = new String[size][9];
		String id;
		while(true) {
			System.out.print(":");
			String command = in.nextLine();

			switch(command.charAt(0)) {		//
				case 'q':					//
					return;					//FAZER A NEXTPAYMENT
				case 'l':					//
					System.out.print(usage);//
					break;
				case '1':
					String []attributes = new String[9];
					System.out.println("Forneca as seguintes informacoes:");
					System.out.print("\tNome: ");
					attributes[0] = in.nextLine();
					System.out.print("\tEndereco: ");
					attributes[1] = in.nextLine();
					System.out.print("\tContrato: ");
					attributes[2] = in.nextLine();
					switch(attributes[2]) {
						case "salaried":
							System.out.print("\tSalario mensal: ");
							attributes[3] = in.nextLine();
							attributes[5] = attributes[3];
							break;
						case "commissioned":
						System.out.print("\tSalario e % de comissao: ");
							attributes[3] = in.nextLine();
							//splitting by whitespaces and getting 
							//the first returned substring
							attributes[5] = attributes[3].split(" ")[0];
							break;
						case "hourly":
							System.out.print("\tSalario por hora: ");
							attributes[3] = in.nextLine();
							attributes[5] = "0";
							break;
					}
					attributes[4] = Integer.toString(globalId);
						
					System.out.print("\tMetodo de pagamento: ");
					attributes[7] = in.nextLine();
					System.out.print("\tInformacoes sindicais: ");
					attributes[8] = in.nextLine();

					if(employeeCount == size) {
						employees = growMatrix(employees);
						size = employees.length;
					}
					
					add(attributes, employeeCount, employees);
					employeeCount++;
					globalId++;
					break;
				case '2':
					System.out.print("Forneca o id do funcionario que deseja remover\n"
									+"\t:");
					String rmId = in.nextLine();
					remove(size, rmId, employees);
					employeeCount--;
					break;
				case '3':
					System.out.println("Forneca as seguintes informacoes");
					System.out.print("\tID do funcionario: ");
					id = in.nextLine();
					System.out.print("\tHorario de entrada (hora min): ");
					int arrivalH = in.nextInt();
					int arrivalM = in.nextInt();
					System.out.print("\tHorario de saida (hora min): ");
					int exitH = in.nextInt();
					int exitM = in.nextInt();
					int worked = timeToMinutes(arrivalH, arrivalM, exitH, exitM);
					index = findIndex(size, id, employees);
					employees[index][5] = Double.toString(Double.parseDouble(employees[index][5]) + 
									relativePay(Double.parseDouble(employees[index][3]), worked));
					id = in.nextLine();
					break;
				case '4':
					System.out.println("Forneca as seguintes informacoes");
					System.out.print("\tID do funcionario: ");
					id = in.nextLine();
					System.out.print("\tPreco da venda: ");
					double price = in.nextDouble();
					index = findIndex(size, id, employees);
					employees[index][5] = Double.toString(Double.parseDouble(employees[index][5]) + 
									commissionPayment(Double.parseDouble(
									employees[index][3].split(" ")[1]), price));

					id = in.nextLine();
					break;
				case '5': 
					System.out.println("Forneca as seguintes informacoes");
					System.out.print("\tNome do funcionario: ");
					String name = in.nextLine();
					System.out.print("\tTaxa de servico: ");
					double fee = in.nextDouble();
					index = findName(size, name, employees);
					employees[index][5] = Double.toString(Double.parseDouble(employees[index][5]) - fee);
					id = in.nextLine();
					break;
			}
			printAll(size, employees);
		}
	}
}
