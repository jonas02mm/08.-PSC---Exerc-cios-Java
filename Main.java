import java.util.ArrayList;
import java.util.Scanner;

class Empregado {
    private String nome;
    private int idade;
    private double salario;

    public Empregado(String nome, int idade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", Salário: R$ " + String.format("%.2f", salario);
    }

    public void promover() {
        if (idade > 18) {
            aumentarSalario(25.0);
            System.out.println(nome + " foi promovido(a) e seu salário agora é R$ " + String.format("%.2f", salario));
        } else {
            System.out.println(nome + " não pode ser promovido(a) por ser menor de 18 anos.");
        }
    }

    public void aumentarSalario(double percentual) {
        salario += salario * (percentual / 100);
        System.out.println(nome + " teve um aumento de " + percentual + "% no salário.");
    }

    public void demitir(int motivo) {
        switch (motivo) {
            case 1:
                System.out.println(nome + " foi demitido(a) por justa causa. Deve cumprir aviso prévio.");
                break;
            case 2:
                double multa = salario * 0.40;
                System.out.println(nome + " foi demitido(a) por decisão do empregador. Receberá uma multa de R$ " + String.format("%.2f", multa));
                break;
            case 3:
                double salarioAposentadoria;
                if (salario >= 1000 && salario < 2000) {
                    salarioAposentadoria = 1500;
                } else if (salario >= 2000 && salario < 3000) {
                    salarioAposentadoria = 2500;
                } else if (salario >= 3000 && salario < 4000) {
                    salarioAposentadoria = 3500;
                } else {
                    salarioAposentadoria = 4000;
                }
                System.out.println(nome + " se aposentou e receberá um salário de aposentadoria de R$ " + String.format("%.2f", salarioAposentadoria));
                break;
            default:
                System.out.println("Motivo de demissão inválido.");
        }
    }

    public void fazerAniversario() {
        idade++;
        System.out.println(nome + " fez aniversário e agora tem " + idade + " anos.");
    }
}

public class Main {
    private static ArrayList<Empregado> empregados = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarNovoEmpregado();
                    break;
                case 2:
                    promoverEmpregado();
                    break;
                case 3:
                    aumentarSalarioEmpregado();
                    break;
                case 4:
                    demitirEmpregado();
                    break;
                case 5:
                    fazerAniversarioEmpregado();
                    break;
                case 6:
                    mostrarDetalhesEmpregados();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
    }

    private static void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Criar novo empregado");
        System.out.println("2. Promover empregado");
        System.out.println("3. Aumentar salário do empregado");
        System.out.println("4. Demitir empregado");
        System.out.println("5. Fazer aniversário do empregado");
        System.out.println("6. Mostrar detalhes dos empregados");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarNovoEmpregado() {
        System.out.print("Nome do empregado: ");
        String nome = scanner.nextLine();
        System.out.print("Idade do empregado: ");
        int idade = scanner.nextInt();
        System.out.print("Salário do empregado: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();  // Consumir nova linha

        Empregado novoEmpregado = new Empregado(nome, idade, salario);
        empregados.add(novoEmpregado);
        System.out.println("Empregado criado com sucesso!");
    }

    private static void promoverEmpregado() {
        Empregado empregado = selecionarEmpregado();
        if (empregado != null) {
            empregado.promover();
        }
    }

    private static void aumentarSalarioEmpregado() {
        Empregado empregado = selecionarEmpregado();
        if (empregado != null) {
            System.out.print("Percentual de aumento: ");
            double percentual = scanner.nextDouble();
            scanner.nextLine();
            empregado.aumentarSalario(percentual);
        }
    }

    private static void demitirEmpregado() {
        Empregado empregado = selecionarEmpregado();
        if (empregado != null) {
            System.out.print("Motivo da demissão (1: Justa causa, 2: Decisão do empregador, 3: Aposentadoria): ");
            int motivo = scanner.nextInt();
            scanner.nextLine();
            empregado.demitir(motivo);
            if (motivo == 1) {
                empregados.remove(empregado);
                System.out.println(empregado.getNome() + " foi removido(a) da lista de empregados.");
            }
        }
    }

    private static void fazerAniversarioEmpregado() {
        Empregado empregado = selecionarEmpregado();
        if (empregado != null) {
            empregado.fazerAniversario();
        }
    }

    private static void mostrarDetalhesEmpregados() {
        if (empregados.isEmpty()) {
            System.out.println("Nenhum empregado cadastrado.");
        } else {
            for (Empregado empregado : empregados) {
                System.out.println(empregado);
            }
        }
    }

    private static Empregado selecionarEmpregado() {
        if (empregados.isEmpty()) {
            System.out.println("Nenhum empregado cadastrado.");
            return null;
        }

        System.out.println("Lista de empregados:");
        for (int i = 0; i < empregados.size(); i++) {
            System.out.println((i + 1) + ". " + empregados.get(i).getNome());
        }
        System.out.print("Escolha o número do empregado: ");
        int indice = scanner.nextInt();
        scanner.nextLine();  

        if (indice < 1 || indice > empregados.size()) {
            System.out.println("Número inválido.");
            return null;
        }

        return empregados.get(indice - 1);
    }
}
