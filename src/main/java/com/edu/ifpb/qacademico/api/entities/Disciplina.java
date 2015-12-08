/**
 * <p>
 * Este código é aberto a todos que quiserem usufruir desta API, assim como
 * estjam a disposição para comentar e melhora-lo. Se for utiliza-lo, não deixe
 * de dar os devidos creditos a
 * <p>
 * <b>Leonardo Soares Rodrigues</b>
 * <p>
 * Email: leonardo.soares.ws@gmail.com
 */
package com.edu.ifpb.qacademico.api.entities;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Esta classe representará o recurso de Disciplina da sessão Diario 
 * do sistema q-academico ifpb, tornando possivel recuperar suas 
 * caracteristicas referentes ao aluno previamente 'logado'.</p>
 * 
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
@XmlRootElement
@XmlType(propOrder = {"codigo","nome","professor",
        "cargaHoraria","numeroAulasPrevisto",
        "numeroAulasMinistradas", "faltas",
        "percentFrequenciaObrig", "percentPresenca",
        "atividades", "provaFinal"})
public class Disciplina {
    
    private String codigo;
    private String nome;
    private String cargaHoraria;
    private String professor;
    private String numeroAulasPrevisto;
    private String numeroAulasMinistradas;
    private String faltas;
    private String percentFrequenciaObrig;
    private String percentPresenca;
    private String[] atividades;
    private String provaFinal;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getNumeroAulasPrevisto() {
        return numeroAulasPrevisto;
    }

    public void setNumeroAulasPrevisto(String numeroAulasPrevisto) {
        this.numeroAulasPrevisto = numeroAulasPrevisto;
    }

    public String getNumeroAulasMinistradas() {
        return numeroAulasMinistradas;
    }

    public void setNumeroAulasMinistradas(String numeroAulasMinistradas) {
        this.numeroAulasMinistradas = numeroAulasMinistradas;
    }

    public String getFaltas() {
        return faltas;
    }

    public void setFaltas(String faltas) {
        this.faltas = faltas;
    }

    public String getPercentFrequenciaObrig() {
        return percentFrequenciaObrig;
    }

    public void setPercentFrequenciaObrig(String percentFrequenciaObrig) {
        this.percentFrequenciaObrig = percentFrequenciaObrig;
    }

    public String getPercentPresenca() {
        return percentPresenca;
    }

    public void setPercentPresenca(String percentPresenca) {
        this.percentPresenca = percentPresenca;
    }

    public String[] getAtividades() {
        return atividades;
    }

    public void setAtividades(String[] atividades) {
        this.atividades = atividades;
    }
    
    public String getProvaFinal() {
        return provaFinal;
    }

    public void setProvaFinal(String provaFinal) {
        this.provaFinal = provaFinal;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "codigo=" + codigo + ", nome=" + nome 
                + ", cargaHoraria=" + cargaHoraria + ", professor=" 
                + professor + ", numeroAulasPrevisto=" + numeroAulasPrevisto 
                + ", numeroAulasMinistradas=" + numeroAulasMinistradas 
                + ", faltas=" + faltas + ", percentFrequenciaObrig=" 
                + percentFrequenciaObrig + ", percentPresenca=" 
                + percentPresenca + ", atividades=" + Arrays.toString(atividades) 
                + ", provaFinal=" + provaFinal + '}';
    }

    
}
