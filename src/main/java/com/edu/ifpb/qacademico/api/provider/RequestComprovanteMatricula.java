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
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * <p>
 * Provedor de Comprovate de Matricula
 *
 * <p>
 * Esta classe é responsavel por recuperar o comprovante de matricula, acessando
 * as páginas via <b>HtmlUnit</b>, fazendo login e recuperando as informações
 * para a API.</p>
 * <pre>@see <a href="http://www.htmlunit.sourceforge.net">htmlunit.sourceforge
 * .net</a></pre>
 *
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
public class RequestComprovanteMatricula {

    private HtmlPage currentPage;
    private HtmlImage img;
    private final AlunoAuthenticationSingleton alunoAuthenticationSingleton;

    private final String PAGE_URL
            = "https://academico.ifpb.edu.br/qacademico/index.asp?t=2082&ACAO=IMPRIMIR";

    public RequestComprovanteMatricula(
            AlunoAuthenticationSingleton alunoAuthenticationSingleton) {
        this.alunoAuthenticationSingleton = alunoAuthenticationSingleton;
    }

    /**
     * 
     * @return
     * @throws DifferentPageException 
     */
    public boolean request() throws DifferentPageException {
        try {
            // Recupera a página a partir de uma url e seta no autenticador
            currentPage = this.alunoAuthenticationSingleton.getWebClient()
                    .getPage(PAGE_URL);

            /* Compara os titulos das páginas, se o do página recuperada não 
             for igual a págia solicitada é lançada uma exceção*/
            if (!currentPage.getTitleText().endsWith(PAGE_URL)) {
                throw new DifferentPageException("");
            }
            
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
     * 
     * @return 
     */
    public String requestComprovante() {
        img = currentPage.getFirstByXPath("//img");

        SecureRandom sr = new SecureRandom();

        String randomImgSufix = new BigInteger(130, sr).toString(32);
        String path = System.getProperty("java.io.tmpdir") + randomImgSufix + ".jpg";
        
        try {
            img.saveAs(new File(path));
        } catch (IOException ex) {}
        
        return path;
    }
}
