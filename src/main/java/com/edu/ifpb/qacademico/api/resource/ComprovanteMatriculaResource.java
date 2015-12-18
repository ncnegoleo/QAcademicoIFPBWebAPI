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

import com.edu.ifpb.qacademico.api.provider.ProvideResources;
import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * <p>
 * Recursos de Comprovante de Matricula
 *
 * <p>
 * Esta classe fornece para download os recurso do comprovante de matricula do
 * q-academico ifpb na API usando REST, tornando possivel recuperar as
 * informações no formato JSON.
 *
 * @author Leonardo Soares
 * @version 1.0, 06/12/2015
 * @since 1.0
 */
@Path("/download")
public class ComprovanteMatriculaResource {

    @GET
    @Path("/compr-matricula")
    @Produces("image/jpg")
    public Response getImagem(
            @MatrixParam("matricula") String matricula,
            @MatrixParam("senha") String senha) {

        ProvideResources pds = new ProvideResources();
        String path = pds.getComprovateMatricula(matricula, senha);
        File file = new File(path);
        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content - Disposition",
                "attachment;filename=\"comprovante.jpg\"");
        return response.build();
    }
}
