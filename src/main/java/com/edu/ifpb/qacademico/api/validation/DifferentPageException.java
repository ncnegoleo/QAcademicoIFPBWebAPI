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
package com.edu.ifpb.qacademico.api.validation;

/**
 * Esta classe serve para controlar as excessões de uma página recuperada numa 
 * condição em que esta não seja realmete a página desejada.
 * 
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
public class DifferentPageException extends Exception {
    
    public DifferentPageException(String mensagem) {
        super(mensagem);
    }

    public DifferentPageException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
