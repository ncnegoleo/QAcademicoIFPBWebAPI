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

import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * Esta classe representará o recurso de Histórico da sessão Histórico Escolar
 * do sistema q-academico ifpb, tornando possivel recuperar suas caracteristicas
 * referentes ao aluno previamente 'logado'.</p>
 *
 * @author Leonardo Soares
 * @version 1.0, 18/12/2015
 * @since 1.0
 */
@XmlRootElement
public class HistoricoEscolar {

    // informações refrentes ao aluno
    private String matricula;
    private String nome;
    private String dataNascimento;
    private String documentoID;
    private String orgaoEmissor;
    private String dataEmissao;

    // infrmações referente ao curso
    private String curso;
    private String matrizCurricular;
    private String regime;
    private String periodocidade;

    // coeficientes
    private String coefRedimento;
    private String coefProgressao;

    // componenteCurricular
    private ArrayList<ComponenteCurricular> componenteCurricular;

    // carga horaria exigida/cumprida
    private String[] cHObrigatoria;
    private String[] cHOptativa;
    private String[] cHEstagio;
    private String[] cHComplementar;
    private String[] cHProjetoFinal;
    private String[] cHTotal;
    private String[] creditosTotal;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(String documentoID) {
        this.documentoID = documentoID;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatrizCurricular() {
        return matrizCurricular;
    }

    public void setMatrizCurricular(String matrizCurricular) {
        this.matrizCurricular = matrizCurricular;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getPeriodocidade() {
        return periodocidade;
    }

    public void setPeriodocidade(String periodocidade) {
        this.periodocidade = periodocidade;
    }

    public String getCoefRedimento() {
        return coefRedimento;
    }

    public void setCoefRedimento(String coefRedimento) {
        this.coefRedimento = coefRedimento;
    }

    public String getCoefProgressao() {
        return coefProgressao;
    }

    public void setCoefProgressao(String coefProgressao) {
        this.coefProgressao = coefProgressao;
    }

    public ArrayList<ComponenteCurricular> getComponenteCurricular() {
        return componenteCurricular;
    }

    public void setComponenteCurricular(ArrayList<ComponenteCurricular> componenteCurricular) {
        this.componenteCurricular = componenteCurricular;
    }

    public String[] getcHObrigatoria() {
        return cHObrigatoria;
    }

    public void setcHObrigatoria(String[] cHObrigatoria) {
        this.cHObrigatoria = cHObrigatoria;
    }

    public String[] getcHOptativa() {
        return cHOptativa;
    }

    public void setcHOptativa(String[] cHOptativa) {
        this.cHOptativa = cHOptativa;
    }

    public String[] getcHEstagio() {
        return cHEstagio;
    }

    public void setcHEstagio(String[] cHEstagio) {
        this.cHEstagio = cHEstagio;
    }

    public String[] getcHComplementar() {
        return cHComplementar;
    }

    public void setcHComplementar(String[] cHComplementar) {
        this.cHComplementar = cHComplementar;
    }

    public String[] getcHProjetoFinal() {
        return cHProjetoFinal;
    }

    public void setcHProjetoFinal(String[] cHProjetoFinal) {
        this.cHProjetoFinal = cHProjetoFinal;
    }

    public String[] getcHTotal() {
        return cHTotal;
    }

    public void setcHTotal(String[] cHTotal) {
        this.cHTotal = cHTotal;
    }

    public String[] getCreditosTotal() {
        return creditosTotal;
    }

    public void setCreditosTotal(String[] creditosTotal) {
        this.creditosTotal = creditosTotal;
    }

    @Override
    public String toString() {
        return "HistoricoEscolar{" + "matricula=" + matricula + ", nome="
                + nome + ", dataNascimento=" + dataNascimento
                + ", documentoID=" + documentoID + ", orgaoEmissor="
                + orgaoEmissor + ", dataEmissao=" + dataEmissao
                + ", curso=" + curso + ", matrizCurricular="
                + matrizCurricular + ", regime=" + regime + ", periodocidade="
                + periodocidade + ", coefRedimento=" + coefRedimento
                + ", coefProgressao=" + coefProgressao + ", componenteCurricular="
                + componenteCurricular + ", cHObrigatoria=" + Arrays.toString(cHObrigatoria)
                + ", cHOptativa=" + Arrays.toString(cHOptativa)
                + ", cHEstagio=" + Arrays.toString(cHEstagio)
                + ", cHComplementar=" + Arrays.toString(cHComplementar)
                + ", cHProjetoFinal=" + Arrays.toString(cHProjetoFinal)
                + ", cHTotal=" + Arrays.toString(cHTotal) + ", creditosTotal="
                + Arrays.toString(creditosTotal) + '}';
    }

}
