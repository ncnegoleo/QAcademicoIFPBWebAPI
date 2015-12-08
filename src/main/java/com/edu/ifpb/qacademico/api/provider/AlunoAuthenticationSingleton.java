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

import com.edu.ifpb.qacademico.api.validation.DifferentPageException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * <p>Autenticação do Aluno
 *
 * <p>
 * Esta classe é responsável pela conexão com o sistema web do q-academico do
 * ifpb. O intuito é retornar um objeto no qual seja possivel manipular a pagina
 * e requisições por meio de ações controladas pela API <b>HtmlUnit</b>.
 * <pre>@see <a href="http://www.htmlunit.sourceforge.net">htmlunit.sourceforge
 * .net</a></pre>
 *
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
public class AlunoAuthenticationSingleton {

    private static AlunoAuthenticationSingleton instance = null;

    private final String login;
    private final String password;

    private final WebClient webClient = new WebClient();
    private HtmlPage currentPage;

    private static final String LOGIN_PAGE_URL = "https://academico.ifpb.edu.br/qacademico/index.asp?t=1001";
    private static final String LOGIN_PAGE_TITLE = "Q-Acadêmico Web para IF-PBBem Vindo!";
    private static final String AUTH_FORM_NAME = "frmLogin";
    private static final String SUBMIT_ELEM_NAME = "Submit";
    private static final String USERNAME_ELEM_NAME = "LOGIN";
    private static final String PASSWORD_ELEM_NAME = "SENHA";

    // construtor privado para recuperar a conexão
    private AlunoAuthenticationSingleton(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * este método serve para estabelecer um login no sistema para que seja
     * possivel recuperar informações dentro da sessão do aluno.
     */
    private boolean login() {
        try {
            // recupera a pagina de login
            currentPage = this.webClient.getPage(LOGIN_PAGE_URL);

            // verifica se o titulo da página corresponde ao da pagina recuperada
            if (!currentPage.getTitleText().endsWith(LOGIN_PAGE_TITLE)) {
                throw new DifferentPageException("");
            } else {

                /* recupera os elementos da pagina (estes elementos tiveram suas 
                 caracteristicas previamente analisadas, identificando informações
                 utilizaveis para evitar erros) */
                final HtmlForm form = currentPage.getFormByName(AUTH_FORM_NAME);
                final HtmlSubmitInput button = form.getInputByName(SUBMIT_ELEM_NAME);
                final HtmlTextInput username = form.getInputByName(USERNAME_ELEM_NAME);
                final HtmlPasswordInput pass = form.getInputByName(PASSWORD_ELEM_NAME);

                /* a partir dos elementos Input recuperados são setados os valores 
                 para efetuar o login no sistema */
                username.setValueAttribute(this.login);
                pass.setValueAttribute(this.password);

                /* Este elemento de fato executa o login, utilizando o metodo 
                 click() no botão que submete o formulario */
                this.currentPage = button.click();
            }
        } catch (DifferentPageException ex) {
            System.err.println(this.getClass().getName() + " = A página solicitada não existe "
                    + "ou você não tem autorização para acessá-la!");
        } catch (UnknownHostException ex) {
            System.err.println(this.getClass().getName() + " = Não foi possivel estabelecer uma conexão, "
                    + "verifique sua internet!");
        } catch (IOException ex) {
            System.err.println(this.getClass().getName() + " = Ocorreu algum problema em reornar o recurso "
                    + "solicitado!");
        }

        return true;
    }

    /**
     * Este método recupera uma instancia e também efetua o login no sistema
     * q-academico IFPB (caso o objeto não estaja instanciado) passando os
     * parametros de login/matricula e senha.
     *
     * @param matricula matricula do aluno.
     * @param senha senha do aluno.
     * @return uma nova ou já criada instacia da conexão.
     */
    public static AlunoAuthenticationSingleton getInstance(String matricula, String senha) {
        if (instance == null || isSessionEnded()) {
             // passar para java loggin
            System.out.println("Efetuando Login!!");
            instance = new AlunoAuthenticationSingleton(matricula, senha);
            instance.login();
        }
        return instance;
    }

    /**
     * Recupera uma nova instancia de WebClient para que seja possível setar a
     * página que será utilizada durante as ações automaticas do programa.
     *
     * @return uma nova instancia de {@link WebClient}
     */
    public WebClient getWebClient() {
        return webClient;
    }

    /**
     * Verifica se o tempo da sessão acabou.
     *
     * @return Verdadeiro se o tempo estiver acabado
     */
    public static boolean isSessionEnded() {
        HtmlPage pg = (HtmlPage) instance.getWebClient().getCurrentWindow().getEnclosedPage();
        DomText acssNegado = pg.getFirstByXPath("//font[@color='#FF0000' and @size='2']/text()");
        if(acssNegado != null && acssNegado.asText() != null)
            return acssNegado.asText().equals("Acesso Negado");
        return false;
    }
}
