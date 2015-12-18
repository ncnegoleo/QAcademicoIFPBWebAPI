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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Esta classe representará o recurso dos Componentes Curriculares 
 * da sessão Histórico Escolar do sistema q-academico ifpb, tornando possivel recuperar suas 
 * caracteristicas referentes ao aluno previamente 'logado'.</p>
 * 
 * @author Leonardo Soares
 * @version 1.0, 18/12/2015
 * @since 1.0
 */
@XmlRootElement
public class ComponenteCurricular {
    
    private String anoSemestre;
    private String periodo;
    private String componenteCurricular;
    private String tipo;
    private String cargaHoraria;
    private String creditos;
    private String nota;
    private String frequencia;
    private String situacao;

    public String getAnoSemestre() {
        return anoSemestre;
    }

    public void setAnoSemestre(String anoSemestre) {
        this.anoSemestre = anoSemestre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getComponenteCurricular() {
        return componenteCurricular;
    }

    public void setComponenteCurricular(String componenteCurricular) {
        this.componenteCurricular = componenteCurricular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "ComponenteCurricular{" + "anoSemestre=" + anoSemestre 
                + ", periodo=" + periodo + ", componenteCurricular=" 
                + componenteCurricular + ", tipo=" + tipo + ", cargaHoraria=" 
                + cargaHoraria + ", creditos=" + creditos + ", nota=" + nota 
                + ", frequencia=" + frequencia + ", situacao=" + situacao + '}';
    }
    
    
}
