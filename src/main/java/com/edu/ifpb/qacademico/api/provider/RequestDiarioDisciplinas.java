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
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Convocador de Disciplinas
 *
 * <p>
 * Esta classe é responsável pelo acesso e recuperação dos dados das disciplinas
 * na sessão Diario do q-academico ifpb. Informando o objeto autenticador, o ano
 * e o periodo é possivel recuperar as caracteristicas das disciplinas
 * referentes ao aluno logado de acordo com periodo solicitado.
 * <p>
 * As manipluações dos elementos na página são feitas com auxilio da API
 * <b>HtmlUnit</b> que identifica os elementos e efetua as requisições no site.
 * <pre>@see <a href="http://www.htmlunit.sourceforge.net">htmlunit.sourceforge
 * .net</a></pre>
 *
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
public class RequestDiarioDisciplinas {

    private String anoPeriodo;
    private String semestrePeriodo;

    /* As constates abaixo são usadas para identificação de conteúdos
     especificos na página, de fato textos com os valores referidos.*/
    private final String NOTA_UNICA = "Nota Única";
    private final String PROVA_FINAL = "Prova Final";

    private HtmlPage currentPage;
    private final AlunoAuthenticationSingleton alunoAuthenticationSingleton;

    private final String PAGE_URL
            = "https://academico.ifpb.edu.br/qacademico/index.asp?t=2071";
    private final String PAGE_TITLE = "Q-Acadêmico Web para IF-PBMinhas "
            + "Disciplinas Atualmente em Curso";

    /**
     * Construtor da classe que instancia um novo requerimento das disciplinas.
     *
     * @param alunoAuthenticationSingleton, o autenticador(efetuado através do
     * login do aluno).
     * @param anoPeriodo, ano no qual se deseja recuperar as disciplinas.
     * @param semestrePeriodo, semestre/período no qual se deseja recuperar as
     * disciplinas.
     */
    public RequestDiarioDisciplinas(
            AlunoAuthenticationSingleton alunoAuthenticationSingleton,
            String anoPeriodo, String semestrePeriodo) {

        this.alunoAuthenticationSingleton = alunoAuthenticationSingleton;
        this.anoPeriodo = anoPeriodo;
        this.semestrePeriodo = semestrePeriodo;
    }

    /**
     * Este método garante a conexão com a página da sessão Diario do
     * q-academico ifpb.
     *
     * @return true se a conexão for bem sucedida.
     * @throws DifferentPageException, caso a página não seja a página
     * requisitada!
     */
    public boolean request() throws DifferentPageException {
        try {

            // Recupera a página a partir de uma url e seta no autenticador
            currentPage = this.alunoAuthenticationSingleton.getWebClient()
                    .getPage(PAGE_URL);

            /* Compara os titulos das páginas, se o do página recuperada não 
             for igual a págia solicitada é lançada uma exceção*/
            if (!currentPage.getTitleText().endsWith(PAGE_TITLE)) {
                throw new DifferentPageException("");
            }

            // Recupera o formulario e o selcte interno a esse formulario
            HtmlForm form = currentPage.getFormByName("frmConsultar");
            HtmlSelect select = form.getSelectByName("ANO_PERIODO2");

            // Lista as opções do select recuperado
            List<HtmlOption> options = select.getOptions();

            /* Procura nas opções qual se o conteudo select é igual a string 
             formada pelo "{ano} / {periodo}" se existir, essa opção é 
             selecionada */
            for (HtmlOption option : options) {
                if (option.getText().equals(anoPeriodo + " / " + semestrePeriodo)) {
                    select.setSelectedAttribute(option, true);
                }
            }

            // clica no select
            select.click();

            /* Recupera o botão da página que faz a submissão do select 
             configurado antes */
            HtmlSubmitInput button = form.getFirstByXPath("//input[@value='ok']");
            this.currentPage = button.click();

            // passar para java loggin
            System.out.println("PageUrl = [" + currentPage.getUrl() + "]");

        } catch (UnknownHostException ex) {
            System.err.println(this.getClass().getName()
                    + " = Não foi possivel estabelecer uma conexão, "
                    + "verifique sua internet!");
        } catch (IOException ex) {
            System.err.println(this.getClass().getName()
                    + " = Ocorreu algum problema em reornar o recurso "
                    + "solicitado!");
        }

        return true;
    }

    /**
     * Este método é responsável por recuperar as disiciplinas exibidas na
     * sessão Diario do q-academico ifpb, depois de feita a conexao e
     * requeisição do periodo informado no metodo {@link #request()}.
     *
     * @return ArrayList com as disciplinas solicitadas.
     */
    public ArrayList<Disciplina> requestDisciplinas() {

        ArrayList<Disciplina> disciplinas = new ArrayList<>();

        // recupera as linhas da tabela onde são organizadas as disciplinas
        final List<HtmlTableRow> rows = (List<HtmlTableRow>) currentPage
                .getByXPath("//table[@height='42' "
                        + "and @width='90%']/tbody/tr[not (@class='rotulo')]");

        Disciplina disciplina = new Disciplina();

        // este contador marca quando a informação é uma disciplina
        int discpCount = 0;

        for (int i = 0; i < rows.size(); i++) {

            // se a linha retonada é uma sessão de nota unica/atividades
            if (rows.get(i).getCell(0).getChildNodes().get(0).asText()
                    .equals(NOTA_UNICA)) {

                // formata o conteudo recuperado
                disciplina.setAtividades(setAtividadesValues(
                        rows.get(i).asText()));
                // se a linha retonada é uma sessão de prova final
            } else if (rows.get(i).getCell(0).getChildNodes().get(0).asText()
                    .equals(PROVA_FINAL)) {

                //formata os conteúdos recuperados
                disciplina.setProvaFinal(setProvaFinalValue(rows.get(i)
                        .asText()));

                // se a linha retonada é uma sessão disciplina
            } else if (!rows.get(i).getCell(0).getChildNodes().get(0)
                    .asText().equals(NOTA_UNICA)
                    && !rows.get(i).getCell(0).getChildNodes().get(0)
                    .asText().equals(PROVA_FINAL)) {

                discpCount++;

                /* caso da uma linha retornar uma disciplina 
                 é verificado se a linha anterior foi uma disciplina... */
                if (discpCount == 2) {
                    /* ... se foi uma disciplina a disciplina anterior é
                     adicionada a lista */
                    disciplinas.add(disciplina);
                    discpCount = 1;
                }

                /* caso não exista linha anterior ou ela não for uma disciplina 
                 é criada uma nova disciplina e adicionado o conteudo recuperado */
                disciplina = setDisciplinaContent(
                        getTextDisciplinaResourceLines(rows.get(i)));
            }

            // no final do loop é adicionada a ultima disciplna recuperada
            if (i == rows.size() - 1) {
                disciplinas.add(disciplina);
            }
        }

        return disciplinas;
    }

    public String getAnoPeriodo() {
        return anoPeriodo;
    }

    public void setAnoPeriodo(String anoPeriodo) {
        this.anoPeriodo = anoPeriodo;
    }

    public String getSemestrePeriodo() {
        return semestrePeriodo;
    }

    public void setSemestrePeriodo(String semestrePeriodo) {
        this.semestrePeriodo = semestrePeriodo;
    }

    /* metodo que retorna as linhas das disciplinas recuperadas em forma de 
     table-row */
    private String[] getTextDisciplinaResourceLines(HtmlTableRow row) {
        return row.asText().split("\\r?\\n");
    }

    /* este método seta as disciplinas recuperadas */
    private Disciplina setDisciplinaContent(String[] lines) {
        Disciplina disciplina = new Disciplina();

        // percorre as linhas adicionando os conteudos
        for (int i = 0; i < lines.length; i++) {
            setDescValues(disciplina, lines[i], i);
            if (i == 7) {
                return disciplina;
            }
        }
        return disciplina;
    }

    /* metodo que formata e seta as informações de uma disciplina a partir do 
     texto de uma linha*/
    private void setDescValues(Disciplina disciplina, String line, int flag) {

        String[] result;

        switch (flag) {
            /* caso a linha seja a informação do código + nome da disciplina 
             + nome do professor */
            case 0:
                /* as linhas abaixo dvide a string em três informações ( código,
                 nome da disciplina e nome do professor) */
                result = line.split(" - ");
                result[2] = result[2].split("\\(")[0];
                disciplina.setCodigo(result[0] + " - " + result[1]);
                disciplina.setNome(result[2]);
                String[] profRes = result[3].split("\\s+");
                String profNome = "";
                for (int i = 0; i < profRes.length; i++) {
                    if (i != 0) {
                        profNome += " ";
                    }
                    profNome += profRes[i];
                }
                disciplina.setProfessor(profNome);
                break;
            // caso a linha seja a carga horaria prevista
            case 1:
                result = line.split("Carga horária prevista	");
                disciplina.setCargaHoraria(result[1]);
                break;
            // caso a linha seja o número de aulas previsto
            case 2:
                result = line.split("Número de aulas previsto	");
                disciplina.setNumeroAulasPrevisto(result[1]);
                break;
            // caso a linha seja as aulas ministradas
            case 3:
                result = line.split("Aulas ministradas");
                disciplina.setNumeroAulasMinistradas(result[1].split("\\s+")[1]);
                break;
            // caso a linha seja as faltas
            case 4:
                result = line.split("Faltas	");
                disciplina.setFaltas(result[1]);
                break;
            // caso a linha seja o percentual de presença obrigatório
            case 5:
                result = line.split("Percentual de presença obrigatório	 ");
                disciplina.setPercentFrequenciaObrig(result[1]);
                break;
            // caso a linha seja o percentual a presença
            case 6:
                result = line.split("Presença	");
                disciplina.setPercentPresenca(result[1]);
                break;
            default:
        }
    }

    /* este método serve para separar e formatar as atvidades/nota única das 
    discilplins */
    private String[] setAtividadesValues(String line) {
        String[] result = line.split("Nota Única");
        String[] atvd = result[1].split("\\r?\\n");
        String[] atividades = new String[atvd.length - 1];

        for (int i = 1; i < atvd.length; i++) {
            atividades[i - 1] = atvd[i].replaceAll("\t", " ").trim();
        }

        return atividades;
    }

    /* este método serve para formatar a parte de prova final */
    private String setProvaFinalValue(String line) {
        String subStr = line.substring(11);
        String[] result = subStr.split("\\r?\\n");
        return result[1].replaceAll("\t", " ").trim();
    }
}
