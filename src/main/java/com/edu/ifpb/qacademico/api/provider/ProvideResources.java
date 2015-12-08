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
package com.edu.ifpb.qacademico.api.provider;

import com.edu.ifpb.qacademico.api.entities.Disciplina;
import com.edu.ifpb.qacademico.api.validation.DifferentPageException;
import java.util.ArrayList;

/**
 * <p> Provedor de Recursos
 * 
 * <p>Esta classe é responsavel por recuperar os dados do q-academico ifpb, 
 * acessando as páginas via <b>HtmlUnit</b>, fazendo login e recuperando as 
 * informações para a API.</p>
 * <pre>@see <a href="http://www.htmlunit.sourceforge.net">htmlunit.sourceforge
 * .net</a></pre>
 * 
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
public class ProvideResources {

    private AlunoAuthenticationSingleton aas;
    private RequestDiarioDisciplinas rddps;

    /**
     * Este método é responsável acessar sessão Diário do q-academico ifpb,
     * e recuperar as informações das disciplinas e provê-las para a API.
     * 
     * @param matricula do aluno.
     * @param senha do aluno.
     * @param ano do período a ser recuperado.
     * @param semestre (ou perdíodo) a ser recuperado.
     * @return lista de disciplinas segundo os parametros.
     */
    public ArrayList<Disciplina> getDisciplinas(String matricula, String senha,
            String ano, String semestre) {
        requestDisciplinasByPeriodoConn(matricula, senha, ano, semestre);
        
        try {
            // tenta fazer a requsição para recuperar as informações
            rddps.request();
        } catch (DifferentPageException ex) {
            /* Se a página a ser recuperada mudar (possivelmente pelo timeout 
            da sessão do site) é refeita a conexão com o site e refeita a 
            requesição */
            requestDisciplinasByPeriodoConn(matricula, senha, ano, semestre);
            try{rddps.request();} catch (Exception e) {}
        }
        
        return rddps.requestDisciplinas();
    }
    
    /* este método serve para fazer o login e acessar a sessão Diário no
    q academico ifpb */
    private void requestDisciplinasByPeriodoConn(String matricula,
            String senha, String ano, String semestre) {
        aas = AlunoAuthenticationSingleton.getInstance(matricula, senha);
        rddps = new RequestDiarioDisciplinas(aas, ano, semestre);
    }
}
