package br.com.dio.desafio.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Curso extends Conteudo{

    private int cargaHoraria;

    @Override
    public double calcularXp() {
        return XP_PADRAO * cargaHoraria;
    }


    @Override
    public String toString() {
        return "Curso\n" +
                "\ttitulo = " + getTitulo()  + '\n' +
                "\tDescrição = " + getDescricao() + '\n' +
                "\tCarga Horaria = " + cargaHoraria;
    }
}
