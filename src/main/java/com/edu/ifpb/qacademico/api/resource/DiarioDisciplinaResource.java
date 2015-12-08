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
package com.edu.ifpb.qacademico.api.resource;

import com.edu.ifpb.qacademico.api.entities.Disciplina;
import com.edu.ifpb.qacademico.api.provider.ProvideResources;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Recursos de Diario: Disciplinas
 *
 * <p>
 * Esta classe fornece os recursos das disciplinas localizadas na sessão diário
 * do q-academico ifpb na API usando REST, tornando possivel recuperar as
 * informações das disciplinas no formato JSON.
 *
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
@Path("/diario")
public class DiarioDisciplinaResource {

    /**
     * <p>
     * Este método recupera as disciplinas pelo período.
     * <p>
     * Expemplo de URL:
     * https://...qacademicoifpb-api/diario/2015/2/all;matricula=123;senha=123
     * <br/>
     * Onde, 2015 = ano, 1 = semestre, all = todos os valores são recuperados,
     * matricula = matricula do aluno, senha = senha do aluno.
     *
     * @param matricula do aluno
     * @param senha do aluno
     * @param ano do periodo a ser recuperado
     * @param semestre do período a ser recuperado
     * @return ArrayList das disciplinas
     */
    @GET
    @Path("/{ano}/{semestre}/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=iso-8859-1")
    public ArrayList<Disciplina> getDisciplinasByPeriodo(
            @MatrixParam("matricula") String matricula,
            @MatrixParam("senha") String senha,
            @PathParam("ano") String ano, @PathParam("semestre") String semestre) {
        ProvideResources pds = new ProvideResources();
        return (ArrayList<Disciplina>) pds.getDisciplinas(matricula, senha, ano, semestre);
    }

    /**
     * <p>
     * Este método recupera uma disciplina pelo período localizada por um
     * indice.
     * <p>
     * Expemplo de URL:
     * https://...qacademicoifpb-api/diario/2015/2/4;matricula=123;senha=123
     * <br/>
     * Onde, 2015 = ano, 1 = semestre, 4 = somente o valor com o indice 4,
     * matricula = matricula do aluno, senha = senha do aluno.
     *
     * @param matricula do aluno
     * @param senha do aluno
     * @param ano do periodo a ser recuperado
     * @param semestre do período a ser recuperado
     * @param indice da disciplina
     * @return Object de uma disciplina identificada pelo indice
     */
    @GET
    @Path("/{ano}/{semestre}/{indice}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=iso-8859-1")
    public Disciplina getDisciplinaByPeriodo(
            @MatrixParam("matricula") String matricula,
            @MatrixParam("senha") String senha,
            @PathParam("ano") String ano, @PathParam("semestre") String semestre,
            @PathParam("indice") int indice) {
        ProvideResources pds = new ProvideResources();
        return pds.getDisciplinas(matricula, senha, ano, semestre).get(indice);
    }

    /**
     * <p>
     * Este método recupera as disciplinas do periodo atual.
     * <p>
     * Expemplo de URL:
     * https://...qacademicoifpb-api/diario/atuais/all;matricula=123;senha=123
     * <br/>
     * Onde, atuais = disciplinas atuais, all = todos os valores são
     * recuperados, matricula = matricula do aluno, senha = senha do aluno.
     *
     * @param matricula do aluno
     * @param senha do aluno
     * @return ArrayList das disciplinas
     */
    @GET
    @Path("/atuais/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=iso-8859-1")
    public ArrayList<Disciplina> getDisciplinasAtuais(
            @MatrixParam("matricula") String matricula,
            @MatrixParam("senha") String senha) {
        ProvideResources pds = new ProvideResources();
        return (ArrayList<Disciplina>) pds.getDisciplinas(matricula, senha, "", "");
    }

    /**
     * <p>
     * Este método recupera uma disciplina atual localizada por um indice.
     * <p>
     * Expemplo de URL:
     * https://...qacademicoifpb-api/diario/ayuais/4;matricula=123;senha=123
     * <br/>
     * Onde, atuais = disciplinas atuais, 4 = somente o valor com o indice 4,
     * matricula = matricula do aluno, senha = senha do aluno.
     *
     * @param matricula do aluno
     * @param senha do aluno
     * @param indice da disciplina
     * @return Object de uma disciplina identificada pelo indice
     */
    @GET
    @Path("/atuais/{indice}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=iso-8859-1")
    public Disciplina getDisciplinaAtuais(
            @MatrixParam("matricula") String matricula,
            @MatrixParam("senha") String senha,
            @PathParam("indice") int indice) {
        ProvideResources pds = new ProvideResources();
        return pds.getDisciplinas(matricula, senha, "", "").get(indice);
    }
}
