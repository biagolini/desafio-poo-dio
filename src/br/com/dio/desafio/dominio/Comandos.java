package br.com.dio.desafio.dominio;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class Comandos {
    Scanner scanner = new Scanner(System.in);

    public void corrida(){
        Integer opcao = 0;
        do{
            System.out.printf("Escolha uma opção:\n" +
                                "\t1) Instanciar um Curso;\n"+
                                "\t2) Instanciar uma Mentoria;\n"+
                                "\t3) Instanciar um Bootcamp;\n"+
                                "\t4) Instanciar Devs;\n" +
                                "\t5) Simular Bootcamp com inscritos e calculos de Xp;\n" +
                                "\t0) Finalizar/Sair;\n"  );
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Curso cuso = operacao1();
                    System.out.println("Os dados do seu curso são:");
                    System.out.println(cuso.toString());
                    break;
                case 2:
                    Mentoria mentoria = operacao2();
                    System.out.println("Os dados da mentoria são:");
                    System.out.println(mentoria.toString());
                    break;
                case 3:
                    Bootcamp bootcamp = operacao3();
                    System.out.println("Os dados do Bootcamp são:");
                    System.out.println(bootcamp.toString());
                    break;
                case 4:
                List<Dev> devs = operacao4();
                System.out.println("A lista de devs são:");
                    for (int i = 0; i < devs.size(); i++) {
                        System.out.println(devs.get(i).getNome());
                    }
                break;
                case 5: this.operacao5(); break;
                case 0: System.out.printf("Obrigado por utilizar nosso programa, até logo;\n"  ); break;
                default:System.out.printf("Opção inválida;\n");
            }

            System.out.printf("\n\nDigite enter para proseguir;\n");
            scanner.nextLine();
            String str= scanner.nextLine();
        } while (opcao!=0);
    }

    private Curso operacao1() {
        Curso curso = new Curso();
        String titulo, descricao;
        Integer cargaHoraria;

        System.out.println("Informe o titulo do curso:");
        titulo= scanner.nextLine();
        curso.setTitulo(titulo);

        System.out.println("Informe a descrição do curso:");
        descricao= scanner.nextLine();
        curso.setDescricao(descricao);

        System.out.println("Informe a carga horaria do curso:");
        cargaHoraria = scanner.nextInt();
        scanner.nextLine();
        curso.setCargaHoraria(cargaHoraria);

        return  curso;
    }


    private Mentoria operacao2() {
        Mentoria mentoria = new Mentoria();
        String titulo, descricao;

        System.out.println("Informe o titulo da mentoria:");
        titulo= scanner.nextLine();
        mentoria.setTitulo(titulo);

        System.out.println("Informe a descrição da mentoria:");
        descricao= scanner.nextLine();
        mentoria.setDescricao(descricao);

        mentoria.setData(LocalDate.now());

        return mentoria;

    }

    private Bootcamp operacao3() {
        Integer nCursos, nMentorias, i;

        System.out.printf("Quantos Cursos você irá inscrever nesse Bootcamp?\n");
        nCursos = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("Quantos Mentorias você irá inscrever nesse Bootcamp?\n");
        nMentorias = scanner.nextInt();
        scanner.nextLine();

        System.out.println("# 01: INFORME DADOS DE CURSO:");
        List<Curso> cursos = new ArrayList<>();

        for (i=0; i<nCursos; i++ ){
            System.out.printf("\t --- %dº curso ---\n",i+1);
            Curso atual = this.operacao1();
            cursos.add(atual);
        }

        System.out.println("# 02: INFORME DADOS DE MENTORIA:");
        List<Mentoria> mentorias = new ArrayList<>();
        for (i=0; i<nMentorias; i++ ){
            System.out.printf("\t --- %dº mentoria ---\n",i+1);
            Mentoria atual = this.operacao2();
            mentorias.add(atual);
        }

        System.out.println("# 03: INFORME DADOS DO BOOTCAMP:");
        Bootcamp bootcamp = new Bootcamp();
        String titulo, descricao;

        System.out.println("Informe o titulo do bootcamp:");
        titulo= scanner.nextLine();
        bootcamp.setNome(titulo);

        System.out.println("Informe a descrição do bootcamp:");
        descricao= scanner.nextLine();
        bootcamp.setDescricao(descricao);

        for (i=0; i<nCursos; i++ ){
            bootcamp.getConteudos().add(cursos.get(i));
        }

        for (i=0; i<nMentorias; i++ ){
            bootcamp.getConteudos().add(mentorias.get(i));
        }

        return bootcamp;
    }

    private List<Dev> operacao4() {
        String nome;
        Integer nDevs, i;

        System.out.printf("Quantos Devs você irá inscrever?\n");
        nDevs = scanner.nextInt();
        scanner.nextLine();

        List<Dev> devs = new ArrayList<>();
        for (i=0; i<nDevs; i++ ){
            System.out.printf("Informe um nome:\n");
            nome= scanner.nextLine();
            devs.add(new Dev());
            devs.get(i).setNome(nome);
        }
        return devs;
    }

    private void operacao5() {
        System.out.println("# 03: INFORME DADOS DO BOOTCAMP:");
        Bootcamp bootcamp = this.operacao3();
        String titulo, descricao;

        System.out.println("# 04: INFORME DADOS DO DEV:");
        List<Dev> devs = this.operacao4();

        System.out.println("# 05: PROCESSAR   XP:");

        Integer nDevs, i;

        nDevs = devs.size();

        for (i=0; i<nDevs; i++ ){
            System.out.printf("Para inscrever o Dev:\n" + devs.get(i).getNome() + "no Bootcamp, digite 'S':\n");
            String opcao = scanner.nextLine().toUpperCase();

            if(opcao.equals("S")){
                devs.get(i).inscreverBootcamp(bootcamp);
                devs.get(i).progredir();
                System.out.println("XP = " + devs.get(i).calcularTotalXp());
            }
        }
    }
}
