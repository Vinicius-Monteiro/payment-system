package main;
import java.util.Scanner;

public class Main {
	
	public static void buildCalendar(int [][]calendar, String firstDay) {
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
	}

	public static void add(String []attributes, int index, String [][]employees) {
		
		for(int i = 0; i < 6; i++) {
			employees[index][i] = attributes[i];
		}
		System.out.println("Added employee's ID: " + employees[index][4]);
		return;
	}

	public static int findIndex(int size, String rmId, String [][]employees) {
		for(int i = 0; i < size; i++) {
			if(employees[i][0] != null && 
			Integer.parseInt(employees[i][4]) == Integer.parseInt(rmId)) return i;
		}
		System.out.println("employee not found");
		return -1;//employee not found
	}	

	public static void remove(int size, String rmId, String [][]employees) {
		int index = findIndex(size, rmId, employees);
		if(index == -1)
			System.out.println("Employee not found");
		else {
			while(employees[index + 1][0] != null) {
				for(int i = 0; i < 6; i++) {
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

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Especifique o primeiro dia do ano (e.g. segunda, terca...)\n:");
		//calendário 12x31 com uma linha/coluna a mais para indexar mais facil
		int [][]calendar = new int[13][32];
		String firstDay = in.nextLine();
		buildCalendar(calendar, firstDay);

		System.out.println("Calendário criado.\n");

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
		
		int size = 4, employeeCount = 0, globalId = 1;
		String [][]employees = new String[size][6];
		while(true) {
			System.out.print(":");
			String command = in.nextLine();

			switch(command.charAt(0)) {
				case 'q':
					return;
				case 'l':
					System.out.print(usage);
					break;
				case '1':
					String []attributes = new String[6];
					System.out.println("Forneça as seguintes informações:");
					System.out.print("\tNome: ");
					attributes[0] = in.nextLine();
					System.out.print("\tEndereço: ");
					attributes[1] = in.nextLine();
					System.out.print("\tContrato: ");
					attributes[2] = in.nextLine();
					System.out.print("\tAtributo associado: ");
					attributes[3] = in.nextLine();
					attributes[4] = Integer.toString(globalId);
					attributes[5] = "0";
					if(attributes[2] == "salaried" || attributes[2] == "commissioned") {
						attributes[5] = attributes[3];
					}

					if(employeeCount == size) {
						employees = growMatrix(employees);
						size = employees.length;
					}
					
					add(attributes, employeeCount, employees);
					employeeCount++;
					globalId++;
					break;
				case '2':
					System.out.print("Forneça o id do funcionario que deseja remover\n"
									+"\t:");
					String rmId = in.nextLine();
					remove(size, rmId, employees);
					employeeCount--;
					break;
				case '3':
					System.out.println("Forneça as seguintes informações");
					System.out.print("\tID do funcionário: ");
					String id = in.next();//MUDAR PARA INT POIS FIZ PARSEINT JA NO FINDINDEX
					System.out.print("\tHorario de entrada (hora min): ");
					int arrivalH = in.nextInt();
					int arrivalM = in.nextInt();
					System.out.print("\tHorario de saida (hora min): ");
					int exitH = in.nextInt();
					int exitM = in.nextInt();
					int worked = timeToMinutes(arrivalH, arrivalM, exitH, exitM);
					System.out.println("Id = " + id +  ", chegada = " + arrivalH + ":" + arrivalM + " saida = " + exitH + ":" + exitM);
					System.out.println("Tempo no trabalho = " + worked);
					int index = findIndex(size, id, employees);
					employees[index][5] = Double.toString(Double.parseDouble(employees[index][5]) + 
									relativePay(Double.parseDouble(employees[index][3]), worked));
					id = in.nextLine();
					break;
			}
			//printAll(size, employees);
		}
	}
}
