package br.com.dio.desafio.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class Mentoria extends Conteudo{

    private LocalDate data;

    @Override
    public double calcularXp() {
        return XP_PADRAO + 20d;
    }


    @Override
    public String toString() {
        return "Mentoria\n" +
                "\ttitulo = " + getTitulo()  + '\n' +
                "\tDescrição = " + getDescricao() + '\n' +
                "\tData = " + data ;
    }
}
