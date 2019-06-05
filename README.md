# Folha de Pagamentos

Projeto compilado com openjdk 11.0.3 e escrito no Visual Studio Code.

## Instruções de execução

Fornecer as informações sempre sem acentos ou caracteres especiais. Valores monetários devem ser fornecidos com um ponto para representar casas decimais (e.g. 1500.30).<br>
Informar inicialmente o primeiro dia da semana do ano atual (terca, sabado), bem como a data de início do programa na forma 'dd/mm'.
### Adendos sobre as funcionalidades
#### Funcionalidade 1 

Os três métodos de pagamento são: cheque correios; cheque maos; deposito conta.<br>
As informações sindicais devem ser da forma 
0 ou 1 - se participa do sindicato ou não; ID do empregado no sindicato; taxa mensal em porcentagem.<br>

Exemplo de uso da funcionalidade 1:<br>
Joao da silva<br>
sao paulo<br>
comissionado<br>
1500 20<br>
cheque maos<br>
1 31 10
#### Funcionalidade 3
Exemplo dos horários de entrada e saída:<br>
7 30<br>
18 45

#### Funcionalidade 5

A identificação fornecida deve ser a do sindicato, presente na coluna de "informações do sindicato" do empregado.<br>
A taxa de serviço deve ser um valor avulso.

#### Funcionalidade 6

Deve-se responder com "y" aqueles detalhes que deseja-se mudar, e "n" aqueles que não.<br>
Ao alterar o contrato de um funcionário, deve-se necessariamente fornecer o novo atributo associado (salário, salário e % de comissão, salário/hora).<br>
Ao mudar o contrato de um funcionário, o sistema associará a agenda de pagamento default daquele contrato.

#### Funcionalidade 7

Essa deve ser a última funcionalidade realizada em cada dia, pois irá incrementar a data do sistema.

#### Funcionalidade 8

Undo/redo podem ser realizados sobre todas as funcionalidades, com exceção da 8 (ela mesma) e a 10.<br>
Redo só poderá ser utilizado se a funcionalidade utilizada anteriormente foi a 8.

#### Funcionalidade 10

Agendas de pagamento devem sempre seguir as formas:<br>
<li>mensal inteiro ou mensal $<br>
<li>semanal inteiro dia-da-semana(e.g. terca, sabado)<br>